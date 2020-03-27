package net.journey.dimension.boil.trees;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBoilTree2 extends WorldGenerator {

	@Override
	public boolean generate(World world, Random r, BlockPos pos) {
		int i = pos.getX() - 3, j = pos.getY() - 1, k = pos.getZ() - 3;
		Block log = JourneyBlocks.boilingLog;
		Block leaves = JourneyBlocks.burningLeaves;
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 5, k + 2), leaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 6, k + 2), leaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 2), leaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 2), log.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 1, k + 2), log.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 2, k + 2), log.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 2), log.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 0), leaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 1), leaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 2), log.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 3), leaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 4), leaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 0), leaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 2), leaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 4), leaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 6, k + 0), leaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 6, k + 4), leaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 2), leaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 4, k + 2), leaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 5, k + 2), leaves.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 6, k + 2), leaves.getDefaultState());


		return true;
	}
}