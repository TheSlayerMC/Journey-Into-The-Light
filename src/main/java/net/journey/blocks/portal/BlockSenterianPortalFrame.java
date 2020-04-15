package net.journey.blocks.portal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicates;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;

public class BlockSenterianPortalFrame extends BlockMod {
	
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool EYE = PropertyBool.create("eye");
    protected static final AxisAlignedBB AABB_BLOCK = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
    protected static final AxisAlignedBB AABB_EYE = new AxisAlignedBB(0.3125D, 0.8125D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
    private static BlockPattern portalShape;

    public BlockSenterianPortalFrame(String name, String f) {
        super(name, f, false);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(EYE, Boolean.valueOf(false)));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB_BLOCK;
    }
    
    @Override
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		if ((state.getValue(EYE))) {
			drops.add(new ItemStack(JourneyItems.sentryEye, 1));
		}
		drops.add(new ItemStack(SlayerAPI.toItem(JourneyBlocks.senterianPortalFrame)));
		return drops;   
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BLOCK);

        if(((Boolean)worldIn.getBlockState(pos).getValue(EYE)).booleanValue()) {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_EYE);
        }
    }
    
    public static BlockPattern getOrCreatePortalShape() {
        if(portalShape == null) {
            portalShape = FactoryBlockPattern.start().aisle("?vvv?", ">???<", ">???<", ">???<", "?^^^?").where('?', BlockWorldState.hasState(BlockStateMatcher.ANY)).where('^', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.senterianPortalFrame).where(EYE, Predicates.equalTo(Boolean.valueOf(true))).where(FACING, Predicates.equalTo(EnumFacing.SOUTH)))).where('>', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.senterianPortalFrame).where(EYE, Predicates.equalTo(Boolean.valueOf(true))).where(FACING, Predicates.equalTo(EnumFacing.WEST)))).where('v', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.senterianPortalFrame).where(EYE, Predicates.equalTo(Boolean.valueOf(true))).where(FACING, Predicates.equalTo(EnumFacing.NORTH)))).where('<', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.senterianPortalFrame).where(EYE, Predicates.equalTo(Boolean.valueOf(true))).where(FACING, Predicates.equalTo(EnumFacing.EAST)))).build();
        }
        return portalShape;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return SlayerAPI.toItem(this);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(EYE, Boolean.valueOf(false));
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(EYE, Boolean.valueOf((meta & 4) != 0)).withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 3));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();

        if (((Boolean)state.getValue(EYE)).booleanValue()) {
            i |= 4;
        }

        return i;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING, EYE});
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return ((Boolean)blockState.getValue(EYE)).booleanValue() ? 15 : 0;
    }
    
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
    }
}