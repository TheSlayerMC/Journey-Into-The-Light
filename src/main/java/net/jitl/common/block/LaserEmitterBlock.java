package net.jitl.common.block;

import net.jitl.common.tile.LaserEmitterTile;
import net.jitl.init.JTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class LaserEmitterBlock extends BaseEntityBlock {

    public LaserEmitterBlock(Properties builder) {
        super(builder);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return super.getShape(state, worldIn, pos, context);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LaserEmitterTile(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level_, BlockState state_, BlockEntityType<T> blockEntityType_) {
        return createTicker(level_, blockEntityType_, JTiles.LASER_EMITTER);
    }

    @javax.annotation.Nullable
    protected <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level_, BlockEntityType<T> givenType_, BlockEntityType<? extends LaserEmitterTile> expectedType_) {
        return level_.isClientSide ? null : createTickerHelper(givenType_, expectedType_, LaserEmitterTile::serverTick);
    }
}
