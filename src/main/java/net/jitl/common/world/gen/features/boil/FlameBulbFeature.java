package net.jitl.common.world.gen.features.boil;

import com.mojang.serialization.Codec;
import net.jitl.init.JBlocks;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class FlameBulbFeature extends Feature<NoFeatureConfig> {
    public FlameBulbFeature(Codec<NoFeatureConfig> codec_) {
        super(codec_);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        return place(reader, rand, pos, 8, /*tries*/8, /*spread*/4 /*max height*/);
    }

    public static boolean place(IWorld world_, Random random_, BlockPos blockPos_, int int_, int int1_, int int2_) {
        if (isInvalidPlacementLocation(world_, blockPos_)) {
            return false;
        } else {
            placeTwistingVines(world_, random_, blockPos_, int_, int1_, int2_);
            return true;
        }
    }

    private static void placeTwistingVines(IWorld world_, Random random_, BlockPos blockPos_, int int_, int int1_, int int2_) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for (int i = 0; i < int_ * int_; ++i) {
            blockpos$mutable.set(blockPos_).move(MathHelper.nextInt(random_, -int_, int_), MathHelper.nextInt(random_, -int1_, int1_), MathHelper.nextInt(random_, -int_, int_));
            if (findFirstAirBlockAboveGround(world_, blockpos$mutable) && !isInvalidPlacementLocation(world_, blockpos$mutable)) {
                int j = MathHelper.nextInt(random_, 1, int2_);
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

    private static boolean findFirstAirBlockAboveGround(IWorld world_, BlockPos.Mutable mutable_) {
        do {
            mutable_.move(0, -1, 0);
            if (World.isOutsideBuildHeight(mutable_)) {
                return false;
            }
        } while (world_.getBlockState(mutable_).isAir());

        mutable_.move(0, 1, 0);
        return true;
    }

    public static void placeWeepingVinesColumn(IWorld world_, Random random_, BlockPos.Mutable mutable_, int int_, int int1_, int int2_) {
        for (int i = 1; i <= int_; ++i) {
            if (world_.isEmptyBlock(mutable_)) {
                if (i == int_ || !world_.isEmptyBlock(mutable_.above())) {
                    world_.setBlock(mutable_, JBlocks.FLAME_BULB.defaultBlockState().setValue(AbstractTopPlantBlock.AGE, MathHelper.nextInt(random_, int1_, int2_)), 2);
                    break;
                }

                world_.setBlock(mutable_, JBlocks.FLAME_BULB_PLANT.defaultBlockState(), 2);
            }

            mutable_.move(Direction.UP);
        }

    }

    private static boolean isInvalidPlacementLocation(IWorld world_, BlockPos blockPos_) {
        if (!world_.isEmptyBlock(blockPos_)) {
            return true;
        } else {
            BlockState blockstate = world_.getBlockState(blockPos_.below());
            return !blockstate.is(JBlocks.CHARRED_GRASS);
        }
    }
}