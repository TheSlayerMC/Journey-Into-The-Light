package net.journey.dimension.euca.biomes;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class BiomeGenEucaSilver extends BiomeGenEuca {

    public BiomeGenEucaSilver(String name) {
        super(name);
        this.topBlock = JourneyBlocks.eucaSilverGrass.getDefaultState();
        this.fillerBlock = JourneyBlocks.eucaDirt.getDefaultState();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
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
        return 0xffffff;
    }
}