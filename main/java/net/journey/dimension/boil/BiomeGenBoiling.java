package net.journey.dimension.boil;

import java.awt.Color;

import net.journey.JourneyBlocks;
import net.journey.dimension.DimensionHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;

public class BiomeGenBoiling extends Biome {

	public BiomeGenBoiling(String par1) {
		super(new BiomeProperties(par1).setBaseHeight(DimensionHelper.boilHeight[0]).setHeightVariation(DimensionHelper.boilHeight[1]));
		this.setRegistryName(par1);
		this.topBlock = JourneyBlocks.hotBlock.getDefaultState();
		this.fillerBlock = JourneyBlocks.hotBlock.getDefaultState();
		this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
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
	public int getSkyColorByTemp(float f) {
		return Color.getHSBColor(0.0F, 0.0F, 0.0F).getRGB();
	}
}