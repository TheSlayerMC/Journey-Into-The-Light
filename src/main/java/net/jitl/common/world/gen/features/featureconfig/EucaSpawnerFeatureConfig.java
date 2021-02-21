package net.jitl.common.world.gen.features.featureconfig;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;

public class EucaSpawnerFeatureConfig implements IFeatureConfig {

    public static final Codec<EucaSpawnerFeatureConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(RuleTest.CODEC.fieldOf("spawn_block").forGetter((config -> config.spawnBlock)),
                    SimpleBlockStateProvider.CODEC.fieldOf("spawner_provider").forGetter((config) -> config.spawnerBlock),
                    Codec.INT.fieldOf("min_height").forGetter((config) -> config.minHeight),
                    Codec.INT.fieldOf("max_height").forGetter((config) -> config.maxHeight)
            ).apply(instance, EucaSpawnerFeatureConfig::new));

    public final RuleTest spawnBlock;
    public final SimpleBlockStateProvider spawnerBlock;
    public int minHeight;
    public int maxHeight;

    public EucaSpawnerFeatureConfig(RuleTest spawnBlock, SimpleBlockStateProvider spawnerBlockProvider, int minHeight, int maxHeight) {
        this.spawnBlock = spawnBlock;
        this.spawnerBlock = spawnerBlockProvider;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }
}
