package net.journey.dimension.frozen;

import java.util.List;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.dimension.frozen.gen.WorldGenFrozenTree;
import net.journey.dimension.frozen.gen.WorldGenFrozenTree2;
import net.journey.dimension.frozen.gen.WorldGenIceCrystal1;
import net.journey.dimension.frozen.gen.WorldGenIceCrystal2;
import net.journey.dimension.frozen.gen.WorldGenIceDungeon;
import net.journey.dimension.frozen.gen.WorldGenIceTree;
import net.journey.dimension.frozen.gen.WorldGenIceTree2;
import net.journey.dimension.frozen.gen.WorldGenMerchantHouse;
import net.journey.dimension.frozen.gen.WorldGenNewLamp;
import net.journey.dimension.overworld.gen.WorldGenModFlower;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public class ChunkProviderFrozenLands implements IChunkGenerator {

	private Random rand;
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	private NoiseGeneratorPerlin perlinNoise;
	public NoiseGeneratorOctaves noiseGen5;
	public NoiseGeneratorOctaves noiseGen6;
	public NoiseGeneratorOctaves mobSpawnerNoise;
	private World worldObj;
	private final boolean mapFeaturesEnabled;
	private WorldType theWorldType;
	private final double[] doubleA;
	private final float[] parabolicField;
	private ChunkGeneratorSettings settings;
	private double[] stoneNoise;
	private Biome[] biomesForGeneration;
	private double[] n3, n4, n5, n6;
	private static WorldGenFrozenTree tree = new WorldGenFrozenTree();
	private static WorldGenFrozenTree2 tree2 = new WorldGenFrozenTree2();
	private static WorldGenNewLamp lamp = new WorldGenNewLamp();
	private static WorldGenIceCrystal1 crystal1 = new WorldGenIceCrystal1();
	private static WorldGenIceCrystal2 crystal2 = new WorldGenIceCrystal2();
	private static WorldGenIceDungeon dungeon = new WorldGenIceDungeon();
	private static WorldGenIceTree iceTree = new WorldGenIceTree();
	private static WorldGenIceTree2 iceTree2 = new WorldGenIceTree2();

	public ChunkProviderFrozenLands(World worldIn, long seed, String st) {
		this.stoneNoise = new double[256];
		this.worldObj = worldIn;
		this.mapFeaturesEnabled = true;
		this.theWorldType = worldIn.getWorldInfo().getTerrainType();
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

		for(int j = -2; j <= 2; ++j) {
			for(int k = -2; k <= 2; ++k) {
				float f = 10.0F / MathHelper.sqrt(j * j + k * k + 0.2F);
				this.parabolicField[j + 2 + (k + 2) * 5] = f;
			}
		}

		if(st != null) this.settings = ChunkGeneratorSettings.Factory.jsonToFactory(st).build();

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

		for(int k = 0; k < 4; ++k) {
			int l = k * 5;
			int i1 = (k + 1) * 5;

			for(int j1 = 0; j1 < 4; ++j1) {
				int k1 = (l + j1) * 33;
				int l1 = (l + j1 + 1) * 33;
				int i2 = (i1 + j1) * 33;
				int j2 = (i1 + j1 + 1) * 33;

				for(int k2 = 0; k2 < 32; ++k2) {
					double d0 = 0.125D;
					double d1 = this.doubleA[k1 + k2];
					double d2 = this.doubleA[l1 + k2];
					double d3 = this.doubleA[i2 + k2];
					double d4 = this.doubleA[j2 + k2];
					double d5 = (this.doubleA[k1 + k2 + 1] - d1) * d0;
					double d6 = (this.doubleA[l1 + k2 + 1] - d2) * d0;
					double d7 = (this.doubleA[i2 + k2 + 1] - d3) * d0;
					double d8 = (this.doubleA[j2 + k2 + 1] - d4) * d0;

					for(int l2 = 0; l2 < 8; ++l2) {
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for(int i3 = 0; i3 < 4; ++i3) {
							double d14 = 0.25D;
							double d16 = (d11 - d10) * d14;
							double d15 = d10 - d16;

							for(int j3 = 0; j3 < 4; ++j3) {
								if((d15 += d16) > 0.0D) {
									cp.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, JourneyBlocks.brittleIce.getDefaultState());
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
		for(int i = 0; i < 16; i++) {
			for(int k = 0; k < 16; k++) {
				for(int j = 175; j > 0; j--) {
					if(cp.getBlockState(i, j, k) != Blocks.AIR.getDefaultState()) {
						top = j;
						break;
					}
				}
				for(int j = top - 10; j > top - 40; j--) {
					if(cp.getBlockState(i, j, k) != Blocks.BEDROCK.getDefaultState()) {
						cp.setBlockState(i, j, k, Blocks.AIR.getDefaultState());
					}
				}
			}
		}
		this.rand.setSeed(this.rand.nextInt(100));
		this.generateLowerNoise(x * 4, 0, z * 4);

		for(int k = 0; k < 4; ++k) {
			int l = k * 5;
			int i1 = (k + 1) * 5;

			for(int j1 = 0; j1 < 4; ++j1) {
				int k1 = (l + j1) * 33;
				int l1 = (l + j1 + 1) * 33;
				int i2 = (i1 + j1) * 33;
				int j2 = (i1 + j1 + 1) * 33;

				for(int k2 = 0; k2 < 32; ++k2) {
					double d0 = 0.125D;
					double d1 = this.doubleA[k1 + k2];
					double d2 = this.doubleA[l1 + k2];
					double d3 = this.doubleA[i2 + k2];
					double d4 = this.doubleA[j2 + k2];
					double d5 = (this.doubleA[k1 + k2 + 1] - d1) * d0;
					double d6 = (this.doubleA[l1 + k2 + 1] - d2) * d0;
					double d7 = (this.doubleA[i2 + k2 + 1] - d3) * d0;
					double d8 = (this.doubleA[j2 + k2 + 1] - d4) * d0;

					for(int l2 = 0; l2 < 8; ++l2) {
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for(int i3 = 0; i3 < 4; ++i3) {
							double d14 = 0.25D;
							double d16 = (d11 - d10) * d14;
							double d15 = d10 - d16;

							for(int j3 = 0; j3 < 4; ++j3) {
								if((d15 += d16) > 0.0D) {
									cp.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, JourneyBlocks.frozenDirt.getDefaultState());
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

		for(int i = 0; i < 16; i++) {
			for(int k = 0; k < 16; k++) {
				for(int j = 48; j>0; j--) {
					if(cp.getBlockState(i, j, k) == JourneyBlocks.brittleIce.getDefaultState()) {
						cp.setBlockState(i, j, k, JourneyBlocks.frozenDirt.getDefaultState());
					}
				}
				for(int j = 48; j > 0; j--) {
					if(cp.getBlockState(i, j, k) == JourneyBlocks.frozenDirt.getDefaultState()) {
						cp.setBlockState(i, j, k, JourneyBlocks.frozenGrass.getDefaultState());
						break;
					}
				}
				for(int j = 0; j < 64; j++) {
					if(cp.getBlockState(i, j, k) == JourneyBlocks.frozenGrass.getDefaultState()) {
						top = j;
						break;
					}
				}
				for(int j = top - 8; j > 0; j--) {
					if(cp.getBlockState(i, j, k) == JourneyBlocks.frozenDirt.getDefaultState()) {
						cp.setBlockState(i, j, k, JourneyBlocks.frozenStone.getDefaultState());
					}
				}
			}
		}

	}

	public void replaceBiomeBlocks(int x, int z, ChunkPrimer p, Biome[] b) {
		double d0 = 0.03125D;
		this.stoneNoise = this.perlinNoise.getRegion(this.stoneNoise, (double)(x * 16), (double)(z * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
		for(int k = 0; k < 16; k++) {
			for(int l = 0; l < 16; l++) {
				Biome Biome = b[l + k * 16];
				Biome.genTerrainBlocks(this.worldObj, this.rand, p, x * 16 + k, z * 16 + l, this.stoneNoise[l + k * 16]);
			}
		}
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		this.rand.setSeed(x * 341873128712L + z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		this.setBlocksInChunk(x, z, chunkprimer);
        this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
		this.replaceBiomeBlocks(x, z, chunkprimer, this.biomesForGeneration);
		Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
		byte[] abyte = chunk.getBiomeArray();
        for(int i = 0; i < abyte.length; ++i)
            abyte[i] = (byte)Biome.getIdForBiome(this.biomesForGeneration[i]);
		chunk.generateSkylightMap();
		return chunk;
	}

	private void generateNoise(int x, int y, int z) {
		this.n6 = this.noiseGen6.generateNoiseOctaves(this.n6, x, z, 5, 5, (double)this.settings.depthNoiseScaleX, (double)this.settings.depthNoiseScaleZ, (double)this.settings.depthNoiseScaleExponent);
		float f = this.settings.coordinateScale;
		float f1 = this.settings.heightScale;
		this.n3 = this.noiseGen3.generateNoiseOctaves(this.n3, x, y, z, 5, 33, 5, (double)(f / this.settings.mainNoiseScaleX/500), (double)(f1 / this.settings.mainNoiseScaleY), (double)(f / this.settings.mainNoiseScaleZ/500));
		this.n4 = this.noiseGen1.generateNoiseOctaves(this.n4, x, y, z, 5, 33, 5, f, f1, f);
		this.n5 = this.noiseGen2.generateNoiseOctaves(this.n5, x, y, z, 5, 33, 5, f, f1, f);
		int l = 0;
		int i1 = 0;
		for(int j1 = 0; j1 < 5; j1++) {
			for(int k1 = 0; k1 < 5; k1++) {
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;
				byte b0 = 2;
				//Biome biome = this.biomesForGeneration[j1 + 2 + (l + 2) * 10];
				for(int l1 = -b0; l1 <= b0; l1++) {
					for(int i2 = -b0; i2 <= b0; i2++) {
						Biome biome1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
						float f5 = this.settings.biomeDepthOffSet + biome1.getBaseHeight() * this.settings.biomeDepthWeight;
						float f6 = this.settings.biomeScaleOffset + biome1.getHeightVariation() * this.settings.biomeScaleWeight;
						float f7 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f5 + 2.0F);
						if(biome1.getBaseHeight() > biome1.getBaseHeight()) f7 /= 2.0F;
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
				if(d7 < 0.0D)  d7 = -d7 * 0.3D;
				d7 = d7 * 3.0D - 2.0D;
				if(d7 < 0.0D) {
					d7 /= 2.0D;
					if(d7 < -1.0D) d7 = -1.0D;
					d7 /= 1.4D;
					d7 /= 2.0D;
				} else {
					if(d7 > 1.0D) d7 = 1.0D;
					d7 /= 8.0D;
				}

				i1++;
				double d8 = f3;
				double d9 = f2;
				d8 += d7 * 0.2D;
				d8 = d8 * (double)this.settings.baseSize / 8.0D;
				double d0 = (double)this.settings.baseSize + d8 * 4.0D;

				for(int j2 = 0; j2 < 33; ++j2) {
					double d1 = (j2 - d0) * (double)this.settings.stretchY * 128.0D / 256.0D / d9;

					if(d1 < 0.0D)  d1 *= 4.0D;
					double d2 = this.n4[l] / ((double)this.settings.lowerLimitScale);
					double d3 = this.n5[l] / (double)this.settings.upperLimitScale;
					double d4 = (this.n3[l] / 10.0D + 1.0D) / 2.0D;
					double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

					if(j2 > 29) {
						double d6 = (j2 - 29) / 3.0F;
						d5 = d5 * (1.0D - d6) + -10.0D * d6;
					}

					this.doubleA[l] = d5;
					l++;
				}
			}
		}
	}

	private void generateLowerNoise(int x, int y, int z) {
		this.n6 = this.noiseGen6.generateNoiseOctaves(this.n6, x, z, 5, 5, (double)this.settings.depthNoiseScaleX, (double)this.settings.depthNoiseScaleZ, (double)this.settings.depthNoiseScaleExponent);
		float f = this.settings.coordinateScale;
		float f1 = this.settings.heightScale;
		this.n3 = this.noiseGen3.generateNoiseOctaves(this.n3, x, y, z, 5, 33, 5, (double)(f / (this.settings.mainNoiseScaleX/2)), (double)(f1 / this.settings.mainNoiseScaleY), (double)(f / (this.settings.mainNoiseScaleZ/2)));
		this.n4 = this.noiseGen1.generateNoiseOctaves(this.n4, x, y, z, 5, 33, 5, f, f1, f);
		this.n5 = this.noiseGen2.generateNoiseOctaves(this.n5, x, y, z, 5, 33, 5, f, f1, f);
		int l = 0;
		int i1 = 0;

		for(int j1 = 0; j1 < 5; ++j1) {
			for(int k1 = 0; k1 < 5; ++k1) {
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;
				byte b0 = 2;
				Biome biome = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];

				for(int l1 = -b0; l1 <= b0; ++l1) {
					for(int i2 = -b0; i2 <= b0; ++i2) {
						Biome biome1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];						
						float f5 = this.settings.biomeDepthOffSet + biome1.getBaseHeight() * this.settings.biomeDepthWeight;
						float f6 = this.settings.biomeScaleOffset + biome1.getHeightVariation() * this.settings.biomeScaleWeight;

						float f7 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f5 + 2.0F);

						if(biome1.getBaseHeight() > biome.getBaseHeight()) f7 /= 2.0F;
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
				if(d7 < 0.0D) d7 = -d7 * 0.3D;
				d7 = d7 * 3.0D - 2.0D;
				if(d7 < 0.0D) {
					d7 /= 2.0D;
					if(d7 < -1.0D) d7 = -1.0D;
					d7 /= 1.4D;
					d7 /= 2.0D;
				} else {
					if(d7 > 1.0D) d7 = 1.0D;
					d7 /= 8.0D;
				}

				i1++;
				double d8 = f3;
				double d9 = f2;
				d8 += d7 * 0.2D;
				d8 = d8 * (double)this.settings.baseSize / 8.0D;
				double d0 = (double)this.settings.baseSize + d8 * 4.0D;

				for(int j2 = 0; j2 < 33; ++j2) {
					double d1 = 100+(j2 - d0) * (double)this.settings.stretchY * 128.0D / 256.0D / d9;

					if(d1 < 0.0D) d1 *= 4.0D;
					double d2 = this.n4[l] / ((double)this.settings.lowerLimitScale);
					double d3 = this.n5[l] / (double)this.settings.upperLimitScale;
					double d4 = (this.n3[l] / 10.0D + 1.0D) / 2.0D;
					double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

					if(j2 > 29) {
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
	public void populate(int chunkX, int chunkZ) {
		int k = chunkX * 16;
		int l = chunkZ * 16;
		this.rand.setSeed(chunkX * this.rand.nextInt() + chunkZ * this.rand.nextInt() ^ this.worldObj.getSeed());

		for(int n = 0; n < 6; n++) {
			int x = chunkX * 16 + rand.nextInt(16) + 8, z = chunkZ * 16 + rand.nextInt(16) + 8;
			int y = 0;
			for(int j = 5; j < 100; j++) {
				if(worldObj.getBlockState(new BlockPos(x, j, z)).getBlock() == Blocks.AIR) {
					y = j;
					break;
				}
			}
			tree.generate(worldObj, rand, new BlockPos(x, y, z));
		}

		if(rand.nextInt(100)==0) {
			int x = chunkX * 16 + rand.nextInt(16) + 8, z = chunkZ * 16 + rand.nextInt(16) + 8;
			int y = 0;
			for(int j = 5; j < 100; j++) {
				if(worldObj.getBlockState(new BlockPos(x, j, z)).getBlock() == JourneyBlocks.frozenGrass) {
					y = j;
					break;
				}
			}
			new WorldGenMerchantHouse().generate(worldObj, rand, new BlockPos(x, y, z));
		}

		for(int n = 0; n < 2; n++) {
			int x = chunkX * 16 + rand.nextInt(16) + 8, z = chunkZ * 16 + rand.nextInt(16) + 8;
			int y = 0;
			for(int j = 5; j < 100; j++) {
				if(worldObj.getBlockState(new BlockPos(x, j, z)).getBlock() == Blocks.AIR) {
					y = j;
					break;
				}
			}
			tree2.generate(worldObj, rand, new BlockPos(x, y, z));
		}

		if(rand.nextInt(4)==0) {
			int x = chunkX * 16 + rand.nextInt(16) + 8, z = chunkZ * 16 + rand.nextInt(16) + 8;
			int y = 0;
			for(int j = 5; j < 100; j++) {
				if(worldObj.getBlockState(new BlockPos(x, j, z)).getBlock() == Blocks.AIR) {
					y = j;
					break;
				}
			}
			lamp.generate(worldObj, rand, new BlockPos(x, y, z));
		}

		if(rand.nextInt(380)==0) {
			int x = chunkX * 16 + rand.nextInt(16) + 8, 
					z = chunkZ * 16 + rand.nextInt(16) + 8;
			int y = 0;
			for(int j = 150; j > 50; j--) {
				if(worldObj.getBlockState(new BlockPos(x, j, z)).getBlock() == JourneyBlocks.brittleIce) {
					y = j;
					break;
				}
			}
			dungeon.generate(worldObj, rand, new BlockPos(x, y, z));
		}

		for(int n = 0; n < 2; n++) {
			int x = k + rand.nextInt(16), z = l + rand.nextInt(16);
			int y = 0;
			for(int j = 150; j > 50; j--) {
				if(worldObj.getBlockState(new BlockPos(x, j, z)).getBlock() == JourneyBlocks.brittleIce) {
					y = j;
					break;
				}
			}
			crystal1.generate(worldObj, rand, new BlockPos(x, y, z));
		}

		for(int n = 0; n < 2; n++) {
			int x = k + rand.nextInt(16), z = l + rand.nextInt(16);
			int y = 0;
			for(int j = 150; j > 50; j--) {
				if(worldObj.getBlockState(new BlockPos(x, j, z)).getBlock() == JourneyBlocks.brittleIce) {
					y = j;
					break;
				}
			}
			iceTree.generate(worldObj, rand, new BlockPos(x, y, z));
		}

		for(int n = 0; n < 2; n++) {
			int x = k + rand.nextInt(16), z = l + rand.nextInt(16);
			int y = 0;
			for(int j = 150; j > 50; j--) {
				if(worldObj.getBlockState(new BlockPos(x, j, z)).getBlock() == JourneyBlocks.brittleIce) {
					y = j;
					break;
				}
			}
			iceTree2.generate(worldObj, rand, new BlockPos(x, y, z));
		}

		if(rand.nextInt(4) == 0) {
			int x = chunkX * 16 + rand.nextInt(16) + 8, z = chunkZ * 16 + rand.nextInt(16) + 8;
			int y = 0;
			for(int j = 150; j > 50; j--) {
				if(worldObj.getBlockState(new BlockPos(x, j, z)).getBlock() == JourneyBlocks.brittleIce) {
					y = j;
					break;
				}
			}
			crystal2.generate(worldObj, rand, new BlockPos(x, y, z));
		}

		//Bottom flowers
		for(int i = 0; i < 10; i++) {
			int x = chunkX * 16 + rand.nextInt(16) + 8, y = rand.nextInt(40), z = chunkZ * 16 + rand.nextInt(16) + 8;
			new WorldGenModFlower(JourneyBlocks.iceBud).generate(worldObj, rand, new BlockPos(x, y, z));
		}

		for(int i = 0; i < 10; i++) {
			int x = chunkX * 16 + rand.nextInt(16) + 8, y = rand.nextInt(40), z = chunkZ * 16 + rand.nextInt(16) + 8;
			new WorldGenModFlower(JourneyBlocks.frostberryThorn).generate(worldObj, rand, new BlockPos(x, y, z));
		}

		for(int i = 0; i < 10; i++) {
			int x = chunkX * 16 + rand.nextInt(16) + 8, y = rand.nextInt(40), z = chunkZ * 16 + rand.nextInt(16) + 8;
			new WorldGenModFlower(JourneyBlocks.frozenBlooms).generate(worldObj, rand, new BlockPos(x, y, z));
		}

		//Top flowers
		for(int i = 0; i < 10; i++) {
			int x = chunkX * 16 + rand.nextInt(16) + 8, y = rand.nextInt(70), z = chunkZ * 16 + rand.nextInt(16) + 8;
			if(worldObj.getBlockState(new BlockPos(x, y, z)).getBlock() == JourneyBlocks.brittleIce) {  
				new WorldGenModFlower(JourneyBlocks.permaFlower).generate(worldObj, rand, new BlockPos(x, y, z));
			}
		}

		for(int i = 0; i < 10; i++) {
			int x = chunkX * 16 + rand.nextInt(16) + 8, y = rand.nextInt(70), z = chunkZ * 16 + rand.nextInt(16) + 8;
			if(worldObj.getBlockState(new BlockPos(x, y, z)).getBlock() == JourneyBlocks.brittleIce) { 
				new WorldGenModFlower(JourneyBlocks.shiverFlower).generate(worldObj, rand, new BlockPos(x, y, z));
			}
		}

		for(int i = 0; i < 10; i++) {
			int x = chunkX * 16 + rand.nextInt(16) + 8, y = rand.nextInt(70), z = chunkZ * 16 + rand.nextInt(16) + 8;
			if(worldObj.getBlockState(new BlockPos(x, y, z)).getBlock() == JourneyBlocks.brittleIce) { 
				new WorldGenModFlower(JourneyBlocks.iceBush).generate(worldObj, rand, new BlockPos(x, y, z));
			}
		}
	}

	@Override
	public List <SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = this.worldObj.getBiome(pos);
		return biome.getSpawnableList(creatureType);
	}

	@Override
	public void recreateStructures(Chunk c, int x, int z) { }

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