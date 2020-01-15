package net.journey.dimension.corba.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenCorbaLamp extends WorldGenerator {
	
	public boolean locationIsValidSpawn(World w, int x, int y, int z) {
		return WorldGenAPI.checkRadius(w, new BlockPos(x,y,z), 11, JourneyBlocks.corbaGrass);
	}
	
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		if(locationIsValidSpawn(world, i, j, k)) return true;
		IBlockState leaves = WorldGenAPI.getCorbaLeaves().getDefaultState(), log = WorldGenAPI.getCorbaLog().getDefaultState();
			world.setBlockState(new BlockPos(i + 0, j + 0, k + 1), JourneyBlocks.corbaPost.getDefaultState());
			world.setBlockState(new BlockPos(i + 1, j + 0, k + 0), JourneyBlocks.corbaPost.getDefaultState());
			world.setBlockState(new BlockPos(i + 1, j + 0, k + 1), JourneyBlocks.corbaPlank.getDefaultState());
			world.setBlockState(new BlockPos(i + 1, j + 0, k + 2), JourneyBlocks.corbaPost.getDefaultState());
			world.setBlockState(new BlockPos(i + 1, j + 1, k + 1), JourneyBlocks.corbaPost.getDefaultState());
			world.setBlockState(new BlockPos(i + 1, j + 2, k + 1), JourneyBlocks.corbaPost.getDefaultState());
			world.setBlockState(new BlockPos(i + 1, j + 3, k + 1), JourneyBlocks.corbaLamp.getDefaultState());
			world.setBlockState(new BlockPos(i + 2, j + 0, k + 1), JourneyBlocks.corbaPost.getDefaultState());
		return true;
	}
}