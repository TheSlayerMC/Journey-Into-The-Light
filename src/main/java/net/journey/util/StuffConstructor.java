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
	/**
	 * Registers block.
	 * Also sets registry name, translation key, creative tab and adds it to lang generator.
	 * Registers default itemblock for it.
	 */
	public static void regAndSetupBlock(Block block, String name, String enName, @Nullable CreativeTabs tab) {
		regAndSetupBlock(block, name, enName, tab, new ItemBlock(block));
	}

	/**
	 * Registers block.
	 * Also sets registry name, translation key, creative tab and adds it to lang generator.
	 * Registers its itemblock, if provided.
	 */
	public static void regAndSetupBlock(Block block, String name, String enName, @Nullable CreativeTabs tab, @Nullable Item itemBlock) {
		name = name.toLowerCase();

		block.setRegistryName(JITL.MOD_ID, name)
				.setTranslationKey(name)
				.setCreativeTab(tab);

		JourneyBlocks.blocks.add(block);

		if (itemBlock != null) {
			JourneyBlocks.itemBlocks.add(itemBlock
					.setRegistryName(block.getRegistryName()));
		}

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
