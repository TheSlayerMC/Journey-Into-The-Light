package net.jitl.init;

import net.jitl.JITL;
import net.minecraft.resources.ResourceLocation;

import ResourceLocation;

public class JLootTables {

    public static final ResourceLocation LOOT_POUCH_BASIC = register("gameplay/loot_pouch_basic");
    public static final ResourceLocation LOOT_POUCH_GOLD = register("gameplay/loot_pouch_gold");
    public static final ResourceLocation LOOT_POUCH_DIAMOND = register("gameplay/loot_pouch_diamond");

    public static final ResourceLocation OVERWORLD_CHESTS = register("chests/overworld_chests");
    public static final ResourceLocation ILLAGER_TREASURE_ROOM = register("chests/illager_bunker/treasure_room");
    public static final ResourceLocation FROZEN_TROLL_TRADES = register("gameplay/frozen_troll_trades");

    private static ResourceLocation register(String id) {
        return JITL.rl(id);
    }
}