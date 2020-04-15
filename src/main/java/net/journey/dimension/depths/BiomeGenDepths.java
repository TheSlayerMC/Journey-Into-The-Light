package net.journey.dimension.depths;

import net.journey.JourneyBlocks;
import net.minecraft.world.biome.Biome;

public class BiomeGenDepths extends Biome {

    public BiomeGenDepths() {
        super(new BiomeProperties("Depths"));
        this.topBlock = JourneyBlocks.depthsGrass.getDefaultState();
        this.fillerBlock = JourneyBlocks.depthsDirt.getDefaultState();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
    }
}