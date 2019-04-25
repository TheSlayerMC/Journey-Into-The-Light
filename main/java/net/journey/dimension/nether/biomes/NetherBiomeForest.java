package net.journey.dimension.nether.biomes;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class NetherBiomeForest extends NetherBiome {
	public NetherBiomeForest(String name) {
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
					chunk.setBlockState(p2, JourneyBlocks.heatSoil.getDefaultState());
		}
		if (chunk.getBlockState(pos).getBlock() == Blocks.NETHERRACK)
			chunk.setBlockState(pos, JourneyBlocks.heatSoil.getDefaultState());
	}
}
