package net.jitl.common.block.trees;

import ConfiguredFeature;
import net.jitl.init.JConfiguredFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class DyingFrozenTree extends JTree {
    /**
     * Get a {@link net.minecraft.world.gen.feature.ConfiguredFeature} of tree
     */
    @Nullable
    protected ConfiguredFeature<JBaseTreeFeatureConfig, ?> getConfiguredFeature(Random randomIn, boolean largeHive) {
        return (ConfiguredFeature<JBaseTreeFeatureConfig, ?>) JConfiguredFeatures.LARGE_FROZEN_TREE.get(); //TODO fix this...??
    }
}