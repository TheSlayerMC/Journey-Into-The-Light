package net.journey.dimension.nether.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenHellThornTall extends WorldGenerator {

@Override
public boolean generate(World world, Random r, BlockPos pos) {
int i = pos.getX() - 6, j = pos.getY() - 1, k = pos.getZ() - 6;
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 0), JourneyBlocks.hellThornRoot.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 1, k + 0), JourneyBlocks.nethicGrass.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 2, k + 0), JourneyBlocks.hellThornBottom.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 3, k + 0), JourneyBlocks.hellThornBottom.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 4, k + 0), JourneyBlocks.hellThornBottom.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 5, k + 0), JourneyBlocks.hellThornBottom.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 6, k + 0), JourneyBlocks.hellThornBottom.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 7, k + 0), JourneyBlocks.hellThornBottom.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 8, k + 0), JourneyBlocks.hellThornBottom.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 9, k + 0), JourneyBlocks.hellThornBottom.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 10, k + 0), JourneyBlocks.hellThornBottom.getDefaultState());
		world.setBlockState(new BlockPos(i + 0, j + 11, k + 0), JourneyBlocks.hellThornTop.getDefaultState());
		return true;
	}
}

