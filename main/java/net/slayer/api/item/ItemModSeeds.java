package net.slayer.api.item;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.slayer.api.SlayerAPI;

public class ItemModSeeds extends ItemSeeds {
	
	public ItemModSeeds(String name, String f, Block block) {
		super(block, Blocks.FARMLAND);
		LangRegistry.addItem(SlayerAPI.PREFIX + name, f);
		setUnlocalizedName(SlayerAPI.PREFIX + name);
		setCreativeTab(JourneyTabs.crops);
		JourneyItems.items.add(this);
		JourneyItems.itemNames.add(SlayerAPI.PREFIX + name);
		setRegistryName(SlayerAPI.MOD_ID, name);
	}
}