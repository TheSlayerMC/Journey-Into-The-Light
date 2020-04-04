package net.journey.dimension.euca.layer;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import net.journey.dimension.DimensionHelper;
import net.journey.dimension.euca.biomes.BiomeGenEucaSilver;
import net.journey.util.Config;
import net.minecraft.init.Biomes;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.slayer.api.SlayerAPI;

public class GenLayerEucaBiome extends GenLayer {
	@SuppressWarnings("unchecked")
	private ArrayList[] biomes;

	public GenLayerEucaBiome(long l, GenLayer layer) {
		super(l);
		this.parent = layer;
		biomes = new ArrayList[2];
		ArrayList<BiomeEntry> eucaBiomes = new ArrayList<BiomeEntry>();
		eucaBiomes.add(new BiomeEntry(DimensionHelper.euca, Config.eucaBiome));
		eucaBiomes.add(new BiomeEntry(DimensionHelper.eucaSilver, Config.eucaSilverBiome));
		biomes[2] = eucaBiomes;
	}

	public GenLayerEucaBiome(long l) {
		super(l);
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth) {
		int[] dest = IntCache.getIntCache(width * depth);

		for (int dz = 0; dz < depth; dz++) {
			for (int dx = 0; dx < width; dx++) {
				this.initChunkSeed(dx + x, dz + z);
				try {
					dest[dx + dz * width] = Biome.getIdForBiome(new BiomeGenEucaSilver("eucaSilver"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return dest;
	}
}