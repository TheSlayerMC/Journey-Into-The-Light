package net.journey.dimension.euca.biomes;

import net.journey.JourneyBlocks;

public class BiomeGenEucaSilver extends BiomeGenEuca {

    public BiomeGenEucaSilver(String name) {
        super(name);
        this.topBlock = JourneyBlocks.eucaSilverGrass.getDefaultState();
        this.fillerBlock = JourneyBlocks.eucaDirt.getDefaultState();
    }
}