package net.journey.dimension.corba.biomes;

import java.awt.Color;
import java.util.Random;

import net.journey.dimension.base.biome.JDimensionBiome;
import net.journey.dimension.corba.biomes.properties.BiomePropertiesCorba;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.handler.Helper;
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

	}

	public final void generateModdedBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		IBlockState STONE = JourneyBlocks.corbaGrass.getDefaultState();
		int i = worldIn.getSeaLevel();
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate1 = this.fillerBlock;
		int j = -1;
		int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int l = x & 15;
		int i1 = z & 15;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for(int j1 = 255; j1 >= 0; --j1) {

			if (j1 == 62 && chunkPrimerIn.getBlockState(i1, j1, l).getBlock() == Blocks.WATER && rand.nextInt(5) == 1) {
				chunkPrimerIn.setBlockState(i1, j1 + 1, l, WATER_LILY); //Make new lillypad
				break;
			}
			
			if (j1 <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
			} else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				}
				else if (iblockstate2.getBlock() == JourneyBlocks.corbaStone) {
					if (j == -1) {
						if (k <= 0) {
							iblockstate = AIR;
							iblockstate1 = STONE;
						}
						else if (j1 >= i - 4 && j1 <= i + 1) {
							iblockstate = this.topBlock;
							iblockstate1 = this.fillerBlock;
						}

						if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
							iblockstate = WATER;
						}

						j = k;
						if (j1 >= i - 1) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
						}
						else if (j1 < i - 7 - k) {
							iblockstate = AIR;
							iblockstate1 = STONE;
							chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
						} else {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
						}
					}
					else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);

						if (j == 0 && iblockstate1.getBlock() == Blocks.SAND && k > 1) {
							j = rand.nextInt(4) + Math.max(0, j1 - 63);
							iblockstate1 = iblockstate1.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? RED_SANDSTONE : SANDSTONE;
						}
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