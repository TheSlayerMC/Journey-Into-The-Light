package net.jitl.common.block.trees;

import net.jitl.init.world.JConfiguredFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;

public class DyingFrozenTree extends AbstractTreeGrower {


    @Nullable
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random randomIn, boolean largeHive) {
        return (ConfiguredFeature<TreeConfiguration, ?>) JConfiguredFeatures.BRADBERRY_BUSH.get(); //FIX Add back feature
    }
}