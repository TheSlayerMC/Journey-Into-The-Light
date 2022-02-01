package net.jitl.common.world.gen.features.featureconfig;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class LargeIcicleFeatureConfig implements FeatureConfiguration {
    public static final Codec<LargeIcicleFeatureConfig> CODEC = RecordCodecBuilder.create((instance_) -> {
        return instance_.group(Codec.intRange(1, 512).fieldOf("floor_to_ceiling_search_range").orElse(30).forGetter((LargeIcicleFeatureConfig_) -> {
            return LargeIcicleFeatureConfig_.floorToCeilingSearchRange;
        }), IntProvider.codec(1, 60).fieldOf("column_radius").forGetter((LargeIcicleFeatureConfig1_) -> {
            return LargeIcicleFeatureConfig1_.columnRadius;
        }), FloatProvider.codec(0.0F, 20.0F).fieldOf("height_scale").forGetter((LargeIcicleFeatureConfig2_) -> {
            return LargeIcicleFeatureConfig2_.heightScale;
        }), Codec.floatRange(0.1F, 1.0F).fieldOf("max_column_radius_to_cave_height_ratio").forGetter((LargeIcicleFeatureConfig3_) -> {
            return LargeIcicleFeatureConfig3_.maxColumnRadiusToCaveHeightRatio;
        }), FloatProvider.codec(0.1F, 10.0F).fieldOf("stalactite_bluntness").forGetter((LargeIcicleFeatureConfig4_) -> {
            return LargeIcicleFeatureConfig4_.stalactiteBluntness;
        }), FloatProvider.codec(0.1F, 10.0F).fieldOf("stalagmite_bluntness").forGetter((LargeIcicleFeatureConfig5_) -> {
            return LargeIcicleFeatureConfig5_.stalagmiteBluntness;
        }), FloatProvider.codec(0.0F, 2.0F).fieldOf("wind_speed").forGetter((LargeIcicleFeatureConfig6_) -> {
            return LargeIcicleFeatureConfig6_.windSpeed;
        }), Codec.intRange(0, 100).fieldOf("min_radius_for_wind").forGetter((LargeIcicleFeatureConfig7_) -> {
            return LargeIcicleFeatureConfig7_.minRadiusForWind;
        }), Codec.floatRange(0.0F, 5.0F).fieldOf("min_bluntness_for_wind").forGetter((LargeIcicleFeatureConfig8_) -> {
            return LargeIcicleFeatureConfig8_.minBluntnessForWind;
        })).apply(instance_, LargeIcicleFeatureConfig::new);
    });
    public final int floorToCeilingSearchRange;
    public final IntProvider columnRadius;
    public final FloatProvider heightScale;
    public final float maxColumnRadiusToCaveHeightRatio;
    public final FloatProvider stalactiteBluntness;
    public final FloatProvider stalagmiteBluntness;
    public final FloatProvider windSpeed;
    public final int minRadiusForWind;
    public final float minBluntnessForWind;

    public LargeIcicleFeatureConfig(int floorToCeilingSearchRange_, IntProvider columnRadius_, FloatProvider heightScale_, float maxColumnRadiusToCaveHeightRatio_, FloatProvider stalactiteBluntness_, FloatProvider stalagmiteBluntness_, FloatProvider windSpeed_, int minRadiusForWind_, float minBluntnessForWind_) {
        this.floorToCeilingSearchRange = floorToCeilingSearchRange_;
        this.columnRadius = columnRadius_;
        this.heightScale = heightScale_;
        this.maxColumnRadiusToCaveHeightRatio = maxColumnRadiusToCaveHeightRatio_;
        this.stalactiteBluntness = stalactiteBluntness_;
        this.stalagmiteBluntness = stalagmiteBluntness_;
        this.windSpeed = windSpeed_;
        this.minRadiusForWind = minRadiusForWind_;
        this.minBluntnessForWind = minBluntnessForWind_;
    }
}