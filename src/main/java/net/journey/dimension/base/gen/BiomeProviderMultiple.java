package net.journey.dimension.base.gen;

import net.journey.dimension.base.gen.layer.GenLayerBiomes;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.storage.WorldInfo;

public class BiomeProviderMultiple extends BiomeProvider {

    public BiomeProviderMultiple(WorldInfo info) {
        super(info);
    }

    @Override
    public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) {
        GenLayer biomes = new GenLayerBiomes(1, this.getBiomesToSpawnIn());
        biomes = new GenLayerZoom(1000, biomes);
        biomes = new GenLayerZoom(1001, biomes);
        biomes = new GenLayerZoom(1002, biomes);
        biomes = new GenLayerZoom(1003, biomes);
        biomes = new GenLayerZoom(1004, biomes);
        GenLayer layer = new GenLayerVoronoiZoom(10L, biomes);
        layer.initWorldGenSeed(seed);

        return new GenLayer[] { biomes, layer };
    }
}
