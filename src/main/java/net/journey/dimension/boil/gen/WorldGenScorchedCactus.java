package net.journey.dimension.boil.gen;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenScorchedCactus extends WorldGenerator {

	@Override
	public boolean generate(World world, Random random, BlockPos blockPos) {
		BlockPos.MutableBlockPos placePos = new BlockPos.MutableBlockPos(WorldGenAPI.findPosAboveSurface(world, WorldGenAPI.optimizeAndRandomize(blockPos, random))).move(EnumFacing.DOWN);

		int height = 2 + random.nextInt(6);

		if (world.getBlockState(placePos.up()) == Blocks.AIR.getDefaultState()) {
			for (int i = 0; i < height; i++) {
				setBlockAndNotifyAdequately(world, placePos.move(EnumFacing.UP), JourneyBlocks.scorchedCactus.getDefaultState());
			}
		}
		return true;
	}
}