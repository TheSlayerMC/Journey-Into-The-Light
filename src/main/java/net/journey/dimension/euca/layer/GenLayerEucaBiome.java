package net.journey.dimension.euca.layer;

import net.journey.dimension.DimensionHelper;
import net.journey.util.Config;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager.BiomeEntry;

import java.util.ArrayList;

public class GenLayerEucaBiome extends GenLayer {

    public GenLayerEucaBiome(long l) {
        super(l);
        ArrayList<BiomeEntry> eucaBiomes = new ArrayList<BiomeEntry>();
        eucaBiomes.add(new BiomeEntry(DimensionHelper.euca, Config.eucaBiome));
        eucaBiomes.add(new BiomeEntry(DimensionHelper.eucaSilver, Config.eucaSilverBiome));
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int nx = x - 1;
        int nz = z - 1;
        int nwidth = width + 2;
        int ndepth = depth + 2;
        int[] input = IntCache.getIntCache(width * depth);
        int[] output = IntCache.getIntCache(width * depth);

        int gold = Biome.getIdForBiome(DimensionHelper.euca);
        int silver = Biome.getIdForBiome(DimensionHelper.eucaSilver);

        for (int dz = 0; dz < depth; dz++) {
            for (int dx = 0; dx < width; dx++) {

                int right = input[dx + 0 + (dz + 1) * nwidth];
                int left = input[dx + 2 + (dz + 1) * nwidth];
                int up = input[dx + 1 + (dz + 0) * nwidth];
                int down = input[dx + 1 + (dz + 2) * nwidth];
                int center = input[dx + 1 + (dz + 1) * nwidth];
                this.initChunkSeed(dx + x, dz + z);
                //if(input[dx + dz * width] != Biome.getIdForBiome(DimensionHelper.euca) || input[dx + dz * width] != Biome.getIdForBiome(DimensionHelper.eucaSilver))
                //	input[dx + dz * width] = gold;

                if (onBorder(gold, center, right, left, up, down)) {
                    input[dx + dz * width] = silver;
                }
                if (onBorder(silver, center, right, left, up, down)) {
                    input[dx + dz * width] = gold;
                }

            }
        }
        return input;
    }

    private boolean onBorder(int biome, int center, int right, int left, int up, int down) {
        if (center != biome) return false;
        if (right != biome) return true;
        if (left != biome) return true;
        if (up != biome) return true;
        return down != biome;
    }
}