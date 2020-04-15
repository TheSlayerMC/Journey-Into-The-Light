package net.journey.dimension.corba;

import net.journey.dimension.DimensionHelper;
import net.minecraft.world.biome.Biome.BiomeProperties;

public class BiomePropertiesCorba extends BiomeProperties {

    public BiomePropertiesCorba() {
        super("Corba");
        this.setBaseHeight(DimensionHelper.corbaHeight[0]);
        this.setHeightVariation(DimensionHelper.corbaHeight[1]);
        this.setRainfall(0.0F);
        this.setRainDisabled();
        this.setBaseBiome("corba");
    }
}