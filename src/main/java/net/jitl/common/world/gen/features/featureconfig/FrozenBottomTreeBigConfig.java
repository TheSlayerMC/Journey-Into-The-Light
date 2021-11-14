package net.jitl.common.world.gen.features.featureconfig;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;

public class FrozenBottomTreeBigConfig implements IFeatureConfig {

    public static final Codec<FrozenBottomTreeBigConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(RuleTest.CODEC.fieldOf("spawn_block").forGetter((config -> config.spawnBlock)),
                    SimpleBlockStateProvider.CODEC.fieldOf("leaf_provider").forGetter((config) -> config.leafBlock),
                    SimpleBlockStateProvider.CODEC.fieldOf("log_provider").forGetter((config) -> config.logBlock),
                    Codec.INT.fieldOf("leaf_size").forGetter((config) -> config.leafSize),
                    Codec.INT.fieldOf("min_height").forGetter((config) -> config.minHeight),
                    Codec.INT.fieldOf("max_height").forGetter((config) -> config.maxHeight)
            ).apply(instance, FrozenBottomTreeBigConfig::new));

    public final RuleTest spawnBlock;
    public final SimpleBlockStateProvider leafBlock;
    public final SimpleBlockStateProvider logBlock;
    public int leafSize;
    public int minHeight;
    public int maxHeight;

    public FrozenBottomTreeBigConfig(RuleTest spawnBlock, SimpleBlockStateProvider leafBlockProvider, SimpleBlockStateProvider logBlockProvider, int leafSize, int minHeight, int maxHeight) {
        this.spawnBlock = spawnBlock;
        this.leafBlock = leafBlockProvider;
        this.logBlock = logBlockProvider;
        this.leafSize = leafSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }
}
