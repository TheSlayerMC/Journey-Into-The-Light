package net.jitl.core.init;

import net.jitl.core.JITL;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.SetTag;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class JTags {

    //protected static final StaticTagHelper<EntityType<?>> KNOWLEDGE = StaticTags.create(Registry.ENTITY_TYPE_REGISTRY, "tags/knowledge");//make custom reg

    public static final SetTag.Named<Item> FROZEN_TROLL_LOVED_ITEMS = tagItem("frozen_troll_loved_items");
    public static final SetTag.Named<Block> EUCA_STONE_ORE_REPLACEABLES = tagBlock("euca_stone_ore_replaceables");
    public static final SetTag.Named<Block> BOIL_STONE_ORE_REPLACEABLES = tagBlock("boil_stone_ore_replaceables");
    public static final SetTag.Named<Block> FROZEN_STONE_ORE_REPLACEABLES = tagBlock("frozen_stone_ore_replaceables");
    public static final SetTag.Named<Block> JLOGS = tagBlock("jlogs");

    /*public static final SetTag.Named<EntityType<?>> OVERWORLD_MOBS = tagKnowledge("overworld");
    public static final SetTag.Named<EntityType<?>> END_MOBS = tagKnowledge("end");
    public static final SetTag.Named<EntityType<?>> NETHER_MOBS = tagKnowledge("nether");
    public static final SetTag.Named<EntityType<?>> EUCA_MOBS = tagKnowledge("euca");
    public static final SetTag.Named<EntityType<?>> FROZEN_MOBS = tagKnowledge("frozen");
    public static final SetTag.Named<EntityType<?>> BOIL_MOBS = tagKnowledge("boil");*/


    private static Tag.Named<Item> tagItem(String name) {
        return ItemTags.createOptional(JITL.rl(name));
    }

    private static Tag.Named<Block> tagBlock(String name) {
        return BlockTags.createOptional(JITL.rl(name));
    }

    /*private static Tag.Named<EntityType<?>> tagKnowledge(String name) {
        return KNOWLEDGE.createOptional(JITL.rl(name), null);
    }*/
}
