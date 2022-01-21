package net.jitl.common.world.gen.features;

import com.mojang.serialization.Codec;
import net.jitl.core.init.JBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

@Deprecated
public class CaveVinesFeature extends Feature<NoneFeatureConfiguration> {
    private static final Direction[] DIRECTIONS = Direction.values();

    public CaveVinesFeature(Codec<NoneFeatureConfiguration> codec_) {
        super(codec_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel reader = context.level();
        Random rand = context.random();
        if (!reader.isEmptyBlock(pos)) {
            return false;
        } else {
            BlockState blockstate = reader.getBlockState(pos.above());
            if (!blockstate.is(Blocks.STONE)) {
                return false;
            } else {
                this.placeOnRoof(reader, rand, pos);
                return true;
            }
        }
    }

    private void placeOnRoof(LevelAccessor world, Random random, BlockPos blockPos) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int i = 0; i < 100; ++i) {
            mutable.setWithOffset(blockPos,
                    random.nextInt(8) - random.nextInt(8),
                    random.nextInt(2) - random.nextInt(7),
                    random.nextInt(8) - random.nextInt(8));
            if (world.isEmptyBlock(mutable)) {
                BlockState blockstate = world.getBlockState(mutable.above());
                if (blockstate.is(Blocks.STONE)) {
                    int j = Mth.nextInt(random, 1, 2);
                    if (random.nextInt(6) == 0) {
                        j *= 2;
                    }

                    if (random.nextInt(5) == 0) {
                        j = 1;
                    }
                    placeVines(world, random, mutable, j, 17, 25);
                }
            }
        }

    }

    public static void placeVines(LevelAccessor world, Random random, BlockPos.MutableBlockPos mutable, int amount, int minAge, int maxAge) {
        for (int i = 0; i <= amount; ++i) {
            if (world.isEmptyBlock(mutable)) {
                if (i == amount || !world.isEmptyBlock(mutable.below())) {
                    world.setBlock(mutable, JBlocks.DEEPVINE.defaultBlockState().setValue(GrowingPlantHeadBlock.AGE, Mth.nextInt(random, minAge, maxAge)), 2);
                    break;
                }

                world.setBlock(mutable, JBlocks.DEEPVINE_PLANT.defaultBlockState(), 2);
            }

            mutable.move(Direction.DOWN);
        }

    }
}
