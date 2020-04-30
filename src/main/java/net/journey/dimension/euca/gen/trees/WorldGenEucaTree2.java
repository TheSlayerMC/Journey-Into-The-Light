package net.journey.dimension.euca.gen.trees;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenEucaTree2 extends WorldGenerator {

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        int i = pos.getX(), j = pos.getY(), k = pos.getZ();
        IBlockState leaves = WorldGenAPI.getEucaLeaves().getDefaultState(), log = WorldGenAPI.getEucaLog().getDefaultState();
        int height = 4 + rand.nextInt(4);
        for (int y = 0; y < height; y++) {
            this.setBlockAndNotifyAdequately(world, new BlockPos(i, y + j, k), log);
        }

        j += height;
        int x = i - 2, y1 = j - 5, z = k - 2;
        
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 0, y1 + 2, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 0, y1 + 3, z + 1), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 0, y1 + 3, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 0, y1 + 3, z + 3), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 0, y1 + 4, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y1 + 1, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y1 + 2, z + 1), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y1 + 2, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y1 + 2, z + 3), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y1 + 3, z + 0), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y1 + 3, z + 1), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y1 + 3, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y1 + 3, z + 3), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y1 + 3, z + 4), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y1 + 4, z + 1), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y1 + 4, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y1 + 4, z + 3), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y1 + 5, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 1, z + 1), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 1, z + 3), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 2, z + 0), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 2, z + 1), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 2, z + 3), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 2, z + 4), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 3, z + 0), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 3, z + 1), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 3, z + 3), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 3, z + 4), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 4, z + 0), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 4, z + 1), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 4, z + 3), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 4, z + 4), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 5, z + 1), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 5, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 2, y1 + 5, z + 3), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y1 + 1, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y1 + 2, z + 1), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y1 + 2, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y1 + 2, z + 3), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y1 + 3, z + 0), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y1 + 3, z + 1), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y1 + 3, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y1 + 3, z + 3), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y1 + 3, z + 4), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y1 + 4, z + 1), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y1 + 4, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y1 + 4, z + 3), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 3, y1 + 5, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 4, y1 + 2, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 4, y1 + 3, z + 1), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 4, y1 + 3, z + 2), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 4, y1 + 3, z + 3), leaves);
		this.setBlockAndNotifyAdequately(world, new BlockPos(x + 4, y1 + 4, z + 2), leaves);
		return true;
    }
}