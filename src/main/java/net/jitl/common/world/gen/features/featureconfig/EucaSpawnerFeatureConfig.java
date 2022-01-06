package net.jitl.common.world.gen.features.featureconfig;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public class EucaSpawnerFeatureConfig implements FeatureConfiguration {

    public static final Codec<EucaSpawnerFeatureConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(RuleTest.CODEC.fieldOf("spawn_block").forGetter((config -> config.spawnBlock)),
                    SimpleStateProvider.CODEC.fieldOf("spawner_provider").forGetter((config) -> config.spawnerBlock),
                    Codec.INT.fieldOf("min_height").forGetter((config) -> config.minHeight),
                    Codec.INT.fieldOf("max_height").forGetter((config) -> config.maxHeight)
            ).apply(instance, EucaSpawnerFeatureConfig::new));

    public final RuleTest spawnBlock;
    public final SimpleStateProvider spawnerBlock;
    public int minHeight;
    public int maxHeight;

    public EucaSpawnerFeatureConfig(RuleTest spawnBlock, SimpleStateProvider spawnerBlockProvider, int minHeight, int maxHeight) {
        this.spawnBlock = spawnBlock;
        this.spawnerBlock = spawnerBlockProvider;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }
}
