package net.journey.eventhandler.migration;

import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;

public class BlockMigrator extends Migrator<Block> {
	@Override
	public void regRemappers() {
		regRemapper("deathgrass", JourneyBlocks.deathGrass);
		regRemapper("netherweeds", JourneyBlocks.netherweed);
		regRemapper("hellbell", JourneyBlocks.hellBell);
		regRemapper("earthenNetherTallGrass", JourneyBlocks.earthenTallNethigrass);
		regRemapper("earthenNetherShortGrass", JourneyBlocks.earthenShortNethigrass);
		regRemapper("earthenNetherFlower", JourneyBlocks.earthenHeatflower);
	}
}
