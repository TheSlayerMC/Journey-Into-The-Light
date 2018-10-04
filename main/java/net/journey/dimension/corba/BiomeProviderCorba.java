package net.journey.dimension.corba;

import net.journey.dimension.DimensionHelper;
import net.minecraft.world.biome.BiomeProviderSingle;

public class BiomeProviderCorba extends BiomeProviderSingle {

	public BiomeProviderCorba() {
		super(DimensionHelper.corba);
	}
}