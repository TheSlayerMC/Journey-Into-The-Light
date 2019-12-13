package net.journey.dimension.euca.gen.trees;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenEucaTree3 extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		IBlockState leaves = WorldGenAPI.getEucaLeaves().getDefaultState(), log = WorldGenAPI.getEucaLog().getDefaultState();
		int height = 15+rand.nextInt(10);
		for(int y = 0; y<height; y++) {
			world.setBlockState(new BlockPos(i, y+j, k), log);
		}
		
		j+=height;
	
		world.setBlockState(new BlockPos(i, j, k), leaves);
		world.setBlockState(new BlockPos(i - 4, j, k - 2), leaves);
		world.setBlockState(new BlockPos(i - 4, j, k - 1), leaves);
		world.setBlockState(new BlockPos(i - 4, j, k), leaves);
		world.setBlockState(new BlockPos(i - 4, j, k + 1), leaves);
		world.setBlockState(new BlockPos(i - 4, j, k + 2), leaves);
		world.setBlockState(new BlockPos(i - 3, j, k - 2), leaves);
		world.setBlockState(new BlockPos(i - 3, j, k - 1), leaves);
		world.setBlockState(new BlockPos(i - 3, j, k), leaves);
		world.setBlockState(new BlockPos(i - 3, j, k + 1), leaves);
		world.setBlockState(new BlockPos(i - 3, j, k + 2), leaves);
		world.setBlockState(new BlockPos(i - 2, j, k - 4), leaves);
		world.setBlockState(new BlockPos(i - 2, j, k - 3), leaves);
		world.setBlockState(new BlockPos(i - 2, j, k - 2), leaves);
		world.setBlockState(new BlockPos(i - 2, j, k - 1), leaves);
		world.setBlockState(new BlockPos(i - 2, j, k), leaves);
		world.setBlockState(new BlockPos(i - 2, j, k + 1), leaves);
		world.setBlockState(new BlockPos(i - 2, j, k + 2), leaves);
		world.setBlockState(new BlockPos(i - 2, j, k + 3), leaves);
		world.setBlockState(new BlockPos(i - 2, j, k + 4), leaves);
		world.setBlockState(new BlockPos(i - 2, j + 1, k - 2), leaves);
		world.setBlockState(new BlockPos(i - 2, j + 1, k - 1), leaves);
		world.setBlockState(new BlockPos(i - 2, j + 1, k), leaves);
		world.setBlockState(new BlockPos(i - 2, j + 1, k + 1), leaves);
		world.setBlockState(new BlockPos(i - 2, j + 1, k + 2), leaves);
		world.setBlockState(new BlockPos(i - 1, j, k - 4), leaves);
		world.setBlockState(new BlockPos(i - 1, j, k - 3), leaves);
		world.setBlockState(new BlockPos(i - 1, j, k - 2), leaves);
		world.setBlockState(new BlockPos(i - 1, j, k - 1), leaves);
		world.setBlockState(new BlockPos(i - 1, j, k), leaves);
		world.setBlockState(new BlockPos(i - 1, j, k + 1), leaves);
		world.setBlockState(new BlockPos(i - 1, j, k + 2), leaves);
		world.setBlockState(new BlockPos(i - 1, j, k + 3), leaves);
		world.setBlockState(new BlockPos(i - 1, j, k + 4), leaves);
		world.setBlockState(new BlockPos(i - 1, j + 1, k - 2), leaves);
		world.setBlockState(new BlockPos(i - 1, j + 1, k - 1), leaves);
		world.setBlockState(new BlockPos(i - 1, j + 1, k), leaves);
		world.setBlockState(new BlockPos(i - 1, j + 1, k + 1), leaves);
		world.setBlockState(new BlockPos(i - 1, j + 1, k + 2), leaves);
		world.setBlockState(new BlockPos(i - 1, j + 2, k - 1), leaves);
		world.setBlockState(new BlockPos(i - 1, j + 2, k), leaves);
		world.setBlockState(new BlockPos(i - 1, j + 2, k + 1), leaves);
		world.setBlockState(new BlockPos(i, j, k), leaves);
		world.setBlockState(new BlockPos(i, j, k - 4), leaves);
		world.setBlockState(new BlockPos(i, j, k - 3), leaves);
		world.setBlockState(new BlockPos(i, j, k - 2), leaves);
		world.setBlockState(new BlockPos(i, j, k - 1), leaves);
		world.setBlockState(new BlockPos(i, j, k), log);
		world.setBlockState(new BlockPos(i, j, k + 1), leaves);
		world.setBlockState(new BlockPos(i, j, k + 2), leaves);
		world.setBlockState(new BlockPos(i, j, k + 3), leaves);
		world.setBlockState(new BlockPos(i, j, k + 4), leaves);
		world.setBlockState(new BlockPos(i, j, k + 5), leaves);
		world.setBlockState(new BlockPos(i, j + 1, k - 2), leaves);
		world.setBlockState(new BlockPos(i, j + 1, k - 1), leaves);
		world.setBlockState(new BlockPos(i, j + 1, k), log);
		world.setBlockState(new BlockPos(i, j + 1, k + 1), leaves);
		world.setBlockState(new BlockPos(i, j + 1, k + 2), leaves);
		world.setBlockState(new BlockPos(i, j + 2, k - 1), leaves);
		world.setBlockState(new BlockPos(i, j + 2, k), leaves);
		world.setBlockState(new BlockPos(i, j + 2, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 1, j, k - 4), leaves);
		world.setBlockState(new BlockPos(i + 1, j, k - 3), leaves);
		world.setBlockState(new BlockPos(i + 1, j, k - 2), leaves);
		world.setBlockState(new BlockPos(i + 1, j, k - 1), leaves);
		world.setBlockState(new BlockPos(i + 1, j, k), leaves);
		world.setBlockState(new BlockPos(i + 1, j, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 1, j, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 1, j, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 1, j, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 1, k - 2), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 1, k - 1), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 1, k), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 1, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 1, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 2, k - 1), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 2, k), leaves);
		world.setBlockState(new BlockPos(i + 1, j + 2, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 2, j, k - 4), leaves);
		world.setBlockState(new BlockPos(i + 2, j, k - 3), leaves);
		world.setBlockState(new BlockPos(i + 2, j, k - 2), leaves);
		world.setBlockState(new BlockPos(i + 2, j, k - 1), leaves);
		world.setBlockState(new BlockPos(i + 2, j, k), leaves);
		world.setBlockState(new BlockPos(i + 2, j, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 2, j, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 2, j, k + 3), leaves);
		world.setBlockState(new BlockPos(i + 2, j, k + 4), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 1, k - 2), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 1, k - 1), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 1, k), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 2, j + 1, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 3, j, k - 2), leaves);
		world.setBlockState(new BlockPos(i + 3, j, k - 1), leaves);
		world.setBlockState(new BlockPos(i + 3, j, k), leaves);
		world.setBlockState(new BlockPos(i + 3, j, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 3, j, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 4, j, k - 2), leaves);
		world.setBlockState(new BlockPos(i + 4, j, k - 1), leaves);
		world.setBlockState(new BlockPos(i + 4, j, k), leaves);
		world.setBlockState(new BlockPos(i + 4, j, k + 1), leaves);
		world.setBlockState(new BlockPos(i + 4, j, k + 2), leaves);
		world.setBlockState(new BlockPos(i + 5, j, k), leaves);

		return true;
	}
}