package net.jitl.init.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;

public class JCaveCarverConfiguration extends CaveCarverConfiguration {
    public static final Codec<JCaveCarverConfiguration> CODEC = RecordCodecBuilder.create((instance_) -> {
        return instance_.group(CarverConfiguration.CODEC.forGetter((caveCarverConfiguration_) -> {
            return caveCarverConfiguration_;
        }), FloatProvider.CODEC.fieldOf("horizontal_radius_multiplier").forGetter((caveCarverConfiguration1_) -> {
            return caveCarverConfiguration1_.horizontalRadiusMultiplier;
        }), FloatProvider.CODEC.fieldOf("vertical_radius_multiplier").forGetter((caveCarverConfiguration2_) -> {
            return caveCarverConfiguration2_.verticalRadiusMultiplier;
        }), FloatProvider.codec(-1.0F, 1.0F).fieldOf("floor_level").forGetter((caveCarverConfiguration3_) -> {
            return caveCarverConfiguration3_.floorLevel;
        })).apply(instance_, JCaveCarverConfiguration::new);
    });
    public final FloatProvider horizontalRadiusMultiplier;
    public final FloatProvider verticalRadiusMultiplier;
    public final FloatProvider floorLevel;

    public JCaveCarverConfiguration(float probability_, HeightProvider y_, FloatProvider yScale_, VerticalAnchor lavaLevel_, CarverDebugSettings debugSettings_, FloatProvider horizontalRadiusMultiplier_, FloatProvider verticalRadiusMultiplier_, FloatProvider floorLevel_) {
        super(probability_, y_, yScale_, lavaLevel_, debugSettings_, horizontalRadiusMultiplier_, verticalRadiusMultiplier_, floorLevel_);
        this.horizontalRadiusMultiplier = horizontalRadiusMultiplier_;
        this.verticalRadiusMultiplier = verticalRadiusMultiplier_;
        this.floorLevel = floorLevel_;
    }

    public JCaveCarverConfiguration(float probability_, HeightProvider y_, FloatProvider yScale_, VerticalAnchor lavaLevel_, boolean aquifersEnabled_, FloatProvider horizontalRadiusMultiplier_, FloatProvider verticalRadiusMultiplier_, FloatProvider floorLevel_) {
        this(probability_, y_, yScale_, lavaLevel_, CarverDebugSettings.DEFAULT, horizontalRadiusMultiplier_, verticalRadiusMultiplier_, floorLevel_);
    }

    public JCaveCarverConfiguration(CarverConfiguration config_, FloatProvider horizontalRadiusMultiplier1_, FloatProvider verticalRadiusMultiplier1_, FloatProvider floorLevel1_) {
        this(config_.probability, config_.y, config_.yScale, config_.lavaLevel, config_.debugSettings, horizontalRadiusMultiplier1_, verticalRadiusMultiplier1_, floorLevel1_);
    }
}