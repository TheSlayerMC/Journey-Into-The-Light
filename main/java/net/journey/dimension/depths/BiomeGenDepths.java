package net.journey.dimension.depths;

import net.journey.JourneyBlocks;

public class BiomeGenDepths extends BiomeGenBase {

	public BiomeGenDepths(int par1) {
		super(par1);
		this.setBiomeName("The Depths");
		this.topBlock = JourneyBlocks.depthsGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.depthsDirt.getDefaultState();
		this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
	}
}