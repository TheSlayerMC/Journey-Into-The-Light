package net.jitl.common.block;

import net.jitl.init.JBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorldReader;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class IcyIvyTopBlock extends AbstractTopPlantBlock {
    protected static final VoxelShape SHAPE = Block.box(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public IcyIvyTopBlock(AbstractBlock.Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1D);
    }

    /**
     * Used to determine how much to grow the plant when using bonemeal. Kelp always returns 1, where as the nether vines
     * return a random value at least 1.
     */
    @Override
    protected int getBlocksToGrowWhenBonemealed(@NotNull Random rand) {
        return PlantBlockHelper.getBlocksToGrowWhenBonemealed(rand);
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }

    @Override
    protected @NotNull Block getBodyBlock() {
        return JBlocks.ICY_IVY_PLANT;
    }

    @Override
    protected boolean canGrowInto(@NotNull BlockState state) {
        return PlantBlockHelper.isValidGrowthState(state);
    }
}
