package net.jitl.common.world.biome.nether;

import net.jitl.common.world.biome.base.JBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;

public class BloodForestBiome extends JBiome {

	//test
	@Override
	protected void setupBiome(Biome.Builder biomeBuilder) {
		biomeBuilder.temperature(1.0F)
				.depth(1.0F)
				.build();
	}

	@Override
	protected void setupGenSettings(BiomeGenerationSettings.Builder settingsBuilder) {
	}
}
