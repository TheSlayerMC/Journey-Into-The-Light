package net.journey.dimension.cloudia;

import net.journey.JourneyBlocks;
import net.journey.dimension.cloudia.gen.*;
import net.journey.dimension.overworld.gen.WorldGenModFlower;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChunkProviderCloudia implements IChunkGenerator {

    private Random rand;
    private World worldObj;
    private Biome[] biomesForGeneration;


    public ChunkProviderCloudia(World worldIn, long seed) {
        this.worldObj = worldIn;
        this.rand = new Random(seed);
        new NoiseGeneratorOctaves(this.rand, 4);
        new NoiseGeneratorOctaves(this.rand, 4);
    }

    @Override
    public Chunk generateChunk(int cx, int cz) {
        this.rand.setSeed(cx * 391279512714L + cz * 132894987741L);
        ChunkPrimer primer = new ChunkPrimer();
        this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, cx * 16, cz * 16, 16, 16);
        Chunk chunk = new Chunk(this.worldObj, primer, cx, cz);
        byte[] abyte = chunk.getBiomeArray();
        for (int k = 0; k < abyte.length; ++k) abyte[k] = (byte) Biome.getIdForBiome(this.biomesForGeneration[k]);
        chunk.generateSkylightMap();
        return chunk;
    }

    private static WorldGenerator castle = new WorldGenStarlightCastle();
    private static WorldGenerator tower = new WorldGenTower();
    private static WorldGenerator hut = new WorldGenHut();
    private static WorldGenerator lamp = new WorldGenCloudiaLamp();
    private static WorldGenerator tree = new WorldGenStarlightTree();
    private static WorldGenerator island = new WorldGenIsland();
    private static WorldGenerator village = new WorldGenStarlightVillage();

    private WorldGenerator tallGrass = new WorldGenModFlower(JourneyBlocks.cloudiaTallGrass, JourneyBlocks.cloudiaGrass ,false);
    private WorldGenerator flower = new WorldGenModFlower(JourneyBlocks.cloudiaFlower, JourneyBlocks.cloudiaGrass, false);

    @Override
    public void populate(int cx, int cz) {
        this.rand.setSeed(this.worldObj.getSeed() * (cx + cz) * this.rand.nextInt());
        final int x1 = cx * 16;
        final int z1 = cz * 16;
        int i;
        final Random r = rand;

        BlockPos chunkStart = new BlockPos(x1, 0, z1);

        for (i = 0; i < 128; i++) {
            tallGrass.generate(worldObj, r, chunkStart);
            flower.generate(worldObj, r,    chunkStart);
        }
		
		/* for(i = 0; i < 100; i++) {
			y = r.nextInt(128) + 1;
			if(isBlockTop(x, y, z, JourneyBlocks.cloudiaGrass, worldObj)) 
				new WorldGenMelon(worldObj, r, new BlockPos(x, y, z), JourneyCrops.airRootMelon, JourneyBlocks.cloudiaGrass).generate(worldObj, r, new BlockPos(x, y, z));
		} */

        if (this.rand.nextInt(60) == 0) {
            BlockPos pos = WorldGenAPI.createRandom(x1, 20, 84, z1, r, 8);
            if (worldObj.isAirBlock(pos))
                tower.generate(worldObj, rand, pos);
        }

        if (this.rand.nextInt(60) == 0) {
            BlockPos pos = WorldGenAPI.createRandom(x1, 20, 84, z1, r, 8);
            if (worldObj.isAirBlock(pos))
                tower.generate(worldObj, rand, pos);
        }

        if (this.rand.nextInt(30) == 0) {
            BlockPos pos = WorldGenAPI.createRandom(x1, 20, 84, z1, r, 8);
            if (worldObj.isAirBlock(pos))
                island.generate(worldObj, rand, pos);
        }

        if (this.rand.nextInt(2) == 0) {
            generateStructure(x1, z1, r, lamp);
        }

        if (this.rand.nextInt(2) == 0) {
            generateStructure(x1, z1, r, tree);
        }

        if (this.rand.nextInt(2) == 0) {
            BlockPos pos = WorldGenAPI.createRandom(x1, 20, 84, z1, r, 8);
            if (worldObj.isAirBlock(pos))
                island.generate(worldObj, rand, pos);
        }

        // strucutre too big, will lag anyway
        if (this.rand.nextInt(40) == 0) {
            BlockPos pos = new BlockPos(x1 + 1, rand.nextInt(20) + 64 , z1 + r.nextInt(8));
            if (worldObj.isAirBlock(pos)){
                village.generate(worldObj, rand, pos);
            }
        }

        /**if (this.rand.nextInt(15) == 0) {
         x = x1 + this.rand.nextInt(16) + 8;
         z = z1 + this.rand.nextInt(16) + 8;
         int y = rand.nextInt(250);
         if(y > 30 && y < 130) new WorldGenCloudiaLand().generate(worldObj, rand, new BlockPos(x, y, z));
         }*/
    }

    private void generateStructure(int x1, int z1, Random r, WorldGenerator tower) {
        BlockPos pos = WorldGenAPI.createRandom(x1, 20, z1, r).up(64);
        if (worldObj.isAirBlock(pos))
            tower.generate(worldObj, rand, pos);
    }

    public static boolean isBlockTop(int x, int y, int z, Block grass, World w) {
        if (w.getBlockState(new BlockPos(x, y, z)).getBlock() != grass)
            return false;

        AtomicBoolean result = new AtomicBoolean(true);

        BlockPos.getAllInBoxMutable(x, y + 1, z, x, y + 5, z).forEach(pos -> {
            if (!w.isAirBlock(pos)) {
                result.set(false);
                return;
            }
        });

        return result.get();
    }

    @Override
    public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = this.worldObj.getBiome(pos);
        return biome.getSpawnableList(creatureType);
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
    public void recreateStructures(Chunk chunkIn, int x, int z) {
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}