package net.journey.dimension.euca;

import net.journey.dimension.base.DimensionHelper;
import net.journey.dimension.euca.gen.WorldGenEucaPumpkin;
import net.journey.dimension.euca.gen.WorldGenEucaSphere;
import net.journey.dimension.euca.gen.WorldGenEucaWater;
import net.journey.dimension.euca.gen.WorldGenSmeltery;
import net.journey.dimension.euca.gen.dungeon.EucaSmallSphereDungeon;
import net.journey.dimension.euca.gen.trees.*;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.Config;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.ForgeEventFactory;
import net.slayer.api.worldgen.WorldGenAPI;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class ChunkGeneratorEuca implements IChunkGenerator {
	private final NoiseGeneratorPerlin surfaceNoise;
	protected Biome[] biomesForGeneration;
	double[] pnr;
	double[] ar;
	double[] br;
	private final Random rand;
	private final World world;
	private final NoiseGeneratorOctaves noiseGen1;
	private final NoiseGeneratorOctaves perlinNoise1;
	private double[] buffer;
	//private ArrayList<WorldGenerator> treesgreen;
	private final WorldGenerator[] treesnormal = {
			new WorldGenEucaTree2()
	};
	private final WorldGenerator[] treestall = {
			new WorldGenEucaTree()
	};

	private final WorldGenMinable celestium;
	private final WorldGenMinable storonOre;
	private final WorldGenMinable koriteOre;
	private final WorldGenMinable mekyumOre;

	private final WorldGenSmeltery worldGenSmeltery = new WorldGenSmeltery();
	private final WorldGenBotSpawner spawner = new WorldGenBotSpawner();
	private final WorldGenEucaWater water = new WorldGenEucaWater(Blocks.FLOWING_WATER, false);
	private final WorldGenEucaSphere sphere = new WorldGenEucaSphere();
	private final WorldGenEucaPumpkin pumpkin = new WorldGenEucaPumpkin();
	private final EucaSmallSphereDungeon smallsphere = new EucaSmallSphereDungeon();


	public ChunkGeneratorEuca(World world, long seed) {
		this.world = world;
		this.rand = new Random(seed);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.perlinNoise1 = new NoiseGeneratorOctaves(this.rand, 8);
		this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);

		celestium = new WorldGenMinable(JourneyBlocks.celestiumOre.getDefaultState(), Config.celestiumOreGenAmount, BlockStateMatcher.forBlock(JourneyBlocks.eucaStone));
		storonOre = new WorldGenMinable(JourneyBlocks.storonOre.getDefaultState(), Config.storonOreGenAmount, BlockStateMatcher.forBlock(JourneyBlocks.eucaStone));
		koriteOre = new WorldGenMinable(JourneyBlocks.koriteOre.getDefaultState(), Config.koriteOreGenAmount, BlockStateMatcher.forBlock(JourneyBlocks.eucaStone));
		mekyumOre = new WorldGenMinable(JourneyBlocks.mekyumOre.getDefaultState(), Config.mekyumOreGenAmount, BlockStateMatcher.forBlock(JourneyBlocks.eucaStone));

		//	treesgreen = new ArrayList<WorldGenerator>(3);
		//	treesgreen.add(new WorldGenEucaTree6());
		//treesgreen.add(new WorldGenEucaTree7());
		//treesgreen.add(new WorldGenEucaTree8());
		//treesgreen.add(new WorldGenEucaTree9());
	}

	protected static long getSeed(int x, int z) {
		return x * 0x4f9939f508L + z * 0x1ef1565bd5L;
	}

	public void setBlocksInChunk(int x, int z, ChunkPrimer chunkPrimer) {
		this.buffer = this.setupNoiseGenerators(this.buffer, x * 2, z * 2);
		this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 100, 100);

		for (int i1 = 0; i1 < 2; i1++) {
			for (int j1 = 0; j1 < 2; j1++) {
				for (int k1 = 0; k1 < 32; k1++) {
					double d1 = this.buffer[(i1 * 3 + j1) * 33 + k1];
					double d2 = this.buffer[(i1 * 3 + (j1 + 1)) * 33 + k1];
					double d3 = this.buffer[((i1 + 1) * 3 + j1) * 33 + k1];
					double d4 = this.buffer[((i1 + 1) * 3 + (j1 + 1)) * 33 + k1];

					double d5 = (this.buffer[(i1 * 3 + j1) * 33 + (k1 + 1)] - d1) * 0.25D;
					double d6 = (this.buffer[(i1 * 3 + (j1 + 1)) * 33 + (k1 + 1)] - d2) * 0.25D;
					double d7 = (this.buffer[((i1 + 1) * 3 + j1) * 33 + (k1 + 1)] - d3) * 0.25D;
					double d8 = (this.buffer[((i1 + 1) * 3 + (j1 + 1)) * 33 + (k1 + 1)] - d4) * 0.25D;

					for (int l1 = 0; l1 < 4; l1++) {
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * 0.125D;
						double d13 = (d4 - d2) * 0.125D;

						for (int i2 = 0; i2 < 8; i2++) {
							double d15 = d10;
							double d16 = (d11 - d10) * 0.125D;

							for (int k2 = 0; k2 < 8; k2++) {
								int x1 = i2 + i1 * 8;
								int y = l1 + k1 * 4;
								int z1 = k2 + j1 * 8;

								IBlockState filler = Blocks.AIR.getDefaultState();

								if (d15 < -38D) {
								}
								if (d15 < -39D && d15 > -43D) {
									if (d15 < -41D) {
									}
								}
								if (d15 < -44D && d15 > -46D) {
									if (d15 < -44.25D) {
									}
								}
								if (d15 > 0.0D) {
									filler = JourneyBlocks.eucaStone.getDefaultState();
								}
								chunkPrimer.setBlockState(x1, y, z1, filler);
								d15 += d16;
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

	public void replaceBiomeBlocks(int chunkX, int chunkZ, ChunkPrimer primer, Biome[] biomesIn) {
		if (!ForgeEventFactory.onReplaceBiomeBlocks(this, chunkX, chunkZ, primer, this.world)) return;
		this.buffer = this.surfaceNoise.getRegion(this.buffer, chunkX * 16, chunkZ * 16, 16, 16, 0.0625D, 0.0625D, 1.0D);

		for (int relX = 0; relX < 16; ++relX) {
			for (int relZ = 0; relZ < 16; ++relZ) {
				Biome biome = biomesIn[relX + relZ * 16];
				biome.genTerrainBlocks(this.world, this.rand, primer, chunkX * 16 + relX, chunkZ * 16 + relZ, this.buffer[relX + relZ * 16]);
			}
		}
	}

	private double[] setupNoiseGenerators(double[] buffer, int x, int z) {
		if (buffer == null) {
			buffer = new double[3366];
		}

		double d = 1368.824D;
		double d1 = 684.41200000000003D;

		this.pnr = this.perlinNoise1.generateNoiseOctaves(this.pnr, x, 0, z, 3, 33, 3, d / 80D, d1 / 160D, d / 80D);
		this.ar = this.noiseGen1.generateNoiseOctaves(this.ar, x, 0, z, 3, 33, 3, d, d1, d);
		this.br = this.noiseGen1.generateNoiseOctaves(this.br, x, 0, z, 3, 33, 3, d, d1, d);

		int id = 0;

		for (int j2 = 0; j2 < 3; j2++) {
			for (int l2 = 0; l2 < 3; l2++) {
				for (int j3 = 0; j3 < 33; j3++) {
					double d8;

					double d10 = this.ar[id] / 84D;
					double d11 = this.br[id] / 84D;
					double d12 = (this.pnr[id] / 60D + 1.0D) / 2D;

					if (d12 < 0.0D) {
						d8 = d10;
					} else if (d12 > 1.0D) {
						d8 = d11;
					} else {
						d8 = d10 + (d11 - d10) * d12;
					}
					d8 -= 8D;
					if (j3 > 33 - 32) {
						double d13 = (float) (j3 - (33 - 32)) / ((float) 32 - 1.0F);
						d8 = d8 * (1.0D - d13) + -30D * d13;
					}
					if (j3 < 8) {
						double d14 = (float) (8 - j3) / ((float) 8 - 1.0F);
						d8 = d8 * (1.0D - d14) + -30D * d14;
					}
					buffer[id] = d8;
					id++;
				}
			}
		}

		return buffer;
	}

	@Override
	public @NotNull Chunk generateChunk(int x, int z) {
		this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
		ChunkPrimer chunkPrimer = new ChunkPrimer();
		this.setBlocksInChunk(x, z, chunkPrimer);
		this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
		this.replaceBiomeBlocks(x, z, chunkPrimer, biomesForGeneration);
		Chunk chunk = new Chunk(this.world, chunkPrimer, x, z);

		byte[] chunkBiomes = chunk.getBiomeArray();
		for (int i = 0; i < chunkBiomes.length; ++i) {
			chunkBiomes[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
		}
		chunk.generateSkylightMap();

		return chunk;
	}

	@Override
	public void populate(int chunkX, int chunkZ) {
		ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
		int chunkSize = 16;
		int x1 = chunkX * chunkSize;
		int z1 = chunkZ * chunkSize;
		int times;
		BlockPos chunkStart = new BlockPos(chunkX * chunkSize, 0, chunkZ * chunkSize);

		boolean hasVillagesGenerated = false;

		Biome biome = world.getBiome(chunkStart.add(16, 0, 16)); //why do we need to get a biome of the another chunk?
		biome.decorate(this.world, this.rand, chunkStart);
		if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, world, rand, chunkX, chunkZ, hasVillagesGenerated, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS))
			WorldEntitySpawner.performWorldGenSpawning(this.world, biome, chunkX + 8, chunkZ + 8, 16, 16, this.rand);

		if (rand.nextInt(6) == 0) {
			generateStructure(x1, z1, worldGenSmeltery);
		}

		for (times = 0; times < 1; times++) {
			generateStructure(x1, z1, spawner);
		}

		for (times = 0; times < 500; times++) {
			int randX = chunkX * 16 + 8 + rand.nextInt(16);
			int randZ = chunkZ * 16 + 8 + rand.nextInt(16);
			int randY = rand.nextInt(150) + 1;
			if (world.getBiome(new BlockPos(randX, randY, randZ)) == DimensionHelper.EUCA_GOLD_BIOME && isBlockTop(randX, randY - 1, randZ, JourneyBlocks.eucaGrass)) {
				treesnormal[rand.nextInt(treesnormal.length)].generate(world, rand, new BlockPos(randX, randY, randZ));
			}
		}

		for (times = 0; times < 70; times++) {
			int randX = chunkX * 16 + 8 + rand.nextInt(16);
			int randZ = chunkZ * 16 + 8 + rand.nextInt(16);
			int randY = rand.nextInt(150) + 1;
			if (world.getBiome(new BlockPos(randX, randY, randZ)) == DimensionHelper.EUCA_GOLD_BIOME && isBlockTop(randX, randY - 1, randZ, JourneyBlocks.eucaGrass)) {
				treestall[rand.nextInt(treestall.length)].generate(world, rand, new BlockPos(randX, randY, randZ));
			}
		}

		for (times = 0; times < 570; times++) {
			int randX = chunkX * 16 + 8 + rand.nextInt(16);
			int randZ = chunkZ * 16 + 8 + rand.nextInt(16);
			int randY = rand.nextInt(150) + 1;
			if (world.getBiome(new BlockPos(randX, randY, randZ)) == DimensionHelper.EUCA_SILVER_BIOME && isBlockTop(randX, randY - 1, randZ, JourneyBlocks.eucaSilverGrass)) {
				new WorldGenEucaSilverTree(true).generate(world, rand, new BlockPos(randX, randY, randZ));
			}
		}

		for (times = 0; times < 50; times++) {
			int randX = chunkX * 16 + 8 + rand.nextInt(16);
			int randZ = chunkZ * 16 + 8 + rand.nextInt(16);
			int randY = rand.nextInt(150) + 1;
			if (world.getBiome(chunkStart) == DimensionHelper.EUCA_GOLDITE_GRAINS_BIOME && isBlockTop(randX, randY - 1, randZ, JourneyBlocks.eucaGolditeGrass))
				new WorldGenEucaGolditeTree().generate(world, rand, new BlockPos(randX, randY, randZ));
		}

		for (times = 0; times < 50; times++) {
			int randX = chunkX * 16 + 8 + rand.nextInt(16);
			int randZ = chunkZ * 16 + 8 + rand.nextInt(16);
			int randY = rand.nextInt(150) + 1;
			if (world.getBiome(chunkStart) == DimensionHelper.EUCA_GOLDITE_GRAINS_BIOME && isBlockTop(randX, randY - 1, randZ, JourneyBlocks.eucaGolditeGrass))
				new WorldGenEucaGolditeTree2().generate(world, rand, new BlockPos(randX, randY, randZ));
		}

		for (times = 0; times < 1; times++) {
			pumpkin.generate(world, rand, chunkStart);
		}

		for (times = 0; times < 20; times++) {
			water.generate(world, rand, new BlockPos(x1 + this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, z1 + this.rand.nextInt(16) + 8));
		}

		if (rand.nextInt(25) == 0) {
			sphere.generate(world, rand, new BlockPos(x1, this.rand.nextInt(120) + 4, z1));
		}

		if (rand.nextInt(256) == 0) {
			smallsphere.generate(world, rand, new BlockPos(x1, this.rand.nextInt(120) + 4, z1));
		}

		for (chunkX = 0; chunkX < Config.celestiumOreTrys; chunkX++) {
			celestium.generate(world, rand, chunkStart.add(rand.nextInt(16), rand.nextInt(world.getHeight()), rand.nextInt(16)));
		}

		for (chunkX = 0; chunkX < Config.koriteOreTrys; chunkX++) {
			koriteOre.generate(world, rand, chunkStart.add(rand.nextInt(16), rand.nextInt(world.getHeight()), rand.nextInt(16)));
		}

		for (chunkX = 0; chunkX < Config.mekyumOreTrys; chunkX++) {
			mekyumOre.generate(world, rand, chunkStart.add(rand.nextInt(16), rand.nextInt(world.getHeight()), rand.nextInt(16)));
		}

		for (chunkX = 0; chunkX < Config.storonOreTrys; chunkX++) {
			storonOre.generate(world, rand, chunkStart.add(rand.nextInt(16), rand.nextInt(world.getHeight()), rand.nextInt(16)));
		}

		/*for (times = 0; times < 5; times++) {
			x = x1 + this.rand.nextInt(chunkSize);
			z = z1 + this.rand.nextInt(chunkSize);
			int yCoord = rand.nextInt(128) + 1;
			if (isBlockTop(x, yCoord - 1, z, JourneyBlocks.eucaGrass)) {
				treesgreen.get(rand.nextInt(treesgreen.size())).generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		} */
	}

	private void generateStructure(int x, int z, WorldGenerator... generators) {
		BlockPos pos = WorldGenAPI.createRandom(x, 40, 128, z, rand, 8);
		if (isBlockTop(pos.getX(), pos.getY() - 1, pos.getZ(), JourneyBlocks.eucaGrass)) {
			generators[rand.nextInt(generators.length)].generate(world, rand, pos);
		}
	}

	public boolean isBlockTop(int x, int y, int z, Block grass) {
		return WorldGenAPI.isBlockTop(x, y, z, grass, world);
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome biome = this.world.getBiome(pos);
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