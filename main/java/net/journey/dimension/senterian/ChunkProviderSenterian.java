package net.journey.dimension.senterian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.journey.dimension.senterian.room.SenterianCeiling;
import net.journey.dimension.senterian.room.SenterianRoomBase;
import net.journey.dimension.senterian.room.SenterianRoomHall1;
import net.journey.dimension.senterian.room.SenterianRoomHall2;
import net.journey.dimension.senterian.room.SenterianRoomHall3;
import net.journey.dimension.senterian.room.SenterianRoomNPC;
import net.journey.dimension.senterian.room.SenterianRoomSpawner1;
import net.journey.dimension.senterian.room.SenterianRoomStairs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;

public class ChunkProviderSenterian implements IChunkGenerator {

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
				return chunkCoords.chunkCoordX == this.chunkCoordX
						&& chunkCoords.chunkCoordZ == this.chunkCoordZ;
			}
		}

		public int hashCode() {
			return this.chunkCoordX + this.chunkCoordZ * 31;
		}
	}

	private ArrayList rooms;
	private SenterianCeiling ceiling;
	private SenterianRoomStairs stairs;
	private SenterianRoomBase[] hallways;
	private World worldObj;
	private Random random;
	private Map chunkTileEntityMap;
	private Biome[] biomesForGeneration;

	public ChunkProviderSenterian(World world, long seed) {

		worldObj = world;
		random = new Random(seed);

		rooms = new ArrayList(4);

		rooms.add(new SenterianRoomHall1());
		//Rooms.add(new SenterianRoomChest());
		//Rooms.add(new SenterianRoomStairs());
		rooms.add(new SenterianRoomNPC());
		rooms.add(new SenterianRoomSpawner1());
		/*Rooms.add(new SenterianRoomSpawner2());
        Rooms.add(new SenterianRoomSpawner3());
        Rooms.add(new SenterianRoomSpawner4());*/
		hallways = new SenterianRoomBase[] {new SenterianRoomHall2(), new SenterianRoomHall3()};
		stairs = new SenterianRoomStairs();
		ceiling = new SenterianCeiling();
		this.chunkTileEntityMap = new HashMap();
	}

	@Override
	public Chunk generateChunk(int chunkX, int chunkZ) {
		SenterianChunkPrimer senterianChunk = new SenterianChunkPrimer();
		
		int bottomLayer = 0;
		int secondLayer = 5;
		int thirdLayer = 10;
		int topLayer = 15;
		
		//Generates all rooms
		SenterianRoomBase room = (SenterianRoomBase) (rooms.get(random.nextInt(rooms.size())));
		room.generate(senterianChunk, random, 0, topLayer, 0);
		
		room = (SenterianRoomBase) (rooms.get(random.nextInt(rooms.size())));
		room.generate(senterianChunk, random, 0, thirdLayer, 0);

		room = (SenterianRoomBase) (rooms.get(random.nextInt(rooms.size())));
		room.generate(senterianChunk, random, 0, secondLayer, 0);
		
		room = (SenterianRoomBase) (rooms.get(random.nextInt(rooms.size())));
		room.generate(senterianChunk, random, 0, bottomLayer, 0);
				
		//Chance to generate stair room on all but top layer
		int stairRarity = 5;
		if(random.nextInt(stairRarity) == 0)
			stairs.generate(senterianChunk, random, 0, bottomLayer, 0);
		
		if(random.nextInt(stairRarity) == 0)
			stairs.generate(senterianChunk, random, 0, secondLayer, 0);
		
		if(random.nextInt(stairRarity) == 0)
			stairs.generate(senterianChunk, random, 0, thirdLayer, 0);
		
		//These double as a hallway and a blocker on the exit of the room next to it
		int hallwayRarity = 8;
		if(random.nextInt(hallwayRarity) == 0)
			hallways[random.nextInt(hallways.length)].generate(senterianChunk, random, 0, bottomLayer, 0);
		
		if(random.nextInt(hallwayRarity) == 0)
			hallways[random.nextInt(hallways.length)].generate(senterianChunk, random, 0, secondLayer, 0);
		
		if(random.nextInt(hallwayRarity) == 0)
			hallways[random.nextInt(hallways.length)].generate(senterianChunk, random, 0, thirdLayer, 0);
		
		if(random.nextInt(hallwayRarity) == 0)
			hallways[random.nextInt(hallways.length)].generate(senterianChunk, random, 0, topLayer, 0);
		

		//Forces a roof over the whole room
		ceiling.generate(senterianChunk, random, 0, 20, 0);
		chunkTileEntityMap.put(new ChunkCoords(chunkX, chunkZ), senterianChunk.chunkTileEntityPositions);

		Chunk chunk = new Chunk(this.worldObj, senterianChunk, chunkX, chunkZ);
		byte[] abyte = chunk.getBiomeArray();
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);

		for (int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
		}
		
		
		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void populate(int chunkX, int chunkZ) {
		int x = chunkX * 16;
		int z = chunkZ * 16;
		
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome biome = this.worldObj.getBiome(pos);
		return biome.getSpawnableList(creatureType);
	}

	@Override
	public void recreateStructures(Chunk c, int x, int z) { }

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