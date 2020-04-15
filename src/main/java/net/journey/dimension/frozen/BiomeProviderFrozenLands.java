package net.journey.dimension.frozen;

import net.journey.dimension.DimensionHelper;
import net.minecraft.world.biome.BiomeProviderSingle;

public class BiomeProviderFrozenLands extends BiomeProviderSingle {

	public BiomeProviderFrozenLands() {
		super(DimensionHelper.frozen);
	}
}