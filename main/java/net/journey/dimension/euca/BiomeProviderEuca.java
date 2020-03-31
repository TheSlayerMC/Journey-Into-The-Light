package net.journey.dimension.euca;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.journey.dimension.DimensionHelper;
import net.journey.util.Helper;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class BiomeProviderEuca extends BiomeProvider {

	public static List<Biome> eucaBiomes = Lists.newArrayList(DimensionHelper.euca, DimensionHelper.eucaSilver);
	public static Biome[] eucaBiomes1 = new Biome[] {DimensionHelper.euca, DimensionHelper.eucaSilver};

	public BiomeProviderEuca() {
		super(new WorldInfoEuca());
		allowedBiomes.clear();
		getBiomesToSpawnIn().clear();
		
		allowedBiomes.add(DimensionHelper.euca);
		allowedBiomes.add(DimensionHelper.eucaSilver);
		
		getBiomesToSpawnIn().add(DimensionHelper.euca);
		getBiomesToSpawnIn().add(DimensionHelper.eucaSilver);
	}

}