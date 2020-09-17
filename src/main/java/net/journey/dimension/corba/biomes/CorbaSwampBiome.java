package net.journey.dimension.corba.biomes;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.Random;

public class CorbaSwampBiome extends CorbaBiome {

	protected static final IBlockState WATER_LILY = JourneyBlocks.swampLily.getDefaultState();

	public CorbaSwampBiome(BiomeProperties properties, IBlockState topBlock, IBlockState fillerBlock) {
		super(properties, topBlock, fillerBlock);
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
		IBlockState STONE = JourneyBlocks.corbaGrass.getDefaultState();
		int i = worldIn.getSeaLevel();
		IBlockState iblockstate;

		if (rand.nextInt(3) == 0) iblockstate = this.topBlock;
		else iblockstate = JourneyBlocks.driedMud.getDefaultState();

		IBlockState iblockstate1 = this.fillerBlock;
		int j = -1;
		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);

		int relX = x & 15;
		int relZ = z & 15;

		for (int j1 = 255; j1 >= 0; --j1) {

			if (j1 == 62 && chunkPrimerIn.getBlockState(relX, j1, relZ).getBlock() == Blocks.WATER && rand.nextInt(5) == 1) {
				chunkPrimerIn.setBlockState(relX, j1 + 1, relZ, WATER_LILY); //Make new lillypad
				break;
			}

			if (j1 <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(relX, j1, relZ, BEDROCK);
			} else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(relX, j1, relZ);

				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				} else if (iblockstate2.getBlock() == JourneyBlocks.corbaStone) {
					if (j == -1) {
						if (k <= 0) {
							iblockstate = AIR;
							iblockstate1 = STONE;
						} else if (j1 >= i - 4 && j1 <= i + 1) {
							if (rand.nextInt(3) == 0) iblockstate = this.topBlock;
							else iblockstate = JourneyBlocks.driedMud.getDefaultState();
							if (rand.nextInt(3) == 0) iblockstate1 = this.fillerBlock;
							else iblockstate1 = JourneyBlocks.driedMud.getDefaultState();
						}

						if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
							if (rand.nextInt(3) == 0) iblockstate = this.topBlock;
							else iblockstate = Blocks.WATER.getDefaultState();
						}

						j = k;
						if (j1 >= i - 1) {
							chunkPrimerIn.setBlockState(relX, j1, relZ, iblockstate);
						}
						else if (j1 < i - 7 - k) {
							iblockstate = AIR;
							iblockstate1 = STONE;
							chunkPrimerIn.setBlockState(relX, j1, relZ, GRAVEL);
						} else {
							chunkPrimerIn.setBlockState(relX, j1, relZ, iblockstate1);
						}
					}
					else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(relX, j1, relZ, iblockstate1);

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
		return 0x7da200;
	}

	//c6ff00
}