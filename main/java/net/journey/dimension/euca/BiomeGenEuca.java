package net.journey.dimension.euca;

import net.journey.JourneyBlocks;
import net.minecraft.world.biome.Biome;

public class BiomeGenEuca extends Biome {

	public BiomeGenEuca(String par1) {
		super(new BiomeProperties(par1));
		this.setRegistryName(par1);
		this.topBlock = JourneyBlocks.eucaGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.eucaStone.getDefaultState();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		getSkyColorByTemp(0xffd800);
	}
}