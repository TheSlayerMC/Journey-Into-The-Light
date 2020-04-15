package net.slayer.api.block;

import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.minecraft.block.BlockBookshelf;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBlock;
import net.slayer.api.SlayerAPI;

public class BlockModBookshelf extends BlockBookshelf {

    public BlockModBookshelf(String name) {
        setTranslationKey(name);
        setCreativeTab(JourneyTabs.blocks);
        setSoundType(SoundType.WOOD);
        JourneyBlocks.blockName.add(SlayerAPI.PREFIX + name);
        JourneyBlocks.blocks.add(this);
        setRegistryName(SlayerAPI.MOD_ID, name);
        JourneyItems.items.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}