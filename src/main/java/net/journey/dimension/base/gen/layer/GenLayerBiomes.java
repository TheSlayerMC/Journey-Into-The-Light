package net.journey.dimension.base.gen.layer;

import net.journey.util.Config;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import javax.annotation.Nullable;
import java.util.List;

public class GenLayerBiomes extends GenLayer {
    List<Biome> biomes;
    List<Biome> rareBiomes;

    public GenLayerBiomes(long l, List<Biome> biomes, @Nullable List<Biome> rareBiomes) {
        super(l);
        this.biomes = biomes;
        this.rareBiomes = rareBiomes;
    }

    @Override
    public int[] getInts(int areaX, int areaZ, int width, int depth) {
        int ints[] = IntCache.getIntCache(width * depth);
        for(int z = 0; z < depth; z++) {
            for(int x = 0; x < width; x++) {
                this.initChunkSeed(x + areaX, z + areaZ);
                if(nextInt(Config.specialBiomeRarity) == 0) {
                    ints[x + z * width] = Biome.getIdForBiome(rareBiomes.get(this.nextInt(rareBiomes.size())));
                }
                else {
                    ints[x + z * width] = Biome.getIdForBiome(biomes.get(this.nextInt(biomes.size())));
                }
            }
        }
        return ints;
    }
}