package net.journey.util;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.slayer.api.SlayerAPI;

public class JourneyFuelHandler {

    @SubscribeEvent
    public void fuelEvent(FurnaceFuelBurnTimeEvent event) {
        ItemStack i = event.getItemStack();

        if (i.getItem() == JourneyItems.blazium) event.setBurnTime(3200);

        if (i.getItem() == JourneyItems.firestoneClump) event.setBurnTime(500);

        if (i.getItem() == JourneyItems.iridium) event.setBurnTime(2600);

        if (i.getItem() == SlayerAPI.toItem(JourneyBlocks.iridiumBlock)) event.setBurnTime(2600 * 2);
    }
}
