package net.journey.dimension.depths;

import java.util.List;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.dimension.WorldGenStructure;
import net.journey.dimension.cloudia.ChunkProviderCloudia;
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
import net.journey.util.JourneyLootTables;
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
	private double[] gen1, gen2, gen3, gen4;
	private ChunkGeneratorSettings settings;

	private WorldGenSpike spike = new WorldGenSpike();
	private WorldGenModFlower flower = new WorldGenModFlower(JourneyBlocks.depthsFlower, JourneyBlocks.depthsGrass, false);
	private WorldGenModFlower flower2 = new WorldGenModFlower(JourneyBlocks.depthsBlueFlower, JourneyBlocks.depthsGrass, false);
	private WorldGenDepthsLights lights = new WorldGenDepthsLights();
	private WorldGenDepthsLights.WorldGendepthsLights2 lights2 = new WorldGenDepthsLights.WorldGendepthsLights2();
	private WorldGenDepthsTree tree = new WorldGenDepthsTree(true);
	private WorldGenDepthsTree1 tree1 = new WorldGenDepthsTree1(true);
	private WorldGenDepthsTree2 tree2 = new WorldGenDepthsTree2(true);
	private WorldGenPlant2 plant2 = new WorldGenPlant2(true);
	private WorldGenPlant3 plant3 = new WorldGenPlant3(true);
	private WorldGenDarkbloom darkbloom = new WorldGenDarkbloom();
	private WorldGenSorcererShrine shrine = new WorldGenSorcererShrine();
	private WorldGenGuardianTower tower = new WorldGenGuardianTower();
	private WorldGenPlant1 plant1 = new WorldGenPlant1(true);
	private WorldGenMinable flairum = new WorldGenMinable(JourneyBlocks.flairiumOre.getDefaultState(), 8, BlockStateMatcher.forBlock(JourneyBlocks.depthsStone));
	private WorldGenMinable des = new WorldGenMinable(JourneyBlocks.desOre.getDefaultState(), 8, BlockStateMatcher.forBlock(JourneyBlocks.depthsStone));
	private WorldGenMinable floorgems = new WorldGenMinable(JourneyBlocks.depthsLights.getDefaultState(), 40, BlockStateMatcher.forBlock(JourneyBlocks.depthsGrass));
	private WorldGenStructure depthsHouse = new WorldGenStructure("depths_house1");
	private WorldGenStructure dungeon = new WorldGenStructure("big", JourneyLootTables.TEST_CHEST);


	public ChunkProviderDepths(World worldIn, long s, String st) {
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

		if (st != null) this.settings = ChunkGeneratorSettings.Factory.jsonToFactory(st).build();

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

	public void setBlocksInChunk(int x, int z, ChunkPrimer cp) {
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
		this.generateNoise(x * 4, 0, z * 4);
		IBlockState grass = JourneyBlocks.depthsGrass.getDefaultState();
		IBlockState filler = JourneyBlocks.depthsDirt.getDefaultState();
		IBlockState stone = JourneyBlocks.depthsStone.getDefaultState();
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
					double d1 = this.da[k1 + k2];
					double d2 = this.da[l1 + k2];
					double d3 = this.da[i2 + k2];
					double d4 = this.da[j2 + k2];
					double d5 = (this.da[k1 + k2 + 1] - d1) * d0;
					double d6 = (this.da[l1 + k2 + 1] - d2) * d0;
					double d7 = (this.da[i2 + k2 + 1] - d3) * d0;
					double d8 = (this.da[j2 + k2 + 1] - d4) * d0;

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
								if ((d15 += d16) > 0.0D) {
									cp.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, stone);
									cp.setBlockState(k * 4 + i3, k2 * 8 + l2 + 1, j1 * 4 + j3, grass);
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
				for(int j = 100; j > 0; j--) {
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

		for(int i = 0; i < 16; i++) {
			for(int k = 0; k < 16; k++) {
				cp.setBlockState(i, 110, k, stone);
				cp.setBlockState(i, 0, k, Blocks.BEDROCK.getDefaultState());
				for (int j = 48; j > 0; j--) {
					if (cp.getBlockState(i, j, k) == stone) {
						cp.setBlockState(i, j, k, filler);
					}
				}
				for(int j = 48; j > 0; j--) {
					if(cp.getBlockState(i, j, k) == filler) {
						cp.setBlockState(i, j, k, grass);
						break;
					}
				}
				for(int j = 0; j < 64; j++) {
					if (cp.getBlockState(i, j, k) == grass) {
						top = j;
						break;
					}
				}
				for(int j = top - 4; j > 0; j--) {
					if (cp.getBlockState(i, j, k) == filler) {
						cp.setBlockState(i, j, k, stone);
					}
				}
			}
		}
	}

	private void generateNoise(int x, int y, int z) {
		this.gen4 = this.noiseGen6.generateNoiseOctaves(this.gen4, x, z, 5, 5, (double) this.settings.depthNoiseScaleX, (double) this.settings.depthNoiseScaleZ, (double) this.settings.depthNoiseScaleExponent);
		float f = this.settings.coordinateScale;
		float f1 = this.settings.heightScale;
		this.gen1 = this.noiseGen3.generateNoiseOctaves(this.gen1, x, y, z, 5, 33, 5, (double) (f / this.settings.mainNoiseScaleX / 500), (double) (f1 / this.settings.mainNoiseScaleY), (double) (f / this.settings.mainNoiseScaleZ / 500));
		this.gen2 = this.noiseGen1.generateNoiseOctaves(this.gen2, x, y, z, 5, 33, 5, f, f1, f);
		this.gen3 = this.noiseGen2.generateNoiseOctaves(this.gen3, x, y, z, 5, 33, 5, f, f1, f);
		int l = 0;
		int i1 = 0;
		for (int j1 = 0; j1 < 5; j1++) {
			for (int k1 = 0; k1 < 5; k1++) {
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;
				byte b0 = 2;
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
				double d7 = this.gen4[i1] / 8000.0D;
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
					double d2 = this.gen2[l] / ((double) this.settings.lowerLimitScale);
					double d3 = this.gen3[l] / (double) this.settings.upperLimitScale;
					double d4 = (this.gen1[l] / 10.0D + 1.0D) / 2.0D;
					double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

					if (j2 > 29) {
						double d6 = (j2 - 29) / 3.0F;
						d5 = d5 * (1.0D - d6) + -10.0D * d6;
					}

					this.da[l] = d5;
					l++;
				}
			}
		}
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		return this.provideChunk(x, z);
	}

	@SuppressWarnings("SPIKE")
	public Chunk provideChunk(int x, int z) {
		this.rand.setSeed(x * 341873128712L + z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		this.setBlocksInChunk(x, z, chunkprimer);
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
		/*if (rand.nextInt(50) == 0) {
            int ycoord = 2;
            if(chunkprimer.getBlockState(8, ycoord, 8).getBlock() == Blocks.AIR && chunkprimer.getBlockState(8, ycoord + 1, 8).getBlock() == Blocks.AIR) {
                spike.generate(chunkprimer, rand, new BlockPos(8, ycoord, 8));
            }
        }*/
		Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
		byte[] abyte = chunk.getBiomeArray();
		for (int k = 0; k < abyte.length; ++k) abyte[k] = (byte) Biome.getIdForBiome(this.biomesForGeneration[k]);
		chunk.generateSkylightMap();
		return chunk;
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
	public void recreateStructures(Chunk c, int x, int z) { }

	@Override
	public void populate(int cx, int cz) {
		int x1 = cx * 16;
		int z1 = cz * 16;
		int i, times;
		
		WorldGenerator[] trees = new WorldGenerator[] {tree, tree1, tree2};
		
		int top = 64;
		int bottom = 0;

		BlockPos chunkStart = new BlockPos(x1, 0, z1);
		for(i = 0; i < 20; i++) {
			floorgems.generate(worldObj, rand, new BlockPos(x1, rand.nextInt(250), z1));
		}

		for (i = 0; i < 40; i++) {
			flower.generate(worldObj, rand,  chunkStart);
			flower2.generate(worldObj, rand, chunkStart);
		}

		for (i = 0; i < 25; i++) {
			flairum.generate(worldObj, rand, new BlockPos(x1, rand.nextInt(250), z1));
			des.generate(worldObj, rand, new BlockPos(x1, rand.nextInt(250), z1));
		}
		
		for(times = 0; times < 100; times++) {
			int randX = cx * 16 + 8 + rand.nextInt(16);
			int randZ = cz * 16 + 8 + rand.nextInt(16);
			int randY = rand.nextInt(80) + 1;
			if(isBlockTop(randX, randY - 1, randZ, JourneyBlocks.depthsGrass)) {
				trees[rand.nextInt(trees.length)].generate(worldObj, rand, new BlockPos(randX, randY, randZ));
			}
		}

		for (i = 0; i < 15; i++) {
			lights.generate(this.worldObj, rand, WorldGenAPI.createRandom(x1, 4, 120, z1, rand, 16));
			lights2.generate(this.worldObj, rand, WorldGenAPI.createRandom(x1, 4, 120, z1, rand, 16));
		}

		if(rand.nextInt(1000) == 0) {
			//generateBottomStructure(dungeon, x1, z1);
		}

		for (i = 0; i < 120; i++) {
			generateStructure(plant1, x1, z1);
			generateStructure(plant2, x1, z1);
			generateStructure(plant3, x1, z1);
			generateStructure(darkbloom, x1, z1);
		}

		for(i = 0; i < 10; i++) {
			//generateTopStructure(depthsHouse, x1, z1);
		}

		if (rand.nextInt(1) == 0) {
			generateBottomStructure(shrine, x1, z1);
		}

		if (rand.nextInt(2) == 0) {
			generateBottomStructure(tower, x1, z1);
		}

	}

	private void generateStructure(WorldGenerator gen, int x, int z){
		BlockPos pos = WorldGenAPI.createRandom(x, 1, 90 + 1, z, rand, 8);
		if (isBlockTop(pos.getX(), pos.getY() - 1, pos.getZ(), JourneyBlocks.depthsGrass)) {
			gen.generate(worldObj, rand, pos);
		}
	}

	private void generateTopStructure(WorldGenerator gen, int x, int z){
		BlockPos pos = WorldGenAPI.createRandom(x, 64, 90 + 1, z, rand, 8);
		if (isBlockTop(pos.getX(), pos.getY() - 1, pos.getZ(), JourneyBlocks.depthsGrass)) {
			gen.generate(worldObj, rand, pos);
		}
	}

	private void generateBottomStructure(WorldGenerator gen, int x, int z){
		BlockPos pos = WorldGenAPI.createRandom(x, 1, 64 + 1, z, rand, 8);
		if (isBlockTop(pos.getX(), pos.getY() - 1, pos.getZ(), JourneyBlocks.depthsGrass)) {
			gen.generate(worldObj, rand, pos);
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