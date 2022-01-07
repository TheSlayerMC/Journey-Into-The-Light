package net.jitl.common.block.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public abstract class FallingEntityBlock extends FallingBlock implements EntityBlock {
    protected FallingEntityBlock(BlockBehaviour.Properties properties_) {
        super(properties_);
    }

    public RenderShape getRenderShape(BlockState state_) {
        return RenderShape.INVISIBLE;
    }

    public boolean triggerEvent(BlockState state_, Level level_, BlockPos pos_, int id_, int param_) {
        super.triggerEvent(state_, level_, pos_, id_, param_);
        BlockEntity blockentity = level_.getBlockEntity(pos_);
        return blockentity != null && blockentity.triggerEvent(id_, param_);
    }

    @Nullable
    public MenuProvider getMenuProvider(BlockState state_, Level level_, BlockPos pos_) {
        BlockEntity blockentity = level_.getBlockEntity(pos_);
        return blockentity instanceof MenuProvider ? (MenuProvider) blockentity : null;
    }

    /**
     * {@return the ticker if the given type and expected type are the same, or {@code null} if they are different}
     */
    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> givenType_, BlockEntityType<E> expectedType_, BlockEntityTicker<? super E> ticker_) {
        return expectedType_ == givenType_ ? (BlockEntityTicker<A>) ticker_ : null;
    }
}