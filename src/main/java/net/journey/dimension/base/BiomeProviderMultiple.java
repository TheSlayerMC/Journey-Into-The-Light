package net.journey.dimension.base;

import net.journey.dimension.base.gen.layer.GenLayerBiomes;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.storage.WorldInfo;

import java.util.List;

public class BiomeProviderMultiple extends BiomeProvider {

    public BiomeProviderMultiple(WorldInfo info, List<Biome> allowedBiomes) {
        super(info);
        getBiomesToSpawnIn().clear();
        getBiomesToSpawnIn().addAll(allowedBiomes);
    }

    @Override
    public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) {
        GenLayer biomes = new GenLayerBiomes(1, this.getBiomesToSpawnIn());
        biomes = new GenLayerZoom(1000 /*baseSeed*/, biomes /*parent*/);
        biomes = new GenLayerZoom(1001 /*baseSeed*/, biomes /*parent*/);
        biomes = new GenLayerZoom(1002 /*baseSeed*/, biomes /*parent*/);
        biomes = new GenLayerZoom(1003 /*baseSeed*/, biomes /*parent*/);
        biomes = new GenLayerZoom(1004 /*baseSeed*/, biomes /*parent*/);
        GenLayer layer = new GenLayerVoronoiZoom(10L /*baseSeed*/, biomes /*parent*/);
        layer.initWorldGenSeed(seed);

        return new GenLayer[]{biomes, layer};
    }
}
