package net.jitl.common.world.gen.features.featureconfig;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class IcicleFeatureConfig implements FeatureConfiguration {
    public static final Codec<IcicleFeatureConfig> CODEC = RecordCodecBuilder.create((instance_) -> {
        return instance_.group(Codec.floatRange(0.0F, 1.0F).fieldOf("chance_of_taller_dripstone").orElse(0.2F).forGetter((IcicleFeatureConfig_) -> {
            return IcicleFeatureConfig_.chanceOfTallerDripstone;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("chance_of_directional_spread").orElse(0.7F).forGetter((IcicleFeatureConfig1_) -> {
            return IcicleFeatureConfig1_.chanceOfDirectionalSpread;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("chance_of_spread_radius2").orElse(0.5F).forGetter((IcicleFeatureConfig2_) -> {
            return IcicleFeatureConfig2_.chanceOfSpreadRadius2;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("chance_of_spread_radius3").orElse(0.5F).forGetter((IcicleFeatureConfig3_) -> {
            return IcicleFeatureConfig3_.chanceOfSpreadRadius3;
        })).apply(instance_, IcicleFeatureConfig::new);
    });
    public final float chanceOfTallerDripstone;
    public final float chanceOfDirectionalSpread;
    public final float chanceOfSpreadRadius2;
    public final float chanceOfSpreadRadius3;

    public IcicleFeatureConfig(float chanceOfTallerDripstone_, float chanceOfDirectionalSpread_, float chanceOfSpreadRadius2_, float chanceOfSpreadRadius3_) {
        this.chanceOfTallerDripstone = chanceOfTallerDripstone_;
        this.chanceOfDirectionalSpread = chanceOfDirectionalSpread_;
        this.chanceOfSpreadRadius2 = chanceOfSpreadRadius2_;
        this.chanceOfSpreadRadius3 = chanceOfSpreadRadius3_;
    }
}