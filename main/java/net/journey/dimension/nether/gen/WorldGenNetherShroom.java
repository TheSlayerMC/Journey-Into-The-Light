package net.journey.dimension.nether.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNetherShroom extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, BlockPos pos) {
		Block plant = JourneyBlocks.hellshroom;
		switch(r.nextInt(3)){
		case 0: plant = JourneyBlocks.hellshroom; break;
		}
		for(int i = 0; i < 50; i++) {
			if(w.getBlockState(pos.down()).getBlock() == Blocks.netherrack || w.getBlockState(pos.down()).getBlock() == JourneyBlocks.heatSoil && w.getBlockState(pos) == Blocks.air.getDefaultState() && pos.getY() < 250) {
				w.setBlockState(pos, plant.getDefaultState(), 2);
			}
		}
		return true;
	}
}