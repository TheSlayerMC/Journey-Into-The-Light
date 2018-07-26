package net.journey.blocks.base;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.journey.JourneyBlocks;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

public class BlockModFire extends BlockMod {

    protected static final AxisAlignedBB AABB_BLOCK = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0000000000000000001F, 0.0D);
    
	public BlockModFire(String name, String finalN) {
		super(EnumMaterialTypes.FIRE, name, finalN, 0.0F);
		setLightLevel(1.0F);
		setUnlocalizedName(name);
		setCreativeTab(null);
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
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		entityIn.setFire(6);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World w, BlockPos pos, Random rand) {
		for(int i = 0; i < 3; ++i) {
			double d0 = (double)pos.getX() + rand.nextDouble();
			double d1 = (double)pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
			double d2 = (double)pos.getZ() + rand.nextDouble();
			w.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState s) {
		if(!JourneyBlocks.eucaPortal.makePortal(world, pos)) {
			if(!world.getBlockState(pos.down()).isTopSolid()) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.depthsPortal.makePortal(world, pos)) {
			if(!world.getBlockState(pos.down()).isTopSolid()) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.boilPortal.makePortal(world, pos)) {
			if(!world.getBlockState(pos.down()).isTopSolid()) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.frozenPortal.makePortal(world, pos)) {
			if(!world.getBlockState(pos.down()).isTopSolid()) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.cloudiaPortal.makePortal(world, pos)) {
			if(!world.getBlockState(pos.down()).isTopSolid()) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.terraniaPortal.makePortal(world, pos)) {
			if(!world.getBlockState(pos.down()).isTopSolid()) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
		if(!JourneyBlocks.goldenPortal.makePortal(world, pos)) {
			if(!world.getBlockState(pos.down()).isTopSolid()) world.setBlockToAir(pos);
			else world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
		}
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }
}