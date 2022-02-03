package net.jitl.common.world.gen.features.frozen;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.features.IcicleUtils;
import net.jitl.common.world.gen.features.featureconfig.IcicleFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.Optional;
import java.util.Random;

public class IcicleFeature extends Feature<IcicleFeatureConfig> {
    public IcicleFeature(Codec<IcicleFeatureConfig> codec_) {
        super(codec_);
    }

    @Override
    public boolean place(FeaturePlaceContext<IcicleFeatureConfig> featurePlaceContext_) {
        LevelAccessor levelaccessor = featurePlaceContext_.level();
        BlockPos blockpos = featurePlaceContext_.origin();
        Random random = featurePlaceContext_.random();
        IcicleFeatureConfig IcicleFeatureConfig = featurePlaceContext_.config();
        Optional<Direction> optional = getTipDirection(levelaccessor, blockpos, random);
        if (optional.isEmpty()) {
            return false;
        } else {
            BlockPos blockpos1 = blockpos.relative(optional.get().getOpposite());
            createPatchOfDripstoneBlocks(levelaccessor, random, blockpos1, IcicleFeatureConfig);
            int i = random.nextFloat() < IcicleFeatureConfig.chanceOfTallerDripstone && IcicleUtils.isEmptyOrWater(levelaccessor.getBlockState(blockpos.relative(optional.get()))) ? 2 : 1;
            IcicleUtils.growPointedIcicle(levelaccessor, blockpos, optional.get(), i, false);
            return true;
        }
    }

    private static Optional<Direction> getTipDirection(LevelAccessor world_, BlockPos pos_, Random random_) {
        boolean flag = IcicleUtils.isIcicleBase(world_.getBlockState(pos_.above()));
        boolean flag1 = IcicleUtils.isIcicleBase(world_.getBlockState(pos_.below()));
        if (flag && flag1) {
            return Optional.of(random_.nextBoolean() ? Direction.DOWN : Direction.UP);
        } else if (flag) {
            return Optional.of(Direction.DOWN);
        } else {
            return flag1 ? Optional.of(Direction.UP) : Optional.empty();
        }
    }

    private static void createPatchOfDripstoneBlocks(LevelAccessor world_, Random random_, BlockPos pos_, IcicleFeatureConfig config_) {
        IcicleUtils.placeIcicleBlockIfPossible(world_, pos_);

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (!(random_.nextFloat() > config_.chanceOfDirectionalSpread)) {
                BlockPos blockpos = pos_.relative(direction);
                IcicleUtils.placeIcicleBlockIfPossible(world_, blockpos);
                if (!(random_.nextFloat() > config_.chanceOfSpreadRadius2)) {
                    BlockPos blockpos1 = blockpos.relative(Direction.getRandom(random_));
                    IcicleUtils.placeIcicleBlockIfPossible(world_, blockpos1);
                    if (!(random_.nextFloat() > config_.chanceOfSpreadRadius3)) {
                        BlockPos blockpos2 = blockpos1.relative(Direction.getRandom(random_));
                        IcicleUtils.placeIcicleBlockIfPossible(world_, blockpos2);
                    }
                }
            }
        }

    }
}
