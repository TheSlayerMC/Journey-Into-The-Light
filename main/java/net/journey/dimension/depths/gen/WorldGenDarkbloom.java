package net.journey.dimension.depths.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDarkbloom extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, BlockPos pos) {
		Block top = JourneyBlocks.darkbloomTop, bottom = JourneyBlocks.darkbloomBottom;
			if(w.getBlockState(pos.down()).getBlock() == JourneyBlocks.depthsGrass && w.getBlockState(pos) == Blocks.AIR.getDefaultState() && w.getBlockState(pos.up()) == Blocks.AIR.getDefaultState()) {
				w.setBlockState(pos, bottom.getDefaultState(), 2);
				w.setBlockState(pos.up(), top.getDefaultState(), 2);
			}
		return true;
	}
}