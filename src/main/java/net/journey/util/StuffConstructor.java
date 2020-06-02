package net.journey.util;

import net.journey.JITL;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import org.jetbrains.annotations.Nullable;

public class StuffConstructor {

	public static void regAndSetupBlock(Block block, String name, String enName, float hardness, @Nullable CreativeTabs tab) {
		block.setHardness(hardness);
		regAndSetupBlock(block, name, enName, tab);
	}

	public static void regAndSetupBlock(Block block, String name, String enName, @Nullable CreativeTabs tab) {
		name = name.toLowerCase();

		block.setRegistryName(JITL.MOD_ID, name)
				.setTranslationKey(name)
				.setCreativeTab(tab);

		JourneyBlocks.blocks.add(block);

		JourneyBlocks.itemBlocks.add(new ItemBlock(block)
				.setRegistryName(block.getRegistryName()));

		LangGeneratorFacade.addBlockEntry(block, enName);
	}

	public static void regAndSetupItem(Item item, String name, String enName, @Nullable CreativeTabs tab) {
		name = name.toLowerCase();

		item.setRegistryName(JITL.MOD_ID, name)
				.setTranslationKey(name)
				.setCreativeTab(tab);

		JourneyItems.items.add(item);

		LangGeneratorFacade.addItemEntry(item, enName);
	}

}
