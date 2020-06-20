package net.journey.dimension.euca.biomes;

import net.journey.api.block.GroundPredicate;
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
	
	protected final GroundPredicate EUCA_GRASS_GROUND = GroundPredicate.SOLID_SIDE.and(GroundPredicate.blockPredicate(block -> block == JourneyBlocks.eucaGrass));
	protected final GroundPredicate EUCA_SILVER_GRASS_GROUND = GroundPredicate.SOLID_SIDE.and(GroundPredicate.blockPredicate(block -> block == JourneyBlocks.eucaSilverGrass));
	
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
	public void genTerrainBlocks(@NotNull World world, @NotNull Random rand, @NotNull ChunkPrimer chunkPrimer, int chunkX, int chunkZ, double noiseVal) {
		int fillersLeft = -1;
		int fillerCount = 3;
		IBlockState top = topBlock;
		IBlockState filler = fillerBlock;

		int relX = chunkX & 15;
		int relZ = chunkZ & 15;

		for (int y = 127; y >= 0; y--) {
			Block block = chunkPrimer.getBlockState(relX, y, relZ).getBlock();

			if (block == Blocks.AIR) {
				fillersLeft = -1;
			} else if (block == JourneyBlocks.eucaStone) {
				if (fillersLeft == -1) {
					fillersLeft = fillerCount;
					chunkPrimer.setBlockState(relX, y, relZ, top);
				} else if (fillersLeft > 0) {
					--fillersLeft;
					chunkPrimer.setBlockState(relX, y, relZ, filler);
				}
			}
		}
	}
}
