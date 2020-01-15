package net.journey.dimension.euca.gen.trees;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenBotSpawner extends WorldGenerator {

	public boolean locationIsValidSpawn(World w, int x, int y, int z) {
		return WorldGenAPI.checkRadius(w, new BlockPos(x,y,z), 11, JourneyBlocks.eucaGrass);
	}
	
	@Override
	public boolean generate(World w, Random r, BlockPos pos) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		if(locationIsValidSpawn(w, x, y, z)) return true;
		int height = r.nextInt(10) + 10;
		WorldGenAPI.addRectangle(1, 1, 1, w, x + 3, y + height + 2, z + 1, JourneyBlocks.goldbotSpawner);
		WorldGenAPI.addRectangle(1, 1, 1, w, x + 3, y + height + 3, z + 1, JourneyBlocks.silverbotSpawner);
		WorldGenAPI.addRectangle(1, 1, height + 1, w, x + 3, y + 1, z + 1, JourneyBlocks.eucaBricks);
		WorldGenAPI.addRectangle(3, 1, height, w, x + 2, y + 1, z + 1, JourneyBlocks.eucaBricks);
		WorldGenAPI.addRectangle(1, 3, height, w, x + 3, y + 1, z, JourneyBlocks.eucaBricks);
		WorldGenAPI.addRectangle(7, 3, 1, w, x, y + height, z, JourneyBlocks.eucaTile);	
		WorldGenAPI.addRectangle(3, 7, 1, w, x + 2, y + height, z - 2, JourneyBlocks.eucaTile);		
		WorldGenAPI.addRectangle(5, 5, 1, w, x + 1, y + height, z - 1, JourneyBlocks.eucaTile);
		return false;
	}
}