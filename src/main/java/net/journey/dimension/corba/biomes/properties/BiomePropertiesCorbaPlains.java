package net.journey.dimension.corba.biomes.properties;

import net.journey.dimension.base.DimensionHelper;
import net.minecraft.world.biome.Biome.BiomeProperties;

public class BiomePropertiesCorbaPlains extends BiomeProperties {

    public BiomePropertiesCorbaPlains() {
        super("Corba Plains");
        this.setBaseHeight(DimensionHelper.CORBA_PLAINS_HEIGHT[0]);
        this.setHeightVariation(DimensionHelper.CORBA_PLAINS_HEIGHT[1]);
        this.setRainfall(0.0F);
        this.setRainDisabled();
        this.setBaseBiome("corba");
    }
}