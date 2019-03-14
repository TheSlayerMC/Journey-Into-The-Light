package net.journey.dimension.corba.gen;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.JourneyCrops;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenCorbaFarm extends WorldGenerator {

	public boolean locationIsValidSpawn(World w, int x, int y, int z) {
		for(int i = 0; i < 11; i++) {
			for(int l = 0; l < 11; l++) {
				if(w.getBlockState(new BlockPos(x + i, y, z + l)) != JourneyBlocks.corbaGrass) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean generate(World w, Random r, BlockPos pos) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		if(locationIsValidSpawn(w, x, y, z)) return true;
		Block crop = JourneyCrops.tomatoCrop;
		WorldGenAPI.addRectangle(11, 9, 5, w, x, y - 4, z, JourneyBlocks.corbaStone);
		WorldGenAPI.addRectangle(11, 9, 1, w, x, y + 1, z, JourneyBlocks.corbaGrass);
		WorldGenAPI.addRectangle(9, 7, 1, w, x + 1, y + 1, z + 1, Blocks.WATER);
		WorldGenAPI.addRectangle(5, 1, 1, w, x + 2, y + 1, z + 3, Blocks.FARMLAND);
		WorldGenAPI.addRectangle(5, 1, 1, w, x + 4, y + 1, z + 2, Blocks.FARMLAND);
		WorldGenAPI.addRectangle(5, 1, 1, w, x + 2, y + 1, z + 5, Blocks.FARMLAND);
		WorldGenAPI.addRectangle(5, 1, 1, w, x + 4, y + 1, z + 6, Blocks.FARMLAND);
		WorldGenAPI.addRectangle(5, 1, 1, w, x + 2, y + 2, z + 3, crop);
		WorldGenAPI.addRectangle(5, 1, 1, w, x + 4, y + 2, z + 2, crop);
		WorldGenAPI.addRectangle(5, 1, 1, w, x + 2, y + 2, z + 5, crop);
		WorldGenAPI.addRectangle(5, 1, 1, w, x + 4, y + 2, z + 6, crop);
		return true;
	}
}