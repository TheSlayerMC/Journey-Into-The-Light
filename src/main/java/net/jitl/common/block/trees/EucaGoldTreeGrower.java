package net.jitl.common.block.trees;

import net.jitl.core.init.world.JConfiguredFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class EucaGoldTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getConfiguredFeature(Random random_, boolean largeHive_) {
        return JConfiguredFeatures.EUCA_GOLD_TREES.get();
    }
}
