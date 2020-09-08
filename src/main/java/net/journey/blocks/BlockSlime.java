package net.journey.blocks;

import net.journey.entity.mob.corba.EntityCorbanianMollusk;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;

public class BlockSlime extends BlockMod {

	protected static final AxisAlignedBB SLIME_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);

	public BlockSlime(EnumMaterialTypes type, String name, String enName, float hardness) {
		super(type, name, enName, hardness);
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.down()).isNormalCube();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return SLIME_AABB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return SLIME_AABB;
	}

	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!(entityIn instanceof EntityCorbanianMollusk)) {
			entityIn.motionX *= 0.5D;
			entityIn.motionZ *= 0.5D;
		}
	}
}
