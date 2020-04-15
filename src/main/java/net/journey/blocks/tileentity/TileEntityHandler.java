package net.journey.blocks.tileentity;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.SlayerAPI;

public class TileEntityHandler {

    public static void register() {
        GameRegistry.registerTileEntity(TileEntityJourneyChest.class, new ResourceLocation(SlayerAPI.PREFIX + "Journey Chest"));
        GameRegistry.registerTileEntity(TileEntitySummoningTable.class, new ResourceLocation(SlayerAPI.PREFIX + "Summoning Table"));
        GameRegistry.registerTileEntity(TileEntityGrindstone.class, new ResourceLocation(SlayerAPI.PREFIX + "Grindstone"));
        GameRegistry.registerTileEntity(TileEntityCloudAltar.class, new ResourceLocation(SlayerAPI.PREFIX + "CLoud Altar"));
        GameRegistry.registerTileEntity(TileEntityNetherFurnace.class, new ResourceLocation(SlayerAPI.PREFIX + "Nether Furnace"));
        GameRegistry.registerTileEntity(TileEntityTrap.class, new ResourceLocation(SlayerAPI.PREFIX + "Trap"));
    }
}
