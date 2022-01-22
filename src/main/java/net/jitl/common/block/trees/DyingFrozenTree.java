package net.jitl.common.block.trees;

import net.jitl.core.init.world.JConfiguredFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Random;

public class DyingFrozenTree extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getConfiguredFeature(@NotNull Random random, boolean largeHive_) {
        ConfiguredFeature tree = switch (random.nextInt(2)) {
            case 0 -> JConfiguredFeatures.LARGE_FROZEN_TREE.get();
            case 1 -> JConfiguredFeatures.MEDIUM_FROZEN_TREE.get();
            case 2 -> JConfiguredFeatures.SMALL_FROZEN_TREE.get();
            default -> null;
        };
        return tree;
    }
}