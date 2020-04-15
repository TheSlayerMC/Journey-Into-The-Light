package net.journey.blocks.base;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

public class BlockModFire extends BlockMod {

	public BlockModFire(String name, String finalN) {
		super(name, finalN);
		setLightLevel(1.0F);
		setTranslationKey(name);
		setCreativeTab(null);
	}

	public static final AxisAlignedBB BLOCK_AABB = new AxisAlignedBB(1F, 0.0F, 1F, 0F, 0.1F, 0F);

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BLOCK_AABB;
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public boolean requiresUpdates() {
		return false;
	}

	@Override
	public boolean isCollidable() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState s) {
		if (!JourneyBlocks.eucaPortal.makePortal(world, pos)) {
			if (!world.getBlockState(pos.down()).isTopSolid())
				world.setBlockToAir(pos);
			else
				world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if (!JourneyBlocks.boilPortal.makePortal(world, pos)) {
			if (!world.getBlockState(pos.down()).isTopSolid())
				world.setBlockToAir(pos);
			else
				world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if (!JourneyBlocks.frozenPortal.makePortal(world, pos)) {
			if (!world.getBlockState(pos.down()).isTopSolid())
				world.setBlockToAir(pos);
			else
				world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if (!JourneyBlocks.cloudiaPortal.makePortal(world, pos)) {
			if (!world.getBlockState(pos.down()).isTopSolid())
				world.setBlockToAir(pos);
			else
				world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if (!JourneyBlocks.terraniaPortal.makePortal(world, pos)) {
			if (!world.getBlockState(pos.down()).isTopSolid())
				world.setBlockToAir(pos);
			else
				world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(world.isAirBlock(pos.down())) {
			world.setBlockToAir(pos);
		}
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}