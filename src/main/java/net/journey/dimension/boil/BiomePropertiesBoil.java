package net.journey.dimension.boil;

import net.journey.dimension.DimensionHelper;
import net.minecraft.world.biome.Biome.BiomeProperties;

public class BiomePropertiesBoil extends BiomeProperties {
	
	public BiomePropertiesBoil() {
		super("Boiling Point");
		this.setBaseHeight(DimensionHelper.boilHeight[0]);
		this.setHeightVariation(DimensionHelper.boilHeight[1]);
		this.setRainfall(0.0F);
		this.setRainDisabled();
		this.setBaseBiome("boiling_point");
	}
}