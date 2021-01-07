package net.jitl.common.world.gen.features;

import com.mojang.serialization.Codec;
import net.jitl.init.JBlocks;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CaveVinesFeature extends Feature<NoFeatureConfig> {
    private static final Direction[] DIRECTIONS = Direction.values();

    public CaveVinesFeature(Codec<NoFeatureConfig> codec_) {
        super(codec_);
    }

    @Override
    public boolean place(ISeedReader reader, @NotNull ChunkGenerator generator, @NotNull Random rand, @NotNull BlockPos pos, @NotNull NoFeatureConfig config) {
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

    private void placeOnRoof(IWorld world, Random random, BlockPos blockPos) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (int i = 0; i < 100; ++i) {
            mutable.setWithOffset(blockPos,
                    random.nextInt(8) - random.nextInt(8),
                    random.nextInt(2) - random.nextInt(7),
                    random.nextInt(8) - random.nextInt(8));
            if (world.isEmptyBlock(mutable)) {
                BlockState blockstate = world.getBlockState(mutable.above());
                if (blockstate.is(Blocks.STONE)) {
                    int j = MathHelper.nextInt(random, 1, 2);
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

    public static void placeVines(IWorld world, Random random, BlockPos.Mutable mutable, int amount, int minAge, int maxAge) {
        for (int i = 0; i <= amount; ++i) {
            if (world.isEmptyBlock(mutable)) {
                if (i == amount || !world.isEmptyBlock(mutable.below())) {
                    world.setBlock(mutable, JBlocks.CAVE_VINES.defaultBlockState().setValue(AbstractTopPlantBlock.AGE, MathHelper.nextInt(random, minAge, maxAge)), 2);
                    break;
                }

                world.setBlock(mutable, JBlocks.CAVE_VINES_PLANT.defaultBlockState(), 2);
            }

            mutable.move(Direction.DOWN);
        }

    }
}
