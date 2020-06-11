package net.journey.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public abstract class JDoubleBlock extends BlockMod {
    public static final PropertyEnum<EnumHalf> HALF = PropertyEnum.create("half", EnumHalf.class);

    public JDoubleBlock(EnumMaterialTypes blockType, String name, String enName, float hardness, CreativeTabs tab) {
        super(blockType, name, enName, hardness, tab);
        setTickRandomly(true);
    }

    public static EnumHalf getHalf(IBlockState state) {
        if (!(state.getBlock() instanceof JDoubleBlock))
            throw new RuntimeException("Something tried to get half property of state that is not " + JDoubleBlock.class.getName());
        return state.getValue(HALF);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        worldIn.setBlockState(pos.up(), state.withProperty(HALF, EnumHalf.TOP));// we set block also on client to prevent top block placing being slow for eyes
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);

        updateState(worldIn, pos, state);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);

        updateState(worldIn, pos, state);
    }

    protected boolean canStay(World world, BlockPos pos, IBlockState state) {
        if (state.getValue(HALF) == EnumHalf.TOP) {
            IBlockState bottomState = world.getBlockState(pos.down());
            return bottomState.getBlock() == this && bottomState.getValue(HALF) == EnumHalf.BOTTOM;
        } else {
            IBlockState topState = world.getBlockState(pos.up());
            return topState.getBlock() == this && topState.getValue(HALF) == EnumHalf.TOP;
        }
    }

    /**
     * Places both bottom and top blocks.
     *
     * @param world     world where to set
     * @param bottomPos position where bottom block will be placed.
     * @param flags     the same as in {@link World#setBlockState(BlockPos, IBlockState, int)}
     */
    public void placeAt(World world, BlockPos bottomPos, int flags) {
        world.setBlockState(bottomPos, this.getDefaultState().withProperty(HALF, EnumHalf.BOTTOM), flags);
        world.setBlockState(bottomPos.up(), this.getDefaultState().withProperty(HALF, EnumHalf.TOP), flags);
    }

    protected void updateState(World world, BlockPos pos, IBlockState state) {
        if (!canStay(world, pos, state)) {
            boolean isTop = state.getValue(HALF) == EnumHalf.TOP;

            BlockPos top = isTop ? pos : pos.up();
            BlockPos bottom = isTop ? pos.down() : pos;

            Block topBlock = isTop ? this : world.getBlockState(bottom).getBlock();
            Block bottomBlock = isTop ? world.getBlockState(bottom).getBlock() : this;

            if (!isTop) dropBlockAsItem(world, pos, state, 0);

            if (topBlock == this) world.setBlockState(top, Blocks.AIR.getDefaultState(), 2);

            if (bottomBlock == this) world.setBlockState(bottom, Blocks.AIR.getDefaultState(), 3);
        }
    }

    @Override
    public void onBlockHarvested(World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull EntityPlayer player) {
        if (!worldIn.isRemote) {
            if (state.getValue(HALF) == EnumHalf.TOP) {
                if (worldIn.getBlockState(pos.down()).getBlock() == this) {
                    if (player.capabilities.isCreativeMode) {
                        worldIn.setBlockToAir(pos.down());
                    } else {
                        worldIn.destroyBlock(pos.down(), true);
                    }
                }
            } else if (worldIn.getBlockState(pos.up()).getBlock() == this) {
                worldIn.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 2);
            }
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public @NotNull IBlockState getStateFromMeta(int meta) {
        return meta == 0 ? getDefaultState().withProperty(HALF, EnumHalf.BOTTOM) : getDefaultState().withProperty(HALF, EnumHalf.TOP);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(HALF) == EnumHalf.BOTTOM ? 0 : 1;
    }

    protected abstract @NotNull BlockStateContainer createBlockState();
}
