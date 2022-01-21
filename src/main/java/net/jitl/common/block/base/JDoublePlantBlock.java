package net.jitl.common.block.base;

import net.jitl.core.JITL;
import net.jitl.core.api.block.GroundPredicate;
import net.jitl.core.util.Logs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class JDoublePlantBlock extends DoublePlantBlock {

    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    private GroundPredicate groundPredicate;
    private Direction plantDirection = Direction.UP;

    public JDoublePlantBlock(Properties properties) {
        super(properties);
    }

    public void setGroundPredicate(@Nullable GroundPredicate groundPredicate) {
        this.groundPredicate = groundPredicate;
    }

    public JDoublePlantBlock setPredicate(@Nullable GroundPredicate groundPredicate) {
        this.groundPredicate = groundPredicate;
        return this;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
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
    public boolean canSurvive(BlockState state, @NotNull LevelReader worldIn, @NotNull BlockPos pos) {
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
            return mayPlaceOn(state, worldIn, pos);
        } else {
            BlockState blockstate = worldIn.getBlockState(pos.below());
            if (state.getBlock() != this) return mayPlaceOn(state, worldIn, pos);
            return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    @Override
    protected boolean mayPlaceOn(@NotNull BlockState state, BlockGetter worldIn, BlockPos pos) {
        BlockPos groundPos = pos.offset(plantDirection.getOpposite().getNormal());
        BlockState groundState = worldIn.getBlockState(groundPos);

        if (worldIn instanceof LevelReader) {
            return groundPredicate.testGround((LevelReader) worldIn, groundPos, groundState, plantDirection);
        } else {
            JITL.LOGGER.warn(
                    "Can't test the surface for {} placement. " +
                            "The World is supposed to be {}, but is {}. ",
                    getClass().getName(),
                    LevelReader.class.getName(),
                    worldIn.getClass().getName());
            Logs.printReportMessage();
            return false;
        }
    }
}
