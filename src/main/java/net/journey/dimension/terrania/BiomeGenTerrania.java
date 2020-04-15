package net.journey.dimension.terrania;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

import java.awt.*;

public class BiomeGenTerrania extends Biome {

    public BiomeGenTerrania() {
        super(new BiomeProperties("Terrania"));
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
    public int getSkyColorByTemp(float f) {
        return Color.getHSBColor(0.0F, 0.0F, 0.0F).getRGB();
    }
}