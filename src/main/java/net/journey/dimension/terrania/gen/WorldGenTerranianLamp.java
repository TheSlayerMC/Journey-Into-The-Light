package net.journey.dimension.terrania.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenTerranianLamp extends WorldGenerator {
	
	public boolean locationIsValidSpawn(World w, int x, int y, int z) {
		return WorldGenAPI.checkRadius(w, new BlockPos(x,y,z), 11, JourneyBlocks.terranianGrass);
	}
	
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		if(locationIsValidSpawn(world, i, j, k)) return true;
		IBlockState leaves = WorldGenAPI.getCorbaLeaves().getDefaultState(), log = WorldGenAPI.getCorbaLog().getDefaultState();
			this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 0, k + 1), JourneyBlocks.terranianPost.getDefaultState());
			this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 0), JourneyBlocks.terranianPost.getDefaultState());
			this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 1), JourneyBlocks.terranianPanels.getDefaultState());
			this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 2), JourneyBlocks.terranianPost.getDefaultState());
			this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 1, k + 1), JourneyBlocks.terranianPost.getDefaultState());
			this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 1), JourneyBlocks.terranianPost.getDefaultState());
			this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 1), JourneyBlocks.terraniaLamp.getDefaultState());
			this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 1), JourneyBlocks.terranianPost.getDefaultState());
		return true;
	}
}