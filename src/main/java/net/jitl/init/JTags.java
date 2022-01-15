package net.jitl.init;

import net.jitl.JITL;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.tags.SetTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

public class JTags {
    public static final SetTag.Named<Item> FROZEN_TROLL_LOVED_ITEMS = tagItem("frozen_troll_loved_items");
    public static final SetTag.Named<Block> EUCA_STONE_ORE_REPLACEABLES = tagBlock("euca_stone_ore_replaceables");

    private static Tag.Named<Item> tagItem(String name) {
        return ItemTags.createOptional(JITL.rl(name));
    }

    private static Tag.Named<Block> tagBlock(String name) {
        return BlockTags.createOptional(JITL.rl(name));
    }

}
