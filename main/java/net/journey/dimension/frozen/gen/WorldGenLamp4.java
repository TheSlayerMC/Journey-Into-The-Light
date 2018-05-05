package net.journey.dimension.frozen.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLamp4 extends WorldGenerator {

	@Override
	public boolean generate(World world, Random r, BlockPos pos) {
		int i = pos.getX(), j = pos.getY() + 1, k = pos.getZ();
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 0), JourneyBlocks.workshopStone.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 1), JourneyBlocks.workshopStone.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 2), JourneyBlocks.workshopStone.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 0), JourneyBlocks.workshopStone.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 1), JourneyBlocks.workshopStone.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 2), JourneyBlocks.workshopStone.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 1, k + 1), JourneyBlocks.workshopStone.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 1), JourneyBlocks.workshopStone.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 1, j + 3, k + 1), JourneyBlocks.frozenLamp.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 0), JourneyBlocks.workshopStone.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 1), JourneyBlocks.workshopStone.getDefaultState(), 2);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 2), JourneyBlocks.workshopStone.getDefaultState(), 2);
		return true;
	}
}