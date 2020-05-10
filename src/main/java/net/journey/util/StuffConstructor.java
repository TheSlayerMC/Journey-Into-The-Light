package net.journey.util;

import net.journey.JITL;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class StuffConstructor {
    public static void regAndSetupBlock(Block block, String name, String enName, CreativeTabs tab) {
        block.setCreativeTab(tab)
                .setRegistryName(JITL.MOD_ID, name)
                .setTranslationKey(name);

        JourneyBlocks.blocks.add(block);

        JourneyBlocks.itemBlocks.add(new ItemBlock(block)
                .setRegistryName(block.getRegistryName()));

        LangGeneratorFacade.addBlockEntry(block, enName);
    }

    public static void regAndSetupBlock(Block block, String name, String enName, float hardness, CreativeTabs tab) {
        block.setHardness(hardness);
        regAndSetupBlock(block, name, enName, tab);
    }
}
