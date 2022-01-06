package net.jitl.common.block.crop;

import net.jitl.init.JBlocks;
import net.jitl.util.JBlockProperties;
import net.minecraft.block.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import java.util.Random;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import IntegerProperty;
import VoxelShape;

public class GolditeFarmlandBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
    public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE;

    public GolditeFarmlandBlock() {
        super(JBlockProperties.DIRT_PROPS.create());
        this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, Integer.valueOf(0)));
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing == Direction.UP && !stateIn.canSurvive(worldIn, currentPos)) {
            worldIn.getBlockTicks().scheduleTick(currentPos, this, 1);
        }

        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockState blockstate = worldIn.getBlockState(pos.above());
        return !blockstate.getMaterial().isSolid();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? JBlocks.GOLDITE_DIRT.defaultBlockState() : super.getStateForPlacement(context);
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
        if (!state.canSurvive(worldIn, pos)) {
            turnToDirt(state, worldIn, pos);
        }

    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
        int i = state.getValue(MOISTURE);
        if (!isNearWater(worldIn, pos) && !worldIn.isRainingAt(pos.above())) {
            if (i > 0) {
                worldIn.setBlock(pos, state.setValue(MOISTURE, Integer.valueOf(i - 1)), 2);
            } else if (!isUnderCrops(worldIn, pos)) {
                turnToDirt(state, worldIn, pos);
            }
        } else if (i < 7) {
            worldIn.setBlock(pos, state.setValue(MOISTURE, Integer.valueOf(7)), 2);
        }

    }

    @Override
    public void fallOn(Level worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        if (!worldIn.isClientSide && net.minecraftforge.common.ForgeHooks.onFarmlandTrample(worldIn, pos, JBlocks.GOLDITE_DIRT.defaultBlockState(), fallDistance, entityIn)) { // Forge: Move logic to Entity#canTrample
            turnToDirt(worldIn.getBlockState(pos), worldIn, pos);
        }

        super.fallOn(worldIn, pos, entityIn, fallDistance);
    }

    public static void turnToDirt(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.setBlockAndUpdate(pos, pushEntitiesUp(state, JBlocks.GOLDITE_DIRT.defaultBlockState(), worldIn, pos));
    }

    private boolean isUnderCrops(BlockGetter worldIn, BlockPos pos) {
        BlockState plant = worldIn.getBlockState(pos.above());
        BlockState state = worldIn.getBlockState(pos);
        return plant.getBlock() instanceof net.minecraftforge.common.IPlantable && state.canSustainPlant(worldIn, pos, Direction.UP, (net.minecraftforge.common.IPlantable)plant.getBlock());
    }

    private static boolean isNearWater(LevelReader worldIn, BlockPos pos) {
        for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
            if (worldIn.getFluidState(blockpos).is(FluidTags.WATER)) {
                return true;
            }
        }

        return net.minecraftforge.common.FarmlandWaterManager.hasBlockWaterTicket(worldIn, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(MOISTURE);
    }

    public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
        return false;
    }
}
