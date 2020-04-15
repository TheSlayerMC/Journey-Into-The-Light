package net.journey.dimension.euca;

import net.journey.dimension.BiomeGenJourney;
import net.journey.dimension.DimensionHelper;
import net.journey.dimension.euca.layer.GenLayerEucaBiome;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeProviderEuca extends BiomeProvider {

    private GenLayer genLayerBiomes;
    private GenLayer genLayerBiomeIndex;
    private BiomeCache biomeCache;
    private List<Biome> biomes;

    public BiomeProviderEuca(World world) {
        super(new WorldInfoEuca());

        getBiomesToSpawnIn().clear();
        getBiomesToSpawnIn().add(DimensionHelper.euca);
        getBiomesToSpawnIn().add(DimensionHelper.eucaSilver);

        allowedBiomes.add(DimensionHelper.euca);
        allowedBiomes.add(DimensionHelper.eucaSilver);

        this.biomeCache = new BiomeCache(this);
        this.biomes = new ArrayList<Biome>();
        this.addBiomes(this.biomes);
        getLayers(world.getSeed());
        GenLayer[] genLayers = getLayers(world.getSeed());
        this.genLayerBiomes = genLayers[0];
        this.genLayerBiomeIndex = genLayers[1];
    }

    public static GenLayer[] getLayers(long seed) {
        GenLayer biomes = new GenLayerEucaBiome(seed);
        biomes = new GenLayerZoom(100L, biomes);
        GenLayer zoom = new GenLayerVoronoiZoom(10L, biomes);

        biomes.initWorldGenSeed(seed);
        zoom.initWorldGenSeed(seed);

        return new GenLayer[]{biomes, zoom};
    }

    public void addBiomes(List<Biome> list) {
        list.add(DimensionHelper.euca);
        list.add(DimensionHelper.eucaSilver);
    }

    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height) {
        IntCache.resetIntCache();

        int len = width * height;

        if (biomes == null || biomes.length < len) {
            biomes = new Biome[len];
        }

        int[] ints = this.genLayerBiomes.getInts(x, z, width, height);
        for (int i = 0; i < len; ++i) {
            try {
                biomes[i] = Biome.getBiome(ints[i]);
            } catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
                CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
                crashreportcategory.addCrashSection("biomes[] size", Integer.valueOf(biomes.length));
                crashreportcategory.addCrashSection("x", Integer.valueOf(x));
                crashreportcategory.addCrashSection("z", Integer.valueOf(z));
                crashreportcategory.addCrashSection("w", Integer.valueOf(width));
                crashreportcategory.addCrashSection("h", Integer.valueOf(height));
                throw new ReportedException(crashreport);
            }

        }
        return biomes;
    }

    @Override
    public Biome[] getBiomes(@Nullable Biome[] oldBiomeList, int x, int z, int width, int depth) {
        return this.getBiomes(oldBiomeList, x, z, width, depth, true);
    }

    @Override
    public Biome[] getBiomes(@Nullable Biome[] biome, int x, int z, int width, int height, boolean cache) {
        IntCache.resetIntCache();

        int len = width * height;

        if (biome == null || biome.length < len) {
            biome = new Biome[len];
        }

        if (cache && width == 16 && height == 16 && (x & 0xF) == 0 && (z & 0xF) == 0) {
            Biome[] biomes = this.biomeCache.getCachedBiomes(x, z);
            System.arraycopy(biomes, 0, biome, 0, len);
            return biome;
        }

        int[] ints = this.genLayerBiomeIndex.getInts(x, z, width, height);

        for (int i = 0; i < len; ++i) {
            biome[i] = Biome.getBiome(ints[i]);
        }

        return biome;
    }

    @Override
    public boolean areBiomesViable(int x, int z, int range, List biomes) {
        IntCache.resetIntCache();
        int x1 = x - range >> 2;
        int z1 = z - range >> 2;
        int x2 = x + range >> 2;
        int z2 = z + range >> 2;
        int x3 = x2 - x1 + 1;
        int z3 = z2 - z1 + 1;
        int len = x3 * z3;
        int[] ints = this.genLayerBiomes.getInts(x1, z1, x3, z3);

        try {
            for (int i = 0; i < len; ++i) {
                Biome biome = Biome.getBiome(ints[i]);
                if (!biomes.contains(biome) || !(biome instanceof BiomeGenJourney))
                    return false;
            }

            return true;
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
            crashreportcategory.addCrashSection("Layer", this.genLayerBiomes.toString());
            crashreportcategory.addCrashSection("x", Integer.valueOf(x));
            crashreportcategory.addCrashSection("z", Integer.valueOf(z));
            crashreportcategory.addCrashSection("radius", Integer.valueOf(range));
            crashreportcategory.addCrashSection("allowed", biomes);
            throw new ReportedException(crashreport);
        }
    }

    @Override
    @Nullable
    public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
        IntCache.resetIntCache();
        int x1 = x - range >> 2;
        int z1 = z - range >> 2;
        int x2 = x + range >> 2;
        int z2 = z + range >> 2;
        int x3 = x2 - x1 + 1;
        int z3 = z2 - z1 + 1;
        int len = x3 * z3;
        int[] aint = this.genLayerBiomes.getInts(x1, z1, x3, z3);
        BlockPos chunkposition = null;

        for (int i = 0; i < len; ++i) {
            int x4 = x1 + i % x3 << 2;
            int z4 = z1 + i / x3 << 2;
            Biome biome = Biome.getBiome(aint[i]);

            if (biomes.contains(biome) && (chunkposition == null || random.nextInt(i + 1) == 0)) {
                chunkposition = new BlockPos(x4, 0, z4);
            }
        }

        return chunkposition;
    }

    @Override
    public void cleanupCache() {
        biomeCache.cleanupCache();
        super.cleanupCache();
    }
}