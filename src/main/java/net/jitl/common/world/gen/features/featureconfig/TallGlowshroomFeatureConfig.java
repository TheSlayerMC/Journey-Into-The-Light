package net.jitl.common.world.gen.features.featureconfig;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;

public class TallGlowshroomFeatureConfig implements IFeatureConfig {
    public static final Codec<TallGlowshroomFeatureConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(RuleTest.CODEC.fieldOf("spawn_block").forGetter((TallGlowshroomFeatureConfig -> TallGlowshroomFeatureConfig.spawnBlock))
            ).apply(instance, TallGlowshroomFeatureConfig::new));

    public final RuleTest spawnBlock;

    public TallGlowshroomFeatureConfig(RuleTest spawnBlock) {
        this.spawnBlock = spawnBlock;
    }
}
