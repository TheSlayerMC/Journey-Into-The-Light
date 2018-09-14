package net.journey.dimension.terrania.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenTerranianLamp extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		IBlockState leaves = WorldGenAPI.getCorbaLeaves().getDefaultState(), log = WorldGenAPI.getCorbaLog().getDefaultState();
		i-=5;
		k-=5;
			world.setBlockState(new BlockPos(i + 0, j + 0, k + 1), JourneyBlocks.terranianPost.getDefaultState());
			world.setBlockState(new BlockPos(i + 1, j + 0, k + 0), JourneyBlocks.terranianPost.getDefaultState());
			world.setBlockState(new BlockPos(i + 1, j + 0, k + 1), JourneyBlocks.terranianPanels.getDefaultState());
			world.setBlockState(new BlockPos(i + 1, j + 0, k + 2), JourneyBlocks.terranianPost.getDefaultState());
			world.setBlockState(new BlockPos(i + 1, j + 1, k + 1), JourneyBlocks.terranianPost.getDefaultState());
			world.setBlockState(new BlockPos(i + 1, j + 2, k + 1), JourneyBlocks.terranianPost.getDefaultState());
			world.setBlockState(new BlockPos(i + 1, j + 3, k + 1), JourneyBlocks.terraniaLamp.getDefaultState());
			world.setBlockState(new BlockPos(i + 2, j + 0, k + 1), JourneyBlocks.terranianPost.getDefaultState());
		return true;
	}
}