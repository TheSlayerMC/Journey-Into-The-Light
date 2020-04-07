package net.journey.dimension.frozen.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFrozenTree extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random r, BlockPos bp) {
		int i = bp.getX() - 3, j = bp.getY(), k = bp.getZ() - 2;

		IBlockState leaves = JourneyBlocks.frozenLeaves.getDefaultState();
		IBlockState log = JourneyBlocks.frozenBark.getDefaultState();
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i, j + 3, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i, j + 3, k + 3), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 3, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 3, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 3, k + 3), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 3, k + 4), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 4, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 4, k + 3), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 5, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 5, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 5, k + 3), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 5, k + 4), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 7, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 7, k + 3), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j, k + 2), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j, k + 3), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 1, k + 2), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 1, k + 3), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 2, k + 2), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 2, k + 3), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 3, k), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 3, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 3, k + 2), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 3, k + 3), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 3, k + 4), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 3, k + 5), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 4, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 4, k + 2), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 4, k + 3), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 4, k + 4), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 5, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 5, k + 2), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 5, k + 3), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 5, k + 4), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 6, k + 2), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 6, k + 3), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 7, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 7, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 7, k + 3), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 7, k + 4), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 8, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 8, k + 3), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j, k + 2), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j, k + 3), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 1, k + 2), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 1, k + 3), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 2, k + 2), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 2, k + 3), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 3, k), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 3, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 3, k + 2), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 3, k + 3), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 3, k + 4), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 3, k + 5), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 4, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 4, k + 2), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 4, k + 3), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 4, k + 4), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 5, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 5, k + 2), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 5, k + 3), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 5, k + 4), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 6, k + 2), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 6, k + 3), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 7, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 7, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 7, k + 3), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 7, k + 4), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 8, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 8, k + 3), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 4, j + 3, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 4, j + 3, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 4, j + 3, k + 3), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 4, j + 3, k + 4), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 4, j + 4, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 4, j + 4, k + 3), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 4, j + 5, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 4, j + 5, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 4, j + 5, k + 3), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 4, j + 5, k + 4), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 4, j + 7, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 4, j + 7, k + 3), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 5, j + 3, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 5, j + 3, k + 3), leaves);
		return true;
	}
}
