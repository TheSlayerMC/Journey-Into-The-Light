package net.journey.dimension.boil.biome;

import net.journey.dimension.base.biome.JDimensionBiome;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

import java.awt.*;
import java.util.Random;

public class BiomeGenCharredFields extends JDimensionBiome {

	public BiomeGenCharredFields() {
		super(new BiomeProperties("Charred Fields").setBaseHeight(-2.0F).setHeightVariation(0.0F));
		this.topBlock = JourneyBlocks.charredGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.rubble.getDefaultState();
	}

	@Override
	public int getWaterColorMultiplier() {
		return 0xC40600;
	}

	@Override
	public boolean canRain() {
		return false;
	}

	@Override
	public TempCategory getTempCategory() {
		return TempCategory.WARM;
	}

	@Override
	public boolean getEnableSnow() {
		return false;
	}

	@Override
	public int getSkyColorByTemp(float f) {
		return Color.getHSBColor(0.0F, 0.0F, 0.0F).getRGB();
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {

	}

	public final void generateModdedBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		IBlockState STONE = this.fillerBlock;
		int i = 16;
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate1 = this.fillerBlock;
		int j = -1;
		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int l = x & 15;
		int i1 = z & 15;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int j1 = 255; j1 >= 0; --j1) {

			if (j1 <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
			} else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				} else if (iblockstate2.getBlock() == JourneyBlocks.ashBlock) {
					if (j == -1) {
						if (k <= 0) {
							iblockstate = AIR;
							iblockstate1 = STONE;
						} else if (j1 >= i - 4 && j1 <= i + 1) {
							iblockstate = this.topBlock;
							iblockstate1 = this.fillerBlock;
						}

						j = k;
						if (j1 >= i - 1) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
						} else if (j1 < i - 7 - k) {
							iblockstate = AIR;
							iblockstate1 = STONE;
							chunkPrimerIn.setBlockState(i1, j1, l, fillerBlock);
						} else {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
					}
				}
			}
		}
	}
}
