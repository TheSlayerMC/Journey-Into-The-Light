package net.jitl.common.block.base;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

import java.util.Random;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class JLeavesBlock extends LeavesBlock {

    public JLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) { }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) { }
}
