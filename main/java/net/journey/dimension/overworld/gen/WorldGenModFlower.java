package net.journey.dimension.overworld.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.block.BlockModFlower;

public class WorldGenModFlower extends WorldGenerator {

	private BlockModFlower flower;
	private Block grass; 
	
	public WorldGenModFlower(BlockModFlower b, Block grass) {
		this.flower = b;
		this.grass = grass;
	}

	@Override
	public boolean generate(World w, Random rand, BlockPos p) {
		for(int i = 0; i < 64; i++) {
			BlockPos blockpos = p.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            
			if(w.isAirBlock(blockpos) && w.getBlockState(blockpos.down()).getBlock() == grass && flower.canPlaceBlockAt(w, blockpos)) {
				w.setBlockState(blockpos, flower.getDefaultState(), 2);
				return true;
			}
		}
		return false;
	}
}