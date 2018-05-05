package net.journey.dimension.frozen.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNewLamp extends WorldGenerator {

	@Override
	public boolean generate(World w, Random rand, BlockPos pos) {
		if(!w.getBlockState(pos.down()).getBlock().isFullBlock())return false;
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
