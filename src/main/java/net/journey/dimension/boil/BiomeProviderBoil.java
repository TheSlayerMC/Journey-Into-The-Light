package net.journey.dimension.boil;

import net.journey.dimension.DimensionHelper;
import net.minecraft.world.biome.BiomeProviderSingle;

public class BiomeProviderBoil extends BiomeProviderSingle {

	public BiomeProviderBoil() {
		super(DimensionHelper.boiling);
	}
}