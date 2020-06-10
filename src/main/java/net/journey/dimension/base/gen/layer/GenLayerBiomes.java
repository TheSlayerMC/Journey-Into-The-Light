package net.journey.dimension.base.gen.layer;

import net.journey.util.Config;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import javax.annotation.Nullable;
import java.util.List;

public class GenLayerBiomes extends GenLayer {
    List<Biome> biomes;

    public GenLayerBiomes(long l, List<Biome> biomes) {
        super(l);
        this.biomes = biomes;
    }

    @Override
    public int[] getInts(int areaX, int areaZ, int width, int depth) {
        int ints[] = IntCache.getIntCache(width * depth);
        for(int z = 0; z < depth; z++) {
            for(int x = 0; x < width; x++) {
                this.initChunkSeed(x + areaX, z + areaZ);
                ints[x + z * width] = Biome.getIdForBiome(biomes.get(this.nextInt(biomes.size())));
            }
        }
        return ints;
    }
}