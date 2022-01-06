package net.jitl.common.block.base;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class JTileContainerBlock extends Block {
    private final BiFunction<BlockState, BlockGetter, BlockEntity> tileFactory;

    public JTileContainerBlock(Properties properties, BiFunction<BlockState, BlockGetter, BlockEntity> tileFactory) {
        super(properties);
        this.tileFactory = tileFactory;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
        return tileFactory.apply(state, world);
    }
}
