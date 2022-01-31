package net.jitl.common.world.gen.features;

import net.jitl.common.block.IcicleBlock;
import net.jitl.core.JITL;
import net.jitl.core.init.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;

import java.util.function.Consumer;

public class IcicleUtils {
    public static double getIcicleHeight(double radius_, double maxRadius_, double scale_, double minRadius_) {
        if (radius_ < minRadius_) {
            radius_ = minRadius_;
        }

        double d0 = 0.384D;
        double d1 = radius_ / maxRadius_ * 0.384D;
        double d2 = 0.75D * Math.pow(d1, 1.3333333333333333D);
        double d3 = Math.pow(d1, 0.6666666666666666D);
        double d4 = 0.3333333333333333D * Math.log(d1);
        double d5 = scale_ * (d2 - d3 - d4);
        d5 = Math.max(d5, 0.0D);
        return d5 / 0.384D * maxRadius_;
    }

    public static boolean isCircleMostlyEmbeddedInStone(WorldGenLevel level_, BlockPos pos_, int radius_) {
        if (isEmptyOrWaterOrLava(level_, pos_)) {
            return false;
        } else {
            float f = 6.0F;
            float f1 = 6.0F / (float) radius_;

            for (float f2 = 0.0F; f2 < ((float) Math.PI * 2F); f2 += f1) {
                int i = (int) (Mth.cos(f2) * (float) radius_);
                int j = (int) (Mth.sin(f2) * (float) radius_);
                if (isEmptyOrWaterOrLava(level_, pos_.offset(i, 0, j))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isEmptyOrWater(LevelAccessor level_, BlockPos pos_) {
        return level_.isStateAtPosition(pos_, IcicleUtils::isEmptyOrWater);
    }

    public static boolean isEmptyOrWaterOrLava(LevelAccessor level_, BlockPos pos_) {
        return level_.isStateAtPosition(pos_, IcicleUtils::isEmptyOrWaterOrLava);
    }

    public static void buildBaseToTipColumn(Direction direction_, int height_, boolean mergeTip_, Consumer<BlockState> blockSetter_) {
        if (height_ >= 3) {
            blockSetter_.accept(createPointedIcicle(direction_, DripstoneThickness.BASE));

            for (int i = 0; i < height_ - 3; ++i) {
                blockSetter_.accept(createPointedIcicle(direction_, DripstoneThickness.MIDDLE));
            }
        }

        if (height_ >= 2) {
            blockSetter_.accept(createPointedIcicle(direction_, DripstoneThickness.FRUSTUM));
        }

        if (height_ >= 1) {
            blockSetter_.accept(createPointedIcicle(direction_, mergeTip_ ? DripstoneThickness.TIP_MERGE : DripstoneThickness.TIP));
        }

    }

    public static void growPointedIcicle(LevelAccessor level_, BlockPos pos_, Direction direction_, int height_, boolean mergeTip_) {
        if (isIcicleBase(level_.getBlockState(pos_.relative(direction_.getOpposite())))) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = pos_.mutable();
            buildBaseToTipColumn(direction_, height_, mergeTip_, (state3_) -> {
                if (state3_.is(JBlocks.ICICLE)) {
                    state3_ = state3_.setValue(IcicleBlock.WATERLOGGED, level_.isWaterAt(blockpos$mutableblockpos));
                }

                level_.setBlock(blockpos$mutableblockpos, state3_, 2);
                blockpos$mutableblockpos.move(direction_);
            });
        }
    }

    public static boolean placeIcicleBlockIfPossible(LevelAccessor level_, BlockPos pos_) {
        BlockState blockstate = level_.getBlockState(pos_);
        JITL.LOGGER.info("Trying to place");
        if (blockstate == JBlocks.PERMAFROST.defaultBlockState()) {
            JITL.LOGGER.info("can place");
            level_.setBlock(pos_, JBlocks.FROSTY_ICE.defaultBlockState(), 2);
            return true;
        } else {
            return false;
        }
    }

    private static BlockState createPointedIcicle(Direction direction_, DripstoneThickness DripstoneThickness_) {
        return JBlocks.ICICLE.defaultBlockState().setValue(IcicleBlock.TIP_DIRECTION, direction_).setValue(IcicleBlock.THICKNESS, DripstoneThickness_);
    }

    public static boolean isIcicleBaseOrLava(BlockState state_) {
        return isIcicleBase(state_) || state_.is(Blocks.LAVA);
    }

    public static boolean isIcicleBase(BlockState state_) {
        return state_.is(JBlocks.FROSTY_ICE) || state_ == JBlocks.PERMAFROST.defaultBlockState();
    }

    public static boolean isEmptyOrWater(BlockState state1_) {
        return state1_.isAir() || state1_.is(Blocks.WATER);
    }

    public static boolean isEmptyOrWaterOrLava(BlockState state2_) {
        return state2_.isAir() || state2_.is(Blocks.WATER) || state2_.is(Blocks.LAVA);
    }
}