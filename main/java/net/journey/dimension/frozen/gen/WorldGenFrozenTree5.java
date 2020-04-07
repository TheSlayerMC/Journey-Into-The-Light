package net.journey.dimension.frozen.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFrozenTree5 extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random r, BlockPos bp) {
		int i = bp.getX() - 1, j = bp.getY(), k = bp.getZ() - 1;
		IBlockState leaves = JourneyBlocks.frozenLeaves.getDefaultState();
		IBlockState log = JourneyBlocks.frozenBark.getDefaultState();
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i, j + 3, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i, j + 4, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i, j + 5, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i, j + 6, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i, j + 7, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j, k + 1), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 1, k + 1), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 2, k + 1), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 3, k), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 3, k + 1), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 3, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 4, k), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 4, k + 1), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 4, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 5, k), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 5, k + 1), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 5, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 6, k), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 6, k + 1), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 6, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 7, k), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 7, k + 1), log);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 7, k + 2), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 8, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 3, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 4, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 5, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 6, k + 1), leaves);
		this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 7, k + 1), leaves);
		return true;
	}
}