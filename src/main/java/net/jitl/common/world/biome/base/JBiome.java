package net.jitl.common.world.biome.base;

import net.minecraft.world.biome.Biome;

public class JBiome {

	//1.16 completely fucked biome creation
	//TODO: finish builder
	public Biome build() {
		Biome.Builder biomeBuilder = new Biome.Builder();
		return biomeBuilder.build();
	}
}
