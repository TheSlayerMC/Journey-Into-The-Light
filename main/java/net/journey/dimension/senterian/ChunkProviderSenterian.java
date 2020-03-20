package net.journey.dimension.senterian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.journey.dimension.senterian.room.RoomBase;
import net.journey.dimension.senterian.room.RoomChest;
import net.journey.dimension.senterian.room.RoomHall;
import net.journey.dimension.senterian.room.RoomNPC;
import net.journey.dimension.senterian.room.RoomSpawner1;
import net.journey.dimension.senterian.room.RoomStairs;
import net.journey.dimension.senterian.room.SenterianCeiling;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
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

	private ArrayList Rooms;
	private ArrayList BossRooms;
	private SenterianCeiling Ceiling;
	private World worldObj;
	private Random random;
	private Map chunkTileEntityMap;
	private Biome[] biomesForGeneration;

	public ChunkProviderSenterian(World world, long seed) {

		worldObj = world;
		random = new Random(seed);

		Rooms = new ArrayList(4);

		Rooms.add(new RoomHall());
		//Rooms.add(new RoomChest());
		Rooms.add(new RoomStairs());
		Rooms.add(new RoomNPC());
		Rooms.add(new RoomSpawner1());
		/*Rooms.add(new RoomSpawner2());
        Rooms.add(new RoomSpawner3());
        Rooms.add(new RoomSpawner4());*/
		Ceiling = new SenterianCeiling();
		this.chunkTileEntityMap = new HashMap();
	}

	@Override
	public Chunk generateChunk(int chunkX, int chunkZ) {
		SenterianChunkPrimer senterianChunk = new SenterianChunkPrimer();
		
		RoomBase room = (RoomBase) (Rooms.get(random.nextInt(Rooms.size())));
		room.generate(senterianChunk, random, 0, 15, 0);
		
		room = (RoomBase) (Rooms.get(random.nextInt(Rooms.size())));
		room.generate(senterianChunk, random, 0, 10, 0);

		room = (RoomBase) (Rooms.get(random.nextInt(Rooms.size())));
		room.generate(senterianChunk, random, 0, 5, 0);
		
		room = (RoomBase) (Rooms.get(random.nextInt(Rooms.size())));
		room.generate(senterianChunk, random, 0, 0, 0);
		
		Ceiling.generate(senterianChunk, random, 0, 19, 0);
		
		chunkTileEntityMap.put(new ChunkCoords(chunkX, chunkZ), senterianChunk.chunkTileEntityPositions);

		Chunk chunk = new Chunk(this.worldObj, senterianChunk, chunkX, chunkZ);
		byte[] abyte = chunk.getBiomeArray();
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);

		for (int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
		}
		if(random.nextInt(2) == 0)
		new RoomStairs().generate(senterianChunk, random, 0, 0, 0);
		
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