package net.journey.dimension.cloudia.zone;

import java.util.Random;

import net.journey.dimension.cloudia.CloudiaChunkPrimer;
import net.minecraft.init.Blocks;

public class CloudiaEmptyChunk extends CloudiaZoneBase {

	public boolean generate(CloudiaChunkPrimer chunk, Random rand, int x, int y, int z, int yHeight) {
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < yHeight; j++) {//figure out what height to fill with air
				for(int k = 0; k < 16; k++) {
					setBlock(chunk, x + i, y + j, z + k, Blocks.AIR);
				}
			}
		}
		return true;
	}

	@Override
	public boolean generate(CloudiaChunkPrimer chunk, Random rand, int x, int y, int z) {
		return false;
	}
}