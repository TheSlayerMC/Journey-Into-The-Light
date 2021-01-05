package net.jitl.common.world.gen.features;

import com.mojang.serialization.Codec;
import net.jitl.common.world.gen.features.featureconfig.TallGlowshroomFeatureConfig;
import net.jitl.init.JBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.util.RandHelper;

import java.util.Random;

public class TallGlowshroomFeature extends Feature<TallGlowshroomFeatureConfig> {
    public TallGlowshroomFeature(Codec<TallGlowshroomFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(@NotNull ISeedReader reader, @NotNull ChunkGenerator generator, @NotNull Random rand, @NotNull BlockPos pos, @NotNull TallGlowshroomFeatureConfig config) {
        if (config.spawnBlock.test(reader.getBlockState(pos.below()), rand) && reader.isEmptyBlock(pos) && reader.isEmptyBlock(pos.above())) {
            System.out.println("good surface");

            BlockState blockState = RandHelper.chooseEqually(rand,
                    JBlocks.RED_GLOWSHROOM.defaultBlockState(),
                    JBlocks.BLUE_GLOWSHROOM.defaultBlockState(),
                    JBlocks.BLUE_GLOWSHROOM.defaultBlockState());
            System.out.println("generating");
            reader.setBlock(pos, blockState, 2 | 16);
            return true;
        } else {
            System.out.println("not good surface");
            return false;
        }
    }
}
