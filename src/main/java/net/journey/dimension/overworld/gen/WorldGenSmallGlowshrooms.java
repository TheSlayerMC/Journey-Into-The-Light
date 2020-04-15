package net.journey.dimension.overworld.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSmallGlowshrooms extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, BlockPos pos) {
		Block top = JourneyBlocks.greenGlowshroomTop;
		switch(r.nextInt(3)){
		case 0:
			top = JourneyBlocks.greenGlowshroomTop; 
			break;
		case 1: 
			top = JourneyBlocks.redGlowshroomTop; 
			break;
		case 2: 
			top = JourneyBlocks.blueGlowshroomTop; 
			break;
		}
		
		if(w.getBlockState(pos.down()).getBlock() == Blocks.STONE && w.getBlockState(pos) == Blocks.AIR.getDefaultState() && pos.getY() < 60) {
			this.setBlockAndNotifyAdequately(w, pos, top.getDefaultState());
		}
		return true;
	}
}