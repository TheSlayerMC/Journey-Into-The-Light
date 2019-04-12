package net.journey.dimension.nether.region;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.dimension.nether.NetherBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class RegionSandy extends NetherBase{

	public RegionSandy(String name) {
		super(name);
	}
	
	@Override
	public void generate(Chunk chunk, BlockPos pos, Random rand) {
		for (int j = 0; j < 16; ++j) {
			for (int k = 0; k < 16; ++k) {
				BlockPos p2 = pos.down(j);
				if (p2.getY() > -1 && chunk.getBlockState(p2).getBlock() == Blocks.NETHERRACK)
					if (chunk.getBlockState(p2.down()).getBlock() == Blocks.AIR) {
						chunk.setBlockState(p2, Blocks.NETHERRACK.getDefaultState());
					} else {
						chunk.setBlockState(p2, JourneyBlocks.heatSand.getDefaultState());
					}	
			} if (chunk.getBlockState(pos).getBlock() == Blocks.NETHERRACK) {
				chunk.setBlockState(pos, JourneyBlocks.heatSand.getDefaultState());
			}
		}
	}
}
