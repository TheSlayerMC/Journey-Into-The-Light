package net.journey.dimension.euca.biomes.properties;

import net.journey.dimension.base.DimensionHelper;
import net.minecraft.world.biome.Biome;

public class BiomePropertiesEucaSilver extends Biome.BiomeProperties {

    public BiomePropertiesEucaSilver() {
        super("Euca Silver");
        this.setRainfall(0.0F);
        this.setBaseHeight(DimensionHelper.CORBA_PLAINS_HEIGHT[0]);
        this.setHeightVariation(DimensionHelper.CORBA_PLAINS_HEIGHT[1]);
        this.setRainDisabled();
        this.setBaseBiome("euca");
    }
}