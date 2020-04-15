package net.journey.dimension.nether.gen;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenHellThornMedium extends WorldGenerator {

    @Override
    public boolean generate(World world, Random r, BlockPos pos) {
        int i = pos.getX() - 6, j = pos.getY() - 1, k = pos.getZ() - 6;
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 0, k + 0), JourneyBlocks.hellThornRoot.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 1, k + 0), JourneyBlocks.nethicGrass.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 2, k + 0), JourneyBlocks.hellThornBottom.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 3, k + 0), JourneyBlocks.hellThornBottom.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 4, k + 0), JourneyBlocks.hellThornBottom.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 5, k + 0), JourneyBlocks.hellThornBottom.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 6, k + 0), JourneyBlocks.hellThornTop.getDefaultState());
        return true;
    }
}