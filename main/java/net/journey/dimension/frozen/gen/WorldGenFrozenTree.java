package net.journey.dimension.frozen.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFrozenTree extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos p) {
		if(world.getBlockState(p.down()).getBlock() != JourneyBlocks.frozenGrass) return false;
		int height = 11+rand.nextInt(5)*2;
		for(int y = 0; y<height; y++) {
			world.setBlockState(p.up(y), JourneyBlocks.frozenBark.getDefaultState());
		}
		
		for(int y = height/4; y<height; y+=2) {
			world.setBlockState(p.up(y).east(1), JourneyBlocks.frozenLeaves.getDefaultState());
			world.setBlockState(p.up(y).west(1), JourneyBlocks.frozenLeaves.getDefaultState());
			world.setBlockState(p.up(y).north(1), JourneyBlocks.frozenLeaves.getDefaultState());
			world.setBlockState(p.up(y).south(1), JourneyBlocks.frozenLeaves.getDefaultState());
			world.setBlockState(p.up(y).east(1).north(1), JourneyBlocks.frozenLeaves.getDefaultState());
			world.setBlockState(p.up(y).west(1).north(1), JourneyBlocks.frozenLeaves.getDefaultState());
			world.setBlockState(p.up(y).east(1).south(1), JourneyBlocks.frozenLeaves.getDefaultState());
			world.setBlockState(p.up(y).west(1).south(1), JourneyBlocks.frozenLeaves.getDefaultState());
		}
		
		world.setBlockState(p.up(height), JourneyBlocks.frozenLeaves.getDefaultState());
		
		return true;
	}
}