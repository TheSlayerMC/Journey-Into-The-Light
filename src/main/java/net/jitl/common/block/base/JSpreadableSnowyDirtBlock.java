package net.jitl.common.block.base;

import net.minecraft.block.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.lighting.LayerLightEngine;
import net.minecraft.server.level.ServerLevel;

import java.util.Random;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class JSpreadableSnowyDirtBlock extends SnowyDirtBlock {
    private final Block dirtBlock;

    public JSpreadableSnowyDirtBlock(Properties builder, Block dirtBlock) {
        super(builder);
        this.dirtBlock = dirtBlock;
    }

    private static boolean canBeGrass(BlockState state, LevelReader worldReader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = worldReader.getBlockState(blockpos);
        if (blockstate.is(Blocks.SNOW) && blockstate.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        } else if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LayerLightEngine.getLightBlockInto(worldReader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(worldReader, blockpos));
            return i < worldReader.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState state, LevelReader worldReader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return canBeGrass(state, worldReader, pos) && !worldReader.getFluidState(blockpos).is(FluidTags.WATER);
    }

    /**
     * Performs a random tick on a block.
     */
    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
        if (!canBeGrass(state, worldIn, pos)) {
            if (!worldIn.isAreaLoaded(pos, 3))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            worldIn.setBlockAndUpdate(pos, dirtBlock.defaultBlockState());
        } else {
            if (worldIn.getMaxLocalRawBrightness(pos.above()) >= 9) {
                BlockState blockstate = this.defaultBlockState();

                for (int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (worldIn.getBlockState(blockpos).is(dirtBlock) && canPropagate(blockstate, worldIn, blockpos)) {
                        worldIn.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, worldIn.getBlockState(blockpos.above()).is(Blocks.SNOW)));
                    }
                }
            }

        }
    }
}