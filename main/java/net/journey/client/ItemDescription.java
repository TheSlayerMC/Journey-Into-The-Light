package net.journey.client;

import java.util.List;

import net.journey.JourneyConsumables;
import net.journey.JourneyItems;
import net.journey.JourneyConsumables;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;

public class ItemDescription {

	public static void addInformation(ItemStack itemStack, List<String> list) {
		Item item = itemStack.getItem();
		if(item == JourneyConsumables.glowshroom) list.add(SlayerAPI.Colour.GREEN + "When eaten: Grants Night Vision"); 
		if(item == JourneyConsumables.terrashroom) list.add(SlayerAPI.Colour.LIGHT_PURPLE + "When eaten: Grants Night Vision"); 
		if(item == JourneyConsumables.corveggies) list.add(SlayerAPI.Colour.YELLOW + "When eaten: Grants Saturation"); 
		if(item == JourneyConsumables.glowa) list.add(SlayerAPI.Colour.YELLOW + "When eaten: Grants Night Vision");
		if(item == JourneyConsumables.crakeBulb) list.add(SlayerAPI.Colour.YELLOW + "When eaten: Grants Water Breathing");
		if(item == JourneyConsumables.crackenCanes) list.add(SlayerAPI.Colour.YELLOW + "When eaten: Grants Damage Boost");
		if(item == JourneyConsumables.spineberries) list.add(SlayerAPI.Colour.YELLOW + "When eaten: Grants Absorption");
		if(item == JourneyConsumables.zatPedal) list.add(SlayerAPI.Colour.YELLOW + "When eaten: Grants Fire Resistance");
		if(item == JourneyConsumables.mintCandyCane) list.add(SlayerAPI.Colour.RED + "Increased Mining Speed");
		if(item == JourneyConsumables.fruityCandyCane) list.add(SlayerAPI.Colour.BLUE + "When eaten: Grants Damage Boost");
		if(item == JourneyConsumables.cherryCandyCane) list.add(SlayerAPI.Colour.PURPLE + "When eaten: Grants Regeneration");
		if(item == JourneyConsumables.greenHonglowShroom) list.add(SlayerAPI.Colour.DARK_GREEN + "Green - Jump Boost");
		if(item == JourneyConsumables.blueHonglowShroom) list.add(SlayerAPI.Colour.BLUE + "Blue - Speed Boost");
		if(item == JourneyConsumables.redHonglowShroom) list.add(SlayerAPI.Colour.RED + "Red - Regeneration");
		if(item == JourneyConsumables.honglowShroom) list.add(SlayerAPI.Colour.GREEN + "Light Green - Nightvision (only)");
		if(item == JourneyConsumables.honglowShroom || item == JourneyConsumables.redHonglowShroom || item == JourneyConsumables.greenHonglowShroom || item == JourneyConsumables.blueHonglowShroom) 
			list.add(SlayerAPI.Colour.YELLOW + "Note: All Variants Grant Nightvision Upon Eaten");
		if(item == JourneyItems.iridium) list.add("Longer Smelting Fuel");
		if(item == JourneyItems.demonicEye) list.add("Inserted into the Corba Portal Frame");
		if(item == JourneyItems.darkGem) list.add("Inserted into the Depths Portal Frame");
		if(item == JourneyItems.sentryEye) list.add("Inserted into the Senterian Portal Frame");

	}
}