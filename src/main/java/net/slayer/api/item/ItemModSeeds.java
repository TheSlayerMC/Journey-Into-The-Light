package net.slayer.api.item;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

public class ItemModSeeds extends ItemSeeds {

    public ItemModSeeds(Block block) {
        super(block, Blocks.FARMLAND);
    }
}