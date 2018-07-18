package net.journey.dimension.boil.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBoilingFire extends WorldGenerator{

	@Override
	public boolean generate(World w, Random r, BlockPos p) {
		for(int i = 0; i < 100;){
			BlockPos pos1 = new BlockPos(p.getX() + r.nextInt(8) - r.nextInt(8), p.getY() + r.nextInt(4) - r.nextInt(4), p.getZ() + r.nextInt(8) - r.nextInt(8));{
				w.setBlockState(pos1, Blocks.FIRE.getDefaultState(), 2);
				return true;
			}
		}
		return false;
	}
}