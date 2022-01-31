package net.jitl.common.world.gen.features.featureconfig;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class FrostyIceClusterFeatureConfig implements FeatureConfiguration {
    public static final Codec<FrostyIceClusterFeatureConfig> CODEC = RecordCodecBuilder.create((instance_) -> {
        return instance_.group(Codec.intRange(1, 512).fieldOf("floor_to_ceiling_search_range").forGetter((FrostyIceClusterFeatureConfig_) -> {
            return FrostyIceClusterFeatureConfig_.floorToCeilingSearchRange;
        }), IntProvider.codec(1, 128).fieldOf("height").forGetter((FrostyIceClusterFeatureConfig1_) -> {
            return FrostyIceClusterFeatureConfig1_.height;
        }), IntProvider.codec(1, 128).fieldOf("radius").forGetter((FrostyIceClusterFeatureConfig3_) -> {
            return FrostyIceClusterFeatureConfig3_.radius;
        }), Codec.intRange(0, 64).fieldOf("max_stalagmite_stalactite_height_diff").forGetter((FrostyIceClusterFeatureConfig4_) -> {
            return FrostyIceClusterFeatureConfig4_.maxStalagmiteStalactiteHeightDiff;
        }), Codec.intRange(1, 64).fieldOf("height_deviation").forGetter((FrostyIceClusterFeatureConfig5_) -> {
            return FrostyIceClusterFeatureConfig5_.heightDeviation;
        }), IntProvider.codec(0, 128).fieldOf("dripstone_block_layer_thickness").forGetter((FrostyIceClusterFeatureConfig6_) -> {
            return FrostyIceClusterFeatureConfig6_.dripstoneBlockLayerThickness;
        }), FloatProvider.codec(0.0F, 2.0F).fieldOf("density").forGetter((FrostyIceClusterFeatureConfig7_) -> {
            return FrostyIceClusterFeatureConfig7_.density;
        }), FloatProvider.codec(0.0F, 2.0F).fieldOf("wetness").forGetter((FrostyIceClusterFeatureConfig8_) -> {
            return FrostyIceClusterFeatureConfig8_.wetness;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("chance_of_dripstone_column_at_max_distance_from_center").forGetter((FrostyIceClusterFeatureConfig9_) -> {
            return FrostyIceClusterFeatureConfig9_.chanceOfDripstoneColumnAtMaxDistanceFromCenter;
        }), Codec.intRange(1, 64).fieldOf("max_distance_from_edge_affecting_chance_of_dripstone_column").forGetter((FrostyIceClusterFeatureConfig10_) -> {
            return FrostyIceClusterFeatureConfig10_.maxDistanceFromEdgeAffectingChanceOfDripstoneColumn;
        }), Codec.intRange(1, 64).fieldOf("max_distance_from_center_affecting_height_bias").forGetter((FrostyIceClusterFeatureConfig2_) -> {
            return FrostyIceClusterFeatureConfig2_.maxDistanceFromCenterAffectingHeightBias;
        })).apply(instance_, FrostyIceClusterFeatureConfig::new);
    });
    public final int floorToCeilingSearchRange;
    public final IntProvider height;
    public final IntProvider radius;
    public final int maxStalagmiteStalactiteHeightDiff;
    public final int heightDeviation;
    public final IntProvider dripstoneBlockLayerThickness;
    public final FloatProvider density;
    public final FloatProvider wetness;
    public final float chanceOfDripstoneColumnAtMaxDistanceFromCenter;
    public final int maxDistanceFromEdgeAffectingChanceOfDripstoneColumn;
    public final int maxDistanceFromCenterAffectingHeightBias;

    public FrostyIceClusterFeatureConfig(int floorToCeilingSearchRange_, IntProvider height_, IntProvider radius_, int maxStalagmiteStalactiteHeightDiff_, int heightDeviation_, IntProvider dripstoneBlockLayerThickness_, FloatProvider desity_, FloatProvider wetness_, float chanceOfDripstoneColumnAtMaxDistanceFromCenter_, int maxDistanceFromEdgeAffectingChanceOfDripstoneColumn_, int maxDistanceFromCenterAffectingHeightBias_) {
        this.floorToCeilingSearchRange = floorToCeilingSearchRange_;
        this.height = height_;
        this.radius = radius_;
        this.maxStalagmiteStalactiteHeightDiff = maxStalagmiteStalactiteHeightDiff_;
        this.heightDeviation = heightDeviation_;
        this.dripstoneBlockLayerThickness = dripstoneBlockLayerThickness_;
        this.density = desity_;
        this.wetness = wetness_;
        this.chanceOfDripstoneColumnAtMaxDistanceFromCenter = chanceOfDripstoneColumnAtMaxDistanceFromCenter_;
        this.maxDistanceFromEdgeAffectingChanceOfDripstoneColumn = maxDistanceFromEdgeAffectingChanceOfDripstoneColumn_;
        this.maxDistanceFromCenterAffectingHeightBias = maxDistanceFromCenterAffectingHeightBias_;
    }
}