package net.jitl.common.block.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public class JFallingTileContainerBlock extends FallingEntityBlock {
    private final BiFunction<BlockPos, BlockState, BlockEntity> tileFactory;

    public JFallingTileContainerBlock(Properties properties, BiFunction<BlockPos, BlockState, BlockEntity> tileFactory) {
        super(properties);
        this.tileFactory = tileFactory;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos_, BlockState state_) {
        return tileFactory.apply(pos_, state_);
    }
}
