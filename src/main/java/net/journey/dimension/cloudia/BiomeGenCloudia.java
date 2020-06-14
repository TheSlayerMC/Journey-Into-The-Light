package net.journey.dimension.cloudia;

import net.journey.dimension.base.biome.JDimensionBiome;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class BiomeGenCloudia extends JDimensionBiome {

    public BiomeGenCloudia() {
        super(new BiomeProperties("Cloudia"));
        this.topBlock = JourneyBlocks.cloudiaGrass.getDefaultState();
        this.fillerBlock = JourneyBlocks.cloudiaDirt.getDefaultState();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1) {
        return Color.getHSBColor(1.0F, 0.255F, 0.208F).getRGB();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 0xff00e4;
    }
}