package net.journey.dimension.nether.biomes;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.dimension.nether.JNWorldGenerator;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class NetherBiomeForestEdge extends NetherBiomeForest {
	public NetherBiomeForestEdge(String name) {
		super(name);
	}

	@Override
	public void genFloorObjects(Chunk chunk, BlockPos pos, Random random) {
		if (random.nextFloat() <= plantDensity && chunk.getBlockState(pos).getBlock() == Blocks.SOUL_SAND) {
			if (random.nextInt(3) == 0 && chunk.getBlockState(pos).getBlock() == Blocks.SOUL_SAND
					&& chunk.getBlockState(pos.up()).getBlock() == Blocks.AIR)
				chunk.setBlockState(pos.up(), JourneyBlocks.hellshroom.getDefaultState());
		}
	}
}
