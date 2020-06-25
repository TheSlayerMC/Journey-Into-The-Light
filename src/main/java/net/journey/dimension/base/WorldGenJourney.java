package net.journey.dimension.base;

import net.journey.api.block.GroundPredicate;
import net.journey.dimension.base.gen.JWorldGenMinable;
import net.journey.dimension.base.gen.JWorldGenPlants;
import net.journey.dimension.base.gen.WorldGenBush;
import net.journey.dimension.base.gen.WorldGenSingleBlock;
import net.journey.dimension.corba.gen.JWorldGenRuins;
import net.journey.dimension.nether.gen.WorldGenModGlowstone;
import net.journey.dimension.nether.gen.WorldGenNetherFlower;
import net.journey.dimension.nether.gen.WorldGenNetherShroom;
import net.journey.dimension.nether.gen.WorldGenNetherTower;
import net.journey.dimension.nether.gen.dungeon.WorldGenBoilPortal;
import net.journey.dimension.nether.gen.dungeon.WorldGenGhastTower;
import net.journey.dimension.nether.gen.dungeon.WorldGenNetherDungeons;
import net.journey.dimension.nether.gen.trees.WorldGenBleedheartTree0;
import net.journey.dimension.nether.gen.trees.WorldGenBleedheartTree1;
import net.journey.dimension.nether.gen.trees.WorldGenEarthenTree;
import net.journey.dimension.overworld.gen.*;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.Config;
import net.journey.util.handler.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;
import java.util.function.Supplier;

public class WorldGenJourney implements IWorldGenerator {

    private static final LazyLoadBase<JWorldGenMinable> SHADIUM_ORE_GEN;
    private static final LazyLoadBase<JWorldGenMinable> LUNIUM_ORE_GEN;
    private static final LazyLoadBase<JWorldGenMinable> SAPPHIRE_ORE_GEN;
    private static final LazyLoadBase<JWorldGenMinable> IRIDIUM_ORE_GEN;

    private static final LazyLoadBase<WorldGenBush> JUICEBERRY_BUSH_GEN = create(() -> new WorldGenBush(JourneyBlocks.juiceberryBush, Blocks.GRASS));
    private static final LazyLoadBase<WorldGenBush> BRADBERRY_BUSH_GEN = create(() -> new WorldGenBush(JourneyBlocks.bradberryBush, Blocks.GRASS));
    private static final LazyLoadBase<WorldGenBush> TANGLEBERRY_BUSH_GEN = create(() -> new WorldGenBush(JourneyBlocks.tangleberryBush, Blocks.GRASS));
    private static final LazyLoadBase<WorldGenBush> BOGBERRY_BUSH_GEN = create(() -> new WorldGenBush(JourneyBlocks.bogberryBush, Blocks.GRASS));
    private static final LazyLoadBase<WorldGenBush> SIZZLEBERRY_BUSH_GEN = create(() -> new WorldGenBush(JourneyBlocks.sizzleberryBush, Blocks.NETHERRACK));

    private static final LazyLoadBase<JWorldGenRuins> RUINS_GEN = create(() -> new JWorldGenRuins(
            GroundPredicate.blockPredicate(block -> block == Blocks.GRASS),
            JWorldGenRuins.LootType.LOOT_BOX,
            Blocks.COBBLESTONE.getDefaultState(),
            Blocks.MOSSY_COBBLESTONE.getDefaultState(),
            Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CRACKED),
            Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY),
            Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.DEFAULT)));

    /*private static final LazyLoadBase<JWorldGenRuins> RUINS_GEN_SPECIAL = create(() -> new JWorldGenRuins
            (Blocks.GRASS,
                    JWorldGenRuins.LootType.CONTAINER,
                    Blocks.COBBLESTONE,
                    Blocks.MOSSY_COBBLESTONE,
                    Blocks.STONEBRICK).setSpecialBlock(JourneyBlocks.eucaChest).setLootTable(JourneyLootTables.LOOT_DIAMOND));*/

    private static final LazyLoadBase<WorldGenTallGlowshroom> TALL_GLOWSHROOMS_GEN = create(WorldGenTallGlowshroom::new);
    private static final LazyLoadBase<WorldGenCaveVines> CAVE_VINE_GEN = create(WorldGenCaveVines::new);
    private static final LazyLoadBase<WorldGenSmallGlowshrooms> SMALL_GLOWSHROOMS = create(WorldGenSmallGlowshrooms::new);

    private static final LazyLoadBase<WorldGenSingleBlock> ANCIENT_BLOCK_GEN = create(() -> new WorldGenSingleBlock(JourneyBlocks.ancientMachineBlock, 254, 1));
    @Deprecated // use per-chunk random instance which comes from method params
    private static Random r = new Random();

    static {
        SHADIUM_ORE_GEN = create(() -> JWorldGenMinable.create(JourneyBlocks.shadiumOre, Config.shadiumOreGenAmount, Config.shadiumOreGenMaxY));
        LUNIUM_ORE_GEN = create(() -> JWorldGenMinable.create(JourneyBlocks.luniumOre, Config.luniumOreGenAmount, Config.luniumOreGenMaxY));
        SAPPHIRE_ORE_GEN = create(() -> JWorldGenMinable.create(JourneyBlocks.sapphireOre, Config.sapphireOreGenAmount, Config.sapphireOreGenMaxY));
        IRIDIUM_ORE_GEN = create(() -> JWorldGenMinable.create(JourneyBlocks.iridiumOre, Config.iridiumOreGenAmount, Config.iridiumOreGenMaxY));
    }

    public WorldGenJourney() {
        r = new Random();
        LogHelper.info("Loading world generator");
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

    private static void worldMinableGenNether(Block spawn, int vein, World w, int x, int y, int z) {
        (new WorldGenMinable(spawn.getDefaultState(), vein, BlockStateMatcher.forBlock(Blocks.NETHERRACK))).generate(w, r, new BlockPos(x, y, z));
    }

    private static void worldGenNetherFeature(Block spawn, Block toGenerateIn, int vein, World w, int x, int y, int z) {
        (new WorldGenMinable(spawn.getDefaultState(), vein, BlockStateMatcher.forBlock(toGenerateIn))).generate(w, r, new BlockPos(x, y, z));
    }

    private static void worldMinableGenEnd(Block spawn, int vein, World w, int x, int y, int z) {
        (new WorldGenMinable(spawn.getDefaultState(), vein, BlockStateMatcher.forBlock(Blocks.END_STONE))).generate(w, r, new BlockPos(x, y, z));
    }

    public void generateNether(World w, Random r, int chunkX, int chunkZ) {
        int x, y, z;
        int times;
        BlockPos chunkStart = new BlockPos(chunkX, 0, chunkZ);

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
            (new WorldGenModGlowstone(w, r, chunkStart, JourneyBlocks.bleedstone)).generate(w, r, new BlockPos(x, y, z));
        }

        if (r.nextInt(3) == 0) {
            y = r.nextInt(256) + 1;
            x = chunkX + r.nextInt(16) + 8;
            z = chunkZ + r.nextInt(16) + 8;
            (new WorldGenModGlowstone(w, r, chunkStart, JourneyBlocks.smithstone)).generate(w, r, new BlockPos(x, y, z));
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
            ANCIENT_BLOCK_GEN.getValue().generate(w, r, new BlockPos(x, y, z));
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

        for (times = 0; times < 5; times++) {
            y = r.nextInt(128) + 1;
            x = chunkX + r.nextInt(16) + 8;
            z = chunkZ + r.nextInt(16) + 8;
            SIZZLEBERRY_BUSH_GEN.getValue().generate(w, r, new BlockPos(x, y, z));
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
            x = chunkX + r.nextInt(16) + 8;
            z = chunkZ + r.nextInt(16) + 8;
            if (y > 20 && y < 110) if (isBlockTop(x, y, z, Blocks.NETHERRACK, w) || isBlockTop(x, y, z, JourneyBlocks.heatSand, w))
                new WorldGenGhastTower().generate(w, r, new BlockPos(x, y, z));
        }

        if (r.nextInt(5) == 0) {
            y = r.nextInt(128) + 1;
            x = chunkX + r.nextInt(10) + 8;
            z = chunkZ + r.nextInt(10) + 8;
            if (y > 20 && y < 110) if (isBlockTop(x, y, z, Blocks.NETHERRACK, w))
                new WorldGenNetherDungeons().generate(w, r, new BlockPos(x, y, z));
        }

        new JWorldGenPlants(JourneyBlocks.hellThorn, 15).generate(w, r, chunkStart); //TODO change generator to the cave flower behaviour (random height)
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

        if (rand.nextInt(50) == 0) {
            RUINS_GEN.getValue().generate(w, rand, startPos);
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

        for (int i = 0; i < Config.shadiumOreTrys; i++) {
            SHADIUM_ORE_GEN.getValue().generate(w, rand, startPos);
        }

        for (int i = 0; i < Config.luniumOreTrys; i++) {
            LUNIUM_ORE_GEN.getValue().generate(w, rand, startPos);
        }

        for (int i = 0; i < Config.sapphireOreTrys; i++) {
            SAPPHIRE_ORE_GEN.getValue().generate(w, rand, startPos);
        }

        for (int i = 0; i < Config.iridiumOreTrys; i++) {
            IRIDIUM_ORE_GEN.getValue().generate(w, rand, startPos);
        }

        if (rand.nextInt(Config.towerDungeon) == 0) {
            y = rand.nextInt(200);
            x = posX + rand.nextInt(16) + 8;
            z = posZ + rand.nextInt(16) + 8;
            if (w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.GRASS.getDefaultState())
                new WorldGenTowerDungeon().generate(w, rand, new BlockPos(x, y, z));
        }

        if (rand.nextInt(64) == 0) {
            y = rand.nextInt(30);
            x = posX + rand.nextInt(16) + 8;
            z = posZ + rand.nextInt(16) + 8;
            if (w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.STONE.getDefaultState() && y > 10)
                new WorldGenRockiteDungeon().generate(w, rand, new BlockPos(x, y, z));
        }

        if (rand.nextInt(5) == 0) {
            y = rand.nextInt(200);
            x = posX + rand.nextInt(16) + 8;
            z = posZ + rand.nextInt(16) + 8;
            if (w.getBlockState(new BlockPos(x, y - 1, z)) == Blocks.GRASS.getDefaultState())
                new WorldGenAncientDungeon().generate(w, rand, new BlockPos(x, y, z));
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
}