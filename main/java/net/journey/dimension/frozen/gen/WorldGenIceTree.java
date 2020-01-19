package net.journey.dimension.frozen.gen;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenIceTree extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int i = pos.getX(), j = pos.getY() + 1, k = pos.getZ();
		world.setBlockState(new BlockPos(i + 0, j + 4, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 4, k + 1), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 4, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 4, k + 3), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 5, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 7, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 2), JourneyBlocks.iceLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 2), JourneyBlocks.iceLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 2, k + 1), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 2, k + 2), JourneyBlocks.iceLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 2, k + 3), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 3, k + 2), JourneyBlocks.iceLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 0), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 1), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 2), JourneyBlocks.iceLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 3), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 4), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 5, k + 1), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 5, k + 2), JourneyBlocks.iceLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 5, k + 3), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 6, k + 2), JourneyBlocks.iceLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 7, k + 1), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 7, k + 2), JourneyBlocks.iceLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 7, k + 3), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 8, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 2, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 4, k + 1), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 4, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 4, k + 3), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 5, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 7, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 4, k + 2), JourneyBlocks.iceLeaves.getDefaultState());

		return true;
	}
}