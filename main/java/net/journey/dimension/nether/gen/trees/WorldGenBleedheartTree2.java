package net.journey.dimension.nether.gen.trees;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.blocks.crop.base.BlockFruitCrop;
import net.minecraft.block.BlockCocoa;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBleedheartTree2 extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		world.setBlockState(new BlockPos(i + 0, j + 3, k + 2), JourneyBlocks.sizzlerWoodLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 2), JourneyBlocks.sizzlerWoodLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 1, k + 2), JourneyBlocks.sizzlerWoodLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 1), JourneyBlocks.sizzlerWoodLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 2), JourneyBlocks.sizzlerWoodLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 3, k + 0), JourneyBlocks.sizzlerWoodLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 3, k + 1), JourneyBlocks.sizzlerWoodLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 3, k + 2), JourneyBlocks.sizzlerWoodLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 4, k + 2), JourneyBlocks.sizzlerWoodLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 5, k + 2), JourneyBlocks.sizzlerWoodLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 5, k + 3), JourneyBlocks.sizzlerWoodLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 6, k + 2), JourneyBlocks.sizzlerWoodLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 6, k + 3), JourneyBlocks.sizzlerWoodLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 6, k + 4), JourneyBlocks.sizzlerWoodLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 7, k + 1), JourneyBlocks.sizzlerWoodLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 4, k + 2), JourneyBlocks.sizzlerWoodLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 5, k + 2), JourneyBlocks.sizzlerWoodLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 5, k + 2), JourneyBlocks.sizzlerWoodLog.getDefaultState());
		world.setBlockState(new BlockPos(i + 3, j + 6, k + 2), JourneyBlocks.sizzlerWoodLeaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 5, k + 1), JourneyBlocks.bleedheartFruit.getStateFromMeta(4).withProperty(BlockFruitCrop.AGE, 0));
		return true;
	}
}
