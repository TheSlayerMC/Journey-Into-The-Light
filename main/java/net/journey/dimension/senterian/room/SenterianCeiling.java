package net.journey.dimension.senterian.room;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.dimension.senterian.SenterianChunkPrimer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.ChunkPrimer;
import net.slayer.api.worldgen.WorldGenAPI;

public class SenterianCeiling extends RoomBase {
	
	public SenterianCeiling() { }

	@Override
	public boolean generate(SenterianChunkPrimer world, Random rand, int x, int y, int z) {
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 16; j++) {
				this.setBlock(world, i, y, j, JourneyBlocks.senterianFloor);
			}
		}
		return true;
	}
}