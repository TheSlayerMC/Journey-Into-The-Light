package net.jitl.common.world.gen.features.boil;

import com.mojang.serialization.Codec;
import net.jitl.init.JBlocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class FlameBulbFeature extends Feature<NoneFeatureConfiguration> {
    public FlameBulbFeature(Codec<NoneFeatureConfiguration> codec_) {
        super(codec_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context_) {
        return place(context_.level(), context_.random(), context_.origin(), 8, /*tries*/8, /*spread*/4 /*max height*/);
    }

    public static boolean place(LevelAccessor world_, Random random_, BlockPos blockPos_, int int_, int int1_, int int2_) {
        if (isInvalidPlacementLocation(world_, blockPos_)) {
            return false;
        } else {
            placeTwistingVines(world_, random_, blockPos_, int_, int1_, int2_);
            return true;
        }
    }

    private static void placeTwistingVines(LevelAccessor world_, Random random_, BlockPos blockPos_, int int_, int int1_, int int2_) {
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

        for (int i = 0; i < int_ * int_; ++i) {
            blockpos$mutable.set(blockPos_).move(Mth.nextInt(random_, -int_, int_), Mth.nextInt(random_, -int1_, int1_), Mth.nextInt(random_, -int_, int_));
            if (findFirstAirBlockAboveGround(world_, blockpos$mutable) && !isInvalidPlacementLocation(world_, blockpos$mutable)) {
                int j = Mth.nextInt(random_, 1, int2_);
                if (random_.nextInt(6) == 0) {
                    j *= 2;
                }

                if (random_.nextInt(5) == 0) {
                    j = 1;
                }
                placeWeepingVinesColumn(world_, random_, blockpos$mutable, j, 17, 25);
            }
        }

    }

    private static boolean findFirstAirBlockAboveGround(LevelAccessor world_, BlockPos.MutableBlockPos mutable_) {
        do {
            mutable_.move(0, -1, 0);
            if (Level.isInSpawnableBounds(mutable_)) {
                return false;
            }
        } while (world_.getBlockState(mutable_).isAir());

        mutable_.move(0, 1, 0);
        return true;
    }

    public static void placeWeepingVinesColumn(LevelAccessor world_, Random random_, BlockPos.MutableBlockPos mutable_, int int_, int int1_, int int2_) {
        for (int i = 1; i <= int_; ++i) {
            if (world_.isEmptyBlock(mutable_)) {
                if (i == int_ || !world_.isEmptyBlock(mutable_.above())) {
                    world_.setBlock(mutable_, JBlocks.FLAME_BULB.defaultBlockState().setValue(GrowingPlantHeadBlock.AGE, Mth.nextInt(random_, int1_, int2_)), 2);
                    break;
                }

                world_.setBlock(mutable_, JBlocks.FLAME_BULB_PLANT.defaultBlockState(), 2);
            }

            mutable_.move(Direction.UP);
        }

    }

    private static boolean isInvalidPlacementLocation(LevelAccessor world_, BlockPos blockPos_) {
        if (!world_.isEmptyBlock(blockPos_)) {
            return true;
        } else {
            BlockState blockstate = world_.getBlockState(blockPos_.below());
            return !blockstate.is(JBlocks.CHARRED_GRASS);
        }
    }
}