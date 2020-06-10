package net.journey.dimension.euca.biomes.properties;

import net.journey.dimension.base.DimensionHelper;
import net.minecraft.world.biome.Biome;

public class BiomePropertiesEuca extends Biome.BiomeProperties {

    public BiomePropertiesEuca() {
        super("Euca Forest");
        this.setRainfall(0.0F);
        this.setRainDisabled();
        this.setBaseBiome("euca");
    }
}