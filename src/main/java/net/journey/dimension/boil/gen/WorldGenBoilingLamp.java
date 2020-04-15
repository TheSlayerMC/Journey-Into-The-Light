package net.journey.dimension.boil.gen;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenBoilingLamp extends WorldGenerator {

    public boolean locationIsValidSpawn(World w, int x, int y, int z) {
        return WorldGenAPI.checkRadius(w, new BlockPos(x, y, z), 11, JourneyBlocks.hotBlock);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        int i = pos.getX(), j = pos.getY(), k = pos.getZ();
        if (locationIsValidSpawn(world, i, j, k)) return true;
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 0, k + 1), JourneyBlocks.sizzlingPost.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 0), JourneyBlocks.sizzlingPost.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 1), JourneyBlocks.brisonblocks.getStateFromMeta(0));
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 2), JourneyBlocks.sizzlingPost.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 1, k + 1), JourneyBlocks.sizzlingPost.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 1), JourneyBlocks.sizzlingPost.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 1), JourneyBlocks.boilingLamp.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 1), JourneyBlocks.sizzlingPost.getDefaultState());
        return true;
    }
}