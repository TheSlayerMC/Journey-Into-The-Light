package net.journey.dimension.boil.gen;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenBoilStalagmite extends WorldGenerator {

	@Override
	public boolean generate(World world, Random random, BlockPos blockPos) {
		BlockPos.MutableBlockPos placePos = new BlockPos.MutableBlockPos(WorldGenAPI.findPosAboveSurface(world, WorldGenAPI.optimizeAndRandomize(blockPos, random))).move(EnumFacing.DOWN);

		int bottomHeight = 1 + random.nextInt(4);
		int largeHeight = 1 + random.nextInt(5);
		int medHeight = 1 + random.nextInt(6);
		int smallHeight = 1 + random.nextInt(7);
		int tinyHeight = 1 + random.nextInt(8);

		for (int i = 0; i < bottomHeight; i++) {
			setBlockAndNotifyAdequately(world, placePos.move(EnumFacing.UP), JourneyBlocks.scorchedRubble.getDefaultState());
		}
		for (int i = 0; i < largeHeight; i++) {
			setBlockAndNotifyAdequately(world, placePos.move(EnumFacing.UP), JourneyBlocks.scorchedStalagmiteLarge.getDefaultState());
		}
		for (int i = 0; i < medHeight; i++) {
			setBlockAndNotifyAdequately(world, placePos.move(EnumFacing.UP), JourneyBlocks.scorchedStalagmiteMedium.getDefaultState());
		}
		for (int i = 0; i < smallHeight; i++) {
			setBlockAndNotifyAdequately(world, placePos.move(EnumFacing.UP), JourneyBlocks.scorchedStalagmiteSmall.getDefaultState());
		}
		for (int i = 0; i < tinyHeight; i++) {
			setBlockAndNotifyAdequately(world, placePos.move(EnumFacing.UP), JourneyBlocks.scorchedStalagmiteTiny.getDefaultState());
		}
		return true;
	}
}
