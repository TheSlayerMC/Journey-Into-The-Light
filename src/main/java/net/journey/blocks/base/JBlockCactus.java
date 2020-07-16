package net.journey.blocks.base;

import net.journey.api.block.GroundPredicate;
import net.journey.init.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class JBlockCactus extends BlockMod {

	private GroundPredicate groundPredicate = GroundPredicate.GRASS_BLOCK;

	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
	protected static final AxisAlignedBB CACTUS_COLLISION_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
	protected static final AxisAlignedBB CACTUS_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);

	public JBlockCactus(String name, String enName) {
		super(EnumMaterialTypes.WOOL, name, enName, JourneyTabs.DECORATION);
		setHardness(1.0F);
	}

	public JBlockCactus setGroundPredicate(@Nullable GroundPredicate groundPredicate) {
		this.groundPredicate = groundPredicate;
		return this;
	}

	public GroundPredicate getGroundPredicate() {
		return groundPredicate;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (worldIn.isAreaLoaded(pos, 1)) {
			BlockPos blockpos = pos.up();
			if (worldIn.isAirBlock(blockpos)) {
				int i;
				for (i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i) {
				}

				if (i < 3) {
					int j = state.getValue(AGE);
					if (ForgeHooks.onCropsGrowPre(worldIn, blockpos, state, true)) {
						if (j == 15) {
							worldIn.setBlockState(blockpos, this.getDefaultState());
							IBlockState iblockstate = state.withProperty(AGE, 0);
							worldIn.setBlockState(pos, iblockstate, 4);
							iblockstate.neighborChanged(worldIn, blockpos, this, pos);
						} else {
							worldIn.setBlockState(pos, state.withProperty(AGE, j + 1), 4);
						}

						ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
					}
				}
			}

		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return CACTUS_COLLISION_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return CACTUS_AABB.offset(pos);
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
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && canBlockStay(worldIn, pos);
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!this.canBlockStay(worldIn, pos)) {
			worldIn.destroyBlock(pos, true);
		}
	}

	public boolean canBlockStay(World worldIn, BlockPos pos) {
		BlockPos groundPos = pos.offset(EnumFacing.UP.getOpposite());
		IBlockState groundState = worldIn.getBlockState(groundPos);
//		System.out.println("Can't stay : " + groundPredicate.testGround(worldIn, groundPos, groundState, plantDirection));
		return groundPredicate.testGround(worldIn, groundPos, groundState, EnumFacing.UP);
	}

	@Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0F);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(AGE, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
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
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}
