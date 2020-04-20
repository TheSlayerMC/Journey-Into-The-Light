package net.journey.dimension.frozen;

import net.journey.dimension.frozen.gen.*;
import net.journey.dimension.overworld.gen.WorldGenModFlower;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.Helper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.List;
import java.util.Random;

public class ChunkProviderFrozenLands implements IChunkGenerator {

	private static WorldGenNewLamp lamp = new WorldGenNewLamp();
	private static WorldGenSpikeDungeon spike = new WorldGenSpikeDungeon();
	private static WorldGenIceDungeon dungeon = new WorldGenIceDungeon();
	private final double[] doubleA;
	private final float[] parabolicField;
	public NoiseGeneratorOctaves noiseGen5;
	public NoiseGeneratorOctaves noiseGen6;
	public NoiseGeneratorOctaves mobSpawnerNoise;
	private Random rand;
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	private NoiseGeneratorPerlin perlinNoise;
	private World worldObj;
	private ChunkGeneratorSettings settings;
	private Biome[] biomesForGeneration;
	private double[] n3, n4, n5, n6;
	private WorldGenerator[] largeBottomTrees = new WorldGenerator[]{
			new WorldGenFrozenTree4(false, true)};
	private WorldGenerator[] smallBottomTrees = new WorldGenerator[]{
			new WorldGenFrozenTree(),
			new WorldGenFrozenTree2(),
			new WorldGenFrozenTree3(),
			new WorldGenFrozenTree5()};
	private WorldGenerator[] crystals = new WorldGenerator[]{new WorldGenIceCrystal1(), new WorldGenIceCrystal2()};
	private WorldGenerator[] topTrees = new WorldGenerator[]{new WorldGenIceTree(), new WorldGenIceTree2()};
	private WorldGenerator[] bottomFlowers = new WorldGenerator[]{
			new WorldGenModFlower(JourneyBlocks.iceBud, JourneyBlocks.frozenGrass, false),
			new WorldGenModFlower(JourneyBlocks.frostberryThorn, JourneyBlocks.frozenGrass, false),
			new WorldGenModFlower(JourneyBlocks.frozenBlooms, JourneyBlocks.frozenGrass, false)};
	private WorldGenerator[] topFlowers = new WorldGenerator[]{
			new WorldGenModFlower(JourneyBlocks.permaFlower, JourneyBlocks.brittleIce),
			new WorldGenModFlower(JourneyBlocks.shiverFlower, JourneyBlocks.brittleIce),
			new WorldGenModFlower(JourneyBlocks.iceBush, JourneyBlocks.brittleIce)};
	private WorldGenMerchantHouse house = new WorldGenMerchantHouse();

	public ChunkProviderFrozenLands(World worldIn, long seed, String st) {
		this.worldObj = worldIn;
		this.rand = new Random(seed);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.perlinNoise = new NoiseGeneratorPerlin(this.rand, 4);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
		this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
		this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
		this.doubleA = new double[825];
		this.parabolicField = new float[25];

		for (int j = -2; j <= 2; ++j) {
			for (int k = -2; k <= 2; ++k) {
				float f = 10.0F / MathHelper.sqrt(j * j + k * k + 0.2F);
				this.parabolicField[j + 2 + (k + 2) * 5] = f;
			}
		}

		if (st != null) this.settings = ChunkGeneratorSettings.Factory.jsonToFactory(st).build();

		net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextHell ctx =
				new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextHell(noiseGen1, noiseGen2, noiseGen3, noiseGen5, noiseGen6, mobSpawnerNoise, mobSpawnerNoise);
		ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, ctx);
		this.noiseGen1 = ctx.getLPerlin1();
		this.noiseGen2 = ctx.getLPerlin2();
		this.noiseGen3 = ctx.getPerlin();
		this.noiseGen5 = ctx.getPerlin2();
		this.noiseGen6 = ctx.getPerlin3();
		this.mobSpawnerNoise = ctx.getScale();
		this.mobSpawnerNoise = ctx.getDepth();
	}

	public void setBlocksInChunk(int x, int z, ChunkPrimer cp) {
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
		this.generateNoise(x * 4, 0, z * 4);
		IBlockState grass = JourneyBlocks.frozenGrass.getDefaultState();
		IBlockState filler = JourneyBlocks.frozenDirt.getDefaultState();
		IBlockState ice = JourneyBlocks.brittleIce.getDefaultState();
		IBlockState stone = JourneyBlocks.frozenStone.getDefaultState();
		IBlockState water = Blocks.FLOWING_WATER.getDefaultState();

		for (int k = 0; k < 4; ++k) {
			int l = k * 5;
			int i1 = (k + 1) * 5;

			for (int j1 = 0; j1 < 4; ++j1) {
				int k1 = (l + j1) * 33;
				int l1 = (l + j1 + 1) * 33;
				int i2 = (i1 + j1) * 33;
				int j2 = (i1 + j1 + 1) * 33;

				for (int k2 = 0; k2 < 32; ++k2) {
					double d0 = 0.125D;
					double d1 = this.doubleA[k1 + k2];
					double d2 = this.doubleA[l1 + k2];
					double d3 = this.doubleA[i2 + k2];
					double d4 = this.doubleA[j2 + k2];
					double d5 = (this.doubleA[k1 + k2 + 1] - d1) * d0;
					double d6 = (this.doubleA[l1 + k2 + 1] - d2) * d0;
					double d7 = (this.doubleA[i2 + k2 + 1] - d3) * d0;
					double d8 = (this.doubleA[j2 + k2 + 1] - d4) * d0;

					for (int l2 = 0; l2 < 8; ++l2) {
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for (int i3 = 0; i3 < 4; ++i3) {
							double d14 = 0.25D;
							double d16 = (d11 - d10) * d14;
							double d15 = d10 - d16;

							for (int j3 = 0; j3 < 4; ++j3) {
								if ((d15 += d16) > 0.0D) {
									cp.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, ice);
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
		int top = 0;
		for (int i = 0; i < 16; i++) {
			for (int k = 0; k < 16; k++) {
				for (int j = 175; j > 0; j--) {
					if (cp.getBlockState(i, j, k) != Blocks.AIR.getDefaultState()) {
						top = j;
						break;
					}
				}
				for (int j = top - 10; j > top - 47; j--) {
					if (cp.getBlockState(i, j, k) != Blocks.BEDROCK.getDefaultState()) {
						cp.setBlockState(i, j, k, Blocks.AIR.getDefaultState());
					}
				}
			}
		}

		for (int i = 0; i < 16; i++) {
			for (int k = 0; k < 16; k++) {
				cp.setBlockState(i, 0, k, Blocks.BEDROCK.getDefaultState());
				for (int j = 48; j > 0; j--) {
					if (cp.getBlockState(i, j, k) == ice) {
						cp.setBlockState(i, j, k, filler);       
					}
				}
				for (int j = 48; j > 0; j--) {
					if (cp.getBlockState(i, j, k) == filler) {
						cp.setBlockState(i, j, k, grass);
						break;
					}
				}
				for (int j = 12; j > 0; j--) {
					if (j < 12 && cp.getBlockState(i, j, k) == Blocks.AIR.getDefaultState()) {
						cp.setBlockState(i, j, k, water);
					}
				}
			}
		}
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		this.rand.setSeed(x * 341873128712L + z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		this.setBlocksInChunk(x, z, chunkprimer);
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
		Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
		chunk.generateSkylightMap();
		return chunk;
	}

	private void generateNoise(int x, int y, int z) {
		this.n6 = this.noiseGen6.generateNoiseOctaves(this.n6, x, z, 5, 5, this.settings.depthNoiseScaleX, this.settings.depthNoiseScaleZ, this.settings.depthNoiseScaleExponent);
		float f = this.settings.coordinateScale;
		float f1 = this.settings.heightScale;
		this.n3 = this.noiseGen3.generateNoiseOctaves(this.n3, x, y, z, 5, 33, 5, f / this.settings.mainNoiseScaleX / 500, f1 / this.settings.mainNoiseScaleY, f / this.settings.mainNoiseScaleZ / 500);
		this.n4 = this.noiseGen1.generateNoiseOctaves(this.n4, x, y, z, 5, 33, 5, f, f1, f);
		this.n5 = this.noiseGen2.generateNoiseOctaves(this.n5, x, y, z, 5, 33, 5, f, f1, f);
		int l = 0;
		int i1 = 0;
		for (int j1 = 0; j1 < 5; j1++) {
			for (int k1 = 0; k1 < 5; k1++) {
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;
				byte b0 = 2;
				//Biome biome = this.biomesForGeneration[j1 + 2 + (l + 2) * 10];
				for (int l1 = -b0; l1 <= b0; l1++) {
					for (int i2 = -b0; i2 <= b0; i2++) {
						Biome biome1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
						float f5 = this.settings.biomeDepthOffSet + biome1.getBaseHeight() * this.settings.biomeDepthWeight;
						float f6 = this.settings.biomeScaleOffset + biome1.getHeightVariation() * this.settings.biomeScaleWeight;
						float f7 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f5 + 2.0F);
						if (biome1.getBaseHeight() > biome1.getBaseHeight()) f7 /= 2.0F;
						f2 += f6 * f7;
						f3 += f5 * f7;
						f4 += f7;
					}
				}

				f2 /= f4;
				f3 /= f4;
				f2 = f2 * 0.9F + 0.1F;
				f3 = (f3 * 4.0F - 1.0F) / 8.0F;
				double d7 = this.n6[i1] / 8000.0D;
				if (d7 < 0.0D) d7 = -d7 * 0.3D;
				d7 = d7 * 3.0D - 2.0D;
				if (d7 < 0.0D) {
					d7 /= 2.0D;
					if (d7 < -1.0D) d7 = -1.0D;
					d7 /= 1.4D;
					d7 /= 2.0D;
				} else {
					if (d7 > 1.0D) d7 = 1.0D;
					d7 /= 8.0D;
				}

				i1++;
				double d8 = f3;
				double d9 = f2;
				d8 += d7 * 0.2D;
				d8 = d8 * (double) this.settings.baseSize / 8.0D;
				double d0 = (double) this.settings.baseSize + d8 * 4.0D;

				for (int j2 = 0; j2 < 33; ++j2) {
					double d1 = (j2 - d0) * (double) this.settings.stretchY * 128.0D / 256.0D / d9;

					if (d1 < 0.0D) d1 *= 4.0D;
					double d2 = this.n4[l] / ((double) this.settings.lowerLimitScale);
					double d3 = this.n5[l] / (double) this.settings.upperLimitScale;
					double d4 = (this.n3[l] / 10.0D + 1.0D) / 2.0D;
					double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

					if (j2 > 29) {
						double d6 = (j2 - 29) / 3.0F;
						d5 = d5 * (1.0D - d6) + -10.0D * d6;
					}

					this.doubleA[l] = d5;
					l++;
				}
			}
		}
	}

	@Override
	public void populate(int i, int j) {
		int chunkSize = 16;
		BlockPos chunkStart = new BlockPos(i * chunkSize, 0, j * chunkSize);
		int x1 = i * chunkSize;
		int z1 = j * chunkSize;

		int times = 0;

		int topYMax = 100;
		int bottomYMax = 50;

		if (rand.nextInt(3) == 0) {
			int randX = i * 16 + 7 + rand.nextInt(9);
			int randZ = j * 16 + 7 + rand.nextInt(9);
			int randY = rand.nextInt(bottomYMax) + 1;
			if (isBlockTop(randX, randY - 1, randZ, JourneyBlocks.frozenGrass)) {
				spike.generate(worldObj, rand, new BlockPos(randX, randY, randZ));
			}
		}

		for (times = 0; times < 100; times++) {
			int randX = i * 16 + 8 + rand.nextInt(5);
			int randZ = j * 16 + 8 + rand.nextInt(5);
			int randY = rand.nextInt(bottomYMax) + 1;
			if (isBlockTop(randX, randY - 1, randZ, JourneyBlocks.frozenGrass)) {
				largeBottomTrees[rand.nextInt(largeBottomTrees.length)].generate(worldObj, rand, new BlockPos(randX, randY, randZ));
			}
		}

		for (times = 0; times < 250; times++) {
			int randX = i * 16 + 8 + rand.nextInt(8);
			int randZ = j * 16 + 8 + rand.nextInt(8);
			int randY = rand.nextInt(topYMax) + 1;
			if (isBlockTop(randX, randY - 1, randZ, JourneyBlocks.frozenGrass)) {
				smallBottomTrees[rand.nextInt(largeBottomTrees.length)].generate(worldObj, rand, new BlockPos(randX, randY, randZ));
			}
		}

		for (times = 0; times < 50; times++) {
			int randX = i * 16 + 8 + rand.nextInt(16);
			int randZ = j * 16 + 8 + rand.nextInt(16);
			int randY = rand.nextInt(topYMax) + 1;
			if (isBlockTop(randX, randY - 1, randZ, JourneyBlocks.brittleIce)) {
				topTrees[rand.nextInt(topTrees.length)].generate(worldObj, rand, new BlockPos(randX, randY, randZ));
			}
		}

		if (rand.nextInt(5) == 0) {
			generateStructure(x1, z1, JourneyBlocks.frozenGrass, house);
		}

		for (times = 0; times < 80; times++) {
			generateStructure(x1, z1, JourneyBlocks.frozenGrass, lamp);
		}

		for (times = 0; times < 8; times++) {
			generateStructure(x1, z1, JourneyBlocks.brittleIce, crystals);
		}

		for (times = 0; times < 100; times++) {
			bottomFlowers[rand.nextInt(bottomFlowers.length)].generate(worldObj, rand, chunkStart);
		}

		for (times = 0; times < 80; times++) {
			topFlowers[rand.nextInt(topFlowers.length)].generate(worldObj, rand, chunkStart);
		}

		//Causes gen lag because of size
		for (times = 0; times < 1; times++) {
			//generateStructure(x1, z1, JourneyBlocks.brittleIce, dungeon);
		}

		for(times = 0; times < 7; times++) {
			BlockPos randomPosForMinable = chunkStart.add(rand.nextInt(16), rand.nextInt(15), rand.nextInt(16));
			if(worldObj.getBlockState(randomPosForMinable.up(1)) == Blocks.FLOWING_WATER.getDefaultState()) {
				new WorldGenMinable(JourneyBlocks.frozenLamp.getDefaultState(), 15, BlockStateMatcher.forBlock(JourneyBlocks.frozenDirt)).generate(worldObj, rand, randomPosForMinable);
			}
		}
	}

	private void generateStructure(int x, int z, Block genOn, WorldGenerator... generators) {
		BlockPos pos = WorldGenAPI.createRandom(x, 30, 128, z, rand, 8);
		if (isBlockTop(pos.getX(), pos.getY() - 1, pos.getZ(), genOn)) {
			generators[rand.nextInt(generators.length)].generate(worldObj, rand, pos);
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
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return true;
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