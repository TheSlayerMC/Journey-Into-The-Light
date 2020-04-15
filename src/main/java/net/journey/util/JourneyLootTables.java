package net.journey.util;

import java.io.File;
import java.util.Collections;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.LootTableManager;
import net.slayer.api.SlayerAPI;

public class JourneyLootTables {

	public static final ResourceLocation TEST_CHEST = register("chests/test");
	
	public static final ResourceLocation BOIL_CHEST = register("chests/boilportal");
	public static final ResourceLocation NETHER_DUNGEON_CHEST = register("chests/netherdungeon");
	public static final ResourceLocation EUCA_SMITH_CHEST = register("chests/eucasmith");
	public static final ResourceLocation FROZEN_MERCH_CHEST = register("chests/frozenmerchant");
	
	public static final ResourceLocation DEPTHS_SHRINE_CHEST = register("chests/depthsshrine");
	public static final ResourceLocation TERRANIA_TREE_HUT_CHEST = register("chests/terraniatreehut");
	
	private static ResourceLocation register(String path) {
		return LootTableList.register(new ResourceLocation(SlayerAPI.MOD_ID, path));
	}
	
}