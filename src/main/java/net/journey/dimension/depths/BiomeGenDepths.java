package net.journey.dimension.depths;

import net.journey.dimension.base.biome.JDimensionBiome;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.world.biome.Biome.BiomeProperties;

public class BiomeGenDepths extends JDimensionBiome {

    public BiomeGenDepths() {
        super(new BiomeProperties("Depths").setHeightVariation(0.2F));
        this.topBlock = JourneyBlocks.depthsGrass.getDefaultState();
        this.fillerBlock = JourneyBlocks.depthsDirt.getDefaultState();
    }
}