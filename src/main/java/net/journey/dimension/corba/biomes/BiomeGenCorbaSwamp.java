package net.journey.dimension.corba.biomes;

import java.awt.Color;
import java.util.Random;

import net.journey.dimension.base.biome.JDimensionBiome;
import net.journey.dimension.corba.biomes.properties.BiomePropertiesCorba;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenFossils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCorbaSwamp extends JDimensionBiome {

	protected static final IBlockState WATER_LILY = Blocks.WATERLILY.getDefaultState();

	public BiomeGenCorbaSwamp() {
		super(new BiomePropertiesCorba("Corba Swamp").setBaseHeight(-0.2F).setHeightVariation(0.1F));
		this.topBlock = JourneyBlocks.corbaGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.corbaStone.getDefaultState();
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return SWAMP_FEATURE;
	}

	@Override
	public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) {
		return BlockFlower.EnumFlowerType.BLUE_ORCHID;
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {

		int i = x & 15;
		int j = z & 15;

		for (int k = 255; k >= 0; --k)
		{
			if (chunkPrimerIn.getBlockState(j, k, i).getMaterial() != Material.AIR)
			{
				if (k == 63 && chunkPrimerIn.getBlockState(j, k, i).getBlock() != Blocks.WATER)
				{
					chunkPrimerIn.setBlockState(j, k, i, WATER);
					chunkPrimerIn.setBlockState(j, k + 1, i, WATER_LILY);

				}

				break;
			}
		}

		this.generateModdedBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	public final void generateModdedBiomeTerrain(World c, Random r, ChunkPrimer chunkPrimerIn, int x, int z, double s) {
		boolean flag = true;
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate1 = this.fillerBlock;
		int k = -1;
		int l = (int) (s / 3.0D + 3.0D + r.nextDouble() * 0.25D);
		int i1 = x & 15;
		int j1 = z & 15;
		for (int k1 = 255; k1 >= 0; --k1) {
			if (k1 <= 1) {
				c.setBlockState(new BlockPos(j1, k1, i1), Blocks.BEDROCK.getDefaultState());
			} else {
				IBlockState iblockstate2 = c.getBlockState(new BlockPos(j1, k1, i1));

				if (iblockstate2.getMaterial() == Material.AIR) k = -1;
				else if (iblockstate2.getBlock() == JourneyBlocks.corbaStone) {
					if (k == -1) {
						if (l <= 0) {
							iblockstate = null;
							iblockstate1 = JourneyBlocks.corbaStone.getDefaultState();
						} else if (k1 >= 7 && k1 <= 8) {
							iblockstate =  this.topBlock;
							iblockstate1 =  this.fillerBlock;
						}

						if (k1 < 8 && (iblockstate == null || iblockstate.getMaterial() == Material.AIR))
							iblockstate = JourneyBlocks.corbaStone.getDefaultState();
						k = l;
						if (k1 >= 8) c.setBlockState(new BlockPos(j1, k1, i1), iblockstate);
						else if (k1 < 7 - l) {
							iblockstate = null;
							iblockstate1 = JourneyBlocks.corbaStone.getDefaultState();
						} else c.setBlockState(new BlockPos(j1, k1, i1), iblockstate1);
					} else if (k > 0) {
						--k;
						c.setBlockState(new BlockPos(j1, k1, i1), iblockstate1);
					}
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos) {
		return 0x5b592d;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos) {
		return 0x5b592d;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return Color.getHSBColor(0.455F, 0.216F, 5.0F).getRGB();
	}

	@Override
	public boolean canRain() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getWaterColorMultiplier() {
		return 0x6cff00;
	}
}