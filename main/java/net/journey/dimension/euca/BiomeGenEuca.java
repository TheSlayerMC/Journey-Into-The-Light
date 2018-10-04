package net.journey.dimension.euca;

import net.journey.JourneyBlocks;
import net.journey.dimension.DimensionHelper;
import net.minecraft.world.biome.Biome;

public class BiomeGenEuca extends Biome {

	public BiomeGenEuca() {
		super(new BiomeProperties("Euca"));
		this.topBlock = JourneyBlocks.eucaGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.eucaStone.getDefaultState();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		getSkyColorByTemp(0xffd800);
	}
}