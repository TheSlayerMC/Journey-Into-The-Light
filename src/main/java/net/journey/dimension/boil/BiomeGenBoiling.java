package net.journey.dimension.boil;

import java.awt.Color;

import net.journey.dimension.base.biome.JDimensionBiome;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.world.biome.Biome.TempCategory;

public class BiomeGenBoiling extends JDimensionBiome {

    public BiomeGenBoiling() {
        super(new BiomePropertiesBoil());
        this.topBlock = JourneyBlocks.hotBlock.getDefaultState();
        this.fillerBlock = JourneyBlocks.hotBlock.getDefaultState();
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
}