package net.journey.dimension.overworld.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTallGlowshrooms extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, BlockPos pos) {
		Block top = JourneyBlocks.greenGlowshroomTop, bottom = JourneyBlocks.greenGlowshroomBottom;
		switch(r.nextInt(3)){
		case 0: 
			bottom = JourneyBlocks.greenGlowshroomBottom;
			top = JourneyBlocks.greenGlowshroomTop;
			break;
		case 1: 
			bottom = JourneyBlocks.redGlowshroomBottom;
			top = JourneyBlocks.redGlowshroomTop; 
			break;
		case 2: 
			bottom = JourneyBlocks.blueGlowshroomBottom;
			top = JourneyBlocks.blueGlowshroomTop; 
			break;
		}
		for(int i = 0; i < 50; i++) {
			if(w.getBlockState(pos.down()).getBlock() == Blocks.STONE && w.getBlockState(pos) == Blocks.AIR.getDefaultState() && w.getBlockState(pos.up()) == Blocks.AIR.getDefaultState() && pos.getY() < 60) {
				w.setBlockState(pos, bottom.getDefaultState(), 2);
				w.setBlockState(pos.up(), top.getDefaultState(), 2);
			}
		}
		return true;
	}
}