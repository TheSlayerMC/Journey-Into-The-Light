package net.journey.dimension.terrania.biome;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class TerraniaShroomForestBiome extends TerraniaBiome {

	public TerraniaShroomForestBiome(BiomeProperties properties, IBlockState topBlock, IBlockState fillerBlock) {
		super(properties, topBlock, fillerBlock);
	}

	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return 0xC40600;
	}

	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return 0xC40600;
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
	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float par1) {
		return Color.getHSBColor(0.280F, 0.316F, 0.5F).getRGB();
	}
}