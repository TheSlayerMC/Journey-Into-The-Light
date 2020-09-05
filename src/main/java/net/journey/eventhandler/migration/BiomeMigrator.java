package net.journey.eventhandler.migration;

import net.journey.dimension.base.DimensionHelper;
import net.minecraft.world.biome.Biome;

public class BiomeMigrator extends Migrator<Biome> {
	@Override
	public void regRemappers() {
		remap("Euca", DimensionHelper.EUCA_GOLD_BIOME);
		remap("Euca Silver", DimensionHelper.EUCA_SILVER_BIOME);
		remap("Euca Goldite", DimensionHelper.EUCA_GOLDITE_GRAINS_BIOME);
		remap("Charred Fields", DimensionHelper.CHARRED_FIELDS_BIOME);
		remap("Scorched Wasteland", DimensionHelper.SCORCHED_WASTELAND_BIOME);
		remap("Boiling Sands", DimensionHelper.BOILING_SANDS_BIOME);
		remap("Boiling Point", DimensionHelper.BOILING_BIOME);
		remap("Cloudia", DimensionHelper.CLOUDIA_BIOME);
		remap("Corba", DimensionHelper.CORBA_BIOME);
		remap("Corba Plains", DimensionHelper.CORBA_PLAINS_BIOME);
		remap("Corba Hills", DimensionHelper.CORBA_HILLS_BIOME);
		remap("Corba Swamp", DimensionHelper.CORBA_SWAMP_BIOME);
		remap("Depths", DimensionHelper.DEPTHS_BIOME);
		remap("Frozen Lands", DimensionHelper.FROZEN_BIOME);
		remap("Terrania", DimensionHelper.TERRANIA_BIOME);
		remap("EnchantedShroomForest", DimensionHelper.ENCHANTED_SHROOM_FOREST_BIOME);
		remap("Senterian Labyrinth", DimensionHelper.SENTERIAN_BIOME);
	}
}
