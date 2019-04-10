package net.journey.dimension.corba;

import net.journey.dimension.DimensionHelper;
import net.minecraft.world.biome.BiomeProviderSingle;

public class BiomeProviderCorba extends BiomeProviderSingle {

	public BiomeProviderCorba() {
		super(DimensionHelper.corba);
		allowedBiomes.clear();
		allowedBiomes.add(DimensionHelper.corbaPlains);
		
		getBiomesToSpawnIn().clear();
		getBiomesToSpawnIn().add(DimensionHelper.corbaPlains);
	}
}