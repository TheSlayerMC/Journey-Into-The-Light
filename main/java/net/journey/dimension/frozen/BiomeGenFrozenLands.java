package net.journey.dimension.frozen;

import net.journey.JourneyBlocks;

public class BiomeGenFrozenLands extends BiomeGenBase {

	public BiomeGenFrozenLands(int par1) {
		super(par1);
		this.setBiomeName("Frozen Lands");
		this.topBlock = JourneyBlocks.frozenGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.frozenDirt.getDefaultState();
		this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        rainfall = 0;
        setEnableSnow();
        enableRain = false;
	}
}