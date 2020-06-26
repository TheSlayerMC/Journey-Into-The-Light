package net.journey.dimension.terrania.biome;

import net.journey.dimension.base.biome.JDimensionBiome;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class BiomeGenEnchantedShroomForest extends JDimensionBiome {

	public BiomeGenEnchantedShroomForest() {
		super(new BiomeProperties("Enchanted Shroom Forest"));
		this.topBlock = JourneyBlocks.terranianGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.terranianDirt.getDefaultState();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
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