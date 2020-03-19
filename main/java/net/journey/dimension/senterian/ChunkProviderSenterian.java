package net.journey.dimension.senterian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.journey.dimension.senterian.room.RoomBase;
import net.journey.dimension.senterian.room.RoomHall;
import net.journey.dimension.senterian.room.RoomSpawner1;
import net.journey.dimension.senterian.room.SenterianCeiling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ChunkProviderSenterian implements IChunkGenerator {

	private World world;
	private Random rand;
	private ArrayList rooms;
    private ArrayList BossRooms;
    private SenterianCeiling Ceiling;
    private double[] buffer;
    private Biome[] biomesForGeneration;
    private int chunkX = 0;
    private int chunkZ = 0;
    private Map chunkTileEntityMap;
    
	public ChunkProviderSenterian(World world, long seed) {
		this.world = world;
        this.rand = new Random(seed);
		rooms = new ArrayList(2);
        BossRooms = new ArrayList(0);
		//rooms.add(new RoomChest());
		rooms.add(new RoomHall());
		//rooms.add(new RoomNPC());
		rooms.add(new RoomSpawner1());
		
        Ceiling = new SenterianCeiling();
        this.chunkTileEntityMap = new HashMap();
	}
	
	@Override
	public Chunk generateChunk(int x, int z)  {
		this.chunkX = x;
        this.chunkZ = z;
        this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
        this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16,
                16);

        SenterianChunkPrimer chunkprimer = new SenterianChunkPrimer();

        for (int i = 4; i > 0; i--) {
        	RoomBase room = (RoomBase) (rooms.get(rand.nextInt(2)));
            if (room instanceof RoomBase && i >= 3)
                room = (RoomBase) (rooms.get(this.rand.nextInt(1) + 1));

            room.generate(chunkprimer, rand, 0, i * 8, 0);
        }

        Ceiling.generate(chunkprimer, rand, 0, 40, 0);

        chunkTileEntityMap.put(new ChunkPos(chunkX, chunkZ), chunkprimer.chunkTileEntityPositions);

        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i) {
            abyte[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
        }

        chunk.generateSkylightMap();
        return chunk;
	}
	
	@Override
	public void recreateStructures(Chunk ch, int x, int z) {}

	@Override
	public List <SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome biome = this.world.getBiome(pos);
		return biome.getSpawnableList(creatureType);
	}

	@Override
	public void populate(int chunkX, int chunkZ) {
		
		 int x = chunkX * 16;
	        int z = chunkZ * 16;

	        BlockPos pos = new BlockPos(x, 0, z);
	        ChunkPos chunkpos = new ChunkPos(chunkX, chunkZ);

	        Biome biome = this.world.getBiome(pos.add(16, 0, 16));

	        this.rand.setSeed(this.world.getSeed());
	        long k = this.rand.nextLong() / 2L * 2L + 1L;
	        long l = this.rand.nextLong() / 2L * 2L + 1L;
	        this.rand.setSeed((long) chunkX * k + (long) chunkZ * l ^ this.world.getSeed());
	        //biome.decorate(this.world, this.rand, pos);

	        List<BlockPos> chunkTileEntityPositions = (List<BlockPos>) chunkTileEntityMap.get(chunkpos);
	        if (chunkTileEntityPositions != null) {
	            Chunk chunk = this.world.getChunkFromChunkCoords(chunkX, chunkZ);
	            for (int i = 0; i < chunkTileEntityPositions.size(); i++) {
	                BlockPos chunkPosition = chunkTileEntityPositions.get(i);
	                IBlockState state = chunk.getBlockState(chunkPosition.getX(), chunkPosition.getY(),
	                        chunkPosition.getZ());
	                TileEntity te = state.getBlock().createTileEntity(this.world, state);
	                this.world.setTileEntity(chunkPosition.add(x, 0, z), te);
	            }
	            chunkTileEntityMap.remove(chunkpos);
	        }

	        if ((chunkX & 1) == 1 && (chunkZ & 1) == 1 && this.rand.nextInt(500) == 0) {
	            int roomToGenerate = rand.nextInt(2);
	            int i = this.rand.nextInt(4) + 1;
	            ((WorldGenerator) (BossRooms.get(roomToGenerate))).generate(this.world, rand, new BlockPos(x, i * 8, z));
	            this.rand.setSeed((long) chunkX * k + (long) chunkZ * l ^ this.world.getSeed() * i << 2 | l);
	        }

	        WorldEntitySpawner.performWorldGenSpawning(this.world, biome, x + 8, z + 8, 16, 16, this.rand);
		
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