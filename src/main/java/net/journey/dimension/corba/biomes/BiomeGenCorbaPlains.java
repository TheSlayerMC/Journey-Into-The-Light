package net.journey.dimension.corba.biomes;

import java.awt.Color;

import net.journey.dimension.base.BiomeGenJourney;
import net.journey.dimension.corba.biomes.properties.BiomePropertiesCorba;
import net.journey.dimension.corba.biomes.properties.BiomePropertiesCorbaPlains;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeGenCorbaPlains extends BiomeGenJourney {

    public BiomeGenCorbaPlains() {
        super(new BiomePropertiesCorbaPlains());
        this.topBlock = JourneyBlocks.cloudiaPlanks.getDefaultState();
        this.fillerBlock = JourneyBlocks.corbaStone.getDefaultState();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
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