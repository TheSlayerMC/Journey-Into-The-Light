package net.journey.dimension.corba;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.journey.dimension.base.DimensionHelper;
import net.journey.dimension.corba.biomes.BiomeGenCorbaSwamp;
import net.journey.dimension.corba.gen.WorldGenCorbaLamp;
import net.journey.dimension.corba.gen.WorldGenCorbaVillage;
import net.journey.dimension.corba.gen.WorldGenHugeCorbaTree;
import net.journey.dimension.corba.gen.WorldGenTreehouse;
import net.journey.dimension.corba.gen.trees.WorldGenCorbaLargeTree;
import net.journey.dimension.corba.gen.trees.WorldGenCorbaMediumTree;
import net.journey.dimension.corba.gen.trees.WorldGenCorbaSmallTree;
import net.journey.dimension.corba.village.MapGenCorbaVillage;
import net.journey.dimension.overworld.gen.WorldGenCaveVines;
import net.journey.dimension.overworld.gen.WorldGenModFlower;
import net.journey.dimension.overworld.gen.WorldGenSmallGlowshrooms;
import net.journey.dimension.overworld.gen.WorldGenTallGlowshroom;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.Config;
import net.journey.util.handler.Helper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.slayer.api.worldgen.WorldGenAPI;

public class ChunkProviderCorba implements IChunkGenerator {

	double[] mainNoiseRegion;
	double[] minLimitRegion;
	double[] maxLimitRegion;
	double[] depthRegion;
	private ChunkGeneratorSettings settings;
	private NoiseGeneratorOctaves minLimitPerlinNoise;
	private NoiseGeneratorOctaves maxLimitPerlinNoise;
	private NoiseGeneratorOctaves mainPerlinNoise;
	private NoiseGeneratorPerlin surfaceNoise;
	public NoiseGeneratorOctaves scaleNoise;
	public NoiseGeneratorOctaves depthNoise;
	public NoiseGeneratorOctaves forestNoise;
	private final WorldType terrainType;
	private final double[] heightMap;
	private final float[] biomeWeights;
	private double[] depthBuffer = new double[256];

	private Random rand;
	private ArrayList<WorldGenerator> trees;
	private World worldObj;
	private MapGenBase caveGenerator;
	private MapGenBase ravineGenerator;
	private Biome[] biomesForGeneration;

	private WorldGenModFlower flower1 = new WorldGenModFlower(JourneyBlocks.corbaSpeckledFlower, JourneyBlocks.corbaGrass);
	private WorldGenModFlower flower2 = new WorldGenModFlower(JourneyBlocks.corbaDarkPurpleFlower, JourneyBlocks.corbaGrass);
	private WorldGenModFlower flower3 = new WorldGenModFlower(JourneyBlocks.corbaRedFlower, JourneyBlocks.corbaGrass);
	private WorldGenModFlower flower4 = new WorldGenModFlower(JourneyBlocks.corbaBlueFlower, JourneyBlocks.corbaGrass);
	private WorldGenModFlower flower5 = new WorldGenModFlower(JourneyBlocks.corbaLightPurpleFlower, JourneyBlocks.corbaGrass);
	private WorldGenModFlower tall = new WorldGenModFlower(JourneyBlocks.corbaTallGrass, JourneyBlocks.corbaGrass);
	private WorldGenModFlower flower = new WorldGenModFlower(JourneyBlocks.corbaFlower, JourneyBlocks.corbaGrass);
	private WorldGenModFlower small_bogshroom = new WorldGenModFlower(JourneyBlocks.bog_shrooms_small, JourneyBlocks.taintedMud);//make custom mushrooms
	private WorldGenModFlower tall_bogshroom = new WorldGenModFlower(JourneyBlocks.bog_shroom_tall, JourneyBlocks.taintedMud);
	private WorldGenModFlower bogweed = new WorldGenModFlower(JourneyBlocks.bogweed, JourneyBlocks.taintedMud);

	private WorldGenTreehouse worldGenTreehouse = new WorldGenTreehouse();
	private WorldGenCorbaVillage village = new WorldGenCorbaVillage();
	private WorldGenHugeCorbaTree tree = new WorldGenHugeCorbaTree();
	private WorldGenCorbaLamp lamp = new WorldGenCorbaLamp();
	private final WorldGenMinable gorbite;
	private final WorldGenMinable orbaditeOre;
	private static final WorldGenTallGlowshroom TALL_GLOWSHROOMS_GEN = new WorldGenTallGlowshroom();
	private static final WorldGenCaveVines CAVE_VINE_GEN = new WorldGenCaveVines();
	private static final WorldGenSmallGlowshrooms SMALL_GLOWSHROOMS = new WorldGenSmallGlowshrooms();

	private MapGenCorbaVillage villageGenerator = new MapGenCorbaVillage();

	public ChunkProviderCorba(World worldIn, long seed, String generatorOptions) {
		this.caveGenerator = new MapGenCaves();
		this.ravineGenerator = new MapGenRavine();
		this.worldObj = worldIn;
		this.rand = new Random(seed);
		this.terrainType = worldIn.getWorldInfo().getTerrainType();
		this.rand = new Random(seed);
		this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
		this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
		this.mainPerlinNoise = new NoiseGeneratorOctaves(this.rand, 8);
		this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);
		this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
		this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
		this.forestNoise = new NoiseGeneratorOctaves(this.rand, 8);
		this.heightMap = new double[825];
		this.biomeWeights = new float[25];

		for(int i = -2; i <= 2; ++i) {
			for(int j = -2; j <= 2; ++j) {
				float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
				this.biomeWeights[i + 2 + (j + 2) * 5] = f;
			}
		}

		if(generatorOptions != null) {
			this.settings = ChunkGeneratorSettings.Factory.jsonToFactory(generatorOptions).build();
			worldIn.setSeaLevel(this.settings.seaLevel);
		}

		trees = new ArrayList<WorldGenerator>(3);
		trees.add(new WorldGenCorbaSmallTree());
		trees.add(new WorldGenCorbaMediumTree());
		trees.add(new WorldGenCorbaLargeTree());



		this.gorbite = new WorldGenMinable(JourneyBlocks.gorbiteOre.getDefaultState(), Config.gorbiteOreGenAmount, BlockStateMatcher.forBlock(JourneyBlocks.corbaStone));
		this.orbaditeOre = new WorldGenMinable(JourneyBlocks.orbaditeOre.getDefaultState(), Config.orbaditeOreGenAmount, BlockStateMatcher.forBlock(JourneyBlocks.corbaStone));

		//this.villageGenerator = (MapGenCorbaVillage)TerrainGen.getModdedMapGen(villageGenerator, InitMapGenEvent.EventType.VILLAGE);

	}

	public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
		this.generateHeightmap(x * 4, 0, z * 4);

		for (int i = 0; i < 4; ++i)
		{
			int j = i * 5;
			int k = (i + 1) * 5;

			for (int l = 0; l < 4; ++l)
			{
				int i1 = (j + l) * 33;
				int j1 = (j + l + 1) * 33;
				int k1 = (k + l) * 33;
				int l1 = (k + l + 1) * 33;

				for (int i2 = 0; i2 < 32; ++i2)
				{
					double d0 = 0.125D;
					double d1 = this.heightMap[i1 + i2];
					double d2 = this.heightMap[j1 + i2];
					double d3 = this.heightMap[k1 + i2];
					double d4 = this.heightMap[l1 + i2];
					double d5 = (this.heightMap[i1 + i2 + 1] - d1) * 0.125D;
					double d6 = (this.heightMap[j1 + i2 + 1] - d2) * 0.125D;
					double d7 = (this.heightMap[k1 + i2 + 1] - d3) * 0.125D;
					double d8 = (this.heightMap[l1 + i2 + 1] - d4) * 0.125D;

					for (int j2 = 0; j2 < 8; ++j2)
					{
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * 0.25D;
						double d13 = (d4 - d2) * 0.25D;

						for (int k2 = 0; k2 < 4; ++k2)
						{
							double d14 = 0.25D;
							double d16 = (d11 - d10) * 0.25D;
							double lvt_45_1_ = d10 - d16;

							for (int l2 = 0; l2 < 4; ++l2)
							{
								if ((lvt_45_1_ += d16) > 0.0D)
								{
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, JourneyBlocks.corbaStone.getDefaultState());
								}

								else if (i2 * 8 + j2 < this.settings.seaLevel) {
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, Blocks.WATER.getDefaultState());
								}
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn)
	{
		if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.worldObj)) return;
		double d0 = 0.03125D;
		this.depthBuffer = this.surfaceNoise.getRegion(this.depthBuffer, (double)(x * 16), (double)(z * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);

		for (int i = 0; i < 16; ++i)
		{
			for (int j = 0; j < 16; ++j)
			{
				Biome biome = biomesIn[j + i * 16];
				if(biome == DimensionHelper.CORBA_SWAMP_BIOME) {
					BiomeGenCorbaSwamp swamp = (BiomeGenCorbaSwamp)biome;
					swamp.generateModdedBiomeTerrain(worldObj, this.rand, primer, x * 16 + i, z * 16 + j, this.depthBuffer[j + i * 16]);
				} else {
					generateBiomeTerrain(biome, this.rand, primer, x * 16 + i, z * 16 + j, this.depthBuffer[j + i * 16]);
				}
			}
		}
	}

	public final void generateBiomeTerrain(Biome b, Random r, ChunkPrimer c, int x, int z, double s) {
		boolean flag = true;
		IBlockState iblockstate = b.topBlock;
		IBlockState iblockstate1 = b.fillerBlock;
		int k = -1;
		int l = (int) (s / 3.0D + 3.0D + r.nextDouble() * 0.25D);
		int i1 = x & 15;
		int j1 = z & 15;
		for (int k1 = 255; k1 >= 0; --k1) {
			if (k1 <= 1) {
				c.setBlockState(j1, k1, i1, Blocks.BEDROCK.getDefaultState());
			} else {
				IBlockState iblockstate2 = c.getBlockState(j1, k1, i1);

				if (iblockstate2.getMaterial() == Material.AIR) k = -1;
				else if (iblockstate2.getBlock() == JourneyBlocks.corbaStone) {
					if (k == -1) {
						if (l <= 0) {
							iblockstate = null;
							iblockstate1 = JourneyBlocks.corbaStone.getDefaultState();
						} else if (k1 >= 7 && k1 <= 8) {
							iblockstate =  b.topBlock;
							iblockstate1 =  b.fillerBlock;
						}

						if (k1 < 8 && (iblockstate == null || iblockstate.getMaterial() == Material.AIR))
							iblockstate = JourneyBlocks.corbaStone.getDefaultState();
						k = l;
						if (k1 >= 8) c.setBlockState(j1, k1, i1, iblockstate);
						else if (k1 < 7 - l) {
							iblockstate = null;
							iblockstate1 = JourneyBlocks.corbaStone.getDefaultState();
						} else c.setBlockState(j1, k1, i1, iblockstate1);
					} else if (k > 0) {
						--k;
						c.setBlockState(j1, k1, i1, iblockstate1);
					}
				}
			}
		}
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		return this.provideChunk(x, z);
	}

	public Chunk provideChunk(int x, int z) {
		this.rand.setSeed(x * 341873128712L + z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		this.setBlocksInChunk(x, z, chunkprimer);
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
		this.replaceBiomeBlocks(x, z, chunkprimer, this.biomesForGeneration);
		this.caveGenerator.generate(this.worldObj, x, z, chunkprimer);
		this.ravineGenerator.generate(this.worldObj, x, z, chunkprimer);

		this.villageGenerator.generate(this.worldObj, x, z, chunkprimer);

		Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
		byte[] abyte = chunk.getBiomeArray();
		for (int k = 0; k < abyte.length; ++k) abyte[k] = (byte) Biome.getIdForBiome(this.biomesForGeneration[k]);
		chunk.generateSkylightMap();
		return chunk;
	}

	private void generateHeightmap(int x, int y, int z) {
		this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, x, z, 5, 5, (double)this.settings.depthNoiseScaleX, (double)this.settings.depthNoiseScaleZ, (double)this.settings.depthNoiseScaleExponent);
		float f = this.settings.coordinateScale;
		float f1 = this.settings.heightScale;
		this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, x, y, z, 5, 33, 5, (double)(f / this.settings.mainNoiseScaleX), (double)(f1 / this.settings.mainNoiseScaleY), (double)(f / this.settings.mainNoiseScaleZ));
		this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, x, y, z, 5, 33, 5, (double)f, (double)f1, (double)f);
		this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, x, y, z, 5, 33, 5, (double)f, (double)f1, (double)f);
		int i = 0;
		int j = 0;

		for (int k = 0; k < 5; ++k)
		{
			for (int l = 0; l < 5; ++l)
			{
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;
				int i1 = 2;
				Biome biome = this.biomesForGeneration[k + 2 + (l + 2) * 10];

				for (int j1 = -2; j1 <= 2; ++j1)
				{
					for (int k1 = -2; k1 <= 2; ++k1)
					{
						Biome biome1 = this.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
						float f5 = this.settings.biomeDepthOffSet + biome1.getBaseHeight() * this.settings.biomeDepthWeight;
						float f6 = this.settings.biomeScaleOffset + biome1.getHeightVariation() * this.settings.biomeScaleWeight;

						if (this.terrainType == WorldType.AMPLIFIED && f5 > 0.0F)
						{
							f5 = 1.0F + f5 * 2.0F;
							f6 = 1.0F + f6 * 4.0F;
						}

						float f7 = this.biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

						if (biome1.getBaseHeight() > biome.getBaseHeight())
						{
							f7 /= 2.0F;
						}

						f2 += f6 * f7;
						f3 += f5 * f7;
						f4 += f7;
					}
				}

				f2 = f2 / f4;
				f3 = f3 / f4;
				f2 = f2 * 0.9F + 0.1F;
				f3 = (f3 * 4.0F - 1.0F) / 8.0F;
				double d7 = this.depthRegion[j] / 8000.0D;

				if (d7 < 0.0D)
				{
					d7 = -d7 * 0.3D;
				}

				d7 = d7 * 3.0D - 2.0D;

				if (d7 < 0.0D)
				{
					d7 = d7 / 2.0D;

					if (d7 < -1.0D)
					{
						d7 = -1.0D;
					}

					d7 = d7 / 1.4D;
					d7 = d7 / 2.0D;
				}
				else
				{
					if (d7 > 1.0D)
					{
						d7 = 1.0D;
					}

					d7 = d7 / 8.0D;
				}

				++j;
				double d8 = (double)f3;
				double d9 = (double)f2;
				d8 = d8 + d7 * 0.2D;
				d8 = d8 * (double)this.settings.baseSize / 8.0D;
				double d0 = (double)this.settings.baseSize + d8 * 4.0D;

				for (int l1 = 0; l1 < 33; ++l1)
				{
					double d1 = ((double)l1 - d0) * (double)this.settings.stretchY * 128.0D / 256.0D / d9;

					if (d1 < 0.0D)
					{
						d1 *= 4.0D;
					}

					double d2 = this.minLimitRegion[i] / (double)this.settings.lowerLimitScale;
					double d3 = this.maxLimitRegion[i] / (double)this.settings.upperLimitScale;
					double d4 = (this.mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
					double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

					if (l1 > 29)
					{
						double d6 = (double)((float)(l1 - 29) / 3.0F);
						d5 = d5 * (1.0D - d6) + -10.0D * d6;
					}

					this.heightMap[i] = d5;
					++i;
				}
			}
		}
	}

	@Override
	public void populate(int cx, int cz) {
		final int x1 = cx * 16;
		final int z1 = cz * 16;
		int i;
		int times;
		Random r = rand;
		BlockPos chunkStart = new BlockPos(x1, 0, z1);
		ChunkPos chunkpos = new ChunkPos(cx, cz);
		BlockPos startPos = new BlockPos(x1, 0, z1);
		this.villageGenerator.generateStructure(this.worldObj, this.rand, chunkpos);

		if(worldObj.getBiome(chunkStart) != DimensionHelper.CORBA_SWAMP_BIOME) {
			for(i = 0; i < 5; i++) {
				tall.generate(worldObj, r, chunkStart);
				flower.generate(worldObj, r, chunkStart);
				flower1.generate(worldObj, r, chunkStart);
				flower2.generate(worldObj, r, chunkStart);
				flower3.generate(worldObj, r, chunkStart);
				flower4.generate(worldObj, r, chunkStart);
				flower5.generate(worldObj, r, chunkStart);
			}
		} 

		if(worldObj.getBiome(chunkStart) == DimensionHelper.CORBA_SWAMP_BIOME) {
			for(i = 0; i < 5; i++) {
				small_bogshroom.generate(worldObj, r, chunkStart);
				tall_bogshroom.generate(worldObj, r, chunkStart);
				bogweed.generate(worldObj, r, chunkStart);
			}
		}

		if(worldObj.getBiome(chunkStart) == DimensionHelper.CORBA_SWAMP_BIOME) {
			for(i = 0; i < 100; i++) {
				int randX = x1 + 8 + rand.nextInt(8);
				int randZ = z1 + 8 + rand.nextInt(8);
				int randY = rand.nextInt(80);
				if (isBlockTop(randX, randY, randZ, JourneyBlocks.corbaGrass) || isBlockTop(randX, randY, randZ, Blocks.WATER)) {
					new WorldGenSwamp().generate(worldObj, rand, new BlockPos(randX, randY, randZ));
				}
			}
		}

		if (rand.nextInt(5) == 0) {
			generateStructure(x1, z1, worldGenTreehouse);
		}

		for (i = 0; i < 64; i++) {
			TALL_GLOWSHROOMS_GEN.generate(worldObj, rand, startPos);
			SMALL_GLOWSHROOMS.generate(worldObj, rand, startPos);
		}

		for (i = 0; i < 55; i++) {
			CAVE_VINE_GEN.generate(worldObj, rand, startPos);
		}

		if (rand.nextInt(6) == 0) {
			BlockPos pos = new BlockPos(x1 + 1, r.nextInt(128) + 1,
					z1 + r.nextInt(8) + 8);

			if (isBlockTop(pos.getX(), pos.getY(), pos.getZ(), JourneyBlocks.corbaGrass)) {
				village.generate(worldObj, rand, pos);
			}
		}

		if (rand.nextInt(6) == 0) {
			generateStructure(x1, z1, tree);
		}

		for(times = 0; times < 200; times++) {
			int randX = x1 + 8 + rand.nextInt(16);
			int randZ = z1 + 8 + rand.nextInt(16);
			int randY = rand.nextInt(80);
			if (isBlockTop(randX, randY - 1, randZ, JourneyBlocks.corbaGrass)) {
				if(worldObj.getBiome(new BlockPos(randX, randY, randZ)) == DimensionHelper.CORBA_BIOME)
					trees.get(rand.nextInt(trees.size())).generate(worldObj, rand, new BlockPos(randX, randY, randZ));
			}
		}


		for (times = 0; times < 30; times++) {
			generateStructure(x1, z1, lamp);
		}

		for (i = 0; i < Config.gorbiteOreTrys; i++) {
			gorbite.generate(worldObj, rand, chunkStart.add(rand.nextInt(16), rand.nextInt(worldObj.getHeight()), rand.nextInt(16)));
		}

		for (i = 0; i < Config.orbaditeOreTrys; i++) {
			orbaditeOre.generate(worldObj, rand, chunkStart.add(rand.nextInt(16), rand.nextInt(worldObj.getHeight()), rand.nextInt(16)));
		}

	}

	private void generateStructure(int x, int z, WorldGenerator generator) {
		BlockPos pos = WorldGenAPI.createRandom(x, 1, 128 + 1, z, rand, 10);
		if (isBlockTop(pos.getX(), pos.getY(), pos.getZ(), JourneyBlocks.corbaGrass)) {
			generator.generate(worldObj, rand, pos);
		}
	}

	public boolean isBlockTop(int x, int y, int z, Block grass) {
		return WorldGenAPI.isBlockTop(x, y, z, grass, worldObj);
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome biome = this.worldObj.getBiome(pos);
		return biome.getSpawnableList(creatureType);
	}

	@Override
	public void recreateStructures(Chunk c, int x, int z) {	
		this.villageGenerator.generate(this.worldObj, x, z, (ChunkPrimer)null);
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
		if ("CorbaVillage".equals(structureName) && this.villageGenerator != null) {
			return this.villageGenerator.getNearestStructurePos(worldIn, position, findUnexplored);
		}
		return null;
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		if ("CorbaVillage".equals(structureName) && this.villageGenerator != null) {
			return this.villageGenerator.isInsideStructure(pos);
		}
		return false;
	}
}