package net.journey.dimension.base.gen.layer;

import net.journey.util.Config;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import java.util.List;

public class GenLayerBiomes extends GenLayer {
    List<Biome> biomes;
    List<Biome> specialBiomes;

    public GenLayerBiomes(long l, List<Biome> biomes, List<Biome> specialBiomes) {
        super(l);
        this.biomes = biomes;
        this.specialBiomes = specialBiomes;
    }

    @Override
    public int[] getInts(int areaX, int areaZ, int width, int depth) {
        int[] ints = IntCache.getIntCache(width * depth);
        for (int z = 0; z < depth; z++) {
            for (int x = 0; x < width; x++) {
                this.initChunkSeed(x + areaX, z + areaZ);
                /**
                 * TODO: make rare biomes work correctly
                 * biomes can't be passed through the constructor, so this is redundant for now
                 * however, this is the correct way to make certain biomes rarer than others
                 */
                if (nextInt(Config.specialBiomeRarity) == 0) {
                    ints[x + z * width] = Biome.getIdForBiome(specialBiomes.get(this.nextInt(specialBiomes.size())));
                } else {
                    ints[x + z * width] = Biome.getIdForBiome(biomes.get(this.nextInt(biomes.size())));
                }
            }
        }
        return ints;
    }
}