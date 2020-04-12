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
	private CloudiaZoneBase[] bridges;
	private World worldObj;
	private Random random;
	private Map chunkTileEntityMap;

    public ChunkProviderCloudia(World worldIn, long seed) {
		worldObj = worldIn;
		random = new Random(seed);
		rooms = new ArrayList(2);
		rooms.add(new CloudiaDungeon1());
		rooms.add(new CloudiaAltar());
		bridges = new CloudiaZoneBase[] {new CloudiaBridgeAll(), new CloudiaBridgeNS(), new CloudiaBridgeEW()};
		this.chunkTileEntityMap = new HashMap();
    }

    @Override
    public Chunk generateChunk(int chunkX, int chunkZ) {
        CloudiaChunkPrimer cloudiaChunk = new CloudiaChunkPrimer();

		int bottomLayer = 16;
		int secondLayer = bottomLayer * 2 - 5;

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
		chunkTileEntityMap.put(new ChunkCoords(chunkX, chunkZ), cloudiaChunk.chunkTileEntityPositions);
		
		Chunk chunk = new Chunk(this.worldObj, cloudiaChunk, chunkX, chunkZ);
		chunk.generateSkylightMap();
		return chunk;
    }

    @Override
    public void populate(int cx, int cz) {
       
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