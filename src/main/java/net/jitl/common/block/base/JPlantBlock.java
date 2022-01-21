package net.jitl.common.block.base;

import net.jitl.core.JITL;
import net.jitl.core.api.block.GroundPredicate;
import net.jitl.core.init.JBlocks;
import net.jitl.core.util.Logs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class JPlantBlock extends BushBlock implements BonemealableBlock, IForgeShearable {
    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 13.0D, 12.0D);
    protected static final VoxelShape TALL_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 30.0D, 12.0D);

    protected Supplier<Block> grownPlant;
    private Direction plantDirection = Direction.UP;

    private GroundPredicate groundPredicate;
    private final boolean offset;

    public JPlantBlock(Properties properties) {
        this(properties, true);
    }

    public JPlantBlock(Properties properties, boolean offset) {
        super(properties);
        this.offset = offset;
    }

    public JPlantBlock setGroundPredicate(@Nullable GroundPredicate groundPredicate) {
        this.groundPredicate = groundPredicate;
        return this;
    }

    public GroundPredicate getGroundPredicate() {
        return groundPredicate;
    }

    public JPlantBlock setPlantDirection(Direction plantDirection) {
        this.plantDirection = plantDirection;
        return this;
    }

    public Direction getPlantDirection() {
        return plantDirection;
    }

    /**
     * Sets the block that's grown when bonemealed
     */
    public JPlantBlock setGrownPlant(@Nullable Supplier<Block> grownPlant) {
        this.grownPlant = grownPlant;
        return this;
    }

    public Block getGrownPlant() {
        return grownPlant.get();
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return this == JBlocks.TALL_FUNGI ? TALL_SHAPE : SHAPE;
    }

    /*
     * Whether this IGrowable can grow

    @Override
    public boolean isValidBonemealTarget(@NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull BlockState state, boolean isClient) {
        return grownPlant.get() != null;
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level worldIn, @NotNull Random rand, @NotNull BlockPos pos, @NotNull BlockState state) {
        return grownPlant.get() != null;
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel worldIn, @NotNull Random rand, @NotNull BlockPos pos, @NotNull BlockState state) {
        JDoublePlantBlock doubleplantblock = (JDoublePlantBlock) (grownPlant.get());
        if (grownPlant.get() != null) {
            if (doubleplantblock.defaultBlockState().canSurvive(worldIn, pos) && worldIn.isEmptyBlock(pos.above())) {
                DoublePlantBlock.placeAt(worldIn, state, pos, 2);
            }
        }
    }*/

    @Override
    public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader worldIn, @NotNull BlockPos pos) {
        return mayPlaceOn(state, worldIn, pos);
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

    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
    @Override
    public @NotNull OffsetType getOffsetType() {
        return offset ? OffsetType.XYZ : super.getOffsetType();
    }
}
