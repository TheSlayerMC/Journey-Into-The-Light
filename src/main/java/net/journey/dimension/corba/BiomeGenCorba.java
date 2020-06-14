package net.journey.dimension.corba;

import net.journey.dimension.base.biome.JDimensionBiome;
import net.journey.dimension.corba.biomes.properties.BiomePropertiesCorba;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class BiomeGenCorba extends JDimensionBiome {

    public BiomeGenCorba() {
        super(new BiomePropertiesCorba("Corba").setBaseHeight(0.2F).setHeightVariation(0.2F));
        this.topBlock = JourneyBlocks.corbaGrass.getDefaultState();
        this.fillerBlock = JourneyBlocks.corbaStone.getDefaultState();
        this.decorator.mushroomsPerChunk = 64;
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