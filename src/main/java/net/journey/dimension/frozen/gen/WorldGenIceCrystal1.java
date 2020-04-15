package net.journey.dimension.frozen.gen;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenIceCrystal1 extends WorldGenerator {

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        int i = pos.getX(), j = pos.getY(), k = pos.getZ();
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 4, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 4, k + 4), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 5, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 5, k + 4), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 3), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 3), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 1, k + 3), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 0), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 4), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 6), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 0), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 6), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 0, k + 3), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 1, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 1, k + 4), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k + 1), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k + 5), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 1), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 5), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 1, k + 3), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 4, k + 0), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 4, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 4, k + 4), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 4, k + 6), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 5, k + 0), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 5, k + 6), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 2, k + 3), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 3, k + 3), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 4, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 4, k + 4), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 5, k + 2), JourneyBlocks.iceLeaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 5, k + 4), JourneyBlocks.iceLeaves.getDefaultState());

        return true;
    }
}