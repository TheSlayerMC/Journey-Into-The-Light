/*
*** MADE BY MITHION'S .SCHEMATIC TO JAVA CONVERTING TOOL v1.6 ***
*/

package net.journey.dimension.wither.gen.trees;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWithanTree3 extends WorldGenerator {
	
	public WorldGenWithanTree3() { }

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();
		world.setBlockState(new BlockPos(i + 0, j + 2, k + 1), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 4, k + 1), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 4, k + 3), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 1, k + 2), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 1), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 2), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 4), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 3, k + 2), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 4, k + 1), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 4, k + 3), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 4, k + 4), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 5, k + 2), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 7, k + 2), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 2), JourneyBlocks.withanBark.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 1), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 2), JourneyBlocks.withanBark.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 3), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 2, k + 2), JourneyBlocks.withanBark.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 2, k + 3), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 2, k + 4), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 3, k + 1), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 3, k + 2), JourneyBlocks.withanBark.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 0), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 1), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 2), JourneyBlocks.withanBark.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 3), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 4), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 5, k + 1), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 5, k + 2), JourneyBlocks.withanBark.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 5, k + 3), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 6, k + 2), JourneyBlocks.withanBark.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 7, k + 1), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 7, k + 2), JourneyBlocks.withanBark.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 7, k + 3), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 8, k + 2), JourneyBlocks.withanBark.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 2), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 2, k + 0), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 2, k + 2), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 2, k + 3), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 2, k + 4), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 3, k + 2), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 4, k + 0), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 4, k + 1), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 4, k + 2), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 4, k + 3), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 4, k + 4), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 5, k + 2), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 7, k + 2), JourneyBlocks.withanLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 4, j + 4, k + 1), JourneyBlocks.withanLeaves.getDefaultState());
		return true;
	}
}