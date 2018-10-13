package net.journey.dimension.overworld.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.blocks.BlockCaveVine;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCaveVines extends WorldGenerator {

	@Override
	public boolean generate(World world, Random random, BlockPos pos) {
		int copyX = pos.getX();
		int copyZ = pos.getZ();
		for (; pos.getY() > 5; pos = pos.down()) {
			if (world.isAirBlock(pos) && BlockCaveVine.canPlaceBelow(world, pos) && random.nextInt(6) > 0) {
				world.setBlockState(pos, JourneyBlocks.caveVine.getDefaultState(), 2);
			} else {
				pos = new BlockPos(copyX + random.nextInt(4) - random.nextInt(4), pos.getY(),
						copyZ + random.nextInt(4) - random.nextInt(4));
			}
		}

		return true;
	}
}