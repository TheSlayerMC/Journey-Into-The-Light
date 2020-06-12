package net.journey.dimension.euca.biomes;

import net.journey.dimension.base.biome.JDimensionBiome;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;

public class EucaBiome extends JDimensionBiome {
	public EucaBiome(BiomeProperties properties, IBlockState topBlock, IBlockState fillerBlock) {
		super(properties, topBlock, fillerBlock);
		this.topBlock = topBlock;
		this.fillerBlock = fillerBlock;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return Color.getHSBColor(0.855F, 0.216F, 5.0F).getRGB();
	}

	@Override
	public boolean canRain() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getWaterColorMultiplier() {
		return 0xff9c00;
	}

	@Override
	public void genTerrainBlocks(@NotNull World world, @NotNull Random rand, @NotNull ChunkPrimer chunkPrimer, int x, int z, double noiseVal) {
		for (int relX = 0; relX < 16; relX++) {
			for (int relZ = 0; relZ < 16; relZ++) {
				int j1 = -1;
				int i1 = (int) (3.0 + rand.nextDouble() * 0.25);
				IBlockState top = topBlock;
				IBlockState filler = fillerBlock;

				for (int y = 127; y >= 0; y--) {
					Block block = chunkPrimer.getBlockState(relX, y, relZ).getBlock();

					if (block == Blocks.AIR) {
						j1 = -1;
					} else if (block == JourneyBlocks.eucaStone) {
						if (j1 == -1) {
							if (i1 <= 0) {
								top = Blocks.AIR.getDefaultState();
								filler = JourneyBlocks.eucaStone.getDefaultState();
							}

							j1 = i1;

							if (y >= 0) {
								chunkPrimer.setBlockState(relX, y, relZ, top);
							} else {
								chunkPrimer.setBlockState(relX, y, relZ, filler);
							}
						} else if (j1 > 0) {
							--j1;
							chunkPrimer.setBlockState(relX, y, relZ, filler);
						}
					}
				}
			}
		}
	}
}
