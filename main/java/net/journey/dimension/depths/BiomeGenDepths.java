package net.journey.dimension.depths;

import net.journey.JourneyBlocks;
import net.minecraft.world.biome.Biome;

public class BiomeGenDepths extends Biome {

	public BiomeGenDepths(String par1) {
		super(new BiomeProperties(par1));
		this.setRegistryName(par1);
		this.topBlock = JourneyBlocks.depthsGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.depthsDirt.getDefaultState();
		this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
	}
}