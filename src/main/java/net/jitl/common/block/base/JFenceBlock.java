package net.jitl.common.block.base;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;

public class JFenceBlock extends FenceBlock {

    public JFenceBlock(Properties properties) {
        super(properties);
    }

    public boolean connectsTo(BlockState state_, boolean isSideSolid_, Direction direction_) {
        Block block = state_.getBlock();
        boolean flag = block == this;
        boolean flag1 = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(state_, direction_);
        return !isExceptionForConnection(state_) && isSideSolid_ || flag || flag1;
    }
}
