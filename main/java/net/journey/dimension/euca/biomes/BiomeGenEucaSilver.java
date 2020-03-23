package net.journey.dimension.euca.biomes;

import net.journey.JourneyBlocks;
import net.minecraft.util.ResourceLocation;

public class BiomeGenEucaSilver extends BiomeGenEuca {

	public BiomeGenEucaSilver(String name) {
		super(name);
		this.topBlock = JourneyBlocks.eucaSilverGrass.getDefaultState();
	}
}