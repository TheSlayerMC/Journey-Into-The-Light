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
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ChunkProviderEuca implements IChunkProvider {

	private Random rand;
	private NoiseGeneratorOctaves noiseGen1, noiseGen2, noiseGen3, noiseGen5, noiseGen6;
	private World worldObj;
	private double[] noiseArray;
	private BiomeGenBase[] biomesForGeneration;
	private double[] noise3, noise1, noise2, noise5, noise6;
	private ArrayList<WorldGenerator> trees;

	public ChunkProviderEuca(World var1, long var2){
		this.worldObj = var1;
		this.rand = new Random(var2);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
		new NoiseGeneratorOctaves(this.rand, 4);
		new NoiseGeneratorOctaves(this.rand, 4);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
		this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
		new WorldGenEucaWater(Blocks.flowing_water, true);
		new WorldGenEucaWater(Blocks.flowing_water, false);

		trees = new ArrayList<WorldGenerator>(3);
		trees.add(new WorldGenEucaTree());
		trees.add(new WorldGenEucaTree2());
		trees.add(new WorldGenEucaTree3());
		trees.add(new WorldGenEucaTree4());
		trees.add(new WorldGenEucaTree5());
	}

	@Override
	public boolean chunkExists(int i, int j) {
		return true;
	}

	@Override
	public Chunk provideChunk(int par1, int par2) {
		this.rand.setSeed(par1 * 391279512714L + par2 * 132894987741L);
		ChunkPrimer primer = new ChunkPrimer();
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
		this.generateTerrain(par1, par2, primer);
		this.replaceBlocksForBiome(primer);
		Chunk chunk = new Chunk(this.worldObj, primer, par1, par2);
		byte[] abyte = chunk.getBiomeArray();
		for(int k = 0; k < abyte.length; ++k) abyte[k] = (byte)this.biomesForGeneration[k].biomeID;
		chunk.generateSkylightMap();
		return chunk;
	}

	public void generateTerrain(int var1, int var2, ChunkPrimer primer) {
		byte b0 = 2;
		int k = b0 + 1;
		byte b1 = 33;
		int l = b0 + 1;
		this.noiseArray = this.initializeNoiseField(this.noiseArray, var1 * b0, 0, var2 * b0, k, b1, l);

		for (int i1 = 0; i1 < b0; ++i1) {
			for (int j1 = 0; j1 < b0; ++j1) {
				for (int k1 = 0; k1 < 32; ++k1) {
					double d0 = 0.25D;
					double d1 = this.noiseArray[((i1 + 0) * l + j1 + 0) * b1 + k1 + 0];
					double d2 = this.noiseArray[((i1 + 0) * l + j1 + 1) * b1 + k1 + 0];
					double d3 = this.noiseArray[((i1 + 1) * l + j1 + 0) * b1 + k1 + 0];
					double d4 = this.noiseArray[((i1 + 1) * l + j1 + 1) * b1 + k1 + 0];
					double d5 = (this.noiseArray[((i1 + 0) * l + j1 + 0) * b1 + k1 + 1] - d1) * d0;
					double d6 = (this.noiseArray[((i1 + 0) * l + j1 + 1) * b1 + k1 + 1] - d2) * d0;
					double d7 = (this.noiseArray[((i1 + 1) * l + j1 + 0) * b1 + k1 + 1] - d3) * d0;
					double d8 = (this.noiseArray[((i1 + 1) * l + j1 + 1) * b1 + k1 + 1] - d4) * d0;
					for (int l1 = 0; l1 < 4; ++l1) {
						double d9 = 0.125D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;
						for (int i2 = 0; i2 < 8; ++i2) {
							double d14 = 0.125D;
							double d15 = d10;
							double d16 = (d11 - d10) * d14;
							for (int j2 = 0; j2 < 8; ++j2) {
								IBlockState iblockstate = null;
								if(d15 > 0.0D) iblockstate = JourneyBlocks.eucaStone.getDefaultState();
								int k2 = i2 + i1 * 8;
								int l2 = l1 + k1 * 4;
								int i3 = j2 + j1 * 8;
								primer.setBlockState(k2, l2, i3, iblockstate);
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

	public final void replaceBlocksForBiome(ChunkPrimer c) {
		for (int i = 0; i < 16; ++i) {
			for (int j = 0; j < 16; ++j) {
				byte b0 = 1;
				int k = -1;
				IBlockState iblockstate = JourneyBlocks.eucaGrass.getDefaultState();
				IBlockState iblockstate1 = JourneyBlocks.eucaStone.getDefaultState();

				for (int l = 127; l >= 0; --l) {
					IBlockState iblockstate2 = c.getBlockState(i, l, j);
					if (iblockstate2.getBlock().getMaterial() == Material.air) 
						k = -1;

					else if (iblockstate2.getBlock() == JourneyBlocks.eucaStone) {
						if (k == -1) {
							if (b0 <= 0) {
								iblockstate = Blocks.air.getDefaultState();
								iblockstate1 = JourneyBlocks.eucaGrass.getDefaultState();
							}
							k = b0;
							if(l >= 0) {
								c.setBlockState(i, l, j, iblockstate);
							} else {
								c.setBlockState(i, l, j, iblockstate1);
								if(c.getBlockState(i, l - 1, j) == JourneyBlocks.eucaStone.getDefaultState()) c.setBlockState(i, l - 1, j, iblockstate1);
								//if(c.getBlockState(i, l - 2, j) == EssenceBlocks.eucaStone.getDefaultState() && rand.nextInt(2) == 0) c.setBlockState(i, l - 2, j, iblockstate1);
							}
						}
						else if (k > 0) {
							--k;
							c.setBlockState(i, l, j, iblockstate1);
							if(c.getBlockState(i, l - 1, j) == JourneyBlocks.eucaStone.getDefaultState()) c.setBlockState(i, l - 1, j, iblockstate1);
							//if(c.getBlockState(i, l - 2, j) == EssenceBlocks.eucaStone.getDefaultState() && rand.nextInt(2) == 0) c.setBlockState(i, l - 2, j, iblockstate1);
						}
					}
				}
			}
		}
	}

	private double[] initializeNoiseField(double[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
		if(var1 == null) var1 = new double[var5 * var6 * var7];
		double var8 = 684.412D;
		double var10 = 684.412D;
		this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, var2, var4, var5, var7, 1.121D, 1.121D, 0.5D);
		this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, var2, var4, var5, var7, 1.0D, 1.0D, 0.5D);
		var8 *= 2.0D;
		this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, var2, var3, var4, var5, var6, var7, var8 / 80.0D, var8 / 160.0D, var8 / 80.0D);
		this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, var2, var3, var4, var5, var6, var7, var8, var10, var8);
		this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, var2, var3, var4, var5, var6, var7, var8, var10, var8);
		int var12 = 0;
		int var13 = 0;
		for(int var15 = 0; var15 < var5; var15++) {
			for(int var17 = 0; var17 < var7; var17++) {
				double var19 = (this.noise5[var13] + 64.0D) / 64.0D;
				double var21 = this.noise6[var13] / 6000.0D;
				if(var21 < 0.0D) var21 = -var21 * 0.3D;
				var21 = var21 * 3.0D - 2.0D;
				if(var21 > 1.0D) var21 = 1.0D;
				var21 /= 8.0D;
				var21 = 0.0D;
				if(var19 < 0.0D) var19 = 0.0D;
				var19 += 0.5D;
				var21 = var21 * var6 / 8.0D;
				var13++;
				double var23 = var6 / 2.0D;
				for(int var25 = 0; var25 < var6; var25++) {
					double var26 = 0.0D;
					double var28 = (var25 - var23) * 8.0D / var19;
					if(var28 < 0.0D) var28 *= -1.0D;
					double var30 = this.noise1[var12] / 128.0D / 1.5D;
					double var32 = this.noise2[var12] / 128.0D;
					double var34 = (this.noise3[var12] / 10.0D + 1.0D);
					if(var34 < 0.0D) var26 = var30;
					else if(var34 > 1.0D) var26 = var32;
					else var26 = var30 + (var32 - var30) * var34;
					var26 -= 8.0D;
					byte var36 = 32;
					double var37;
					if(var25 > var6 - var36) {
						var37 = (var25 - (var6 - var36)) / (var36 - 1.0F);
						var26 = var26 * (1.0D - var37) + -30.0D * var37;
					}
					var36 = 8;
					if(var25 < var36) {
						var37 = (var36 - var25) / (var36 - 1.0F);
						var26 = var26 * (1.0D - var37) + -30.0D * var37;
					}
					var1[var12] = var26;
					++var12;
				}
			}
		}
		return var1;
	}

	@Override
	public void populate(IChunkProvider ichunkprovider, int i, int j) {
		int x1 = i * 16;
		int z1 = j * 16;
		int x, z, times;
		x = x1 + this.rand.nextInt(16) + 8;
		z = z1 + this.rand.nextInt(16) + 8;

		if(rand.nextInt(9)==0) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128) + 1;
			if(isBlockTop(x, yCoord - 1, z, JourneyBlocks.eucaGrass)) {
				new WorldGenSmeltery().generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}

		if(rand.nextInt(10)==0) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128) + 1;
			if(isBlockTop(x, yCoord - 1, z, JourneyBlocks.eucaGrass)) new WorldGenBotSpawner().generate(worldObj, rand, new BlockPos(x, yCoord, z));
		}

		for(times = 0; times < 280; times++) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128) + 1;
			if(isBlockTop(x, yCoord - 1, z, JourneyBlocks.eucaGrass)) {
				trees.get(rand.nextInt(trees.size())).generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}
		
		for(times = 0; times < 140; times++) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128) + 1;
			if(isBlockTop(x, yCoord - 1, z, JourneyBlocks.eucaGrass)) {
				new WorldGenEucaTree4().generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}
		
		for(times = 0; times < 140; times++) {
			x = x1 + this.rand.nextInt(16) + 8;
			z = z1 + this.rand.nextInt(16) + 8;
			int yCoord = rand.nextInt(128) + 1;
			if(isBlockTop(x, yCoord - 1, z, JourneyBlocks.eucaGrass)) {
				new WorldGenEucaTree5().generate(worldObj, rand, new BlockPos(x, yCoord, z));
			}
		}
	}
	
	public boolean isBlockTop(int x, int y, int z, Block grass) {
		return worldObj.getBlockState(new BlockPos(x, y, z)) == grass.getDefaultState() && worldObj.getBlockState(new BlockPos(x, y + 1, z)) == Blocks.air.getDefaultState()
				&& worldObj.getBlockState(new BlockPos(x, y + 2, z)) == Blocks.air.getDefaultState() && worldObj.getBlockState(new BlockPos(x, y + 3, z)) == Blocks.air.getDefaultState()
				&& worldObj.getBlockState(new BlockPos(x, y + 4, z)) == Blocks.air.getDefaultState() && worldObj.getBlockState(new BlockPos(x, y + 5, z)) == Blocks.air.getDefaultState();
	}

	@Override
	public boolean saveChunks(boolean par1, IProgressUpdate par2) {
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "Euca";
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void saveExtraData() { }

	@Override
	public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
		return false;
	}

	@Override
	public List <SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(pos);
		return biomegenbase.getSpawnableList(creatureType);
	}

	@Override
	public Chunk provideChunk(BlockPos pos) {
		return this.provideChunk(pos.getX() >> 4, pos.getZ() >> 4);
	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String p_180513_2_, BlockPos p_180513_3_) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk p_180514_1_, int par1, int par2) { 
		
	}
}