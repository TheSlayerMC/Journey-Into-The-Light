package net.journey.dimension.overworld.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSmallGlowshrooms extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, BlockPos pos) {
		Block top = JourneyBlocks.greenGlowshroomTop;
		switch(r.nextInt(3)){
		case 0: top = JourneyBlocks.greenGlowshroomTop; break;
		case 1: top = JourneyBlocks.redGlowshroomTop; break;
		case 2: top = JourneyBlocks.blueGlowshroomTop; break;
		}
		for(int i = 0; i < 50; i++) {
			if(w.getBlockState(pos.down()).getBlock() == Blocks.stone && w.getBlockState(pos) == Blocks.air.getDefaultState() && pos.getY() < 60) {
				w.setBlockState(pos, top.getDefaultState(), 2);
			}
		}
		return true;
	}
}