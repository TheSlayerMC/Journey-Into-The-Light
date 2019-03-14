package net.journey.dimension.frozen.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNewLamp extends WorldGenerator {

	public boolean locationIsValidSpawn(World w, int x, int y, int z) {
		for(int i = 0; i < 11; i++) {
			for(int l = 0; l < 11; l++) {
				if(w.getBlockState(new BlockPos(x + i, y, z + l)) != JourneyBlocks.frozenGrass) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean generate(World w, Random rand, BlockPos pos) {
		int x = pos.getX(), y = pos.getY() - 1, z = pos.getZ();
		if(locationIsValidSpawn(w, x, y, z)) return true;
		if(!w.getBlockState(pos.down()).getBlock().isFullBlock(null))return false;
		w.setBlockState(pos, JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(2), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(3), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(4), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(5), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(6), JourneyBlocks.frozenLamp.getDefaultState());
		
		w.setBlockState(pos.up(4).east(), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(4).west(), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(4).north(), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(4).south(), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(4).east(2), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(4).west(2), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(4).north(2), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(4).south(2), JourneyBlocks.workshopStone.getDefaultState());
		
		w.setBlockState(pos.up(3).east(2), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(3).west(2), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(3).north(2), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(3).south(2), JourneyBlocks.workshopStone.getDefaultState());
		w.setBlockState(pos.up(2).east(2), JourneyBlocks.frozenLamp.getDefaultState());
		w.setBlockState(pos.up(2).west(2), JourneyBlocks.frozenLamp.getDefaultState());
		w.setBlockState(pos.up(2).north(2), JourneyBlocks.frozenLamp.getDefaultState());
		w.setBlockState(pos.up(2).south(2), JourneyBlocks.frozenLamp.getDefaultState());
		return true;
	}

}
