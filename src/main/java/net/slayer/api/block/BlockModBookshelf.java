package net.slayer.api.block;

import net.journey.JITL;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.BlockBookshelf;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBlock;

public class BlockModBookshelf extends BlockBookshelf {

    public BlockModBookshelf(String name) {
        setTranslationKey(name);
        setCreativeTab(JourneyTabs.BLOCKS);
        setSoundType(SoundType.WOOD);
        JourneyBlocks.blocks.add(this);
        setRegistryName(JITL.MOD_ID, name);
        JourneyBlocks.itemBlocks.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}