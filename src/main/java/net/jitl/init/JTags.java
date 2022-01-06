package net.jitl.init;

import net.jitl.JITL;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.tags.SetTag;
import net.minecraft.tags.ItemTags;

public class JTags {
    public static final SetTag.Named<Item> FROZEN_TROLL_LOVED_ITEMS = tag("frozen_troll_loved_items");

    private static Tag.Named<Item> tag(String name) {
        return ItemTags.createOptional(JITL.rl(name));
    }
}
