package net.journey.dimension.depths;

import java.util.List;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.dimension.depths.gen.WorldGenDarkbloom;
import net.journey.dimension.depths.gen.WorldGenDepthsLights;
import net.journey.dimension.depths.gen.WorldGenDepthsTree;
import net.journey.dimension.depths.gen.WorldGenDepthsTree1;
import net.journey.dimension.depths.gen.WorldGenDepthsTree2;
import net.journey.dimension.depths.gen.WorldGenGuardianTower;
import net.journey.dimension.depths.gen.WorldGenPlant1;
import net.journey.dimension.depths.gen.WorldGenPlant2;
import net.journey.dimension.depths.gen.WorldGenPlant3;
import net.journey.dimension.depths.gen.WorldGenSorcererShrine;
import net.journey.dimension.depths.gen.WorldGenSpike;
import net.journey.dimension.overworld.gen.WorldGenModFlower;
import net.minecraft.block.Block;
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
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextHell;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderDepths implements IChunkGenerator {

	private Random rand;
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	private NoiseGeneratorPerlin noiseGen4;
	public NoiseGeneratorOctaves noiseGen5;
	public NoiseGeneratorOctaves noiseGen6;
	public NoiseGeneratorOctaves mobSpawnerNoise;
	private World worldObj;
	private final double[] da;
	private final float[] parabolicField;
	private double[] stoneNoise;
	private Biome[] biomesForGeneration;
	double[] gen1, gen2, gen3, gen4;

	public ChunkProviderDepths(World worldIn, long s) {
		this.stoneNoise = new double[256];
		this.worldObj = worldIn;
		this.rand = new Random(s);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.noiseGen4 = new NoiseGeneratorPerlin(this.rand, 4);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
		this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
		this.da = new double[825];
		this.parabolicField = new float[25];

		for (int j = -2; j <= 2; ++j) {
			for (int k = -2; k <= 2; ++k) {
				float f = 10.0F / MathHelper.sqrt(j * j + k * k + 0.2F);
				this.parabolicField[j + 2 + (k + 2) * 5] = f;
			}
		}

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

	public void setBlocksInChunk(int x, int z, ChunkPrimer p_180518_3_) {
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
		this.generate(x * 4, 0, z * 4);

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
					double d1 = this.da[k1 + k2];
					double d2 = this.da[l1 + k2];
					double d3 = this.da[i2 + k2];
					double d4 = this.da[j2 + k2];
					double d5 = (this.da[k1 + k2 + 1] - d1) * d0;
					double d6 = (this.da[l1 + k2 + 1] - d2) * d0;
					double d7 = (this.da[i2 + k2 + 1] - d3) * d0;
					double d8 = (this.da[j2 + k2 + 1] - d4) * d0;

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
									int y = k2 * 8 + l2;
									if(y <= 84) p_180518_3_.setBlockState(k * 4 + i3, y, j1 * 4 + j3, JourneyBlocks.depthsStone.getDefaultState());
									p_180518_3_.setBlockState(k * 4 + i3, 85, j1 * 4 + j3, Blocks.BEDROCK.getDefaultState());
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

	public void biomeBlocks(int x, int z, ChunkPrimer c, Biome[] b) {
        if(!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, c, this.worldObj)) return;
		double d0 = 0.03125D;
		this.stoneNoise = this.noiseGen4.getRegion(this.stoneNoise, (double)(x * 16), (double)(z * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
		for(int k = 0; k < 16; ++k) {
			for(int l = 0; l < 16; ++l) {
				generateBiomeTerrain(this.rand, c, x * 16 + k, z * 16 + l, this.stoneNoise[l + k * 16]);
			}
		}
	}

	public final void generateBiomeTerrain(Random r, ChunkPrimer c, int x, int z, double d) {
		int i1 = x & 15;
		int j1 = z & 15;
		c.setBlockState(j1, 1, i1, JourneyBlocks.depthsGrass.getDefaultState());
		c.setBlockState(j1, 0, i1, Blocks.BEDROCK.getDefaultState());
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
		this.biomeBlocks(x, z, chunkprimer, this.biomesForGeneration);
		for(int i = 0; i < 10; i++) {
			int ycoord = rand.nextInt(249) + 1;
			if(chunkprimer.getBlockState(8, ycoord, 8).getBlock() == Blocks.AIR && chunkprimer.getBlockState(8, ycoord - 1, 8).getBlock() == JourneyBlocks.darkFloor && chunkprimer.getBlockState(8, ycoord + 1, 8).getBlock() == Blocks.AIR) {
				new WorldGenSpike().generate(chunkprimer, rand, new BlockPos(8, ycoord, 8));
			}
		}
		Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
		byte[] abyte = chunk.getBiomeArray();
		for(int k = 0; k < abyte.length; ++k) abyte[k] = (byte)Biome.getIdForBiome(this.biomesForGeneration[k]);
		chunk.generateSkylightMap();
		return chunk;
	}

	@SuppressWarnings("unused")
	private void generate(int x, int y, int z) {
		this.gen4 = this.noiseGen6.generateNoiseOctaves(this.gen4, x, z, 5, 5, 200.0D, 200.0D, 0.5D);
		this.gen1 = this.noiseGen3.generateNoiseOctaves(this.gen1, x, y, z, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
		this.gen2 = this.noiseGen1.generateNoiseOctaves(this.gen2, x, y, z, 5, 33, 5, 684.41200000000003D, 684.41200000000003D, 684.41200000000003D);
		this.gen3 = this.noiseGen2.generateNoiseOctaves(this.gen3, x, y, z, 5, 33, 5, 684.41200000000003D, 684.41200000000003D, 684.41200000000003D);
		boolean flag1 = false;
		boolean flag = false;
		int l = 0;
		int i1 = 0;
		double d4 = 8.5D;
		for (int j1 = 0; j1 < 5; j1++) {
			for (int k1 = 0; k1 < 5; k1++) {
				float f = 0.0F;
				float f1 = 0.0F;
				float f2 = 0.0F;
				byte b0 = 2;
				Biome Biome = this.biomesForGeneration[(j1 + 2 + (k1 + 2) * 10)];
				for (int l1 = -b0; l1 <= b0; l1++) {
					for (int i2 = -b0; i2 <= b0; i2++) {
						Biome Biome1 = this.biomesForGeneration[(j1 + l1 + 2 + (k1 + i2 + 2) * 10)];
						float f3 = 0.1F;
						float f4 = 0.2F;
						f3 = 0.0F;
						f4 = -1.0F;
						float f5 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0F);
						if(0.1F > 0.2F) f5 /= 2.0F;
						f += f4 * f5;
						f1 += f3 * f5;
						f2 += f5;
					}
				}
				f /= f2;
				f1 /= f2;
				f = f * 0.9F + 0.1F;
				f1 = (f1 * 4.0F - 1.0F) / 8.0F;
				double d12 = this.gen4[i1] / 8000.0D;
				if (d12 < 0.0D) {
					d12 = -d12 * 0.3D;
				}
				d12 = d12 * 3.0D - 2.0D;
				if (d12 < 0.0D) {
					d12 /= 2.0D;
					if (d12 < -1.0D) {
						d12 = -1.0D;
					}
					d12 /= 1.4D;
					d12 /= 2.0D;
				} else {
					if (d12 > 1.0D) {
						d12 = 1.0D;
					}
					d12 /= 8.0D;
				}
				i1++;
				double d13 = f1;
				double d14 = f;
				d13 += d12 * 0.2D;
				d13 = d13 * 8.5D / 8.0D;
				double d5 = 8.5D + d13 * 4.0D;
				for (int j2 = 0; j2 < 33; j2++) {
					double d6 = (j2 - d5) * 12.0D * 128.0D / 256.0D / d14;
					if (d6 < 0.0D) {
						d6 *= 4.0D;
					}
					double d7 = this.gen2[l] / 512.0D;
					double d8 = this.gen3[l] / 512.0D;
					double d9 = (this.gen1[l] / 10.0D + 1.0D) / 2.0D;
					double d10 = MathHelper.clampedLerp(d7, d8, d9) - d6;
					if (j2 > 29) {
						double d11 = (j2 - 29) / 3.0F;
						d10 = d10 * (1.0D - d11) + -10.0D * d11;
					}
					this.da[l] = d10;
					l++;
				}
			}
		}
	}
	
	public boolean isBlockTop(int x, int y, int z, Block grass) {
		return worldObj.getBlockState(new BlockPos(x, y, z)) == grass.getDefaultState() && worldObj.getBlockState(new BlockPos(x, y + 1, z)) == Blocks.AIR.getDefaultState()
				&& worldObj.getBlockState(new BlockPos(x, y + 2, z)) == Blocks.AIR.getDefaultState() && worldObj.getBlockState(new BlockPos(x, y + 3, z)) == Blocks.AIR.getDefaultState()
				&& worldObj.getBlockState(new BlockPos(x, y + 4, z)) == Blocks.AIR.getDefaultState() && worldObj.getBlockState(new BlockPos(x, y + 5, z)) == Blocks.AIR.getDefaultState();
	}

	@Override
	public List <SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = this.worldObj.getBiome(pos);
		return biome.getSpawnableList(creatureType);
	}

	@Override
	public void recreateStructures(Chunk c, int x, int z) { }

	@Override
	public void populate(int cx, int cz) {
		int x1 = cx * 16;
		int z1 = cz * 16;
		int x, y, z, i;
		x = x1 + this.rand.nextInt(16);
		z = z1 + this.rand.nextInt(16);
		Random r = rand;

		for(i = 0; i < 17; i++) {
			y = r.nextInt(250); x = x1 + this.rand.nextInt(16) + 8; z = z1 + this.rand.nextInt(16) + 8;
			new WorldGenModFlower(JourneyBlocks.depthsFlower).generate(worldObj, r, new BlockPos(x, y, z));
		}

		for(i = 0; i < 17; i++) {
			y = r.nextInt(250); x = x1 + this.rand.nextInt(16) + 8; z = z1 + this.rand.nextInt(16) + 8;
			new WorldGenModFlower(JourneyBlocks.depthsBlueFlower).generate(worldObj, r, new BlockPos(x, y, z));
		}

		for(i = 0; i < 25; i++) {
			y = r.nextInt(250); x = x1 + this.rand.nextInt(16) + 8; z = z1 + this.rand.nextInt(16) + 8;
			(new WorldGenMinable(JourneyBlocks.flairiumOre.getDefaultState(), 8, BlockStateMatcher.forBlock(JourneyBlocks.depthsStone))).generate(worldObj, r, new BlockPos(x, y, z));
		}

		for(i = 0; i < 25; i++) {
			y = r.nextInt(250); x = x1 + this.rand.nextInt(16) + 8; z = z1 + this.rand.nextInt(16) + 8;
			(new WorldGenMinable(JourneyBlocks.desOre.getDefaultState(), 8, BlockStateMatcher.forBlock(JourneyBlocks.depthsStone))).generate(worldObj, r, new BlockPos(x, y, z));
		}

		for(i = 0; i < 11; i++) {
			new WorldGenDepthsLights().generate(this.worldObj, rand, new BlockPos(x1 + rand.nextInt(16) + 8, rand.nextInt(120) + 4, z1 + rand.nextInt(16) + 8));
		}

		for(i = 0; i < 11; i++) {
			new WorldGenDepthsLights.WorldGendepthsLights2().generate(this.worldObj, rand, new BlockPos(x1 + rand.nextInt(16) + 8, rand.nextInt(120) + 4, z1 + rand.nextInt(16) + 8));
		}

		for(i = 0; i < 100; i++) {
			int yCoord = rand.nextInt(128) + 1;
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			if(isBlockTop(x, yCoord - 1, z, JourneyBlocks.depthsGrass)) {
				new WorldGenDepthsTree(true).generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}
		
		for(i = 0; i < 100; i++) {
			int yCoord = rand.nextInt(128) + 1;
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			if(isBlockTop(x, yCoord - 1, z, JourneyBlocks.depthsGrass)) {
				new WorldGenDepthsTree1(true).generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}
		
		for(i = 0; i < 100; i++) {
			int yCoord = rand.nextInt(128) + 1;
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			if(isBlockTop(x, yCoord - 1, z, JourneyBlocks.depthsGrass)) {
				new WorldGenDepthsTree2(true).generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}
		
		for(i = 0; i < 100; i++) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128) + 1;
			if(isBlockTop(x, yCoord - 2, z, JourneyBlocks.depthsGrass)) {
				new WorldGenPlant1(true).generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}
		
		for(i = 0; i < 100; i++) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128) + 1;
			if(isBlockTop(x, yCoord - 2, z, JourneyBlocks.depthsGrass)) {
				new WorldGenPlant2(true).generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}
		
		for(i = 0; i < 100; i++) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128) + 1;
			if(isBlockTop(x, yCoord - 2, z, JourneyBlocks.depthsGrass)) {
				new WorldGenPlant3(true).generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}
		
		for(i = 0; i < 100; i++) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128) + 1;
			if(isBlockTop(x, yCoord - 2, z, JourneyBlocks.depthsGrass)) {
				new WorldGenDarkbloom().generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}
		
		if(rand.nextInt(9)==0) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128) + 1;
			if(isBlockTop(x, yCoord - 1, z, JourneyBlocks.depthsGrass)) {
				new WorldGenSorcererShrine().generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}
		
		if(rand.nextInt(7)==0) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128) + 1;
			if(isBlockTop(x, yCoord - 1, z, JourneyBlocks.depthsGrass)) {
				new WorldGenGuardianTower().generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}
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