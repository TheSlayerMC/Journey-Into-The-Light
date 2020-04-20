package net.journey.blocks.machines;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

public class BlockLock extends BlockMod {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public Item key;

    public BlockLock(String name, String f, Item key) {
        super(EnumMaterialTypes.METAL_SOUND, name, f, 0.5F);
        this.key = key;
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setTickRandomly(true);
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockAdded(worldIn, pos, state);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        float
                x = hitX,
                y = hitY,
                z = hitZ;
        float
                r = 0.0F,
                g = 0.0F,
                b = 0.0F;
        if (playerIn.getHeldItem(EnumHand.MAIN_HAND) != null && playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem() == key) {
            worldIn.playSound(null, x, (double) y + 0.5D, z, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.735F);
            worldIn.spawnParticle(EnumParticleTypes.SPELL, x, y, z, 2.0F, 2.0F, 2.0F, 2);
            if (!worldIn.isRemote) {
                worldIn.setBlockState(pos.add(0, 0, 0), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(pos.add(0, 1, 0), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(pos.add(0, 0, 1), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(pos.add(-1, 0, 0), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(pos.add(0, -1, 0), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(pos.add(0, 0, -1), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(pos.add(1, 0, 0), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(pos.add(1, 1, 0), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(pos.add(-1, -1, 0), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(pos.add(-1, 1, 0), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(pos.add(1, -1, 0), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(pos.add(0, 0, 1), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(pos.add(0, 1, 1), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(pos.add(0, -1, -1), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(pos.add(0, 1, -1), Blocks.AIR.getDefaultState());
                worldIn.setBlockState(pos.add(0, -1, 1), Blocks.AIR.getDefaultState());
                playerIn.getHeldItem(EnumHand.MAIN_HAND).shrink(1);
            }
        }

        return false;
    }
}
