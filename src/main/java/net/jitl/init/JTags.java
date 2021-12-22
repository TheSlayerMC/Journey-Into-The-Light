package net.jitl.init;

import net.jitl.JITL;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class JTags {
    public static final ITag.INamedTag<Item> FROZEN_TROLL_LOVED_ITEMS = tag("frozen_troll_loved_items");

    private static ITag.INamedTag<Item> tag(String name) {
        return ItemTags.createOptional(JITL.rl(name));
    }
}
