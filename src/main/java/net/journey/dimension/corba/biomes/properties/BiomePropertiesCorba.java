package net.journey.dimension.corba.biomes.properties;

import net.journey.dimension.base.DimensionHelper;
import net.minecraft.world.biome.Biome.BiomeProperties;

public class BiomePropertiesCorba extends BiomeProperties {

    public BiomePropertiesCorba(String name) {
        super(name);
        this.setRainfall(0.0F);
        this.setRainDisabled();
        this.setBaseBiome(name);
    }
}