package net.journey.dimension.frozen.gen;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenLamp3 extends WorldGenerator {

    @Override
    public boolean generate(World world, Random r, BlockPos pos) {
        int i = pos.getX(), j = pos.getY() + 1, k = pos.getZ();
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 0, k + 0), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 1, k + 0), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 2, k + 0), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 3, k + 0), JourneyBlocks.frozenLamp.getDefaultState());
        return true;
    }
}