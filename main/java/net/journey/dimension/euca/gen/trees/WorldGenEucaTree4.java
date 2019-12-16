package net.journey.dimension.euca.gen.trees;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.blocks.crop.base.BlockFruitCrop;
import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenEucaTree4 extends WorldGenerator {

	@Override
	public boolean generate(World world, Random r, BlockPos pos) {
		int i = pos.getX(), j = pos.getY() - 1, k = pos.getZ();
		Block log = JourneyBlocks.eucaGoldLog;
		Block leaves = JourneyBlocks.eucaGoldLeaves;
		int height = r.nextInt(4);
		WorldGenAPI.addRectangle(1, 1, 4 + height, world, i, j + 1, k, log);
		j = j + height;
		world.setBlockState(new BlockPos(i, j + 2, k - 2), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i - 1, j + 2, k - 1), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i - 1, j + 2, k), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i - 1, j + 2, k + 1), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i - 1, j + 4, k), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i - 2, j + 2, k), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i, j + 2, k - 1), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i, j + 2, k + 1), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i, j + 2, k + 2), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i, j + 4, k - 1), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i, j + 4, k + 1), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i, j + 5, k), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 2, k - 1), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 2, k), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 1), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 1, j + 4, k), leaves.getDefaultState());
		world.setBlockState(new BlockPos(i + 2, j + 2, k), leaves.getDefaultState());
		return true;
	}
}