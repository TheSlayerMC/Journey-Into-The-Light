package net.journey.dimension.euca.gen.trees;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenEucaTree5 extends WorldGenerator {

    @Override
    public boolean generate(World world, Random r, BlockPos pos) {
        int i = pos.getX(), j = pos.getY() - 1, k = pos.getZ();
        Block log = JourneyBlocks.eucaGoldLog;
        Block leaves = JourneyBlocks.eucaSilverLeaves;
        int height = r.nextInt(3);
        WorldGenAPI.addRectangle(1, 1, 4 + height, world, i, j + 1, k, log);
        j = j + height;
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 2, k - 2), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i - 1, j + 2, k - 1), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i - 1, j + 2, k), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i - 1, j + 2, k + 1), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i - 1, j + 4, k), leaves.getDefaultState());

        this.setBlockAndNotifyAdequately(world, new BlockPos(i - 2, j + 2, k), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 2, k - 1), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 2, k + 1), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 2, k + 2), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 4, k - 1), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 4, k + 1), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 5, k), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k - 1), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 1), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 2, k), leaves.getDefaultState());
        return true;
    }
}