package net.journey.dimension.terrania;

import net.journey.dimension.DimensionHelper;
import net.minecraft.world.biome.BiomeProviderSingle;

public class BiomeProviderTerrania extends BiomeProviderSingle {

	public BiomeProviderTerrania() {
		super(DimensionHelper.terrania);
	}
}