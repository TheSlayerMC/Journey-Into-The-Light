package net.journey.dimension.frozen.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenNewLamp extends WorldGenerator {

	public boolean locationIsValidSpawn(World w, int x, int y, int z) {
		return WorldGenAPI.checkRadius(w, new BlockPos(x,y,z), 11, JourneyBlocks.frozenGrass);
	}
	
	@Override
	public boolean generate(World w, Random rand, BlockPos pos) {
		int x = pos.getX(), y = pos.getY() - 1, z = pos.getZ();
		if(locationIsValidSpawn(w, x, y, z)) return true;
		if(!w.getBlockState(pos.down()).getBlock().isFullBlock(null))return false;
		this.setBlockAndNotifyAdequately(w, pos, JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(2), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(3), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(4), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(5), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(6), JourneyBlocks.frozenLamp.getDefaultState());
		
		this.setBlockAndNotifyAdequately(w, pos.up(4).east(), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(4).west(), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(4).north(), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(4).south(), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(4).east(2), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(4).west(2), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(4).north(2), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(4).south(2), JourneyBlocks.workshopStone.getDefaultState());
		
		this.setBlockAndNotifyAdequately(w, pos.up(3).east(2), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(3).west(2), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(3).north(2), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(3).south(2), JourneyBlocks.workshopStone.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(2).east(2), JourneyBlocks.frozenLamp.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(2).west(2), JourneyBlocks.frozenLamp.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(2).north(2), JourneyBlocks.frozenLamp.getDefaultState());
		this.setBlockAndNotifyAdequately(w, pos.up(2).south(2), JourneyBlocks.frozenLamp.getDefaultState());
		return true;
	}

}
