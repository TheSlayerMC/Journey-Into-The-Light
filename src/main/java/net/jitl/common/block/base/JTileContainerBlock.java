package net.jitl.common.block.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class JTileContainerBlock extends Block implements EntityBlock {
    private final BiFunction<BlockPos, BlockState, BlockEntity> tileFactory;

    public JTileContainerBlock(Properties properties, BiFunction<BlockPos, BlockState, BlockEntity> tileFactory) {
        super(properties);
        this.tileFactory = tileFactory;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos_, BlockState state_) {
        return tileFactory.apply(pos_, state_);
    }
}
