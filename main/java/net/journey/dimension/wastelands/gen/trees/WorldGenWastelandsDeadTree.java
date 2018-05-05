package net.journey.dimension.wastelands.gen.trees;
/*package net.essence.dimension.wastelands.gen.trees;

import java.util.Random;

import net.essence.EssenceBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenWastelandsDeadTree extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos p) {
		if(world.getBlockState(p.down()).getBlock() != EssenceBlocks.wastelandsGrass) return false;
		int height = 11+rand.nextInt(5)*2;
		for(int y = 0; y<height; y++) {
			world.setBlockState(p.up(y), EssenceBlocks.wastelandsLog.getDefaultState());
		}
		
		for(int y = height/4; y<height; y+=2) {
			world.setBlockState(p.up(y).east(1), EssenceBlocks.wastelandsBranches.getDefaultState());
			world.setBlockState(p.up(y).west(1), EssenceBlocks.wastelandsBranches.getDefaultState());
			world.setBlockState(p.up(y).north(1), EssenceBlocks.wastelandsBranches.getDefaultState());
			world.setBlockState(p.up(y).south(1), EssenceBlocks.wastelandsBranches.getDefaultState());
			world.setBlockState(p.up(y).east(1).north(1), EssenceBlocks.wastelandsBranches.getDefaultState());
			world.setBlockState(p.up(y).west(1).north(1), EssenceBlocks.wastelandsBranches.getDefaultState());
			world.setBlockState(p.up(y).east(1).south(1), EssenceBlocks.wastelandsBranches.getDefaultState());
			world.setBlockState(p.up(y).west(1).south(1), EssenceBlocks.wastelandsBranches.getDefaultState());
		}
		
		world.setBlockState(p.up(height), EssenceBlocks.wastelandsBranches.getDefaultState());
		
		return true;
	}
}*/