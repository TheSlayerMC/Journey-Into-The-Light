package net.journey.util.core;

import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.slayer.api.SlayerAPI;

public class StuffConstructor {
    public static void regAndSetupBlock(Block block, String name, String enName, CreativeTabs tab) {
        regAndSetupBlock(block, name, enName, 2.0F, tab);
    }

    public static void regAndSetupBlock(Block block, String name, String enName, float hardness, CreativeTabs tab) {
        LangRegistry.addBlock(name, enName);
        block.setCreativeTab(tab)
                .setRegistryName(SlayerAPI.MOD_ID, name)
                .setTranslationKey(name)
                .setHardness(hardness);

        JourneyBlocks.blockName.add(SlayerAPI.PREFIX + name);
        JourneyBlocks.blocks.add(block);

        JourneyItems.items.add(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }
}
