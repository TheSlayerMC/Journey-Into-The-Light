package net.journey.dimension.depths.gen;

import java.util.Random;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDepthsTree extends WorldGenerator {

    @Override
    public boolean generate(World world, Random r, BlockPos pos) {
        int i = pos.getX() - 6, j = pos.getY() - 1, k = pos.getZ() - 6;
        IBlockState leaves = JourneyBlocks.depthsLeaves.getDefaultState();
        IBlockState log = JourneyBlocks.depthsLog.getDefaultState();

        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 2, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 2, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 3, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 3, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 3, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 4, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 4, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 4, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 5, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 5, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 5, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 6, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 6, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i, j + 6, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 4), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 4), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 4), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 5, k), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 5, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 5, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 5, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 5, k + 4), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 6, k), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 6, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 6, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 6, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 6, k + 4), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j, k + 2), log);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 1, k + 2), log);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 2, k + 2), log);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 2), log);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 4), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 2), log);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 4), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 4), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 6, k), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 6, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 6, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 6, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 6, k + 4), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k + 4), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 4), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 4), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 5, k), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 5, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 5, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 5, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 5, k + 4), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 6, k), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 6, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 6, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 6, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 6, k + 4), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 2, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 2, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 3, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 3, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 3, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 4, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 4, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 4, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 5, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 5, k + 2), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 5, k + 3), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 6, k + 1), leaves);
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 6, k + 2), leaves);
        return true;

    }
}