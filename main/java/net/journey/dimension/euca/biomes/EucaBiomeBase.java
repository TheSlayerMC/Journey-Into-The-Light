package net.journey.dimension.euca.biomes;

import net.journey.JourneyBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public class EucaBiomeBase extends Biome {

	public EucaBiomeBase(BiomeProperties properties, ResourceLocation registryName) {
		super(properties);
		this.topBlock = JourneyBlocks.eucaGrass.getDefaultState();
		this.fillerBlock = JourneyBlocks.eucaDirt.getDefaultState();
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();

		this.setRegistryName(registryName);
	}
}
