package net.journey.dimension.frozen.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFrozenTree2 extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos p) {
		if(world.getBlockState(p.down()).getBlock() != JourneyBlocks.frozenGrass) return false;
		int height = 11+rand.nextInt(5)*2;
		
		for(int y = height / 4; y < height; y+=2) {
			for(int x = -3; x <= 3; x++) {
				for(int z = -3; z <= 3; z++) {
					world.setBlockState(p.east(x).north(z).up(y), JourneyBlocks.frozenLeaves.getDefaultState());
				}
			}
			for(int x = -1; x <= 1; x++) {
				for(int z = -1; z <= 1; z++) {
					world.setBlockState(p.east(x).north(z).up(y+1), JourneyBlocks.frozenLeaves.getDefaultState());
				}
			}
		}
		
		for(int y = 0; y < height; y++) {
			world.setBlockState(p.up(y), JourneyBlocks.frozenBark.getDefaultState());
		}
		
		world.setBlockState(p.up(height), JourneyBlocks.frozenLeaves.getDefaultState());
		
		return true;
	}
}