package net.journey.dimension.nether.gen.trees;

import net.journey.blocks.crop.base.BlockFruitCrop;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.common.JourneyCrops;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class WorldGenBleedheartTree0 extends WorldGenerator {

    @Override
    public boolean generate(World world, Random r, BlockPos pos) {
        int i = pos.getX() - 2, j = pos.getY(), k = pos.getZ() - 2;
        Block log = JourneyBlocks.sizzlerWoodLog;
        Block leaves = JourneyBlocks.sizzlerWoodLeaves;
        int height = r.nextInt(3);
        WorldGenAPI.addRectangle(1, 1, 4 + height, world, i + 2, j + 1, k + 2, log);
        j = j + height;
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 2, k + 2), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 1), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 2), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 3), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 2), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 2, k), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 2, k + 1), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 2, k + 3), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 2, k + 4), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 1), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 3), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 2), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k + 1), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k + 2), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k + 3), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 2), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 2, k + 2), leaves.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 1, k + 3), JourneyCrops.bleedheartFruit.getStateFromMeta(2).withProperty(BlockFruitCrop.AGE, 1));
        return true;
    }
}