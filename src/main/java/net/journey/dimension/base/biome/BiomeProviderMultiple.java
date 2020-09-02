package net.journey.dimension.base.biome;

import net.journey.dimension.base.gen.layer.GenLayerBiomes;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

import java.util.List;

public class BiomeProviderMultiple extends BiomeProvider {

    private final List<Biome> common;
    private final List<Biome> rare;

    public BiomeProviderMultiple(WorldInfo info, List<Biome> common, List<Biome> rare) {
        super(info);
        this.common = common;
        this.rare = rare;
        getBiomesToSpawnIn().clear();
        getBiomesToSpawnIn().addAll(common);
        getBiomesToSpawnIn().addAll(rare);
    }
    
   /* @Override
    public List<Biome> getBiomesToSpawnIn() {
    	return Config.specialBiomeRarity == 0 ? this.rare : this.common;
    }*/

    @Override
    public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) {
        GenLayer biomes = new GenLayerBiomes(1, this.getBiomesToSpawnIn());
        biomes = new GenLayerZoom(1000 /*baseSeed*/, biomes /*parent*/);
        biomes = new GenLayerZoom(1001 /*baseSeed*/, biomes /*parent*/);
        biomes = new GenLayerZoom(1002 /*baseSeed*/, biomes /*parent*/);
        biomes = new GenLayerZoom(1003 /*baseSeed*/, biomes /*parent*/);
        biomes = new GenLayerZoom(1004 /*baseSeed*/, biomes /*parent*/);
        biomes = new GenLayerZoom(1005 /*baseSeed*/, biomes /*parent*/);
        GenLayer layer = new GenLayerVoronoiZoom(10L /*baseSeed*/, biomes /*parent*/);
        layer.initWorldGenSeed(seed);

        WorldTypeEvent.InitBiomeGens event = new WorldTypeEvent.InitBiomeGens(worldType, seed, new GenLayer[]{biomes, layer});
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return event.getNewBiomeGens();
    }
}