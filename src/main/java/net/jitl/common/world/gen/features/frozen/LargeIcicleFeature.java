package net.jitl.common.world.gen.features.frozen;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.features.IcicleUtils;
import net.jitl.common.world.gen.features.featureconfig.LargeIcicleFeatureConfig;
import net.jitl.core.init.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;

public class LargeIcicleFeature extends Feature<LargeIcicleFeatureConfig> {
    public LargeIcicleFeature(Codec<LargeIcicleFeatureConfig> codec_) {
        super(codec_);
    }

    @Override
    public boolean place(FeaturePlaceContext<LargeIcicleFeatureConfig> context_) {
        WorldGenLevel worldgenlevel = context_.level();
        BlockPos blockpos = context_.origin();
        LargeIcicleFeatureConfig LargeIcicleFeatureConfig = context_.config();
        Random random = context_.random();
        if (!IcicleUtils.isEmptyOrWater(worldgenlevel, blockpos)) {
            return false;
        } else {
            Optional<Column> optional = Column.scan(worldgenlevel, blockpos, LargeIcicleFeatureConfig.floorToCeilingSearchRange, IcicleUtils::isEmptyOrWater, IcicleUtils::isIcicleBaseOrLava);
            if (optional.isPresent() && optional.get() instanceof Column.Range column$range) {
                if (column$range.height() < 4) {
                    return false;
                } else {
                    int i = (int) ((float) column$range.height() * LargeIcicleFeatureConfig.maxColumnRadiusToCaveHeightRatio);
                    int j = Mth.clamp(i, LargeIcicleFeatureConfig.columnRadius.getMinValue(), LargeIcicleFeatureConfig.columnRadius.getMaxValue());
                    int k = Mth.randomBetweenInclusive(random, LargeIcicleFeatureConfig.columnRadius.getMinValue(), j);
                    LargeIcicleFeature.LargeIcicle LargeIcicleFeature$LargeIcicle = makeDripstone(blockpos.atY(column$range.ceiling() - 1), false, random, k, LargeIcicleFeatureConfig.stalactiteBluntness, LargeIcicleFeatureConfig.heightScale);
                    LargeIcicleFeature.LargeIcicle LargeIcicleFeature$LargeIcicle1 = makeDripstone(blockpos.atY(column$range.floor() + 1), true, random, k, LargeIcicleFeatureConfig.stalagmiteBluntness, LargeIcicleFeatureConfig.heightScale);
                    LargeIcicleFeature.WindOffsetter LargeIcicleFeature$windoffsetter;
                    if (LargeIcicleFeature$LargeIcicle.isSuitableForWind(LargeIcicleFeatureConfig) && LargeIcicleFeature$LargeIcicle1.isSuitableForWind(LargeIcicleFeatureConfig)) {
                        LargeIcicleFeature$windoffsetter = new LargeIcicleFeature.WindOffsetter(blockpos.getY(), random, LargeIcicleFeatureConfig.windSpeed);
                    } else {
                        LargeIcicleFeature$windoffsetter = LargeIcicleFeature.WindOffsetter.noWind();
                    }

                    boolean flag = LargeIcicleFeature$LargeIcicle.moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(worldgenlevel, LargeIcicleFeature$windoffsetter);
                    boolean flag1 = LargeIcicleFeature$LargeIcicle1.moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(worldgenlevel, LargeIcicleFeature$windoffsetter);
                    if (flag) {
                        LargeIcicleFeature$LargeIcicle.placeBlocks(worldgenlevel, random, LargeIcicleFeature$windoffsetter);
                    }

                    if (flag1) {
                        LargeIcicleFeature$LargeIcicle1.placeBlocks(worldgenlevel, random, LargeIcicleFeature$windoffsetter);
                    }

                    return true;
                }
            } else {
                return false;
            }
        }
    }

    private static LargeIcicleFeature.LargeIcicle makeDripstone(BlockPos root_, boolean pointingUp_, Random random_, int radius_, FloatProvider bluntnessBase_, FloatProvider scaleBase_) {
        return new LargeIcicleFeature.LargeIcicle(root_, pointingUp_, radius_, bluntnessBase_.sample(random_), scaleBase_.sample(random_));
    }

    static final class LargeIcicle {
        private BlockPos root;
        private final boolean pointingUp;
        private int radius;
        private final double bluntness;
        private final double scale;

        LargeIcicle(BlockPos root_, boolean pointingUp_, int radius_, double bluntness_, double scale_) {
            this.root = root_;
            this.pointingUp = pointingUp_;
            this.radius = radius_;
            this.bluntness = bluntness_;
            this.scale = scale_;
        }

        private int getHeight() {
            return this.getHeightAtRadius(0.0F);
        }

        private int getMinY() {
            return this.pointingUp ? this.root.getY() : this.root.getY() - this.getHeight();
        }

        private int getMaxY() {
            return !this.pointingUp ? this.root.getY() : this.root.getY() + this.getHeight();
        }

        boolean moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(WorldGenLevel level_, LargeIcicleFeature.WindOffsetter windOffsetter_) {
            while (this.radius > 1) {
                BlockPos.MutableBlockPos blockpos$mutableblockpos = this.root.mutable();
                int i = Math.min(10, this.getHeight());

                for (int j = 0; j < i; ++j) {
                    if (level_.getBlockState(blockpos$mutableblockpos).is(Blocks.LAVA)) {
                        return false;
                    }

                    if (IcicleUtils.isCircleMostlyEmbeddedInStone(level_, windOffsetter_.offset(blockpos$mutableblockpos), this.radius)) {
                        this.root = blockpos$mutableblockpos;
                        return true;
                    }

                    blockpos$mutableblockpos.move(this.pointingUp ? Direction.DOWN : Direction.UP);
                }

                this.radius /= 2;
            }

            return false;
        }

        private int getHeightAtRadius(float radius_) {
            return (int) IcicleUtils.getIcicleHeight(radius_, this.radius, this.scale, this.bluntness);
        }

        void placeBlocks(WorldGenLevel level_, Random random_, LargeIcicleFeature.WindOffsetter windOffsetter_) {
            for (int i = -this.radius; i <= this.radius; ++i) {
                for (int j = -this.radius; j <= this.radius; ++j) {
                    float f = Mth.sqrt((float) (i * i + j * j));
                    if (!(f > (float) this.radius)) {
                        int k = this.getHeightAtRadius(f);
                        if (k > 0) {
                            if ((double) random_.nextFloat() < 0.2D) {
                                k = (int) ((float) k * Mth.randomBetween(random_, 0.8F, 1.0F));
                            }

                            BlockPos.MutableBlockPos blockpos$mutableblockpos = this.root.offset(i, 0, j).mutable();
                            boolean flag = false;
                            int l = this.pointingUp ? level_.getHeight(Heightmap.Types.WORLD_SURFACE_WG, blockpos$mutableblockpos.getX(), blockpos$mutableblockpos.getZ()) : Integer.MAX_VALUE;

                            for (int i1 = 0; i1 < k && blockpos$mutableblockpos.getY() < l; ++i1) {
                                BlockPos blockpos = windOffsetter_.offset(blockpos$mutableblockpos);
                                if (IcicleUtils.isEmptyOrWaterOrLava(level_, blockpos)) {
                                    flag = true;
                                    Block block = JBlocks.FROSTY_ICE;
                                    level_.setBlock(blockpos, block.defaultBlockState(), 2);
                                } else if (flag && level_.getBlockState(blockpos) == JBlocks.PERMAFROST.defaultBlockState()) {
                                    break;
                                }

                                blockpos$mutableblockpos.move(this.pointingUp ? Direction.UP : Direction.DOWN);
                            }
                        }
                    }
                }
            }

        }

        boolean isSuitableForWind(LargeIcicleFeatureConfig config_) {
            return this.radius >= config_.minRadiusForWind && this.bluntness >= (double) config_.minBluntnessForWind;
        }
    }

    static final class WindOffsetter {
        private final int originY;
        @Nullable
        private final Vec3 windSpeed;

        WindOffsetter(int originY_, Random random_, FloatProvider magnitude_) {
            this.originY = originY_;
            float f = magnitude_.sample(random_);
            float f1 = Mth.randomBetween(random_, 0.0F, (float) Math.PI);
            this.windSpeed = new Vec3(Mth.cos(f1) * f, 0.0D, Mth.sin(f1) * f);
        }

        private WindOffsetter() {
            this.originY = 0;
            this.windSpeed = null;
        }

        static LargeIcicleFeature.WindOffsetter noWind() {
            return new LargeIcicleFeature.WindOffsetter();
        }

        BlockPos offset(BlockPos pos_) {
            if (this.windSpeed == null) {
                return pos_;
            } else {
                int i = this.originY - pos_.getY();
                Vec3 vec3 = this.windSpeed.scale(i);
                return pos_.offset(vec3.x, 0.0D, vec3.z);
            }
        }
    }
}