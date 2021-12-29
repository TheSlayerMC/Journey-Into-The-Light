package net.jitl.common.block.base;

import net.jitl.JITL;
import net.jitl.api.block.GroundPredicate;
import net.jitl.init.JBlocks;
import net.jitl.util.Logs;
import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IForgeShearable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class JPlantBlock extends BushBlock implements IGrowable, IForgeShearable {
    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 13.0D, 12.0D);
    protected static final VoxelShape TALL_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 30.0D, 12.0D);

    protected Supplier<Block> grownPlant;
    private Direction plantDirection = Direction.UP;

    private GroundPredicate groundPredicate;
    private boolean offset;

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
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull IBlockReader worldIn, @NotNull BlockPos pos, @NotNull ISelectionContext context) {
        return this == JBlocks.TALL_GREEN_FUNGI ? TALL_SHAPE : SHAPE;
    }

    /**
     * Whether this IGrowable can grow
     */
    @Override
    public boolean isValidBonemealTarget(@NotNull IBlockReader worldIn, @NotNull BlockPos pos, @NotNull BlockState state, boolean isClient) {
        return grownPlant.get() != null;
    }

    @Override
    public boolean isBonemealSuccess(@NotNull World worldIn, @NotNull Random rand, @NotNull BlockPos pos, @NotNull BlockState state) {
        return grownPlant.get() != null;
    }

    @Override
    public void performBonemeal(@NotNull ServerWorld worldIn, @NotNull Random rand, @NotNull BlockPos pos, @NotNull BlockState state) {
        JDoublePlantBlock doubleplantblock = (JDoublePlantBlock) (grownPlant.get());
        if (grownPlant.get() != null) {
            if (doubleplantblock.defaultBlockState().canSurvive(worldIn, pos) && worldIn.isEmptyBlock(pos.above())) {
                doubleplantblock.placeAt(worldIn, pos, 2);
            }
        }
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, @NotNull IWorldReader worldIn, @NotNull BlockPos pos) {
        return mayPlaceOn(state, worldIn, pos);
    }

    @Override
    protected boolean mayPlaceOn(@NotNull BlockState state, IBlockReader worldIn, BlockPos pos) {
        BlockPos groundPos = pos.offset(plantDirection.getOpposite().getNormal());
        BlockState groundState = worldIn.getBlockState(groundPos);

        if (worldIn instanceof IWorldReader) {
            return groundPredicate.testGround((IWorldReader) worldIn, groundPos, groundState, plantDirection);
        } else {
            JITL.LOGGER.warn(
                    "Can't test the surface for {} placement. " +
                            "The World is supposed to be {}, but is {}. ",
                    getClass().getName(),
                    IWorldReader.class.getName(),
                    worldIn.getClass().getName());
            Logs.printReportMessage();
            return false;
        }
    }

    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
    @Override
    public AbstractBlock.@NotNull OffsetType getOffsetType() {
        return offset ? AbstractBlock.OffsetType.XYZ : super.getOffsetType();
    }
}
