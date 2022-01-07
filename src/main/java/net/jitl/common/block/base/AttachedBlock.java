package net.jitl.common.block.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Random;

public class AttachedBlock extends Block {
    protected static final VoxelShape DOWN_AABB = Block.box(3, (16 - 5), 3, (16 - 3), 16.0D, (16 - 3));
    protected static final VoxelShape UP_AABB = Block.box(3, 0.0D, 3, (16 - 3), 5, (16 - 3));
    protected static final VoxelShape NORTH_AABB = Block.box(3, 3, (16 - 5), (16 - 3), (16 - 3), 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(3, 3, 0.0D, (16 - 3), (16 - 3), 5);
    protected static final VoxelShape WEST_AABB = Block.box((16 - 5), 3, 3, 16.0D, (16 - 3), (16 - 3));
    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 3, 3, 5, (16 - 3), (16 - 3));

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public AttachedBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.UP));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        switch (direction) {
            case DOWN:
                return DOWN_AABB;
            case NORTH:
                return NORTH_AABB;
            case EAST:
                return EAST_AABB;
            case SOUTH:
                return SOUTH_AABB;
            case WEST:
                return WEST_AABB;
            default:
                return UP_AABB;
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        if (!worldIn.isAreaLoaded(pos, 1)) {
            return;
        }
        if (!state.canSurvive(worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext blockItemUseContext) {
        BlockState blockstate = this.defaultBlockState();
        LevelReader worldReader = blockItemUseContext.getLevel();
        BlockPos blockPos = blockItemUseContext.getClickedPos();

        Direction[] directions = blockItemUseContext.getNearestLookingDirections();

        for (int i = 0; i < directions.length; ++i) {
            Direction direction = directions[i];
            Direction direction2 = direction.getOpposite();
            blockstate = blockstate.setValue(FACING, direction2);
            if (blockstate.canSurvive(worldReader, blockPos)) {
                return blockstate;
            }
        }
        return null;
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader reader, BlockPos blockPos) {
        Direction direction = blockState.getValue(FACING);
        BlockPos newPos = blockPos.relative(direction.getOpposite());
        BlockState newState = reader.getBlockState(newPos);
        return newState.isFaceSturdy(reader, newPos, direction);
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.canSurvive(worldIn, currentPos)) {
            worldIn.scheduleTick(currentPos, this, 1);
        }
        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState b, Rotation r) {
        return b.setValue(FACING, r.rotate(b.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState b, Mirror m) {
        return b.rotate(m.getRotation(b.getValue(FACING)));
    }
}
