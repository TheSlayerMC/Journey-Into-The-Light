package net.journey.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.slayer.api.SlayerAPI;

public class JourneyLootTables {

    public static final ResourceLocation TEST_CHEST = register("chests/test");

    public static final ResourceLocation BOIL_CHEST = register("chests/boilportal");
    public static final ResourceLocation NETHER_DUNGEON_CHEST = register("chests/netherdungeon");
    public static final ResourceLocation EUCA_SMITH_CHEST = register("chests/eucasmith");
    public static final ResourceLocation FROZEN_MERCH_CHEST = register("chests/frozenmerchant");

    public static final ResourceLocation DEPTHS_SHRINE_CHEST = register("chests/depthsshrine");
    public static final ResourceLocation TERRANIA_TREE_HUT_CHEST = register("chests/terraniatreehut");
    public static final ResourceLocation ROCKITE__CHEST_LOOT = register("chests/rockite_loot");
    public static final ResourceLocation VANILLA_SIMPLE_DUNGEON = LootTableList.CHESTS_SIMPLE_DUNGEON;

    private static ResourceLocation register(String path) {
        return LootTableList.register(new ResourceLocation(SlayerAPI.MOD_ID, path));
    }
}