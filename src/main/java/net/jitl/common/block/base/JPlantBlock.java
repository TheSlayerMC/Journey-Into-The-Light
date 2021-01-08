package net.jitl.common.block.base;

import net.jitl.api.block.GroundPredicate;
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
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    protected Supplier<Block> grownPlant;
    private Direction plantDirection = Direction.UP;

    private GroundPredicate groundPredicate;

    public JPlantBlock(Properties properties) {
        super(properties);
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
        return SHAPE;
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

        return groundPredicate.testGround((IWorldReader) worldIn, groundPos, groundState, plantDirection);
    }

    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
    @Override
    public AbstractBlock.@NotNull OffsetType getOffsetType() {
        return AbstractBlock.OffsetType.XYZ;
    }
}
