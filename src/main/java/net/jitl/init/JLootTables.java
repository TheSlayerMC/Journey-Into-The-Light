package net.jitl.init;

import com.google.common.collect.Sets;
import net.jitl.JITL;
import net.minecraft.loot.LootTables;
import net.minecraft.util.ResourceLocation;

import java.util.Set;

public class JLootTables {
    private static final Set<ResourceLocation> LOCATIONS = Sets.newHashSet();

    public static final ResourceLocation LOOT_POUCH_BASIC = LootTables.ABANDONED_MINESHAFT;//register("loot/pouch_basic");
    public static final ResourceLocation LOOT_POUCH_GOLD = LootTables.DESERT_PYRAMID;//register("loot/pouch_gold");
    public static final ResourceLocation LOOT_POUCH_DIAMOND = LootTables.WOODLAND_MANSION;//register("loot/pouch_diamond");

    private static ResourceLocation register(String id) {
        return register(JITL.rl(id));
    }

    private static ResourceLocation register(ResourceLocation id) {
        if (LOCATIONS.add(id)) {
            return id;
        } else {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table");
        }
    }

}