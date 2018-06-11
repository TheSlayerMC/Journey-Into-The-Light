package net.slayer.api.item;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

public class ItemModSeeds extends ItemSeeds {
	
	public ItemModSeeds(String name, String f, Block block) {
		super(block, Blocks.FARMLAND);
		LangRegistry.addItem(name, f);
		setUnlocalizedName(name);
		setCreativeTab(JourneyTabs.crops);
		setRegistryName(name);
		JourneyItems.items.add(this);
		JourneyItems.itemNames.add(name);
	}
}