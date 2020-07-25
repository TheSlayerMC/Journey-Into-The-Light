package net.journey.eventhandler;

import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.slayer.api.SlayerAPI;

@Mod.EventBusSubscriber
public class JFuelEventHandler {

	//TODO move to Item.getItemBurnTime(ItemStack) instead, because it's a hook for vanilla items.
	@SubscribeEvent
	public static void onFuelBurnEvent(FurnaceFuelBurnTimeEvent event) {
		ItemStack is = event.getItemStack();
		Item item = is.getItem();

		if (item == JourneyItems.blazium) event.setBurnTime(3200);

		if (item == JourneyItems.firestoneClump) event.setBurnTime(500);

		if (item == JourneyItems.iridium) event.setBurnTime(2600);

		if (item == SlayerAPI.toItem(JourneyBlocks.iridiumBlock)) event.setBurnTime(2600 * 2);
	}
}
