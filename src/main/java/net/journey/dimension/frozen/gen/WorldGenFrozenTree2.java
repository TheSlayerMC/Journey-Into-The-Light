package net.journey.dimension.frozen.gen;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenFrozenTree2 extends WorldGenerator {

    @Override
    public boolean generate(World worldIn, Random r, BlockPos bp) {
        int i = bp.getX() - 2, j = bp.getY(), k = bp.getZ() - 2;

        IBlockState leaves = JourneyBlocks.frozenLeaves.getDefaultState();
        IBlockState log = JourneyBlocks.frozenLog.getDefaultState();
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i, j + 4, k + 2), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 2, k + 2), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 4, k + 1), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 4, k + 2), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 1, j + 4, k + 3), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j, k + 2), log);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 1, k + 2), log);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 2, k + 1), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 2, k + 2), log);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 2, k + 3), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 3, k + 2), log);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 4, k), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 4, k + 1), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 4, k + 2), log);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 4, k + 3), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 4, k + 4), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 5, k + 2), log);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 2, j + 6, k + 2), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 2, k + 2), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 4, k + 1), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 4, k + 2), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 3, j + 4, k + 3), leaves);
        this.setBlockAndNotifyAdequately(worldIn, new BlockPos(i + 4, j + 4, k + 2), leaves);
        return true;
    }
}