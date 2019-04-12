package net.journey.dimension.nether.biomes;

import java.util.ArrayList;
import java.util.List;

import net.journey.util.Config;

public class BiomeRegistry {
	
	/* Created by paulevs, from the Better Nether mod 
	 * Big thanks to him*/

	public static int biomeCount;
	public static NetherBiomeBase[] BiomeRegistry;
	public static NetherBiomeBase BIOME_HEATSANDS;

	public static void init() {
		List<NetherBiomeBase> biomes = new ArrayList<NetherBiomeBase>();
		
		BIOME_HEATSANDS = registerBiome(new NetherBiomeBase("Heatsands"), biomes);
		
		biomeCount = biomes.size();
		BiomeRegistry = new NetherBiomeBase[biomeCount];
		for (int i = 0; i < biomeCount; i++)
			BiomeRegistry[i] = biomes.get(i);
	}

	private static NetherBiomeBase registerBiome(NetherBiomeBase biome, List<NetherBiomeBase> biomes) {
		if (Config.overrideNether) {
			biomes.add(biome);
			return biome;
		} else
			return null;
	}

	private static NetherBiomeBase registerEdgeBiome(NetherBiomeBase biome, NetherBiomeBase mainBiome, int size) {
		if (Config.overrideNether && mainBiome != null) {
			mainBiome.setEdge(biome);
			mainBiome.setEdgeSize(size);
			return biome;
		} else
			return null;
	}

	private static NetherBiomeBase registerSubBiome(NetherBiomeBase biome, NetherBiomeBase mainBiome) {
		if (Config.overrideNether && mainBiome != null) {
			mainBiome.addSubBiome(biome);
			return biome;
		} else
			return null;
	}

	public static NetherBiomeBase getBiomeID(int id) {
		return BiomeRegistry[id];
	}

	public static NetherBiomeBase[] getBiomes() {
		return BiomeRegistry;
	}
}