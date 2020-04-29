package net.journey.dimension.base;

import net.journey.api.world.gen.JWorldGenMinable;
import net.journey.dimension.base.gen.WorldGenAncientBlock;
import net.journey.dimension.boil.gen.WorldGenBoilingFire;
import net.journey.dimension.boil.gen.WorldGenBoilingLava;
import net.journey.dimension.depths.gen.WorldGenDepthsTree;
import net.journey.dimension.euca.gen.WorldGenSmeltery;
import net.journey.dimension.nether.gen.*;
import net.journey.dimension.nether.gen.trees.WorldGenBleedheartTree0;
import net.journey.dimension.nether.gen.trees.WorldGenBleedheartTree1;
import net.journey.dimension.nether.gen.trees.WorldGenEarthenTree;
import net.journey.dimension.overworld.gen.*;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.Config;
import net.journey.util.handler.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

public class WorldGenJourney implements IWorldGenerator {

    private static final LazyLoadBase<WorldGenMinable> celestium;
    private static final LazyLoadBase<WorldGenMinable> flairium;
    private static final LazyLoadBase<WorldGenMinable> gorbite;
    private static final LazyLoadBase<WorldGenMinable> orbaditeOre;
    private static final LazyLoadBase<WorldGenMinable> depthsLights;
    private static final LazyLoadBase<WorldGenMinable> depthsLightsForStone;
    private static final LazyLoadBase<WorldGenMinable> storonOre;
    private static final LazyLoadBase<WorldGenMinable> koriteOre;
    private static final LazyLoadBase<WorldGenMinable> mekyumOre;
    private static final LazyLoadBase<JWorldGenMinable> SHADIUM_ORE_GEN = create(() -> JWorldGenMinable.create(JourneyBlocks.shadiumOre, 6, 13));
    private static final LazyLoadBase<JWorldGenMinable> LUNIUM_ORE_GEN = create(() -> JWorldGenMinable.create(JourneyBlocks.luniumOre, 7, 26));
    private static final LazyLoadBase<JWorldGenMinable> SAPPHIRE_ORE_GEN = create(() -> JWorldGenMinable.create(JourneyBlocks.sapphireOre, 5, 24));
    private static final LazyLoadBase<JWorldGenMinable> IRIDIUM_ORE_GEN = create(() -> JWorldGenMinable.create(JourneyBlocks.iridiumOre, 4, 16));
    private static final WorldGenSmeltery smeltery = new WorldGenSmeltery();
    private static final WorldGenBoilingFire fire = new WorldGenBoilingFire();
    private static final LazyLoadBase<WorldGenModFlower> eucaTallGrass;
    private static final LazyLoadBase<WorldGenModFlower> eucaTallFlowers;
    private static final LazyLoadBase<WorldGenModFlower> eucaBlueFlower;
    private static final LazyLoadBase<WorldGenModFlower> frozenFlower;
    private static final LazyLoadBase<WorldGenModFlower> depthsFlower;
    private static final LazyLoadBase<WorldGenBoilingLava> boilLava;
    private static final LazyLoadBase<WorldGenMinable> pinkCloudiaCloud;
    private static final LazyLoadBase<WorldGenMinable> lightBlueCloudiaCloud;
    private static final LazyLoadBase<WorldGenMinable> withanLight;
    private static final LazyLoadBase<WorldGenMinable> cloudiaRock;
    private static final LazyLoadBase<WorldGenMinable> luniteOre;

    private static final LazyLoadBase<WorldGenBush> JUICEBERRY_BUSH_GEN = create(() -> new WorldGenBush(JourneyBlocks.juiceberryBush, Blocks.GRASS));
    private static final LazyLoadBase<WorldGenBush> BRADBERRY_BUSH_GEN = create(() -> new WorldGenBush(JourneyBlocks.bradberryBush, Blocks.GRASS));
    private static final LazyLoadBase<WorldGenBush> TANGLEBERRY_BUSH_GEN = create(() -> new WorldGenBush(JourneyBlocks.tangleberryBush, Blocks.GRASS));
    private static final LazyLoadBase<WorldGenBush> BOGBERRY_BUSH_GEN = create(() -> new WorldGenBush(JourneyBlocks.bogberryBush, Blocks.GRASS));
    private static final LazyLoadBase<WorldGenBush> sizzleberryBush;

    private static final LazyLoadBase<WorldGenTallGlowshroom> TALL_GLOWSHROOMS_GEN = create(WorldGenTallGlowshroom::new);
    private static final LazyLoadBase<WorldGenCaveVines> CAVE_VINE_GEN = create(WorldGenCaveVines::new);
    private static final LazyLoadBase<WorldGenSmallGlowshrooms> SMALL_GLOWSHROOMS = create(WorldGenSmallGlowshrooms::new);

    private static final LazyLoadBase<WorldGenAncientBlock> ANCIENT_BLOCK_GEN = create(WorldGenAncientBlock::new);
    @Deprecated // use per-chunk random instance which comes from method params
    private static Random r = new Random();

    static {
        flairium = create(JourneyBlocks.flairiumOre, 8, JourneyBlocks.depthsStone);
        depthsLights = create(JourneyBlocks.depthsLights, 25, JourneyBlocks.depthsGrass);
        depthsLightsForStone = create(JourneyBlocks.depthsLights, 25, JourneyBlocks.depthsStone);
        gorbite = create(JourneyBlocks.gorbiteOre, 6, JourneyBlocks.corbaStone);
        orbaditeOre = create(JourneyBlocks.orbaditeOre, 6, JourneyBlocks.corbaStone);
        celestium = create(JourneyBlocks.celestiumOre, 10, JourneyBlocks.eucaStone);
        storonOre = create(JourneyBlocks.storonOre, 6, JourneyBlocks.eucaStone);
        koriteOre = create(JourneyBlocks.koriteOre, 10, JourneyBlocks.eucaStone);
        mekyumOre = create(JourneyBlocks.mekyumOre, 10, JourneyBlocks.eucaStone);
        pinkCloudiaCloud = create(JourneyBlocks.pinkCloudiaCloud, 40, Blocks.AIR);
        lightBlueCloudiaCloud = create(JourneyBlocks.lightBlueCloudiaCloud, 40, Blocks.AIR);
        withanLight = create(JourneyBlocks.withanLight, 25, JourneyBlocks.withanRockReinforced);
        cloudiaRock = create(JourneyBlocks.cloudiaRock, 40, Blocks.AIR);
        luniteOre = create(JourneyBlocks.luniteOre, 10, JourneyBlocks.cloudiaRock);

        eucaTallGrass = create(() -> new WorldGenModFlower(JourneyBlocks.eucaTallGrass, JourneyBlocks.eucaGrass));
        eucaTallFlowers = create(() -> new WorldGenModFlower(JourneyBlocks.eucaTallFlowers, JourneyBlocks.eucaGrass));
        eucaBlueFlower = create(() -> new WorldGenModFlower(JourneyBlocks.eucaBlueFlower, JourneyBlocks.eucaGrass));
        depthsFlower = create(() -> new WorldGenModFlower(JourneyBlocks.depthsFlower, JourneyBlocks.depthsGrass, false));
        frozenFlower = create(() -> new WorldGenModFlower(JourneyBlocks.frozenFlower, JourneyBlocks.frozenGrass, false));

        sizzleberryBush = create(() -> new WorldGenBush(JourneyBlocks.sizzleberryBush, Blocks.NETHERRACK));

        boilLava = create(() -> new WorldGenBoilingLava(Blocks.LAVA));
    }

    private ArrayList<WorldGenerator> trees;

    public WorldGenJourney() {
        r = new Random();
        LogHelper.info("Loading world generator");
        trees = new ArrayList<WorldGenerator>(1);
        trees.add(new WorldGenBleedheartTree0());
        trees.add(new WorldGenBleedheartTree1());
        //trees.add(new WorldGenBleedheartTree2());
        //trees.add(new WorldGenSizzlerWoodTree0());
    }

    private static LazyLoadBase<WorldGenMinable> create(Block ore, int count, Block stone) {
        return create(() -> new WorldGenMinable(ore.getDefaultState(), count, BlockStateMatcher.forBlock(stone)));
    }

    private static <T> LazyLoadBase<T> create(Supplier<T> createFunc) {
        return new LazyLoadBase<T>() {
            @Override
            protected T load() {
                return createFunc.get();
            }
        };
    }

    public static void generateJourneyDimensions(int gen, World w, int chunkX, int chunkZ) {
        BlockPos chunkStart = new BlockPos(chunkX, 0, chunkZ);
        BlockPos randomPosForMinable = chunkStart.add(r.nextInt(16), r.nextInt(w.getHeight()), r.nextInt(16));
        BlockPos randWithOffset = WorldGenAPI.createRandom(chunkX, 1, w.getHeight(), chunkZ, r, 8);

        switch (gen) {
            case 0:
                celestium.getValue().generate(w, r, randomPosForMinable);
                break;
            case 1:
                flairium.getValue().generate(w, r, randomPosForMinable);
                break;
            case 2:
                if (w.getBlockState(randWithOffset).getBlock() != JourneyBlocks.depthsGrass
                        || w.getBlockState(randWithOffset.down()).getBlock() != JourneyBlocks.depthsGrass) {
                    new WorldGenDepthsTree().generate(w, r, randWithOffset);
                }
                break;
            case 3:
                flairium.getValue().generate(w, r, randomPosForMinable);
                break;
            case 4:
                boilLava.getValue().generate(w, r, randWithOffset);
                break;
            case 5:
                fire.generate(w, r, randWithOffset);
                break;
            case 6:
                eucaTallGrass.getValue().generate(w, r, chunkStart);
                break;
            case 7:
                eucaTallFlowers.getValue().generate(w, r, chunkStart);
                break;
            case 8:
                eucaBlueFlower.getValue().generate(w, r, chunkStart);
                break;
            case 9:
                frozenFlower.getValue().generate(w, r, chunkStart);
                break;
            case 10:
                depthsLights.getValue().generate(w, r, randomPosForMinable);
                break;
            case 11:
                depthsLightsForStone.getValue().generate(w, r, randomPosForMinable);
                break;
            case 12:
                depthsFlower.getValue().generate(w, r, chunkStart);
                break;
            case 13:
                gorbite.getValue().generate(w, r, randomPosForMinable);
                break;
            case 14:
                orbaditeOre.getValue().generate(w, r, randomPosForMinable);
                break;
            case 15:
                storonOre.getValue().generate(w, r, randomPosForMinable);
                break;
            case 16:
                koriteOre.getValue().generate(w, r, randomPosForMinable);
                break;
            case 17:
                mekyumOre.getValue().generate(w, r, randomPosForMinable);
                break;
            case 18:
                if (w.getBlockState(randWithOffset) == JourneyBlocks.eucaGrass.getDefaultState())
                    smeltery.generate(w, r, randWithOffset);
                break;
            case 19:
                if (120 > randomPosForMinable.getY() && randomPosForMinable.getY() > 10)
                    pinkCloudiaCloud.getValue().generate(w, r, randomPosForMinable);
                break;
            case 20:
                if (10 < randomPosForMinable.getY() && randomPosForMinable.getY() < 120)
                    lightBlueCloudiaCloud.getValue().generate(w, r, randomPosForMinable);
                break;
            case 21:
                withanLight.getValue().generate(w, r, randomPosForMinable);
                break;
        }
    }

    private static void worldMinableGenVanilla(Block spawn, int vein, World w, int x, int y, int z) {
        (new WorldGenMinable(spawn.getDefaultState(), vein)).generate(w, r, new BlockPos(x, y, z));
    }

    private static void worldMinableGenNether(Block spawn, int vein, World w, int x, int y, int z) {
        (new WorldGenMinable(spawn.getDefaultState(), vein, BlockStateMatcher.forBlock(Blocks.NETHERRACK))).generate(w, r, new BlockPos(x, y, z));
    }

    private static void worldMinableGlowstone(Block spawn, int vein, World w, int x, int y, int z) {
        (new WorldGenMinable(spawn.getDefaultState(), vein, BlockStateMatcher.forBlock(Blocks.GLOWSTONE))).generate(w, r, new BlockPos(x, y, z));
    }

    private static void worldGenNetherFeature(Block spawn, Block toGenerateIn, int vein, World w, int x, int y, int z) {
        (new WorldGenMinable(spawn.getDefaultState(), vein, BlockStateMatcher.forBlock(toGenerateIn))).generate(w, r, new BlockPos(x, y, z));
    }

    private static void worldGenSoulsandFeature(Block spawn, int vein, World w, int x, int y, int z) {
        (new WorldGenMinable(spawn.getDefaultState(), vein, BlockStateMatcher.forBlock(Blocks.SOUL_SAND))).generate(w, r, new BlockPos(x, y, z));
    }

    private static void worldMinableGenEnd(Block spawn, int vein, World w, int x, int y, int z) {
        (new WorldGenMinable(spawn.getDefaultState(), vein, BlockStateMatcher.forBlock(Blocks.END_STONE))).generate(w, r, new BlockPos(x, y, z));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World w, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int dim = w.provider.getDimension();
        switch (dim) {
            case -1:
                generateNether(w, random, chunkX * 16, chunkZ * 16);
                break;
            case 0:
                generateOverworld(w, random, chunkX, chunkZ);
                break;
            case 1:
                generateEnd(w, random, chunkX * 16, chunkZ * 16);
                break;
        }
        if (dim == Config.boil) generateBoilingPoint(w, r, chunkX * 16, chunkZ * 16);
        //if (dim == Config.depths) generateDepths(w, r, chunkX * 16, chunkZ * 16);
        if (dim == Config.euca) generateEuca(w, r, chunkX * 16, chunkZ * 16);
        if (dim == Config.frozen) generateFrozen(w, r, chunkX * 16, chunkZ * 16);
        if (dim == Config.corba) generateCorba(w, r, chunkX * 16, chunkZ * 16);
        if (dim == Config.cloudia) generateCloudia(w, r, chunkX * 16, chunkZ * 16);
        //if(dim == Config.terrania) generateTerrania(w, r, chunkX * 16, chunkZ * 16);
    }

    public void generateNether(World w, Random r, int chunkX, int chunkZ) {
        int x, y, z;
        int times;
        BlockPos pos = new BlockPos(chunkX, 0, chunkZ);

        for (times = 0; times < 10; times++) {
            y = r.nextInt(128) + 1;
            x = chunkX + r.nextInt(16);
            z = chunkZ + r.nextInt(16);
            worldMinableGenNether(JourneyBlocks.hellstoneOre, 5, w, x, y, z);
        }

        for (times = 0; times < 15; times++) {
            y = r.nextInt(128) + 1;
            x = chunkX + r.nextInt(16);
            z = chunkZ + r.nextInt(16);
            worldMinableGenNether(JourneyBlocks.firestoneOre, 10, w, x, y, z);
        }

        for (times = 0; times < 15; times++) {
            y = r.nextInt(128) + 1;
            x = chunkX + r.nextInt(16);
            z = chunkZ + r.nextInt(16);
            worldMinableGenNether(JourneyBlocks.bloodRock, 10, w, x, y, z);
        }

        if (r.nextInt(3) == 0) {
            y = r.nextInt(256) + 1;
            x = chunkX + r.nextInt(16) + 8;
            z = chunkZ + r.nextInt(16) + 8;
            (new WorldGenModGlowstone(w, r, pos, JourneyBlocks.bleedstone)).generate(w, r, new BlockPos(x, y, z));
        }

        if (r.nextInt(3) == 0) {
            y = r.nextInt(256) + 1;
            x = chunkX + r.nextInt(16) + 8;
            z = chunkZ + r.nextInt(16) + 8;
            (new WorldGenModGlowstone(w, r, pos, JourneyBlocks.smithstone)).generate(w, r, new BlockPos(x, y, z));
        }

        for (times = 0; times < 100; times++) {
            y = r.nextInt(255) + 1;
            x = chunkX + r.nextInt(16);
            z = chunkZ + r.nextInt(16);
            worldGenNetherFeature(JourneyBlocks.nethicanSludge, JourneyBlocks.heatSoil, 10, w, x, y, z);
        }

        for (times = 0; times < 25; times++) {
            y = r.nextInt(250) + 1;
            x = chunkX + r.nextInt(16);
            z = chunkZ + r.nextInt(16);
            worldGenNetherFeature(JourneyBlocks.lavaRock, JourneyBlocks.heatSoil, 40, w, x, y, z);
        }

        for (times = 0; times < 300; times++) {
            y = r.nextInt(256);
            x = chunkX + r.nextInt(16) + 8;
            z = chunkZ + r.nextInt(16) + 8;
            if (isBlockTop(x, y, z, JourneyBlocks.heatSoil, w))
                (new WorldGenBleedheartTree0()).generate(w, r, new BlockPos(x, y - 1, z));
        }

        for (times = 0; times < 300; times++) {
            y = r.nextInt(256);
            x = chunkX + r.nextInt(16) + 8;
            z = chunkZ + r.nextInt(16) + 8;
            if (isBlockTop(x, y, z, JourneyBlocks.heatSoil, w))
                (new WorldGenBleedheartTree1()).generate(w, r, new BlockPos(x, y - 1, z));
        }

        for (times = 0; times < 800; times++) {
            y = r.nextInt(256);
            x = chunkX + r.nextInt(16) + 8;
            z = chunkZ + r.nextInt(16) + 8;
            if (isBlockTop(x, y, z, JourneyBlocks.earthenNetherrack, w))
                (new WorldGenEarthenTree()).generate(w, r, new BlockPos(x, y, z));
        }

        for (times = 0; times < 1; times++) {
            y = r.nextInt(256) + 1;
            x = chunkX + r.nextInt(16) + 8;
            z = chunkZ + r.nextInt(16) + 8;
            (new WorldGenAncientBlock()).generate(w, r, new BlockPos(x, y, z));
        }

        for (times = 0; times < 100; times++) {
            y = r.nextInt(250);
            x = chunkX + r.nextInt(16) + 8;
            z = chunkZ + r.nextInt(16) + 8;
            if (isBlockTop(x, y, z, JourneyBlocks.heatSand, w))
                (new WorldGenNetherShroom()).generate(w, r, new BlockPos(x, y, z));
        }

        if (r.nextInt(15) == 0) {
            y = r.nextInt(128) + 1;
            x = chunkX + r.nextInt(8) + 1;
            z = chunkZ + r.nextInt(8) + 1;
            if (y > 20 && y < 110)
                if (isBlockTop(x, y, z, Blocks.NETHERRACK, w))
                    new WorldGenNetherTower().generate(w, r, new BlockPos(x, y, z));
        }

        for (times = 0; times < 2; times++) {
            y = r.nextInt(128) + 1;
            x = chunkX + r.nextInt(16) + 8;
            z = chunkZ + r.nextInt(16) + 8;
            sizzleberryBush.getValue().generate(w, r, new BlockPos(x, y, z));
        }

        for (times = 0; times < 150; times++) {
            y = r.nextInt(250) + 1;
            x = chunkX + r.nextInt(16) + 8;
            z = chunkZ + r.nextInt(16) + 8;
            if (isBlockTop(x, y, z, JourneyBlocks.heatSoil, w))
                new WorldGenNetherFlower(w, r, new BlockPos(x, y, z), JourneyBlocks.deathGrass);
        }

        for (times = 0; times < 50; times++) {
            y = r.nextInt(250) + 1;
            x = chunkX + r.nextInt(16) + 8;
            z = chunkZ + r.nextInt(16) + 8;
            if (isBlockTop(x, y, z, JourneyBlocks.heatSoil, w))
                new WorldGenNetherFlower(w, r, new BlockPos(x, y, z), JourneyBlocks.hellBell);
        }

        if (r.nextInt(4) == 0) {
            y = r.nextInt(128) + 1;
            x = chunkX + r.nextInt(16) + 8;
            z = chunkZ + r.nextInt(16) + 8;
            if (y > 20 && y < 110) if (isBlockTop(x, y, z, Blocks.NETHERRACK, w))
                new WorldGenBoilPortal().generate(w, r, new BlockPos(x, y, z));
        }

        if (r.nextInt(4) == 0) {
            y = r.nextInt(128) + 1;
            x = chunkX + r.nextInt(10) + 8;
            z = chunkZ + r.nextInt(10) + 8;
            if (y > 20 && y < 110) if (isBlockTop(x, y, z, Blocks.NETHERRACK, w))
                new WorldGenNetherDungeons().generate(w, r, new BlockPos(x, y, z));
        }

        for (times = 0; times < 15; times++) {
            y = r.nextInt(64);
            x = chunkX + r.nextInt(16) + 8;
            z = chunkZ + r.nextInt(16) + 8;
            if (isBlockTop(x, y, z, Blocks.NETHERRACK, w))
                new WorldGenHellThorn().generate(w, r, new BlockPos(x, y, z));
        }
    }

    public boolean isBlockTop(int x, int y, int z, Block GRASS, World w) {
        return
                w.getBlockState(new BlockPos(x, y - 1, z)) == GRASS.getDefaultState() &&
                        w.getBlockState(new BlockPos(x, y + 1, z)) == Blocks.AIR.getDefaultState() &&
                        w.getBlockState(new BlockPos(x, y + 2, z)) == Blocks.AIR.getDefaultState() &&
                        w.getBlockState(new BlockPos(x, y + 3, z)) == Blocks.AIR.getDefaultState() &&
                        w.getBlockState(new BlockPos(x, y + 4, z)) == Blocks.AIR.getDefaultState() &&
                        w.getBlockState(new BlockPos(x, y + 5, z)) == Blocks.AIR.getDefaultState() &&

                        w.getBlockState(new BlockPos(x, y + 1, z)) != Blocks.LAVA.getDefaultState() &&
                        w.getBlockState(new BlockPos(x, y + 2, z)) != Blocks.LAVA.getDefaultState() &&
                        w.getBlockState(new BlockPos(x, y + 3, z)) != Blocks.LAVA.getDefaultState() &&
                        w.getBlockState(new BlockPos(x, y + 4, z)) != Blocks.LAVA.getDefaultState() &&
                        w.getBlockState(new BlockPos(x, y + 5, z)) != Blocks.LAVA.getDefaultState();
    }

    public void generateOverworld(World w, Random rand, int chunkX, int chunkZ) {
        int x, y, z;

        BlockPos startPos = new BlockPos(chunkX << 4, 0, chunkZ << 4);

        int posX = startPos.getX(),
                posZ = startPos.getZ();
        Biome biome = w.getBiome(startPos);

        ANCIENT_BLOCK_GEN.getValue().generate(w, rand, startPos);

        if (rand.nextInt(5) == 0
                && biome.getDefaultTemperature() >= 0.6F
                && biome.getDefaultTemperature() < 0.9F
                && BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST)) {
            JUICEBERRY_BUSH_GEN.getValue().generate(w, rand, startPos);
        }

        if (rand.nextInt(5) == 0
                && BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST)
                && BiomeDictionary.hasType(biome, BiomeDictionary.Type.COLD)) {
            BRADBERRY_BUSH_GEN.getValue().generate(w, rand, startPos);
        }

        if (rand.nextInt(3) == 0 // in jungle it is harder to find'em so spawn chance was increased
                && biome.decorator.treesPerChunk > 3 // prevent from generating in jungle biomes without much trees
                && BiomeDictionary.hasType(biome, BiomeDictionary.Type.JUNGLE)) {
            TANGLEBERRY_BUSH_GEN.getValue().generate(w, rand, startPos);
        }

        if (rand.nextInt(5) == 0
                && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SWAMP)) {
            BOGBERRY_BUSH_GEN.getValue().generate(w, rand, startPos);
        }

        for (int i = 0; i < 64; i++) {
            TALL_GLOWSHROOMS_GEN.getValue().generate(w, rand, startPos);
        }

        for (int i = 0; i < 55; i++) {
            CAVE_VINE_GEN.getValue().generate(w, rand, startPos);
        }

        for (int i = 0; i < 64; i++) {
            SMALL_GLOWSHROOMS.getValue().generate(w, rand, startPos);
        }

        if (rand.nextInt(3) == 0) {
            SHADIUM_ORE_GEN.getValue().generate(w, rand, startPos);
        }

        if (rand.nextInt(3) == 0) {
            LUNIUM_ORE_GEN.getValue().generate(w, rand, startPos);
        }

        if (rand.nextInt(2) == 0) {
            SAPPHIRE_ORE_GEN.getValue().generate(w, rand, startPos);
        }

        for (int i = 0; i < 4; i++) {
            IRIDIUM_ORE_GEN.getValue().generate(w, rand, startPos);
        }

        if (rand.nextInt(Config.towerDungeon) == 0) {
            y = rand.nextInt(200);
            x = posX + rand.nextInt(16) + 8;
            z = posZ + rand.nextInt(16) + 8;
            if (w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.GRASS.getDefaultState())
                new WorldGenTowerDungeon().generate(w, rand, new BlockPos(x, y, z));
        }

        if (rand.nextInt(Config.mageHouse) == 0) {
            y = rand.nextInt(200);
            x = posX + rand.nextInt(16) + 8;
            z = posZ + rand.nextInt(16) + 8;
            if (w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.GRASS.getDefaultState() ||
                    w.getBlockState(new BlockPos(x, y, z)) == Blocks.GRASS.getDefaultState() ||
                    w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.SAND.getDefaultState() ||
                    w.getBlockState(new BlockPos(x, y, z)) == Blocks.SAND.getDefaultState())
                new WorldGenMageHouse().generate(w, rand, new BlockPos(x, y, z));
        }
        if (rand.nextInt(Config.blacksmithHouse) == 0) {
            y = rand.nextInt(200);
            x = posX + rand.nextInt(16) + 8;
            z = posZ + rand.nextInt(16) + 8;
            if (w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.GRASS.getDefaultState() ||
                    w.getBlockState(new BlockPos(x, y, z)) == Blocks.GRASS.getDefaultState() ||
                    w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.SAND.getDefaultState() ||
                    w.getBlockState(new BlockPos(x, y, z)) == Blocks.SAND.getDefaultState())
                new WorldGenBlacksmithHouse().generate(w, rand, new BlockPos(x, y, z));

        }
    }

    public void generateEnd(World w, Random r, int chunkX, int chunkZ) {
        int x, y, z;
        int times;
        for (times = 0; times < 6; times++) {
            y = r.nextInt(160);
            x = chunkX + r.nextInt(16);
            z = chunkZ + r.nextInt(16);
            worldMinableGenEnd(JourneyBlocks.enderilliumOre, 5, w, x, y, z);
        }
    }

    private void generateCorba(World w, Random r, int chunkX, int chunkZ) {
        int i = 0;
        for (i = 0; i < 10; i++) WorldGenJourney.generateJourneyDimensions(13, w, chunkX, chunkZ);
        for (i = 0; i < 10; i++) WorldGenJourney.generateJourneyDimensions(14, w, chunkX, chunkZ);
    }

    private void generateFrozen(World w, Random r, int chunkX, int chunkZ) {
        int i = 0;
        for (i = 0; i < 25; i++) WorldGenJourney.generateJourneyDimensions(9, w, chunkX, chunkZ);
    }

    private void generateBoilingPoint(World w, Random r, int chunkX, int chunkZ) {
        int i = 0;
        BlockPos chunkStart = new BlockPos(chunkX, 0, chunkZ);
        BlockPos randomPosForMinable = chunkStart.add(r.nextInt(16), r.nextInt(w.getHeight()), r.nextInt(16));

        if (r.nextInt(4) == 0) WorldGenJourney.generateJourneyDimensions(4, w, chunkX, chunkZ);
        for (i = 0; i < 50; i++) WorldGenJourney.generateJourneyDimensions(5, w, chunkX, chunkZ);
    }

    private void generateDepths(World w, Random r, int chunkX, int chunkZ) {
        int i = 0;
        //for(i = 0; i < 25; i++) GenerationHelper.generateJourneyDimensions(12, w, chunkX, chunkZ);
        //for(i = 0; i < 25; i++) GenerationHelper.generateJourneyDimensions(2, w, chunkX, chunkZ);
        //for(i = 0; i < 5; i++) GenerationHelper.generateJourneyDimensions(10, w, chunkX, chunkZ);
    }

    private void generateWither(World w, Random r, int chunkX, int chunkZ) {
        int i = 0;
        for (i = 0; i < 25; i++) WorldGenJourney.generateJourneyDimensions(21, w, chunkX, chunkZ);
    }

    private void generateEuca(World w, Random r, int chunkX, int chunkZ) {
        int i = 0;

        for (i = 0; i < 8; i++) {
            WorldGenJourney.generateJourneyDimensions(7, w, chunkX, chunkZ);
            WorldGenJourney.generateJourneyDimensions(8, w, chunkX, chunkZ);
            WorldGenJourney.generateJourneyDimensions(6, w, chunkX, chunkZ);
        }

        for (i = 0; i < 30; i++) WorldGenJourney.generateJourneyDimensions(0, w, chunkX, chunkZ);
        for (i = 0; i < 30; i++) WorldGenJourney.generateJourneyDimensions(15, w, chunkX, chunkZ);
        for (i = 0; i < 30; i++) WorldGenJourney.generateJourneyDimensions(16, w, chunkX, chunkZ);
        for (i = 0; i < 30; i++) WorldGenJourney.generateJourneyDimensions(17, w, chunkX, chunkZ);
    }

    private void generateCloudia(World w, Random r, int chunkX, int chunkZ) {
        int i = 0;
        for (i = 0; i < 2; i++) WorldGenJourney.generateJourneyDimensions(19, w, chunkX, chunkZ);
        for (i = 0; i < 2; i++) WorldGenJourney.generateJourneyDimensions(20, w, chunkX, chunkZ);

        BlockPos randomPosForMinable = new BlockPos(chunkX + r.nextInt(16), r.nextInt(64), chunkZ + r.nextInt(16));

        if (r.nextInt(3) == 0) {
            cloudiaRock.getValue().generate(w, r, randomPosForMinable);
        }

        if (r.nextInt(3) == 0) {
            luniteOre.getValue().generate(w, r, randomPosForMinable);
        }
    }
}