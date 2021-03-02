package net.journey.dimension.base.gen;

import net.journey.api.block.GroundPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;

public abstract class JWorldGenHugeTrees extends WorldGenHugeTrees {
	/**
	 * Predicate that checks if tree can be placed on provided block.
	 */
	protected GroundPredicate groundPredicate = GroundPredicate.GRASS_BLOCK;

	public JWorldGenHugeTrees(boolean notify, int baseHeightIn, int extraRandomHeightIn, IBlockState woodMetadataIn, IBlockState leavesMetadataIn) {
		super(notify, baseHeightIn, extraRandomHeightIn, woodMetadataIn, leavesMetadataIn);
	}

	public JWorldGenHugeTrees setGroundPredicate(GroundPredicate groundPredicate) {
		this.groundPredicate = groundPredicate;
		return this;
	}

	/**
	 * returns whether or not there is dirt underneath the block where the tree will be grown.
	 * It also generates dirt around the block in a 2x2 square if there is dirt underneath the blockpos.
	 */
	@Override
	public boolean ensureDirtsUnderneath(BlockPos pos, World worldIn) {
		BlockPos blockpos = pos.down();
		IBlockState state = worldIn.getBlockState(blockpos);

		boolean isSoil = groundPredicate.testGround(worldIn, blockpos, state, EnumFacing.UP);

		if (isSoil && pos.getY() >= 2) {
			this.onPlantGrow(worldIn, blockpos, pos);
			this.onPlantGrow(worldIn, blockpos.east(), pos);
			this.onPlantGrow(worldIn, blockpos.south(), pos);
			this.onPlantGrow(worldIn, blockpos.south().east(), pos);
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected boolean canGrowInto(Block blockType) {
		return true;
	}

	//Just a helper macro
	private void onPlantGrow(World world, BlockPos pos, BlockPos source) {
		IBlockState state = world.getBlockState(pos);
		state.getBlock().onPlantGrow(state, world, pos, source);
	}
}
