package net.journey.dimension.euca;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.dimension.euca.gen.WorldGenEucaWater;
import net.journey.dimension.euca.gen.WorldGenSmeltery;
import net.journey.dimension.euca.gen.trees.WorldGenBotSpawner;
import net.journey.dimension.euca.gen.trees.WorldGenEucaTree;
import net.journey.dimension.euca.gen.trees.WorldGenEucaTree2;
import net.journey.dimension.euca.gen.trees.WorldGenEucaTree3;
import net.journey.dimension.euca.gen.trees.WorldGenEucaTree4;
import net.journey.dimension.euca.gen.trees.WorldGenEucaTree5;
import net.journey.dimension.euca.gen.trees.WorldGenEucaTree6;
import net.journey.dimension.euca.gen.trees.WorldGenEucaTree7;
import net.journey.dimension.euca.gen.trees.WorldGenEucaTree8;
import net.journey.dimension.euca.gen.trees.WorldGenEucaTree9;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ChunkProviderEuca implements IChunkGenerator {

	private Random rand;
	private World worldObj;
	private NoiseGeneratorOctaves noiseGen1, perlinNoise1;
	private double buffer[];
	double pnr[], ar[], br[];
	private ArrayList<WorldGenerator> treesgreen;
	private ArrayList<WorldGenerator> treesnormal;
	private ArrayList<WorldGenerator> treestall;

	public ChunkProviderEuca(World world, long seed) {
		this.worldObj = world;
		this.rand = new Random(seed);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.perlinNoise1 = new NoiseGeneratorOctaves(this.rand, 8);

		treesgreen = new ArrayList<WorldGenerator>(6);
		treesgreen.add(new WorldGenEucaTree6());
		treesgreen.add(new WorldGenEucaTree7());
		treesgreen.add(new WorldGenEucaTree8());
		treesgreen.add(new WorldGenEucaTree9());

		treesnormal = new ArrayList<WorldGenerator>(6);
		treesnormal.add(new WorldGenEucaTree4());
		treesnormal.add(new WorldGenEucaTree5());

		treestall = new ArrayList<WorldGenerator>(3);
		treestall.add(new WorldGenEucaTree());
		treestall.add(new WorldGenEucaTree2());
		treestall.add(new WorldGenEucaTree3());
	}

	public void setBlocksInChunk(int x, int z, ChunkPrimer chunkPrimer) {
		this.buffer = this.setupNoiseGenerators(this.buffer, x * 2, z * 2);

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

	public void buildSurfaces(int i, int j, ChunkPrimer chunkPrimer) {
		for (int k = 0; k < 16; k++) {
			for (int l = 0; l < 16; l++) {
				int j1 = -1;
				int i1 = (int) (3.0D + this.rand.nextDouble() * 0.25D);

				IBlockState top = JourneyBlocks.eucaGrass.getDefaultState();
				IBlockState filler = JourneyBlocks.eucaDirt.getDefaultState();

				for (int k1 = 127; k1 >= 0; k1--) {
					Block block = chunkPrimer.getBlockState(k, k1, l).getBlock();

					if (block == Blocks.AIR) {
						j1 = -1;
					} else if (block == JourneyBlocks.eucaStone) {
						if (j1 == -1) {
							if (i1 <= 0) {
								top = Blocks.AIR.getDefaultState();
								filler = JourneyBlocks.eucaStone.getDefaultState();
							}

							j1 = i1;

							if (k1 >= 0) {
								chunkPrimer.setBlockState(k, k1, l, top);
							} else {
								chunkPrimer.setBlockState(k, k1, l, filler);
							}
						} else if (j1 > 0) {
							--j1;
							chunkPrimer.setBlockState(k, k1, l, filler);
						}
					}
				}
			}
		}
	}

	private double[] setupNoiseGenerators(double buffer[], int x, int z) {
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

					double d10 = this.ar[id] / 512D;
					double d11 = this.br[id] / 512D;
					double d12 = (this.pnr[id] / 10D + 1.0D) / 2D;

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
	public Chunk generateChunk(int x, int z) {
		this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
		ChunkPrimer chunkPrimer = new ChunkPrimer();

		this.setBlocksInChunk(x, z, chunkPrimer);
		this.buildSurfaces(x, z, chunkPrimer);

		Chunk chunk = new Chunk(this.worldObj, chunkPrimer, x, z);
		chunk.generateSkylightMap();

		return chunk;
	}

	@Override
	public void populate(int i, int j) {
		int x1 = i * 16;
		int z1 = j * 16;
		int x, z, times;

		if (rand.nextInt(1) == 0) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128) + 1;
			if (isBlockTop(x, yCoord - 1, z, JourneyBlocks.eucaGrass)) {
				new WorldGenSmeltery().generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}

		for (times = 0; times < 2; times++) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128);
			if (isBlockTop(x, yCoord - 1, z, JourneyBlocks.eucaGrass))
				new WorldGenBotSpawner().generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}

		for (times = 0; times < 5; times++) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128) + 1;
			if (isBlockTop(x, yCoord - 1, z, JourneyBlocks.eucaGrass)) {
				treesgreen.get(rand.nextInt(treesgreen.size())).generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}

		for (times = 0; times < 500; times++) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128) + 1;
			if (isBlockTop(x, yCoord - 1, z, JourneyBlocks.eucaGrass)) {
				treesnormal.get(rand.nextInt(treesnormal.size())).generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}

		for (times = 0; times < 50; times++) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128) + 1;
			if (isBlockTop(x, yCoord - 1, z, JourneyBlocks.eucaGrass)) {
				treestall.get(rand.nextInt(treestall.size())).generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}
	}

	public boolean isBlockTop(int x, int y, int z, Block grass) {
		return worldObj.getBlockState(new BlockPos(x, y, z)) == grass.getDefaultState()
				&& worldObj.getBlockState(new BlockPos(x, y + 1, z)) == Blocks.AIR.getDefaultState()
				&& worldObj.getBlockState(new BlockPos(x, y + 2, z)) == Blocks.AIR.getDefaultState()
				&& worldObj.getBlockState(new BlockPos(x, y + 3, z)) == Blocks.AIR.getDefaultState()
				&& worldObj.getBlockState(new BlockPos(x, y + 4, z)) == Blocks.AIR.getDefaultState()
				&& worldObj.getBlockState(new BlockPos(x, y + 5, z)) == Blocks.AIR.getDefaultState();
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
	public void recreateStructures(Chunk chunkIn, int x, int z) {}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}
}