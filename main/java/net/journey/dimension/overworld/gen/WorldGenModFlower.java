package net.journey.dimension.overworld.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.block.BlockModFlower;

public class WorldGenModFlower extends WorldGenerator {

	private BlockModFlower flower;

	public WorldGenModFlower(BlockModFlower b) {
		this.flower = b;
	}

	@Override
	public boolean generate(World w, Random r, BlockPos p) {
		for(int i = 0; i < 64; i++) {
			BlockPos blockpos1 = new BlockPos(p.getX() + r.nextInt(8) - r.nextInt(8), p.getY() + r.nextInt(4) - r.nextInt(4), p.getZ() + r.nextInt(8) - r.nextInt(8));
			if(flower.canBlockStay(w, blockpos1, false) && w.getBlockState(blockpos1).getBlock() == Blocks.air) {
				w.setBlockState(blockpos1, flower.getDefaultState(), 2);
			}
		}
		return true;
	}
}