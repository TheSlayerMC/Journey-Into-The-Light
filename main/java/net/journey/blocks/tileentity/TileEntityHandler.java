package net.journey.blocks.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.SlayerAPI;

public class TileEntityHandler {

	public static void register() {
		GameRegistry.registerTileEntity(TileEntityJourneyChest.class, SlayerAPI.PREFIX + "Journey Chest");
		GameRegistry.registerTileEntity(TileEntitySummoningTable.class, SlayerAPI.PREFIX + "Summoning Table");
		GameRegistry.registerTileEntity(TileEntityGrindstone.class, SlayerAPI.PREFIX + "Grindstone");
		GameRegistry.registerTileEntity(TileEntityNetherFurnace.class, SlayerAPI.PREFIX + "Nether Furnace");
		GameRegistry.registerTileEntity(TileEntityTrap.class, SlayerAPI.PREFIX + "Trap");
	}
}
