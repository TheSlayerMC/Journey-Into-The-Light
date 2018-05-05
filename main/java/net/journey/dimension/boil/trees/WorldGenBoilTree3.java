package net.journey.dimension.boil.trees;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenBoilTree3 extends WorldGenerator {

	@Override
	public boolean generate(World world, Random r, BlockPos pos) {
		int i = pos.getX() - 3, j = pos.getY() - 1, k = pos.getZ() - 3;
		Block log = JourneyBlocks.boilingLog;
		Block leaves = JourneyBlocks.burningLeaves;
		world.setBlockState(new BlockPos(i + 0, j + 5, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 6, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 7, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 8, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 5, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 8, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 5, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 3), log.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 3), log.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 2, k + 3), log.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 3, k + 3), log.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 4, k + 3), log.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 5, k + 0), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 5, k + 1), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 5, k + 2), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 5, k + 3), log.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 5, k + 4), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 5, k + 5), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 5, k + 6), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 6, k + 0), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 6, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 6, k + 6), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 7, k + 0), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 7, k + 6), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 8, k + 0), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 8, k + 1), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 8, k + 5), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 8, k + 6), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 5, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 5, j + 5, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 5, j + 8, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 6, j + 5, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 6, j + 6, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 6, j + 7, k + 3), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 6, j + 8, k + 3), leaves.getDefaultState());


		return true;
	}
}