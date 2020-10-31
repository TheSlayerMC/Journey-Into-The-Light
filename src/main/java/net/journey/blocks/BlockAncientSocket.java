package net.journey.blocks;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.block.BlockMod;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockAncientSocket extends BlockMod {

	Item insert;
	
    public static final PropertyBool INSERT = PropertyBool.create("insert");
    protected static final AxisAlignedBB AABB_BLOCK = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_INSERT = new AxisAlignedBB(0.3125D, 0.8125D, 0.3125D, 0.6875D, 1.0D, 0.6875D);

    public BlockAncientSocket(String name, String f) {
	    super(name, f, false);
	    this.setDefaultState(this.blockState.getBaseState().withProperty(INSERT, Boolean.FALSE));
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
        if ((state.getValue(INSERT))) {
            drops.add(new ItemStack(insert, 1));
        }
        drops.add(new ItemStack(SlayerAPI.toItem(JourneyBlocks.ANCIENT_SOCKET)));
        return drops;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BLOCK);

	    if (worldIn.getBlockState(pos).getValue(INSERT)) {
		    addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_INSERT);
	    }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return SlayerAPI.toItem(this);
    }

	@Override
	public @NotNull IBlockState getStateForPlacement(@NotNull World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(INSERT, Boolean.FALSE);
	}

	@Override
	public boolean hasComparatorInputOverride(@NotNull IBlockState state) {
		return true;
	}

	@Override
	public @NotNull IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(INSERT, (meta & 1) != 0);
	}

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
	    if (state.getValue(INSERT)) {
		    i = 1;
	    }
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, INSERT);
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
	    return blockState.getValue(INSERT) ? 15 : 0;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
    }
}