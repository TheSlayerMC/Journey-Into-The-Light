package net.journey.eventhandler.migration;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;

public class BlockMigrator extends Migrator<Block> {
	@Override
	public void regRemappers() {
		remap("deathgrass", JourneyBlocks.deathGrass);
		remap("netherweeds", JourneyBlocks.netherweed);
		remap("hellbell", JourneyBlocks.hellBell);
		remap("earthenNetherTallGrass", JourneyBlocks.earthenTallNethigrass);
		remap("earthenNetherShortGrass", JourneyBlocks.earthenShortNethigrass);
		remap("earthenNetherFlower", JourneyBlocks.earthenHeatflower);

		ignore("boss_crystal");
		ignore("boss_crystal_terrania");
		ignore("boss_crystal_euca");
		ignore("boss_crystal_senterian");
		ignore("boss_crystal_cloudia");
		ignore("boss_crystal_corba");
		ignore("boss_crystal_boil");
		ignore("boss_crystal_depths");
		ignore("boss_crystal_nether");
		ignore("boss_crystal_frozen");

		remap("journeychest", JourneyBlocks.journeyChest);
		remap("netherchest", JourneyBlocks.netherChest);
		remap("boilchest", JourneyBlocks.boilChest);
		remap("eucachest", JourneyBlocks.eucaChest);
		remap("frozenchest", JourneyBlocks.frozenChest);
		remap("depthschest", JourneyBlocks.depthsChest);
		remap("corbachest", JourneyBlocks.corbaChest);
		remap("terraniachest", JourneyBlocks.terraniaChest);
		remap("cloudiachest", JourneyBlocks.cloudiaChest);
		remap("lockedchest", JourneyBlocks.lockedChest);
	}
}
