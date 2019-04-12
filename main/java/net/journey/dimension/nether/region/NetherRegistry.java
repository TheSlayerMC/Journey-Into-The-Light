package net.journey.dimension.nether.region;

import java.util.ArrayList;
import java.util.List;

import net.journey.dimension.nether.NetherBase;

public class NetherRegistry {

	public static NetherBase[] BiomeRegistry;
	static List<NetherBase> biomes = new ArrayList<NetherBase>();
	
	public static void init() {
		NetherBase heatsands = registerBiome(new RegionSandy("Heat Sands"), biomes);
	}
	
	private static NetherBase registerBiome(NetherBase biome, List<NetherBase> biomes) {
		biomes.add(biome);
		return biome;
	}
	
	public static NetherBase getBiomeID(int id) {
		return BiomeRegistry[id];
	}

	public static NetherBase[] getBiomes() {
		return BiomeRegistry;
	}
}
