package net.jitl.common.block.trees;

import net.jitl.core.init.world.JConfiguredFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BitterwoodTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getConfiguredFeature(@NotNull Random random, boolean largeHive_) {
        ConfiguredFeature tree = switch (random.nextInt(2)) {
            case 0 -> JConfiguredFeatures.LARGE_FROZEN_BITTERWOOOD_TREE.get();
            case 1 -> JConfiguredFeatures.MEDIUM_FROZEN_BITTERWOOOD_TREE.get();
            case 2 -> JConfiguredFeatures.SMALL_FROZEN_BITTERWOOOD_TREE.get();
            default -> JConfiguredFeatures.SMALL_FROZEN_BITTERWOOOD_TREE.get();
        };
        return tree;
    }
}
