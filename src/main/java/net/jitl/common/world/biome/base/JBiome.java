package net.jitl.common.world.biome.base;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;

public class JBiome {

	protected void setupBiome(Biome.Builder biomeBuilder) {
	}

	protected void setupGenSettings(BiomeGenerationSettings.Builder settingsBuilder) {
	}


	//1.16 completely fucked biome creation
	//TODO: finish builder
	public Biome build() {
		Biome.Builder biomeBuilder = new Biome.Builder();
		BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder();

		setupBiome(biomeBuilder);
		setupGenSettings(genBuilder);

		return biomeBuilder.build();
	}
}
