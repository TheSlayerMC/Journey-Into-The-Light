package net.journey.dimension.cloudia;

import java.util.List;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.dimension.cloudia.gen.WorldGenCloudiaLamp;
import net.journey.dimension.cloudia.gen.WorldGenHut;
import net.journey.dimension.cloudia.gen.WorldGenIsland;
import net.journey.dimension.cloudia.gen.WorldGenStarlightCastle;
import net.journey.dimension.cloudia.gen.WorldGenStarlightTree;
import net.journey.dimension.cloudia.gen.WorldGenStarlightVillage;
import net.journey.dimension.cloudia.gen.WorldGenTower;
import net.journey.dimension.overworld.gen.WorldGenModFlower;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenerator;

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
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, cx* 16, cz* 16, 16, 16);
		Chunk chunk = new Chunk(this.worldObj, primer, cx, cz);
		byte[] abyte = chunk.getBiomeArray();
		for(int k = 0; k < abyte.length; ++k) abyte[k] = (byte)Biome.getIdForBiome(this.biomesForGeneration[k]);
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
	
	@Override
	public void populate(int cx, int cz) {
		this.rand.setSeed(this.worldObj.getSeed() * (cx + cz) * this.rand.nextInt());
		int x1 = cx * 16;
		int z1 = cz * 16;
		int x, y, z, i;
		x = x1 + this.rand.nextInt(16);
		z = z1 + this.rand.nextInt(16);
		Random r = rand;
		
		for(i = 0; i < 150; i++) {
			y = r.nextInt(256); x = x1 + this.rand.nextInt(16) + 8; z = z1 + this.rand.nextInt(16) + 8;
			new WorldGenModFlower(JourneyBlocks.cloudiaTallGrass).generate(worldObj, r, new BlockPos(x, y, z));
			new WorldGenModFlower(JourneyBlocks.cloudiaFlower).generate(worldObj, r, new BlockPos(x, y, z));
		}
		
		/* for(i = 0; i < 100; i++) {
			y = r.nextInt(128) + 1;
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			if(isBlockTop(x, y, z, JourneyBlocks.cloudiaGrass, worldObj)) 
				new WorldGenMelon(worldObj, r, new BlockPos(x, y, z), JourneyCrops.airRootMelon, JourneyBlocks.cloudiaGrass).generate(worldObj, r, new BlockPos(x, y, z));
		} */
		
		if (this.rand.nextInt(60) == 0) {
			int yCoord = rand.nextInt(20) + 64;
			if(worldObj.isAirBlock(new BlockPos(x, yCoord, z)))
				castle.generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}
		
		if (this.rand.nextInt(60) == 0) {
			x = x1 + this.rand.nextInt(16);
			z = z1 + this.rand.nextInt(16);
			int yCoord = rand.nextInt(20) + 64;
			if(worldObj.isAirBlock(new BlockPos(x, yCoord, z)))
				tower.generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}

		if (this.rand.nextInt(30) == 0) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(20) + 64;
			if(worldObj.isAirBlock(new BlockPos(x, yCoord, z)))
				hut.generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}

		if (this.rand.nextInt(2) == 0) {
			x = x1 + this.rand.nextInt(16);
			z = z1 + this.rand.nextInt(16);
			int yCoord = rand.nextInt(20) + 64;
			if(worldObj.isAirBlock(new BlockPos(x, yCoord, z)))
				lamp.generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}
		
		if (this.rand.nextInt(2) == 0) {
			x = x1 + this.rand.nextInt(16);
			z = z1 + this.rand.nextInt(16);
			int yCoord = rand.nextInt(20) + 64;
			if(worldObj.isAirBlock(new BlockPos(x, yCoord, z)))
				tree.generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}
		
		if (this.rand.nextInt(2) == 0) {
			x = x1 + this.rand.nextInt(16);
			z = z1 + this.rand.nextInt(16);
			int yCoord = rand.nextInt(20) + 64;
			if(worldObj.isAirBlock(new BlockPos(x, yCoord, z)))
				island.generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}
		
		if (this.rand.nextInt(40) == 0) {
			x = x1 + this.rand.nextInt(16);
			z = z1 + this.rand.nextInt(16);
			int yCoord = rand.nextInt(20) + 64;
			if(worldObj.isAirBlock(new BlockPos(x, yCoord, z)))
				village.generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}
		
		/**if (this.rand.nextInt(15) == 0) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int y = rand.nextInt(250);
			if(y > 30 && y < 130) new WorldGenCloudiaLand().generate(worldObj, rand, new BlockPos(x, y, z));
		}*/
	}

	public boolean isBlockTop(int x, int y, int z, Block grass, World w) {
		return 
				w.getBlockState(new BlockPos(x, y - 1, z)) == grass.getDefaultState() && 
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

	@Override
	public List <SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
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
	public void recreateStructures(Chunk chunkIn, int x, int z) { }

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}
}