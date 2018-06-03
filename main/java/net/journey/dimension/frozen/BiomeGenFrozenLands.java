package net.journey.dimension.frozen;

import net.journey.JourneyBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;

public class BiomeGenFrozenLands extends Biome {

	public BiomeGenFrozenLands(String par1) {
		super(new BiomeProperties(par1));
		this.setRegistryName(par1);
		this.topBlock = JourneyBlocks.frozenGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.frozenDirt.getDefaultState();
		this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
     }
	
	@Override
	public boolean getEnableSnow() {
		return true;
	}
}