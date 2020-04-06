package net.journey.dimension.cloudia;

import net.journey.JourneyBlocks;
import net.journey.dimension.cloudia.gen.*;
import net.journey.dimension.cloudia.zone.CloudiaAltar;
import net.journey.dimension.cloudia.zone.CloudiaBridgeAll;
import net.journey.dimension.cloudia.zone.CloudiaBridgeEW;
import net.journey.dimension.cloudia.zone.CloudiaBridgeNS;
import net.journey.dimension.cloudia.zone.CloudiaDungeon1;
import net.journey.dimension.cloudia.zone.CloudiaZoneBase;
import net.journey.dimension.overworld.gen.WorldGenModFlower;
import net.journey.dimension.senterian.SenterianChunkPrimer;
import net.journey.dimension.senterian.ChunkProviderSenterian.ChunkCoords;
import net.journey.dimension.senterian.room.SenterianRoomBase;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChunkProviderCloudia implements IChunkGenerator {

	public class ChunkCoords {
		public final int chunkCoordX;
		public final int chunkCoordZ;

		public ChunkCoords(int X, int Z) {
			this.chunkCoordX = X;
			this.chunkCoordZ = Z;
		}

		public boolean equals(Object o) {
			if (!(o instanceof ChunkCoords)) {
				return false;
			} else {
				ChunkCoords chunkCoords = (ChunkCoords) o;
				return chunkCoords.chunkCoordX == this.chunkCoordX && chunkCoords.chunkCoordZ == this.chunkCoordZ;
			}
		}

		public int hashCode() {
			return this.chunkCoordX + this.chunkCoordZ * 31;
		}
	}
	
	private ArrayList rooms;
	private ArrayList bigRooms;
	//private SenterianRoomStairs stairs;
	private CloudiaZoneBase[] bridges;
	private World worldObj;
	private Random random;
	private Map chunkTileEntityMap;
	private Biome[] biomesForGeneration;

    public ChunkProviderCloudia(World worldIn, long seed) {
		worldObj = worldIn;
		random = new Random(seed);
		rooms = new ArrayList(3);
		rooms.add(new CloudiaDungeon1());
		//Rooms.add(new SenterianRoomChest());
		rooms.add(new CloudiaAltar());
		//rooms.add(new SenterianRoomSpawner1());
		/*Rooms.add(new SenterianRoomSpawner2());
        Rooms.add(new SenterianRoomSpawner3());
        Rooms.add(new SenterianRoomSpawner4());*/
		bridges = new CloudiaZoneBase[] {new CloudiaBridgeAll(), new CloudiaBridgeNS(), new CloudiaBridgeEW()};
		//stairs = new SenterianRoomStairs();
		this.chunkTileEntityMap = new HashMap();
		this.chunkTileEntityMap = new HashMap();
        new NoiseGeneratorOctaves(this.random, 4);
        new NoiseGeneratorOctaves(this.random, 4);
    }

    @Override
    public Chunk generateChunk(int cx, int cz) {
        //this.random.setSeed(cx * 391279512714L + cz * 132894987741L);		
        CloudiaChunkPrimer cloudiaChunk = new CloudiaChunkPrimer();

		int bottomLayer = 0;
		int secondLayer = 16;

		//Generates all rooms
		CloudiaZoneBase room = (CloudiaZoneBase) (rooms.get(random.nextInt(rooms.size())));
		room = (CloudiaZoneBase) (rooms.get(random.nextInt(rooms.size())));
		room.generate(cloudiaChunk, random, 0, secondLayer, 0);

		room = (CloudiaZoneBase) (rooms.get(random.nextInt(rooms.size())));
		room.generate(cloudiaChunk, random, 0, bottomLayer, 0);

		//Chance to generate stair room on all but top layer

		//These double as a hallway and a blocker on the exit of the room next to it
		int hallwayRarity = 8;
		if(random.nextInt(hallwayRarity) == 0)
			bridges[random.nextInt(bridges.length)].generate(cloudiaChunk, random, 0, bottomLayer, 0);

		if(random.nextInt(hallwayRarity) == 0)
			bridges[random.nextInt(bridges.length)].generate(cloudiaChunk, random, 0, secondLayer, 0);


		//These rooms need to be generated last
		
		//Forces a roof over the whole room, gets generated at final set
		chunkTileEntityMap.put(new ChunkCoords(cx, cz), cloudiaChunk.chunkTileEntityPositions);
		
        CloudiaChunkPrimer primer = new CloudiaChunkPrimer();
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
        this.random.setSeed(this.worldObj.getSeed() * (cx + cz) * this.random.nextInt());
        final int x1 = cx * 16;
        final int z1 = cz * 16;
        int i;
        final Random r = random;

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

        if (this.random.nextInt(60) == 0) {
            BlockPos pos = WorldGenAPI.createRandom(x1, 20, 84, z1, r, 8);
            if (worldObj.isAirBlock(pos))
                tower.generate(worldObj, random, pos);
        }

        

        if (this.random.nextInt(30) == 0) {
            BlockPos pos = WorldGenAPI.createRandom(x1, 20, 84, z1, r, 8);
            if (worldObj.isAirBlock(pos))
                island.generate(worldObj, random, pos);
        }

        if (this.random.nextInt(2) == 0) {
            generateStructure(x1, z1, r, lamp);
        }

        if (this.random.nextInt(2) == 0) {
            generateStructure(x1, z1, r, tree);
        }

        if (this.random.nextInt(2) == 0) {
            BlockPos pos = WorldGenAPI.createRandom(x1, 20, 84, z1, r, 8);
            if (worldObj.isAirBlock(pos))
                island.generate(worldObj, random, pos);
        }

        if (this.random.nextInt(40) == 0) {
            BlockPos pos = new BlockPos(x1, random.nextInt(20) + 64 , z1 + r.nextInt(8));
            if (worldObj.isAirBlock(pos)){
                village.generate(worldObj, random, pos);
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
            tower.generate(worldObj, random, pos);
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