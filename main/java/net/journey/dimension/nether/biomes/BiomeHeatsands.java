package net.journey.dimension.nether.biomes;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class BiomeHeatsands extends NetherBiomeBase {
	
	/* Created by paulevs, from the Better Nether mod 
	 * Big thanks to him*/
	
	public BiomeHeatsands(String name) {
		super(name);
	}

	@Override
	public void genSurfColumn(Chunk chunk, BlockPos pos, Random random) {
		for (int i = 1; i < 1 + random.nextInt(3); i++) {
			BlockPos p2 = pos.down(i);
			if (p2.getY() > -1 && chunk.getBlockState(p2).getBlock() == Blocks.NETHERRACK)
				if (chunk.getBlockState(p2.down()).getBlock() == Blocks.AIR)
					chunk.setBlockState(p2, Blocks.NETHERRACK.getDefaultState());
				else
					chunk.setBlockState(p2, JourneyBlocks.heatSand.getDefaultState());
		}
		if (chunk.getBlockState(pos).getBlock() == Blocks.NETHERRACK)
			chunk.setBlockState(pos, JourneyBlocks.heatSand.getDefaultState());
	}

	@Override
	public void genFloorObjects(Chunk chunk, BlockPos pos, Random random) {
		if (random.nextFloat() <= plantDensity && chunk.getBlockState(pos).getBlock() == Blocks.GRAVEL
				&& ((random.nextInt(16) == 0 || getFeatureNoise(pos, chunk.x, chunk.z) > 0.3))) {
			if (JourneyBlocks.hellThornTop != Blocks.AIR && random.nextInt(8) == 0) {
				int h = 1 + random.nextInt(3);
				for (int i = 1; i < h; i++)
					if (chunk.getBlockState(pos.up(i)).getBlock() != Blocks.AIR) {
						h = i;
						break;
					}
				for (int i = 1; i < h; i++)
					chunk.setBlockState(pos.up(i), JourneyBlocks.hellThornTop.getDefaultState());
				chunk.setBlockState(pos.up(h), JourneyBlocks.hellThornTop.getDefaultState());
			}
		}
	}
}