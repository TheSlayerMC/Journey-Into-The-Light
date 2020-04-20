package net.journey.blocks.crop.base;

import net.journey.init.common.JourneyCrops;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;

import java.util.List;
import java.util.Random;

public class BlockFruitCrop extends BlockMod implements IGrowable {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 2);
    protected static final AxisAlignedBB[] FRUIT_EAST_AABB = new AxisAlignedBB[]{
            new AxisAlignedBB(0.6875D, 0.4375D, 0.375D, 0.9375D, 0.75D, 0.625D),
            new AxisAlignedBB(0.5625D, 0.3125D, 0.3125D, 0.9375D, 0.75D, 0.6875D),
            new AxisAlignedBB(0.4375D, 0.1875D, 0.25D, 0.9375D, 0.75D, 0.75D)};
    protected static final AxisAlignedBB[] FRUIT_WEST_AABB = new AxisAlignedBB[]{
            new AxisAlignedBB(0.0625D, 0.4375D, 0.375D, 0.3125D, 0.75D, 0.625D),
            new AxisAlignedBB(0.0625D, 0.3125D, 0.3125D, 0.4375D, 0.75D, 0.6875D),
            new AxisAlignedBB(0.0625D, 0.1875D, 0.25D, 0.5625D, 0.75D, 0.75D)};
    protected static final AxisAlignedBB[] FRUIT_NORTH_AABB = new AxisAlignedBB[]{
            new AxisAlignedBB(0.375D, 0.4375D, 0.0625D, 0.625D, 0.75D, 0.3125D),
            new AxisAlignedBB(0.3125D, 0.3125D, 0.0625D, 0.6875D, 0.75D, 0.4375D),
            new AxisAlignedBB(0.25D, 0.1875D, 0.0625D, 0.75D, 0.75D, 0.5625D)};
    protected static final AxisAlignedBB[] FRUIT_SOUTH_AABB = new AxisAlignedBB[]{
            new AxisAlignedBB(0.375D, 0.4375D, 0.6875D, 0.625D, 0.75D, 0.9375D),
            new AxisAlignedBB(0.3125D, 0.3125D, 0.5625D, 0.6875D, 0.75D, 0.9375D),
            new AxisAlignedBB(0.25D, 0.1875D, 0.4375D, 0.75D, 0.75D, 0.9375D)};
    public Item fruitItem;
    public Block sustainableBlock;

    public BlockFruitCrop(String name, String finalName) {
        super(EnumMaterialTypes.SLIME, name, finalName, 0.5F);
        this.setCreativeTab(null);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(AGE, Integer.valueOf(0)));
        this.setTickRandomly(true);
    }

    public BlockFruitCrop(String name, String finalName, Item fruitItem, Block sustainableBlock) {
        super(EnumMaterialTypes.SLIME, name, finalName, 0.5F);
        this.fruitItem = fruitItem;
        this.sustainableBlock = sustainableBlock;
        this.setCreativeTab(null);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(AGE, Integer.valueOf(0)));
        this.setTickRandomly(true);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!this.canBlockStay(worldIn, pos, state)) {
            this.dropBlock(worldIn, pos, state);
        } else {
            int i = state.getValue(AGE).intValue();

            if (i < 2 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(5) == 0)) {
                worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i + 1)), 2);
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
            }
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        pos = pos.offset(state.getValue(FACING));
        IBlockState iblockstate = worldIn.getBlockState(pos);
        return iblockstate.getBlock() == this.sustainableBlock;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        int i = state.getValue(AGE).intValue();

        switch (state.getValue(FACING)) {
            case SOUTH:
                return FRUIT_SOUTH_AABB[i];
            case NORTH:
            default:
                return FRUIT_NORTH_AABB[i];
            case WEST:
                return FRUIT_WEST_AABB[i];
            case EAST:
                return FRUIT_EAST_AABB[i];
        }
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        EnumFacing enumfacing = EnumFacing.fromAngle(placer.rotationYaw);
        worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        if (!facing.getAxis().isHorizontal()) {
            facing = EnumFacing.NORTH;
        }
        return this.getDefaultState().withProperty(FACING, facing.getOpposite()).withProperty(AGE, Integer.valueOf(0));
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!this.canBlockStay(worldIn, pos, state)) {
            this.dropBlock(worldIn, pos, state);
        }
    }

    @Override
    public Item getItemDropped(IBlockState par1, Random par2, int par3) {
        return SlayerAPI.toItem(Blocks.AIR);
    }

    private void dropBlock(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        this.dropBlockAsItem(worldIn, pos, state, 0);
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> dropped = super.getDrops(world, pos, state, fortune);
        int i = state.getValue(AGE).intValue();
        int j = 1;

        if (i >= 2) {
            j = 3;
        }
        for (int k = 0; k < j; ++k) {
            dropped.add(new ItemStack(JourneyCrops.bleedheart));
        }
        return dropped;
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return state.getValue(AGE).intValue() < 2;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(state.getValue(AGE).intValue() + 1)), 2);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta)).withProperty(AGE, Integer.valueOf((meta & 15) >> 2));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(FACING).getHorizontalIndex();
        i = i | state.getValue(AGE).intValue() << 2;
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, AGE);
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }
}