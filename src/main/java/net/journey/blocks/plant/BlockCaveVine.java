package net.journey.blocks.plant;

import net.journey.api.block.GroundPredicate;
import net.journey.api.block.base.JBlockPlant;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCaveVine extends JBlockPlant implements IGrowable {
    protected static final AxisAlignedBB SEMI_BB = new AxisAlignedBB(0.30000001192092896D, 0.5D, 0.30000001192092896D, 0.699999988079071D, 1.0D, 0.699999988079071D);
    protected static final AxisAlignedBB FULL_BB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 1.0D, 0.699999988079071D);
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 3);

    public BlockCaveVine(String name, String enName) {
        super(name, enName);
        setLightLevel(0.3F);
        setPlantDirection(EnumFacing.DOWN);
        setGroundPredicate(GroundPredicate.SOLID_SIDE
                .or((world, groundPos, groundState, plantDirection) -> (groundState.getBlock() == this && groundState.getValue(AGE) == 3)));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return state.getValue(AGE) < 2 ? SEMI_BB : FULL_BB;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(AGE, Math.min(meta, 3));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(AGE);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AGE);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(world, pos, state, rand);

        if (!world.isRemote) {
            if (ForgeHooks.onCropsGrowPre(world, pos, state, rand.nextInt(5) == 0)) {
                grow(world, rand, pos, state);
                ForgeHooks.onCropsGrowPost(world, pos, state, world.getBlockState(pos));
            }
        }
    }

    @Override
    @Deprecated
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
        this.checkAndDropBlock(world, pos, state);
    }

    @Override
    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!this.canBlockStay(worldIn, pos, state)) {
            worldIn.destroyBlock(pos, true);
        }
    }

    @Override
    public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos) {
        return 14500000;
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        if (state.getValue(AGE) != 3) {
            return true;
        }

        BlockPos bottomPos = pos.down();

        IBlockState bottomState = worldIn.getBlockState(bottomPos);
        return bottomState.getBlock() != this && bottomState.getBlock().isReplaceable(worldIn, bottomPos);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return this.getDefaultState();
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return false;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote && rand.nextInt(2) == 0) {
            growInstant(worldIn, pos, state);
        }
    }

    private void growInstant(World worldIn, BlockPos pos, IBlockState state) {
        int age = state.getValue(AGE);

        if (age < 3) {
            worldIn.setBlockState(pos, this.getDefaultState().withProperty(AGE, age + 1));
        } else if (canGrow(worldIn, pos, state, false)) {
            worldIn.setBlockState(pos.down(), this.getDefaultState().withProperty(AGE, 0));
        }
    }
}