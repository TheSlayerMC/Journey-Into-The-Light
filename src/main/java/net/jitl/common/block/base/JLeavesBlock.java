package net.jitl.common.block.base;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class JLeavesBlock extends LeavesBlock {

    public JLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) { }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) { }
}
