package net.jitl.common.block;

import com.google.common.annotations.VisibleForTesting;
import net.jitl.core.init.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

public class IcicleBlock extends Block implements Fallable, SimpleWaterloggedBlock {
    public static final DirectionProperty TIP_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;
    public static final EnumProperty<DripstoneThickness> THICKNESS = BlockStateProperties.DRIPSTONE_THICKNESS;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final int MAX_SEARCH_LENGTH_WHEN_CHECKING_DRIP_TYPE = 11;
    private static final int MAX_SEARCH_LENGTH_WHEN_LOOKING_FOR_TIP_OF_FALLING_STALACTITE = Integer.MAX_VALUE;
    private static final int DELAY_BEFORE_FALLING = 2;
    private static final float DRIP_PROBABILITY_PER_ANIMATE_TICK = 0.02F;
    private static final float DRIP_PROBABILITY_PER_ANIMATE_TICK_IF_UNDER_LIQUID_SOURCE = 0.12F;
    private static final int MAX_SEARCH_LENGTH_BETWEEN_STALACTITE_TIP_AND_CAULDRON = 11;
    private static final float WATER_CAULDRON_FILL_PROBABILITY_PER_RANDOM_TICK = 0.17578125F;
    private static final float LAVA_CAULDRON_FILL_PROBABILITY_PER_RANDOM_TICK = 0.05859375F;
    private static final double MIN_TRIDENT_VELOCITY_TO_BREAK_DRIPSTONE = 0.6D;
    private static final float STALACTITE_DAMAGE_PER_FALL_DISTANCE_AND_SIZE = 1.0F;
    private static final int STALACTITE_MAX_DAMAGE = 40;
    private static final int MAX_STALACTITE_HEIGHT_FOR_DAMAGE_CALCULATION = 6;
    private static final float STALAGMITE_FALL_DISTANCE_OFFSET = 2.0F;
    private static final int STALAGMITE_FALL_DAMAGE_MODIFIER = 2;
    private static final float AVERAGE_DAYS_PER_GROWTH = 5.0F;
    private static final float GROWTH_PROBABILITY_PER_RANDOM_TICK = 0.011377778F;
    private static final int MAX_GROWTH_LENGTH = 7;
    private static final int MAX_STALAGMITE_SEARCH_RANGE_WHEN_GROWING = 10;
    private static final float STALACTITE_DRIP_START_PIXEL = 0.6875F;
    private static final VoxelShape TIP_MERGE_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    private static final VoxelShape TIP_SHAPE_UP = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 11.0D, 11.0D);
    private static final VoxelShape TIP_SHAPE_DOWN = Block.box(5.0D, 5.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    private static final VoxelShape FRUSTUM_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    private static final VoxelShape MIDDLE_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    private static final VoxelShape BASE_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private static final float MAX_HORIZONTAL_OFFSET = 0.125F;

    public IcicleBlock(BlockBehaviour.Properties properties_) {
        super(properties_);
        this.registerDefaultState(this.stateDefinition.any().setValue(TIP_DIRECTION, Direction.UP).setValue(THICKNESS, DripstoneThickness.TIP).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder_) {
        builder_.add(TIP_DIRECTION, THICKNESS, WATERLOGGED);
    }

    @Override
    public boolean canSurvive(BlockState state_, LevelReader level_, BlockPos pos_) {
        return isValidPointedDripstonePlacement(level_, pos_, state_.getValue(TIP_DIRECTION));
    }

    @Override
    public BlockState updateShape(BlockState state_, Direction direction_, BlockState neighborState_, LevelAccessor level_, BlockPos currentPos_, BlockPos neighborPos_) {
        if (state_.getValue(WATERLOGGED)) {
            level_.scheduleTick(currentPos_, Fluids.WATER, Fluids.WATER.getTickDelay(level_));
        }

        if (direction_ != Direction.UP && direction_ != Direction.DOWN) {
            return state_;
        } else {
            Direction direction = state_.getValue(TIP_DIRECTION);
            if (direction == Direction.DOWN && level_.getBlockTicks().hasScheduledTick(currentPos_, this)) {
                return state_;
            } else if (direction_ == direction.getOpposite() && !this.canSurvive(state_, level_, currentPos_)) {
                if (direction == Direction.DOWN) {
                    this.scheduleStalactiteFallTicks(state_, level_, currentPos_);
                } else {
                    level_.scheduleTick(currentPos_, this, 1);
                }

                return state_;
            } else {
                boolean flag = state_.getValue(THICKNESS) == DripstoneThickness.TIP_MERGE;
                DripstoneThickness dripstonethickness = calculateDripstoneThickness(level_, currentPos_, direction, flag);
                return state_.setValue(THICKNESS, dripstonethickness);
            }
        }
    }

    @Override
    public void onProjectileHit(Level level_, BlockState state_, BlockHitResult hit_, Projectile projectile_) {
        BlockPos blockpos = hit_.getBlockPos();
        if (!level_.isClientSide && projectile_.mayInteract(level_, blockpos) && projectile_ instanceof ThrownTrident && projectile_.getDeltaMovement().length() > 0.6D) {
            level_.destroyBlock(blockpos, true);
        }

    }

    @Override
    public void fallOn(Level level_, BlockState state_, BlockPos pos_, Entity entity_, float float_) {
        if (state_.getValue(TIP_DIRECTION) == Direction.UP && state_.getValue(THICKNESS) == DripstoneThickness.TIP) {
            entity_.causeFallDamage(float_ + 2.0F, 2.0F, DamageSource.STALAGMITE);
        } else {
            super.fallOn(level_, state_, pos_, entity_, float_);
        }

    }

    @Override
    public void tick(BlockState state_, ServerLevel level_, BlockPos pos_, Random random_) {
        if (isStalagmite(state_) && !this.canSurvive(state_, level_, pos_)) {
            level_.destroyBlock(pos_, true);
        } else {
            spawnFallingStalactite(state_, level_, pos_);
        }

    }

    @Override
    public void randomTick(BlockState state_, ServerLevel level_, BlockPos pos_, Random random_) {
        maybeFillCauldron(state_, level_, pos_, random_.nextFloat());
        if (random_.nextFloat() < 0.011377778F && isStalactiteStartPos(state_, level_, pos_)) {
            growStalactiteOrStalagmiteIfPossible(state_, level_, pos_, random_);
        }

    }

    public static void maybeFillCauldron(BlockState state_, ServerLevel level_, BlockPos pos_, float dripChance_) {
        if (!(dripChance_ > 0.17578125F) || !(dripChance_ > 0.05859375F)) {
            if (isStalactiteStartPos(state_, level_, pos_)) {
                Fluid fluid = getCauldronFillFluidType(level_, pos_);
                float f;
                if (fluid == Fluids.WATER) {
                    f = 0.17578125F;
                } else {
                    if (fluid != Fluids.LAVA) {
                        return;
                    }

                    f = 0.05859375F;
                }

                if (!(dripChance_ >= f)) {
                    BlockPos blockpos = findTip(state_, level_, pos_, 11, false);
                    if (blockpos != null) {
                        BlockPos blockpos1 = findFillableCauldronBelowStalactiteTip(level_, blockpos, fluid);
                        if (blockpos1 != null) {
                            level_.levelEvent(1504, blockpos, 0);
                            int i = blockpos.getY() - blockpos1.getY();
                            int j = 50 + i;
                            BlockState blockstate = level_.getBlockState(blockpos1);
                            level_.scheduleTick(blockpos1, blockstate.getBlock(), j);
                        }
                    }
                }
            }
        }
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state_) {
        return PushReaction.DESTROY;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context_) {
        LevelAccessor levelaccessor = context_.getLevel();
        BlockPos blockpos = context_.getClickedPos();
        Direction direction = context_.getNearestLookingVerticalDirection().getOpposite();
        Direction direction1 = calculateTipDirection(levelaccessor, blockpos, direction);
        if (direction1 == null) {
            return null;
        } else {
            boolean flag = !context_.isSecondaryUseActive();
            DripstoneThickness dripstonethickness = calculateDripstoneThickness(levelaccessor, blockpos, direction1, flag);
            return dripstonethickness == null ? null : this.defaultBlockState().setValue(TIP_DIRECTION, direction1).setValue(THICKNESS, dripstonethickness).setValue(WATERLOGGED, levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER);
        }
    }

    @Override
    public FluidState getFluidState(BlockState state_) {
        return state_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state_);
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state_, BlockGetter level_, BlockPos pos_) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState state_, BlockGetter level_, BlockPos pos_, CollisionContext context_) {
        DripstoneThickness dripstonethickness = state_.getValue(THICKNESS);
        VoxelShape voxelshape;
        if (dripstonethickness == DripstoneThickness.TIP_MERGE) {
            voxelshape = TIP_MERGE_SHAPE;
        } else if (dripstonethickness == DripstoneThickness.TIP) {
            if (state_.getValue(TIP_DIRECTION) == Direction.DOWN) {
                voxelshape = TIP_SHAPE_DOWN;
            } else {
                voxelshape = TIP_SHAPE_UP;
            }
        } else if (dripstonethickness == DripstoneThickness.FRUSTUM) {
            voxelshape = FRUSTUM_SHAPE;
        } else if (dripstonethickness == DripstoneThickness.MIDDLE) {
            voxelshape = MIDDLE_SHAPE;
        } else {
            voxelshape = BASE_SHAPE;
        }

        Vec3 vec3 = state_.getOffset(level_, pos_);
        return voxelshape.move(vec3.x, 0.0D, vec3.z);
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState state_, BlockGetter level_, BlockPos pos_) {
        return false;
    }

    @Override
    public BlockBehaviour.OffsetType getOffsetType() {
        return BlockBehaviour.OffsetType.XZ;
    }

    @Override
    public float getMaxHorizontalOffset() {
        return 0.125F;
    }

    @Override
    public void onBrokenAfterFall(Level level_, BlockPos pos_, FallingBlockEntity fallingBlock_) {
        if (!fallingBlock_.isSilent()) {
            level_.levelEvent(1045, pos_, 0);
        }

    }

    @Override
    public DamageSource getFallDamageSource() {
        return DamageSource.FALLING_STALACTITE;
    }

    @Override
    public Predicate<Entity> getHurtsEntitySelector() {
        return EntitySelector.NO_CREATIVE_OR_SPECTATOR.and(EntitySelector.LIVING_ENTITY_STILL_ALIVE);
    }

    private void scheduleStalactiteFallTicks(BlockState state_, LevelAccessor level_, BlockPos pos_) {
        BlockPos blockpos = findTip(state_, level_, pos_, Integer.MAX_VALUE, true);
        if (blockpos != null) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = blockpos.mutable();
            blockpos$mutableblockpos.move(Direction.DOWN);
            BlockState blockstate = level_.getBlockState(blockpos$mutableblockpos);
            if (blockstate.getCollisionShape(level_, blockpos$mutableblockpos, CollisionContext.empty()).max(Direction.Axis.Y) >= 1.0D || blockstate.is(Blocks.POWDER_SNOW)) {
                level_.destroyBlock(blockpos, true);
                blockpos$mutableblockpos.move(Direction.UP);
            }

            blockpos$mutableblockpos.move(Direction.UP);

            while (isStalactite(level_.getBlockState(blockpos$mutableblockpos))) {
                level_.scheduleTick(blockpos$mutableblockpos, this, 2);
                blockpos$mutableblockpos.move(Direction.UP);
            }

        }
    }

    private static int getStalactiteSizeFromTip(ServerLevel level_, BlockPos pos_, int range_) {
        int i = 1;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos_.mutable().move(Direction.UP);

        while (i < range_ && isStalactite(level_.getBlockState(blockpos$mutableblockpos))) {
            ++i;
            blockpos$mutableblockpos.move(Direction.UP);
        }

        return i;
    }

    private static void spawnFallingStalactite(BlockState state_, ServerLevel level_, BlockPos pos_) {
        Vec3 vec3 = Vec3.atBottomCenterOf(pos_);
        FallingBlockEntity fallingblockentity = new FallingBlockEntity(level_, vec3.x, vec3.y, vec3.z, state_);
        if (isTip(state_, true)) {
            int i = getStalactiteSizeFromTip(level_, pos_, 6);
            float f = 1.0F * (float) i;
            fallingblockentity.setHurtsEntities(f, 40);
        }

        level_.addFreshEntity(fallingblockentity);
    }

    @VisibleForTesting
    public static void growStalactiteOrStalagmiteIfPossible(BlockState state_, ServerLevel level_, BlockPos pos_, Random random_) {
        BlockState blockstate = level_.getBlockState(pos_.above(1));
        BlockState blockstate1 = level_.getBlockState(pos_.above(2));
        if (canGrow(blockstate, blockstate1)) {
            BlockPos blockpos = findTip(state_, level_, pos_, 7, false);
            if (blockpos != null) {
                BlockState blockstate2 = level_.getBlockState(blockpos);
                if (canDrip(blockstate2) && canTipGrow(blockstate2, level_, blockpos)) {
                    if (random_.nextBoolean()) {
                        grow(level_, blockpos, Direction.DOWN);
                    } else {
                        growStalagmiteBelow(level_, blockpos);
                    }

                }
            }
        }
    }

    private static void growStalagmiteBelow(ServerLevel level_, BlockPos pos_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos_.mutable();

        for (int i = 0; i < 10; ++i) {
            blockpos$mutableblockpos.move(Direction.DOWN);
            BlockState blockstate = level_.getBlockState(blockpos$mutableblockpos);
            if (!blockstate.getFluidState().isEmpty()) {
                return;
            }

            if (isUnmergedTipWithDirection(blockstate, Direction.UP) && canTipGrow(blockstate, level_, blockpos$mutableblockpos)) {
                grow(level_, blockpos$mutableblockpos, Direction.UP);
                return;
            }

            if (isValidPointedDripstonePlacement(level_, blockpos$mutableblockpos, Direction.UP) && !level_.isWaterAt(blockpos$mutableblockpos.below())) {
                grow(level_, blockpos$mutableblockpos.below(), Direction.UP);
                return;
            }
        }

    }

    private static void grow(ServerLevel server_, BlockPos pos_, Direction direction_) {
        BlockPos blockpos = pos_.relative(direction_);
        BlockState blockstate = server_.getBlockState(blockpos);
        if (isUnmergedTipWithDirection(blockstate, direction_.getOpposite())) {
            createMergedTips(blockstate, server_, blockpos);
        } else if (blockstate.isAir() || blockstate.is(Blocks.WATER)) {
            createDripstone(server_, blockpos, direction_, DripstoneThickness.TIP);
        }

    }

    private static void createDripstone(LevelAccessor level_, BlockPos pos_, Direction direction_, DripstoneThickness thickness_) {
        BlockState blockstate = JBlocks.ICICLE.defaultBlockState().setValue(TIP_DIRECTION, direction_).setValue(THICKNESS, thickness_).setValue(WATERLOGGED, level_.getFluidState(pos_).getType() == Fluids.WATER);
        level_.setBlock(pos_, blockstate, 3);
    }

    private static void createMergedTips(BlockState state_, LevelAccessor level_, BlockPos pos_) {
        BlockPos blockpos;
        BlockPos blockpos1;
        if (state_.getValue(TIP_DIRECTION) == Direction.UP) {
            blockpos1 = pos_;
            blockpos = pos_.above();
        } else {
            blockpos = pos_;
            blockpos1 = pos_.below();
        }

        createDripstone(level_, blockpos, Direction.DOWN, DripstoneThickness.TIP_MERGE);
        createDripstone(level_, blockpos1, Direction.UP, DripstoneThickness.TIP_MERGE);
    }
    @Nullable
    private static BlockPos findTip(BlockState state_, LevelAccessor level_, BlockPos pos_, int range_, boolean allowMerged_) {
        if (isTip(state_, allowMerged_)) {
            return pos_;
        } else {
            Direction direction = state_.getValue(TIP_DIRECTION);
            Predicate<BlockState> predicate = (state5_) -> state5_.is(JBlocks.ICICLE) && state5_.getValue(TIP_DIRECTION) == direction;
            return findBlockVertical(level_, pos_, direction.getAxisDirection(), predicate, (state6_) -> isTip(state6_, allowMerged_), range_).orElse(null);
        }
    }

    @Nullable
    private static Direction calculateTipDirection(LevelReader level_, BlockPos pos_, Direction direction_) {
        Direction direction;
        if (isValidPointedDripstonePlacement(level_, pos_, direction_)) {
            direction = direction_;
        } else {
            if (!isValidPointedDripstonePlacement(level_, pos_, direction_.getOpposite())) {
                return null;
            }

            direction = direction_.getOpposite();
        }

        return direction;
    }

    private static DripstoneThickness calculateDripstoneThickness(LevelReader level_, BlockPos pos_, Direction direction_, boolean tryMerge_) {
        Direction direction = direction_.getOpposite();
        BlockState blockstate = level_.getBlockState(pos_.relative(direction_));
        if (isPointedDripstoneWithDirection(blockstate, direction)) {
            return !tryMerge_ && blockstate.getValue(THICKNESS) != DripstoneThickness.TIP_MERGE ? DripstoneThickness.TIP : DripstoneThickness.TIP_MERGE;
        } else if (!isPointedDripstoneWithDirection(blockstate, direction_)) {
            return DripstoneThickness.TIP;
        } else {
            DripstoneThickness dripstonethickness = blockstate.getValue(THICKNESS);
            if (dripstonethickness != DripstoneThickness.TIP && dripstonethickness != DripstoneThickness.TIP_MERGE) {
                BlockState blockstate1 = level_.getBlockState(pos_.relative(direction));
                return !isPointedDripstoneWithDirection(blockstate1, direction_) ? DripstoneThickness.BASE : DripstoneThickness.MIDDLE;
            } else {
                return DripstoneThickness.FRUSTUM;
            }
        }
    }

    public static boolean canDrip(BlockState state1_) {
        return isStalactite(state1_) && state1_.getValue(THICKNESS) == DripstoneThickness.TIP && !state1_.getValue(WATERLOGGED);
    }

    private static boolean canTipGrow(BlockState state_, ServerLevel level_, BlockPos pos_) {
        Direction direction = state_.getValue(TIP_DIRECTION);
        BlockPos blockpos = pos_.relative(direction);
        BlockState blockstate = level_.getBlockState(blockpos);
        if (!blockstate.getFluidState().isEmpty()) {
            return false;
        } else {
            return blockstate.isAir() || isUnmergedTipWithDirection(blockstate, direction.getOpposite());
        }
    }

    private static Optional<BlockPos> findRootBlock(Level level_, BlockPos pos_, BlockState state_, int range_) {
        Direction direction = state_.getValue(TIP_DIRECTION);
        Predicate<BlockState> predicate = (state3_) -> state3_.is(JBlocks.ICICLE) && state3_.getValue(TIP_DIRECTION) == direction;
        return findBlockVertical(level_, pos_, direction.getOpposite().getAxisDirection(), predicate, (state4_) -> !state4_.is(JBlocks.ICICLE), range_);
    }

    private static boolean isValidPointedDripstonePlacement(LevelReader level_, BlockPos pos_, Direction direction_) {
        BlockPos blockpos = pos_.relative(direction_.getOpposite());
        BlockState blockstate = level_.getBlockState(blockpos);
        return blockstate.isFaceSturdy(level_, blockpos, direction_) || isPointedDripstoneWithDirection(blockstate, direction_);
    }

    private static boolean isTip(BlockState state_, boolean allowMerged_) {
        if (!state_.is(JBlocks.ICICLE)) {
            return false;
        } else {
            DripstoneThickness dripstonethickness = state_.getValue(THICKNESS);
            return dripstonethickness == DripstoneThickness.TIP || allowMerged_ && dripstonethickness == DripstoneThickness.TIP_MERGE;
        }
    }

    private static boolean isUnmergedTipWithDirection(BlockState state_, Direction direction_) {
        return isTip(state_, false) && state_.getValue(TIP_DIRECTION) == direction_;
    }

    private static boolean isStalactite(BlockState state_) {
        return isPointedDripstoneWithDirection(state_, Direction.DOWN);
    }

    private static boolean isStalagmite(BlockState state_) {
        return isPointedDripstoneWithDirection(state_, Direction.UP);
    }

    private static boolean isStalactiteStartPos(BlockState state_, LevelReader level_, BlockPos pos_) {
        return isStalactite(state_) && !level_.getBlockState(pos_.above()).is(JBlocks.ICICLE);
    }

    public boolean isPathfindable(BlockState state_, BlockGetter level_, BlockPos pos_, PathComputationType type_) {
        return false;
    }

    private static boolean isPointedDripstoneWithDirection(BlockState state_, Direction direction_) {
        return state_.is(JBlocks.ICICLE) && state_.getValue(TIP_DIRECTION) == direction_;
    }

    @Nullable
    private static BlockPos findFillableCauldronBelowStalactiteTip(Level level_, BlockPos pos_, Fluid fluid_) {
        Predicate<BlockState> predicate = (state2_) -> {
            return state2_.getBlock() instanceof AbstractCauldronBlock /* && ((AbstractCauldronBlock)state2_.getBlock()).canReceiveStalactiteDrip(fluid_)*/;
        };
        return findBlockVertical(level_, pos_, Direction.DOWN.getAxisDirection(), BlockBehaviour.BlockStateBase::isAir, predicate, 11).orElse(null);
    }

    @Nullable
    public static BlockPos findStalactiteTipAboveCauldron(Level level_, BlockPos pos_) {
        return findBlockVertical(level_, pos_, Direction.UP.getAxisDirection(), BlockBehaviour.BlockStateBase::isAir, IcicleBlock::canDrip, 11).orElse(null);
    }

    public static Fluid getCauldronFillFluidType(Level level_, BlockPos pos_) {
        return getFluidAboveStalactite(level_, pos_, level_.getBlockState(pos_)).filter(IcicleBlock::canFillCauldron).orElse(Fluids.EMPTY);
    }

    private static Optional<Fluid> getFluidAboveStalactite(Level level_, BlockPos pos_, BlockState state_) {
        return !isStalactite(state_) ? Optional.empty() : findRootBlock(level_, pos_, state_, 11).map((pos1_) -> {
            return level_.getFluidState(pos1_.above()).getType();
        });
    }

    /**
     * {@return whether the provided {@code fluid} is liquid, namely lava or water}
     */
    private static boolean canFillCauldron(Fluid fluid1_) {
        return fluid1_ == Fluids.LAVA || fluid1_ == Fluids.WATER;
    }

    private static boolean canGrow(BlockState dripstoneBlockState_, BlockState waterState_) {
        return dripstoneBlockState_.is(Blocks.DRIPSTONE_BLOCK) && waterState_.is(Blocks.WATER) && waterState_.getFluidState().isSource();
    }

    private static Fluid getDripFluid(Level level_, Fluid fluid_) {
        if (fluid_.isSame(Fluids.EMPTY)) {
            return level_.dimensionType().ultraWarm() ? Fluids.LAVA : Fluids.WATER;
        } else {
            return fluid_;
        }
    }

    private static Optional<BlockPos> findBlockVertical(LevelAccessor level_, BlockPos pos_, Direction.AxisDirection direction_, Predicate<BlockState> continuePredicate_, Predicate<BlockState> stopPredicate_, int range_) {
        Direction direction = Direction.get(direction_, Direction.Axis.Y);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos_.mutable();

        for (int i = 1; i < range_; ++i) {
            blockpos$mutableblockpos.move(direction);
            BlockState blockstate = level_.getBlockState(blockpos$mutableblockpos);
            if (stopPredicate_.test(blockstate)) {
                return Optional.of(blockpos$mutableblockpos.immutable());
            }

            if (level_.isOutsideBuildHeight(blockpos$mutableblockpos.getY()) || !continuePredicate_.test(blockstate)) {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }
}