package net.journey.dimension.corba.biomes;

import net.journey.dimension.base.biome.JDimensionBiome;
import net.journey.dimension.corba.biomes.properties.BiomePropertiesCorbaPlains;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class BiomeGenCorbaPlains extends JDimensionBiome {

    public BiomeGenCorbaPlains() {
        super(new BiomePropertiesCorbaPlains());
        this.topBlock = JourneyBlocks.corbaGrass.getDefaultState();
        this.fillerBlock = JourneyBlocks.corbaStone.getDefaultState();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0x5b592d;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0x5b592d;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1) {
        return Color.getHSBColor(0.455F, 0.216F, 5.0F).getRGB();
    }

    @Override
    public boolean canRain() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 0x6cff00;
    }
}