package net.journey.dimension.euca.gen.trees;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenEucaTree2 extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		IBlockState leaves = WorldGenAPI.getEucaLeaves().getDefaultState(), log = WorldGenAPI.getEucaLog().getDefaultState();
		int height = 5+rand.nextInt(5);
		for(int y = 0; y<height; y++) {
			world.setBlockState(new BlockPos(i, y+j, k), log);
		}
		
		j+=height;
		
		i-=4;
		k-=4;
		
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 0), leaves);
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 5), leaves);
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 6), leaves);
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 7), leaves);
		world.setBlockState(new BlockPos(i + 0, j + 0, k + 8), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 0), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 5), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 6), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 7), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 0, k + 8), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 0), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 5), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 6), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 7), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 0, k + 8), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 5), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 6), leaves);
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 0), leaves);
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 5), leaves);
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 6), leaves);
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 7), leaves);
		world.setBlockState(new BlockPos(i + 3, j + 0, k + 8), leaves);
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 5), leaves);
		world.setBlockState(new BlockPos(i + 3, j + 1, k + 6), leaves);
		world.setBlockState(new BlockPos(i + 3, j + 2, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 0), leaves);
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 4), log);
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 5), leaves);
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 6), leaves);
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 7), leaves);
		world.setBlockState(new BlockPos(i + 4, j + 0, k + 8), leaves);
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 4), log);
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 5), leaves);
		world.setBlockState(new BlockPos(i + 4, j + 1, k + 6), leaves);
		world.setBlockState(new BlockPos(i + 4, j + 2, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 4, j + 2, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 4, j + 2, k + 5), leaves);
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 0), leaves);
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 5), leaves);
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 6), leaves);
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 7), leaves);
		world.setBlockState(new BlockPos(i + 5, j + 0, k + 8), leaves);
		world.setBlockState(new BlockPos(i + 5, j + 1, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 5, j + 1, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 5, j + 1, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 5, j + 1, k + 5), leaves);
		world.setBlockState(new BlockPos(i + 5, j + 1, k + 6), leaves);
		world.setBlockState(new BlockPos(i + 5, j + 2, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 0), leaves);
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 5), leaves);
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 6), leaves);
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 7), leaves);
		world.setBlockState(new BlockPos(i + 6, j + 0, k + 8), leaves);
		world.setBlockState(new BlockPos(i + 6, j + 1, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 6, j + 1, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 6, j + 1, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 6, j + 1, k + 5), leaves);
		world.setBlockState(new BlockPos(i + 6, j + 1, k + 6), leaves);
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 0), leaves);
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 5), leaves);
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 6), leaves);
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 7), leaves);
		world.setBlockState(new BlockPos(i + 7, j + 0, k + 8), leaves);
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 0), leaves);
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 5), leaves);
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 6), leaves);
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 7), leaves);
		world.setBlockState(new BlockPos(i + 8, j + 0, k + 8), leaves);

		return true;
	}
}