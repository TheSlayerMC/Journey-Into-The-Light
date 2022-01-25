package net.jitl.common.block.trees;

import net.jitl.core.init.world.JConfiguredFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Random;

public class CharredTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getConfiguredFeature(@NotNull Random random, boolean largeHive_) {
        return switch (random.nextInt(2)) {
            case 0 -> JConfiguredFeatures.LARGE_CHARRED_TREE.get();
            case 1 -> JConfiguredFeatures.MEDIUM_BURNED_TREE.get();
            default -> JConfiguredFeatures.SMALL_BURNED_TREE.get();
        };
    }
}
