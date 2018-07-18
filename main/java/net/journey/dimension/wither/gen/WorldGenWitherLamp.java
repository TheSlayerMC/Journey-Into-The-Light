package net.journey.dimension.wither.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWitherLamp extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		x-=5;
		z-=5;
		world.setBlockState(new BlockPos(x + 0, y + 0, z + 0), JourneyBlocks.withanRock.getDefaultState());
		world.setBlockState(new BlockPos(x + 0, y + 1, z + 0), JourneyBlocks.withanRock.getDefaultState());
		world.setBlockState(new BlockPos(x + 0, y + 2, z + 0), JourneyBlocks.withanLamp.getDefaultState());
		return true;
	}
}
