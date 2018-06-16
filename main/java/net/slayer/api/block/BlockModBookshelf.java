package net.slayer.api.block;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.minecraft.block.BlockBookshelf;

public class BlockModBookshelf extends BlockBookshelf {
	
    public BlockModBookshelf(String name) {
        setUnlocalizedName(name);
        setCreativeTab(JourneyTabs.blocks);
        setSoundType(blockSoundType.WOOD);
        JourneyBlocks.blockName.add(name);
		JourneyBlocks.blocks.add(this);
		setRegistryName(name);
    }
}