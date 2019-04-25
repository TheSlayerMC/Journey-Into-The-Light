package net.journey.dimension.nether.biomes;

import java.util.ArrayList;
import java.util.List;

import net.journey.util.Config;

public class BiomeRegister {
	public static int biomeCount;
	public static NetherBiome[] BiomeRegistry;
	public static NetherBiome BIOME_EMPTY_NETHER;
	public static NetherBiome BIOME_TEST;
	public static NetherBiome BIOME_TEST_EDGE;

	public static void registerBiomes() {
		List<NetherBiome> biomes = new ArrayList<NetherBiome>();
		BIOME_EMPTY_NETHER = registerBiome(new NetherBiome("Empty Nether"), biomes);
		BIOME_TEST = registerBiome(new NetherBiomeTest("Test"), biomes);
		BIOME_TEST_EDGE = registerEdgeBiome(new NetherBiomeTestEdge("TEST Edge"), BIOME_TEST, 10);
		biomeCount = biomes.size();
		BiomeRegistry = new NetherBiome[biomeCount];
		for (int i = 0; i < biomeCount; i++)
			BiomeRegistry[i] = biomes.get(i);
	}

	private static NetherBiome registerBiome(NetherBiome biome, List<NetherBiome> biomes) {
		if (Config.mustInitBiome()) {
			biomes.add(biome);
			return biome;
		} else
			return null;
	}

	private static NetherBiome registerEdgeBiome(NetherBiome biome, NetherBiome mainBiome, int size) {
		if (Config.mustInitBiome() && mainBiome != null) {
			mainBiome.setEdge(biome);
			mainBiome.setEdgeSize(size);
			return biome;
		} else
			return null;
	}

	private static NetherBiome registerSubBiome(NetherBiome biome, NetherBiome mainBiome) {
		if (Config.mustInitBiome() && mainBiome != null) {
			mainBiome.addSubBiome(biome);
			return biome;
		} else
			return null;
	}

	public static NetherBiome getBiomeID(int id) {
		return BiomeRegistry[id];
	}

	public static NetherBiome[] getBiomes() {
		return BiomeRegistry;
	}
}
