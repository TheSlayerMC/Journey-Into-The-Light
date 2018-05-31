package net.journey.client;

import java.util.List;

import net.journey.JourneyItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;

public class ItemDescription {

	public static void addInformation(ItemStack itemStack, List<String> list) {
		Item item = itemStack.getItem();
		if(item == JourneyItems.glowshroom) 
			list.add(SlayerAPI.Colour.GREEN + "Grants night vision when eaten"); 
		if(item == JourneyItems.terrashroom)
			list.add(SlayerAPI.Colour.LIGHT_PURPLE + "Grants night vision when eaten"); 
		if(item == JourneyItems.corveggies) 
			list.add(SlayerAPI.Colour.YELLOW + "Grants saturation when eaten"); 
	}
}