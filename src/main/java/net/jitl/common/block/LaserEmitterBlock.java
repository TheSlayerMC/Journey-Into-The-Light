package net.jitl.common.block;

import net.jitl.common.tile.LaserEmitterTile;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import org.jetbrains.annotations.Nullable;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class LaserEmitterBlock extends BaseEntityBlock {
    public LaserEmitterBlock(Properties builder) {
        super(builder);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter worldIn) {
        return new LaserEmitterTile();
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return super.getShape(state, worldIn, pos, context);
    }
}
