package net.jitl.common.world.gen.features.featureconfig;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public class EucaTreeFeatureConfig implements FeatureConfiguration {

    public static final Codec<EucaTreeFeatureConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(RuleTest.CODEC.fieldOf("spawn_block").forGetter((config -> config.spawnBlock)),
                    SimpleStateProvider.CODEC.fieldOf("leaf_provider").forGetter((config) -> config.leafBlock),
                    SimpleStateProvider.CODEC.fieldOf("log_provider").forGetter((config) -> config.logBlock),
                    Codec.INT.fieldOf("leaf_size").forGetter((config) -> config.leafSize),
                    Codec.INT.fieldOf("min_height").forGetter((config) -> config.minHeight),
                    Codec.INT.fieldOf("max_height").forGetter((config) -> config.maxHeight)
            ).apply(instance, EucaTreeFeatureConfig::new));

    public final RuleTest spawnBlock;
    public final SimpleStateProvider leafBlock;
    public final SimpleStateProvider logBlock;
    public int leafSize;
    public int minHeight;
    public int maxHeight;

    public EucaTreeFeatureConfig(RuleTest spawnBlock, SimpleStateProvider leafBlockProvider, SimpleStateProvider logBlockProvider, int leafSize, int minHeight, int maxHeight) {
        this.spawnBlock = spawnBlock;
        this.leafBlock = leafBlockProvider;
        this.logBlock = logBlockProvider;
        this.leafSize = leafSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }
}
