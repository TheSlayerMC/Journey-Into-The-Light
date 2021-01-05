package net.jitl.common.block.base;

import net.jitl.api.block.GroundPredicate;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class JDoublePlantBlock extends DoublePlantBlock {
    private GroundPredicate groundPredicate;
    private Direction plantDirection = Direction.UP;

    public JDoublePlantBlock(Properties properties) {
        super(properties);
    }

    public JDoublePlantBlock setGroundPredicate(@Nullable GroundPredicate groundPredicate) {
        this.groundPredicate = groundPredicate;
        return this;
    }

    public GroundPredicate getGroundPredicate() {
        return groundPredicate;
    }

    public JDoublePlantBlock setPlantDirection(Direction plantDirection) {
        this.plantDirection = plantDirection;
        return this;
    }

    public Direction getPlantDirection() {
        return plantDirection;
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos groundPos = pos.offset(plantDirection.getOpposite().getNormal());
        BlockState groundState = worldIn.getBlockState(groundPos);

        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
            return groundPredicate.testGround((World) worldIn, groundPos, groundState, plantDirection);
        } else {
            BlockState blockstate = worldIn.getBlockState(pos.below());
            if (state.getBlock() != this) return super.canSurvive(state, worldIn, pos);
            return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
        BlockPos groundPos = pos.offset(plantDirection.getOpposite().getNormal());
        BlockState groundState = worldIn.getBlockState(groundPos);

        return groundPredicate.testGround((World) worldIn, groundPos, groundState, plantDirection);
    }
}
