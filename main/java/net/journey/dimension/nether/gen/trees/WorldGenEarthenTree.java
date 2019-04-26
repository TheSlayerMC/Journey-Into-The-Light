package net.journey.dimension.nether.gen.trees;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenEarthenTree extends WorldGenerator {

	@Override
	public boolean generate(World world, Random r, BlockPos pos) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		
		Block log = JourneyBlocks.earthenNetherLog;
		Block leaves = JourneyBlocks.earthenNetherLeaves;
		int height = r.nextInt(2);
		WorldGenAPI.addRectangle(1, 1, 1 + height, world, i, j, k, log);
		j = j + height;
		if(height < 2) {
			world.setBlockState(new BlockPos(i, j + 1 , k), leaves.getDefaultState());
			world.setBlockState(new BlockPos(i - 1, j, k), leaves.getDefaultState());
			world.setBlockState(new BlockPos(i, j, k - 1), leaves.getDefaultState());
			world.setBlockState(new BlockPos(i + 1, j, k), leaves.getDefaultState());
			world.setBlockState(new BlockPos(i, j, k + 1), leaves.getDefaultState());
		}
		if(height >= 2) {
			world.setBlockState(new BlockPos(i, j + 2, k), leaves.getDefaultState());
			world.setBlockState(new BlockPos(i - 1, j + 1 - 1, k), leaves.getDefaultState());
			world.setBlockState(new BlockPos(i, j + 1 - 1, k - 1), leaves.getDefaultState());
			world.setBlockState(new BlockPos(i + 1, j + 1 - 1, k), leaves.getDefaultState());
			world.setBlockState(new BlockPos(i, j + 1 - 1, k + 1), leaves.getDefaultState());
		}
		
		return true;
	}
}