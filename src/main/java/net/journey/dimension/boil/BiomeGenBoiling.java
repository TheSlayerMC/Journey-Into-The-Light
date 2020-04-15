package net.journey.dimension.boil;

import net.journey.JourneyBlocks;
import net.minecraft.world.biome.Biome;

import java.awt.*;

public class BiomeGenBoiling extends Biome {

    public BiomeGenBoiling() {
        super(new BiomePropertiesBoil());
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