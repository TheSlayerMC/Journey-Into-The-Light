package net.jitl.common.world.gen.foliageplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.jitl.init.JFoliagePlacers;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.util.UniformInt;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;

public class SphericalFoliagePlacer extends BlobFoliagePlacer {

    public static final Codec<SphericalFoliagePlacer> CODEC = RecordCodecBuilder.create((instance_) -> {
        return blobParts(instance_).apply(instance_, SphericalFoliagePlacer::new);
    });

    public SphericalFoliagePlacer(UniformInt featureSpread_, UniformInt featureSpread1_, int int_) {
        super(featureSpread_, featureSpread1_, int_);
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return JFoliagePlacers.SPHERICAL_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> blockSetter_, Random rand, TreeConfiguration baseTreeFeatureConfig_, int maxFreeTreeHeight_, FoliageAttachment foliage_, int foliageHeight_, int foliageRadius_, int offset_) {
        int size = foliageRadius_ + foliage_.radiusOffset();
        BlockPos pos = foliage_.pos().above(offset_);
        pos = pos.offset(Direction.UP.getNormal());
        for (byte x = 0; x <= size; x++) {
            for (byte y = 0; y <= size; y++) {
                for (byte z = 0; z <= size; z++) {

                    int distance;

                    if (x >= y & x >= z) {
                        distance = x + (Math.max(y, z) >> 1) + (Math.min(y, z) >> 1);
                    } else if (y >= x & y >= z) {
                        distance = y + (Math.max(x, z) >> 1) + (Math.min(x, z) >> 1);
                    } else {
                        distance = z + (Math.max(x, y) >> 1) + (Math.min(x, y) >> 1);
                    }

                    if (distance <= size) {
                        placeLeavesRow(reader, blockSetter_, rand, baseTreeFeatureConfig_, pos.offset(+x, +y, +z), foliageHeight_, offset_, false);
                        placeLeavesRow(reader, blockSetter_, rand, baseTreeFeatureConfig_, pos.offset(+x, +y, -z), foliageHeight_, offset_, false);
                        placeLeavesRow(reader, blockSetter_, rand, baseTreeFeatureConfig_, pos.offset(-x, +y, +z), foliageHeight_, offset_, false);
                        placeLeavesRow(reader, blockSetter_, rand, baseTreeFeatureConfig_, pos.offset(-x, +y, -z), foliageHeight_, offset_, false);
                        placeLeavesRow(reader, blockSetter_, rand, baseTreeFeatureConfig_, pos.offset(+x, -y, +z), foliageHeight_, offset_, false);
                        placeLeavesRow(reader, blockSetter_, rand, baseTreeFeatureConfig_, pos.offset(+x, -y, -z), foliageHeight_, offset_, false);
                        placeLeavesRow(reader, blockSetter_, rand, baseTreeFeatureConfig_, pos.offset(-x, -y, +z), foliageHeight_, offset_, false);
                        placeLeavesRow(reader, blockSetter_, rand, baseTreeFeatureConfig_, pos.offset(-x, -y, -z), foliageHeight_, offset_, false);
                    }
                }
            }
        }
    }

    @Override
    public int foliageHeight(@NotNull Random random_, int int_, @NotNull TreeConfiguration baseTreeFeatureConfig_) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull Random random_, int int_, int int1_, int int2_, int int3_, boolean boolean_) {
        if (int1_ == 0) {
            return (int_ > 1 || int2_ > 1) && int_ != 0 && int2_ != 0;
        } else {
            return int_ == int3_ && int2_ == int3_ && int3_ > 0;
        }
    }
}
