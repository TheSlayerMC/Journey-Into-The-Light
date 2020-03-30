package net.journey.dimension.euca;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.journey.dimension.DimensionHelper;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;

public class BiomeProviderEuca extends BiomeProvider {

	public static List<Biome> eucaBiomes = Lists.newArrayList(DimensionHelper.euca, DimensionHelper.eucaSilver);
	public static Biome[] eucaBiomes1 = new Biome[] {DimensionHelper.euca, DimensionHelper.eucaSilver};

	public BiomeProviderEuca() {
		super(new WorldInfoEuca());
		allowedBiomes = eucaBiomes;
	}

	@Override
	public List<Biome> getBiomesToSpawnIn() {
		return eucaBiomes;
	}

	public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height)
	{
		if (biomes == null || biomes.length < width * height)
		{
			biomes = new Biome[width * height];
		}

		int check = new Random().nextInt(2);
		if(check == 0)
			Arrays.fill(biomes, 0, width * height, this.eucaBiomes1[0]);
		if(check == 1)
			Arrays.fill(biomes, 0, width * height, this.eucaBiomes1[1]);
		return biomes;
	}

	public Biome[] getBiomes(@Nullable Biome[] oldBiomeList, int x, int z, int width, int depth)
	{
		if (oldBiomeList == null || oldBiomeList.length < width * depth)
		{
			oldBiomeList = new Biome[width * depth];
		}

		int check = new Random().nextInt(2);
		if(check == 0)
			Arrays.fill(oldBiomeList, 0, width * depth, this.eucaBiomes1[0]);
		if(check == 1)
			Arrays.fill(oldBiomeList, 0, width * depth, this.eucaBiomes1[1]);
		return oldBiomeList;
	}

	public Biome[] getBiomes(@Nullable Biome[] listToReuse, int x, int z, int width, int length, boolean cacheFlag)
	{
		return this.getBiomes(listToReuse, x, z, width, length);
	}
}