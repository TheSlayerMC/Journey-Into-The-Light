package net.jitl.common.block;

import net.jitl.init.JBlocks;
import net.minecraft.block.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.LevelReader;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class IcyIvyTopBlock extends GrowingPlantHeadBlock {
    protected static final VoxelShape SHAPE = Block.box(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public IcyIvyTopBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1D);
    }

    /**
     * Used to determine how much to grow the plant when using bonemeal. Kelp always returns 1, where as the nether vines
     * return a random value at least 1.
     */
    @Override
    protected int getBlocksToGrowWhenBonemealed(@NotNull Random rand) {
        return NetherVines.getBlocksToGrowWhenBonemealed(rand);
    }

    @Override
    public boolean isLadder(BlockState state, LevelReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }

    @Override
    protected @NotNull Block getBodyBlock() {
        return JBlocks.ICY_IVY_PLANT;
    }

    @Override
    protected boolean canGrowInto(@NotNull BlockState state) {
        return NetherVines.isValidGrowthState(state);
    }
}
