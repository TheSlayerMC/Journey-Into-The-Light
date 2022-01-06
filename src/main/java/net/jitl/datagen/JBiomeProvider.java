package net.jitl.datagen;

import net.minecraft.data.worldgen.biome.BiomeReport;
import net.minecraft.data.DataGenerator;

public class JBiomeProvider extends BiomeReport {

	//biome json format: https://minecraft.gamepedia.com/Custom_world_generation

	public JBiomeProvider(DataGenerator dataGenerator) {
		super(dataGenerator);
	}
}
