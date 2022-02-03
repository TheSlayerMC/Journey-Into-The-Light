package net.jitl.common.world.gen.features.frozen;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.features.IcicleUtils;
import net.jitl.common.world.gen.features.featureconfig.FrostyIceClusterFeatureConfig;
import net.jitl.core.init.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;

public class FrostyIceClusterFeature extends Feature<FrostyIceClusterFeatureConfig> {
    public FrostyIceClusterFeature(Codec<FrostyIceClusterFeatureConfig> codec_) {
        super(codec_);
    }

    @Override
    public boolean place(FeaturePlaceContext<FrostyIceClusterFeatureConfig> context_) {
        WorldGenLevel worldgenlevel = context_.level();
        BlockPos blockpos = context_.origin();
        FrostyIceClusterFeatureConfig FrostyIceClusterFeatureConfig = context_.config();
        Random random = context_.random();
        if (!IcicleUtils.isEmptyOrWater(worldgenlevel, blockpos)) {
            return false;
        } else {
            int i = FrostyIceClusterFeatureConfig.height.sample(random);
            float f = FrostyIceClusterFeatureConfig.wetness.sample(random);
            float f1 = FrostyIceClusterFeatureConfig.density.sample(random);
            int j = FrostyIceClusterFeatureConfig.radius.sample(random);
            int k = FrostyIceClusterFeatureConfig.radius.sample(random);

            for (int l = -j; l <= j; ++l) {
                for (int i1 = -k; i1 <= k; ++i1) {
                    double d0 = this.getChanceOfStalagmiteOrStalactite(j, k, l, i1, FrostyIceClusterFeatureConfig);
                    BlockPos blockpos1 = blockpos.offset(l, 0, i1);
                    this.placeColumn(worldgenlevel, random, blockpos1, l, i1, f, d0, i, f1, FrostyIceClusterFeatureConfig);
                }
            }

            return true;
        }
    }

    private void placeColumn(WorldGenLevel level_, Random random_, BlockPos pos_, int x_, int z_, float wetness_, double chance_, int height_, float density_, FrostyIceClusterFeatureConfig config_) {
        Optional<Column> optional = Column.scan(level_, pos_, config_.floorToCeilingSearchRange, IcicleUtils::isEmptyOrWater, IcicleUtils::isIcicleBaseOrLava);
        if (optional.isPresent()) {
            OptionalInt optionalint = optional.get().getCeiling();
            OptionalInt optionalint1 = optional.get().getFloor();
            if (optionalint.isPresent() || optionalint1.isPresent()) {
                boolean flag = random_.nextFloat() < wetness_;
                Column column;
                if (flag && optionalint1.isPresent() && this.canPlacePool(level_, pos_.atY(optionalint1.getAsInt()))) {
                    int i = optionalint1.getAsInt();
                    column = optional.get().withFloor(OptionalInt.of(i - 1));
                    level_.setBlock(pos_.atY(i), Blocks.ICE.defaultBlockState(), 2);
                } else {
                    column = optional.get();
                }

                OptionalInt optionalint2 = column.getFloor();
                boolean flag1 = random_.nextDouble() < chance_;
                int j;
                if (optionalint.isPresent() && flag1 && !this.isLava(level_, pos_.atY(optionalint.getAsInt()))) {
                    int k = config_.dripstoneBlockLayerThickness.sample(random_);
                    this.replaceBlocksWithIcicleBlocks(level_, pos_.atY(optionalint.getAsInt()), k, Direction.UP);
                    int l;
                    if (optionalint2.isPresent()) {
                        l = Math.min(height_, optionalint.getAsInt() - optionalint2.getAsInt());
                    } else {
                        l = height_;
                    }

                    j = this.getIcicleHeight(random_, x_, z_, density_, l, config_);
                } else {
                    j = 0;
                }

                boolean flag2 = random_.nextDouble() < chance_;
                int i3;
                if (optionalint2.isPresent() && flag2 && !this.isLava(level_, pos_.atY(optionalint2.getAsInt()))) {
                    int i1 = config_.dripstoneBlockLayerThickness.sample(random_);
                    this.replaceBlocksWithIcicleBlocks(level_, pos_.atY(optionalint2.getAsInt()), i1, Direction.DOWN);
                    if (optionalint.isPresent()) {
                        i3 = Math.max(0, j + Mth.randomBetweenInclusive(random_, -config_.maxStalagmiteStalactiteHeightDiff, config_.maxStalagmiteStalactiteHeightDiff));
                    } else {
                        i3 = this.getIcicleHeight(random_, x_, z_, density_, height_, config_);
                    }
                } else {
                    i3 = 0;
                }

                int j1;
                int j3;
                if (optionalint.isPresent() && optionalint2.isPresent() && optionalint.getAsInt() - j <= optionalint2.getAsInt() + i3) {
                    int k1 = optionalint2.getAsInt();
                    int l1 = optionalint.getAsInt();
                    int i2 = Math.max(l1 - j, k1 + 1);
                    int j2 = Math.min(k1 + i3, l1 - 1);
                    int k2 = Mth.randomBetweenInclusive(random_, i2, j2 + 1);
                    int l2 = k2 - 1;
                    j3 = l1 - k2;
                    j1 = l2 - k1;
                } else {
                    j3 = j;
                    j1 = i3;
                }

                boolean flag3 = random_.nextBoolean() && j3 > 0 && j1 > 0 && column.getHeight().isPresent() && j3 + j1 == column.getHeight().getAsInt();
                if (optionalint.isPresent()) {
                    IcicleUtils.growPointedIcicle(level_, pos_.atY(optionalint.getAsInt() - 1), Direction.DOWN, j3, flag3);
                }

                if (optionalint2.isPresent()) {
                    IcicleUtils.growPointedIcicle(level_, pos_.atY(optionalint2.getAsInt() + 1), Direction.UP, j1, flag3);
                }

            }
        }
    }

    private boolean isLava(LevelReader level_, BlockPos pos_) {
        return level_.getBlockState(pos_).is(Blocks.LAVA);
    }

    private int getIcicleHeight(Random random_, int x_, int z_, float chance_, int height_, FrostyIceClusterFeatureConfig config_) {
        if (random_.nextFloat() > chance_) {
            return 0;
        } else {
            int i = Math.abs(x_) + Math.abs(z_);
            float f = (float) Mth.clampedMap(i, 0.0D, config_.maxDistanceFromCenterAffectingHeightBias, (double) height_ / 2.0D, 0.0D);
            return (int) randomBetweenBiased(random_, 0.0F, (float) height_, f, (float) config_.heightDeviation);
        }
    }

    private boolean canPlacePool(WorldGenLevel level_, BlockPos pos_) {
        BlockState blockstate = level_.getBlockState(pos_);
        if (!blockstate.is(Blocks.ICE) && !blockstate.is(JBlocks.FROSTY_ICE) && !blockstate.is(JBlocks.ICICLE)) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                if (!this.canBeAdjacentToWater(level_, pos_.relative(direction))) {
                    return false;
                }
            }

            return this.canBeAdjacentToWater(level_, pos_.below());
        } else {
            return false;
        }
    }

    private boolean canBeAdjacentToWater(LevelAccessor level_, BlockPos pos_) {
        BlockState blockstate = level_.getBlockState(pos_);
        return blockstate == JBlocks.PERMAFROST.defaultBlockState() || blockstate.getFluidState().is(FluidTags.WATER);
    }

    private void replaceBlocksWithIcicleBlocks(WorldGenLevel level_, BlockPos pos_, int thickness_, Direction direction_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos_.mutable();

        for (int i = 0; i < thickness_; ++i) {
            if (!IcicleUtils.placeIcicleBlockIfPossible(level_, blockpos$mutableblockpos)) {
                return;
            }

            blockpos$mutableblockpos.move(direction_);
        }

    }

    private double getChanceOfStalagmiteOrStalactite(int xRadius_, int zRadius_, int x_, int z_, FrostyIceClusterFeatureConfig config_) {
        int i = xRadius_ - Math.abs(x_);
        int j = zRadius_ - Math.abs(z_);
        int k = Math.min(i, j);
        return Mth.clampedMap((float) k, 0.0F, (float) config_.maxDistanceFromEdgeAffectingChanceOfDripstoneColumn, config_.chanceOfDripstoneColumnAtMaxDistanceFromCenter, 1.0F);
    }

    private static float randomBetweenBiased(Random random_, float min_, float max_, float mean_, float deviation_) {
        return ClampedNormalFloat.sample(random_, mean_, deviation_, min_, max_);
    }
}