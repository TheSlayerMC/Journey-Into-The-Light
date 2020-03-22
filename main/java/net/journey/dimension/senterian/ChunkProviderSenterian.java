package net.journey.dimension.senterian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.journey.dimension.senterian.room.*;
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

	private ArrayList rooms;
	private SenterianCeiling ceiling;
	private SenterianRoomStairs stairs;
	private World worldObj;
	private Random random;
	private Map chunkTileEntityMap;
	private Biome[] biomesForGeneration;

	public ChunkProviderSenterian(World world, long seed) {

		worldObj = world;
		random = new Random(seed);

		rooms = new ArrayList(6);
		rooms.add(new SenterianRoomHall1());
		rooms.add(new SenterianRoomHall2());
		rooms.add(new SenterianRoomHall3());
		rooms.add(new SenterianRoomStairs());
		//rooms.add(new RoomChest());
		rooms.add(new SenterianRoomNPC());
		rooms.add(new SenterianRoomSpawner1());

		stairs = new SenterianRoomStairs();
		ceiling = new SenterianCeiling();
		this.chunkTileEntityMap = new HashMap();
	}

	@Override
	public Chunk generateChunk(int chunkX, int chunkZ) {
		SenterianChunkPrimer senterianChunk = new SenterianChunkPrimer();
		
		SenterianRoomBase room = (SenterianRoomBase) (rooms.get(random.nextInt(rooms.size())));
		room.generate(senterianChunk, random, 0, 15, 0);
		
		room = (SenterianRoomBase) (rooms.get(random.nextInt(rooms.size())));
		room.generate(senterianChunk, random, 0, 10, 0);

		room = (SenterianRoomBase) (rooms.get(random.nextInt(rooms.size())));
		room.generate(senterianChunk, random, 0, 5, 0);
		
		room = (SenterianRoomBase) (rooms.get(random.nextInt(rooms.size())));
		room.generate(senterianChunk, random, 0, 0, 0);
		
		ceiling.generate(senterianChunk, random, 0, 20, 0);
		
		/*if(random.nextInt(15) == 0)
			stairs.generate(senterianChunk, random, 0, 0, 0);
		
		if(random.nextInt(15) == 0)
			stairs.generate(senterianChunk, random, 0, 5, 0);
		
		if(random.nextInt(15) == 0)
			stairs.generate(senterianChunk, random, 0, 10, 0);*/
		
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