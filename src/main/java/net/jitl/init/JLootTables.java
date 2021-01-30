package net.jitl.init;

import net.jitl.JITL;
import net.minecraft.loot.LootTables;
import net.minecraft.util.ResourceLocation;

public class JLootTables {

    public static final ResourceLocation LOOT_POUCH_BASIC = LootTables.ABANDONED_MINESHAFT;//register("loot/pouch_basic");
    public static final ResourceLocation LOOT_POUCH_GOLD = LootTables.DESERT_PYRAMID;//register("loot/pouch_gold");
    public static final ResourceLocation LOOT_POUCH_DIAMOND = LootTables.WOODLAND_MANSION;//register("loot/pouch_diamond");

    private static ResourceLocation register(String id) {
        return JITL.rl(id);
    }
}