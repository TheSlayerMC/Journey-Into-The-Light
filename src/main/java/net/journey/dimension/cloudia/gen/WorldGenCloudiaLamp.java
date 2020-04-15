package net.journey.dimension.cloudia.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCloudiaLamp extends WorldGenerator {
	
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 1), JourneyBlocks.cloudiaDirt.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 2), JourneyBlocks.cloudiaRock.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 3), JourneyBlocks.cloudiaRock.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 0), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 1), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 2), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 3), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 2), JourneyBlocks.cloudiaPost.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 2), JourneyBlocks.cloudiaRock.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 1, k + 1), JourneyBlocks.cloudiaDirt.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 1, k + 2), JourneyBlocks.cloudiaRock.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 2, k + 1), JourneyBlocks.cloudiaRock.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 2, k + 2), JourneyBlocks.cloudiaDirt.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 2, k + 3), JourneyBlocks.cloudiaDirt.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 0), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 1), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 2), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 3), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 4), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 1), JourneyBlocks.cloudiaPost.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 2), JourneyBlocks.cloudiaLog.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 3), JourneyBlocks.cloudiaPost.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 2), JourneyBlocks.cloudiaPost.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 6, k + 2), JourneyBlocks.cloudiaPost.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 7, k + 2), JourneyBlocks.cloudiaLamp.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 1, k + 2), JourneyBlocks.cloudiaRock.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k + 1), JourneyBlocks.cloudiaDirt.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k + 2), JourneyBlocks.cloudiaRock.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k + 3), JourneyBlocks.cloudiaRock.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 0), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 1), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 2), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 3), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 4), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 2), JourneyBlocks.cloudiaPost.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 3, k + 1), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 3, k + 2), JourneyBlocks.cloudiaGrass.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 3, k + 3), JourneyBlocks.cloudiaGrass.getDefaultState());


		return true;
	}
}
