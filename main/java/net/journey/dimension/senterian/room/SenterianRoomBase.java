package net.journey.dimension.senterian.room;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.dimension.senterian.SenterianChunkPrimer;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public abstract class SenterianRoomBase {

	public abstract boolean generate(SenterianChunkPrimer chunk, Random rand, int x, int y, int z);

	public void setBlock(SenterianChunkPrimer chunk, int x, int y, int z, Block block) {
		chunk.setBlockState(x, y, z, block.getDefaultState());
	}

	public void setBlockState(SenterianChunkPrimer chunk, int x, int y, int z, IBlockState state) {
		chunk.setBlockState(x, y, z, state);
	}
}