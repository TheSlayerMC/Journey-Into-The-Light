package net.jitl.common.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class AttachedBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public AttachedBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.UP));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@NotNull BlockItemUseContext blockItemUseContext) {
        BlockState blockstate = this.defaultBlockState();
        Direction[] directions = blockItemUseContext.getNearestLookingDirections();
        for (int i = 0; i < directions.length; i++) {
            Direction direction = directions[i].getOpposite();
            return blockstate.setValue(FACING, direction);
        }
        return null;
    }

    @Override
    public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        Direction lvt_4_1_ = p_196260_1_.getValue(FACING);
        BlockPos lvt_5_1_ = p_196260_3_.relative(lvt_4_1_.getOpposite());
        BlockState lvt_6_1_ = p_196260_2_.getBlockState(lvt_5_1_);
        return lvt_6_1_.isFaceSturdy(p_196260_2_, lvt_5_1_, lvt_4_1_);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos) {
        return blockState;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
        return p_185471_1_.rotate(p_185471_2_.getRotation(p_185471_1_.getValue(FACING)));
    }
}
