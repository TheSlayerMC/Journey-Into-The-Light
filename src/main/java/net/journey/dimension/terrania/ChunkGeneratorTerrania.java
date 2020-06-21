package net.journey.dimension.terrania;

import net.journey.api.block.GroundPredicate;
import net.journey.dimension.base.gen.JWorldGenPlants;
import net.journey.dimension.terrania.gen.WorldGenHollowTree;
import net.journey.dimension.terrania.gen.WorldGenTerranianLamp;
import net.journey.dimension.terrania.gen.dungeon.WorldGenMushroomDungeon;
import net.journey.dimension.terrania.gen.dungeon.WorldGenTallTree;
import net.journey.dimension.terrania.gen.dungeon.WorldGenTreeHut;
import net.journey.dimension.terrania.gen.shroom.WorldGenTerrashroom;
import net.journey.dimension.terrania.gen.trees.WorldGenTerraniaTree;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.List;
import java.util.Random;

public class ChunkGeneratorTerrania implements IChunkGenerator {
    private static final GroundPredicate TERRANIAN_GRASS_GROUND = GroundPredicate.blockPredicate(block -> block == JourneyBlocks.terranianGrass);

    private final double[] da;
    private final float[] parabolicField;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    double[] gen1;
    double[] gen2;
    double[] gen3;
    double[] gen4;
    private final JWorldGenPlants genEnchantedShroomsSmall = new JWorldGenPlants(JourneyBlocks.enchantedShroomsSmall, TERRANIAN_GRASS_GROUND);
    private final JWorldGenPlants genEnchantedShroomTall = new JWorldGenPlants(JourneyBlocks.enchantedShroomTall, TERRANIAN_GRASS_GROUND);
    private final JWorldGenPlants flower = new JWorldGenPlants(JourneyBlocks.terranianTallgrass, TERRANIAN_GRASS_GROUND);
    private final JWorldGenPlants flower1 = new JWorldGenPlants(JourneyBlocks.terramushroom, TERRANIAN_GRASS_GROUND);
    private final JWorldGenPlants flower2 = new JWorldGenPlants(JourneyBlocks.tallTerramushroom, TERRANIAN_GRASS_GROUND);
    private final WorldGenTreeHut hut = new WorldGenTreeHut();
    private final WorldGenHollowTree hollowTree = new WorldGenHollowTree();
    private final WorldGenMushroomDungeon mushroomDungeon = new WorldGenMushroomDungeon();
    private final WorldGenTallTree tallTree = new WorldGenTallTree();
    private final WorldGenTerranianLamp lamp = new WorldGenTerranianLamp();
    private final Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private final NoiseGeneratorPerlin noiseGen4;
    private final World world;
    private double[] stoneNoise;
    private final MapGenBase caveGenerator;
    private final MapGenBase ravineGenerator;
    private Biome[] biomesForGeneration;

    public ChunkGeneratorTerrania(World worldIn, long seed) {
        this.stoneNoise = new double[256];
        this.caveGenerator = new MapGenCaves();
        this.ravineGenerator = new MapGenRavine();
        this.world = worldIn;
        this.rand = new Random(seed);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorPerlin(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.da = new double[825];
        this.parabolicField = new float[25];
        for (int j = -2; j <= 2; ++j) {
            for (int k = -2; k <= 2; ++k) {
                float f = 10.0F / MathHelper.sqrt(j * j + k * k + 0.2F);
                this.parabolicField[j + 2 + (k + 2) * 5] = f;
            }
        }

        net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextHell ctx =
                new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextHell(noiseGen1, noiseGen2, noiseGen3, noiseGen5, noiseGen6, mobSpawnerNoise, mobSpawnerNoise);
        ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, ctx);
        this.noiseGen1 = ctx.getLPerlin1();
        this.noiseGen2 = ctx.getLPerlin2();
        this.noiseGen3 = ctx.getPerlin();
        this.noiseGen5 = ctx.getPerlin2();
        this.noiseGen6 = ctx.getPerlin3();
        this.mobSpawnerNoise = ctx.getScale();
        this.mobSpawnerNoise = ctx.getDepth();
    }

    public void setBlocksInChunk(int x, int z, ChunkPrimer p_180518_3_) {
        this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
        this.generate(x * 4, 0, z * 4);
        for (int k = 0; k < 4; ++k) {
            int l = k * 5;
            int i1 = (k + 1) * 5;
            for (int j1 = 0; j1 < 4; ++j1) {
                int k1 = (l + j1) * 33;
                int l1 = (l + j1 + 1) * 33;
                int i2 = (i1 + j1) * 33;
                int j2 = (i1 + j1 + 1) * 33;
                for (int k2 = 0; k2 < 32; ++k2) {
                    double d0 = 0.125D;
                    double d1 = this.da[k1 + k2];
                    double d2 = this.da[l1 + k2];
                    double d3 = this.da[i2 + k2];
                    double d4 = this.da[j2 + k2];
                    double d5 = (this.da[k1 + k2 + 1] - d1) * d0;
                    double d6 = (this.da[l1 + k2 + 1] - d2) * d0;
                    double d7 = (this.da[i2 + k2 + 1] - d3) * d0;
                    double d8 = (this.da[j2 + k2 + 1] - d4) * d0;

                    for (int l2 = 0; l2 < 8; ++l2) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i3 = 0; i3 < 4; ++i3) {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;

                            for (int j3 = 0; j3 < 4; ++j3) {
                                if ((d15 += d16) > 0.0D) {
                                    p_180518_3_.setBlockState(k * 4 + i3, k2 * 2 + l2, j1 * 4 + j3, JourneyBlocks.terranianStone.getDefaultState());
                                }
                            }
                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }


    public void biomeBlocks(int x, int z, ChunkPrimer c, Biome[] b) {
        double d0 = 0.03125D;
        this.stoneNoise = this.noiseGen4.getRegion(this.stoneNoise, x * 16, z * 16, 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                generateBiomeTerrain(this.rand, c, x * 16 + k, z * 16 + l, this.stoneNoise[l + k * 16]);
            }
        }
    }

    public final void generateBiomeTerrain(Random r, ChunkPrimer c, int x, int z, double s) {
        boolean flag = true;
        IBlockState iblockstate = JourneyBlocks.terranianGrass.getDefaultState();
        IBlockState iblockstate1 = JourneyBlocks.terranianStone.getDefaultState();
        int k = -1;
        int l = (int) (s / 3.0D + 3.0D + r.nextDouble() * 0.25D);
        int i1 = x & 15;
        int j1 = z & 15;
        for (int k1 = 255; k1 >= 0; --k1) {
            if (k1 <= 1) {
                c.setBlockState(j1, k1, i1, Blocks.BEDROCK.getDefaultState());
            } else {
                IBlockState iblockstate2 = c.getBlockState(j1, k1, i1);

                if (iblockstate2.getMaterial() == Material.AIR) k = -1;
                else if (iblockstate2.getBlock() == JourneyBlocks.terranianStone) {
                    if (k == -1) {
                        if (l <= 0) {
                            iblockstate = null;
                            iblockstate1 = JourneyBlocks.terranianStone.getDefaultState();
                        } else if (k1 >= 13 && k1 <= 16) {
                            iblockstate = JourneyBlocks.terranianGrass.getDefaultState();
                            iblockstate1 = JourneyBlocks.terranianStone.getDefaultState();
                        }

                        if (k1 < 15 && (iblockstate == null || iblockstate.getMaterial() == Material.AIR))
                            iblockstate = JourneyBlocks.terranianStone.getDefaultState();
                        k = l;
                        if (k1 >= 14) c.setBlockState(j1, k1, i1, iblockstate);
                        else if (k1 < 13 - l) {
                            iblockstate = null;
                            iblockstate1 = JourneyBlocks.terranianStone.getDefaultState();
                        } else c.setBlockState(j1, k1, i1, iblockstate1);
                    } else if (k > 0) {
                        --k;
                        c.setBlockState(j1, k1, i1, iblockstate1);
                    }
                }
            }
        }
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        this.rand.setSeed(x * 341873128712L + z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.setBlocksInChunk(x, z, chunkprimer);
        this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        this.biomeBlocks(x, z, chunkprimer, this.biomesForGeneration);
        //this.caveGenerator.func_175792_a(this, this.worldObj, x, z, chunkprimer);
        //this.ravineGenerator.func_175792_a(this, this.worldObj, x, z, chunkprimer);
        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        byte[] abyte = chunk.getBiomeArray();
        for (int k = 0; k < abyte.length; ++k) abyte[k] = (byte) Biome.getIdForBiome(this.biomesForGeneration[k]);
        chunk.generateSkylightMap();
        return chunk;
    }

    private void generate(int x, int y, int z) {
        double d0 = 684.412D;
        double d1 = 684.412D;
        double d2 = 512.0D;
        double d3 = 512.0D;
        this.gen4 = this.noiseGen6.generateNoiseOctaves(this.gen4, x, z, 5, 5, 200.0D, 200.0D, 0.5D);
        this.gen1 = this.noiseGen3.generateNoiseOctaves(this.gen1, x, y, z, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
        this.gen2 = this.noiseGen1.generateNoiseOctaves(this.gen2, x, y, z, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        this.gen3 = this.noiseGen2.generateNoiseOctaves(this.gen3, x, y, z, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        boolean flag1 = false;
        boolean flag = false;
        int l = 0;
        int i1 = 0;
        double d4 = 8.5D;

        for (int j1 = 0; j1 < 5; ++j1) {
            for (int k1 = 0; k1 < 5; ++k1) {
                float f = 0.0F;
                float f1 = 0.0F;
                float f2 = 0.0F;
                byte b0 = 2;
                Biome Biome = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];

                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        Biome Biome1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
                        float f3 = 0.1F;
                        float f4 = 0.125F;

                        float f5 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0F);

                        f += f4 * f5 / 2;
                        f1 += f3 * f5 / 2;
                        f2 += f5 / 2;
                    }
                }

                f /= f2;
                f1 /= f2;
                f = f * 0.9F + 0.1F;
                f1 = (f1 * 4.0F - 1.0F) / 8.0F;
                double d12 = this.gen4[i1] / 8000.0D;

                if (d12 < 0.0D) {
                    d12 = -d12 * 0.3D;
                }

                d12 = d12 * 3.0D - 2.0D;

                if (d12 < 0.0D) {
                    d12 /= 2.0D;

                    if (d12 < -1.0D)
                        d12 = -1.0D;

                    d12 /= 1.4D;
                    d12 /= 2.0D;
                } else {
                    if (d12 > 1.0D)
                        d12 = 1.0D;

                    d12 /= 8.0D;
                }

                ++i1;
                double d13 = f1;
                double d14 = f;
                d13 += d12 * 0.4D;
                d13 = d13 * 10.5D / 8.0D;
                double d5 = 8.5D + d13 * 4.0D;

                for (int j2 = 0; j2 < 33; ++j2) {
                    double d6 = (j2 - d5) * 12.0D * 128.0D / 256.0D / d14;

                    if (d6 < 0.0D)
                        d6 *= 4.0D;


                    double d7 = this.gen2[l] / 256.0D;
                    double d8 = this.gen3[l] / 256.0D;
                    double d9 = (this.gen1[l] / 10.0D + 1.0D) / 2.0D;
                    double d10 = MathHelper.clampedLerp(d7, d8, d9) - d6;

                    if (j2 > 29) {
                        double d11 = (j2 - 29) / 3.0F;
                        d10 = d10 * (1.0D - d11) + -10.0D * d11;
                    }

                    this.da[l] = d10;
                    ++l;
                }
            }
        }
    }

    @Override
    public void populate(int chunkX, int chunkZ) {
        ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
        int x1 = chunkX * 16;
        int z1 = chunkZ * 16;
        int times;
        int chunkSize = 16;
        int topYMax = 100;
        int bottomYMax = 50;
        Random r = rand;

        BlockPos chunkStart = new BlockPos(x1, 0, z1);


        for (times = 0; times < 25; times++) {
            generateStructure(x1, z1, new WorldGenTerrashroom(JourneyBlocks.terrashroomBlockPink));
        }

        for (times = 0; times < 25; times++) {
            generateStructure(x1, z1, new WorldGenTerrashroom(JourneyBlocks.terrashroomBlockPurple));
        }

        for (times = 0; times < 2; times++) {
            WorldGenAPI.genOnGround(world, chunkPos, rand, new WorldGenTerraniaTree(true, 0, 1, JourneyBlocks.terranianLog.getDefaultState(), JourneyBlocks.terraniaLeaves.getDefaultState()));
        }

        for (int i = 0; i < 10; i++) {
            genEnchantedShroomsSmall.generate(world, r, chunkStart);
        }

        for (int i = 0; i < 2; i++) {
            genEnchantedShroomTall.generate(world, r, chunkStart);
        }

        for (int i = 0; i < 100; i++) {
            flower.generate(world, r, chunkStart);
        }

        for (int i = 0; i < 20; i++) {
            flower1.generate(world, r, chunkStart);
        }

        for (int i = 0; i < 1; i++) {
            flower2.generate(world, r, chunkStart);
        }

        if (rand.nextInt(1) == 0) {
            generateStructure(x1, z1, hut);
        }

        if (rand.nextInt(1) == 0) {
            BlockPos pos = WorldGenAPI.createRandom(x1, 1, 128, z1, rand, 2);
            if (isBlockTop(pos.getX(), pos.getY() - 1, pos.getZ(), JourneyBlocks.terranianGrass)) {
                hollowTree.generate(world, rand, pos);
            }
        }

        for (times = 0; times < 1; times++) {
            int randX = chunkX * 16 + 7 + rand.nextInt(9);
            int randZ = chunkZ * 16 + 7 + rand.nextInt(9);
            int randY = rand.nextInt(bottomYMax) + 1;
            if (isBlockTop(randX, randY - 1, randZ, JourneyBlocks.terranianGrass)) {
                mushroomDungeon.generate(world, rand, new BlockPos(randX, randY, randZ));
            }
        }

        for (times = 0; times < 3; times++) {
            WorldGenAPI.genOnGround(world, chunkPos, rand, new WorldGenTerraniaTree(true, 5, 10, JourneyBlocks.terranianLog.getDefaultState(), JourneyBlocks.terraniaLeaves.getDefaultState()));
        }

        if (rand.nextInt(8) == 0) {
            BlockPos pos = WorldGenAPI.createRandom(x1, 1, 128, z1, rand, 2);
            if (isBlockTop(pos.getX(), pos.getY() - 1, pos.getZ(), JourneyBlocks.terranianGrass)) {
                tallTree.generate(world, rand, pos);
            }
        }

        for (times = 0; times < 10; times++) {
            generateStructure(x1, z1, lamp);
        }
    }

    private void generateStructure(int x, int z, WorldGenerator... generators) {
        BlockPos pos = WorldGenAPI.createRandom(x, 1, 128, z, rand, 8);
        if (isBlockTop(pos.getX(), pos.getY() - 1, pos.getZ(), JourneyBlocks.terranianGrass)) {
            generators[rand.nextInt(generators.length)].generate(world, rand, pos);
        }
    }

    public boolean isBlockTop(int x, int y, int z, Block grass) {
        return WorldGenAPI.isBlockTop(x, y, z, grass, world);
    }

    @Override
    public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = this.world.getBiome(pos);
        return biome.getSpawnableList(creatureType);
    }

    @Override
    public void recreateStructures(Chunk c, int x, int z) {
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }

}