package net.journey.dimension.corba;

import net.journey.dimension.base.DimensionHelper;
import net.minecraft.world.biome.BiomeProviderSingle;

public class BiomeProviderCorba extends BiomeProviderSingle {

    public BiomeProviderCorba() {
        super(DimensionHelper.CORBA_BIOME);
	    allowedBiomes.clear();
	    allowedBiomes.add(DimensionHelper.CORBA_PLAINS_BIOME);

	    getBiomesToSpawnIn().clear();
	    getBiomesToSpawnIn().add(DimensionHelper.CORBA_PLAINS_BIOME);
    }
}