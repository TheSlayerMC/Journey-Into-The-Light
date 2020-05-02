package net.journey.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

import net.journey.event.LootTableEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
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
    public static final ResourceLocation ROCKITE_CHEST_LOOT = register("chests/overworld/rockite_loot");
    public static final ResourceLocation VANILLA_SIMPLE_DUNGEON = LootTableList.CHESTS_SIMPLE_DUNGEON;

    private static ResourceLocation register(String path) {
        return LootTableList.register(new ResourceLocation(SlayerAPI.MOD_ID, path));
    }
    
    public static void generateLootTableForPlayer(EntityPlayerMP player, ItemStack... items) {
    	LootTable table = player.world.getLootTableManager().getLootTableFromLocation(JourneyLootTables.BOIL_CHEST);
    	LootContext lootcontext = new LootContext.Builder(player.getServerWorld()).withPlayer(player).withLuck(player.getLuck()).build();
    	List<ItemStack> stacks = table.generateLootForPools(player.world.rand, lootcontext);
    }
    
	@Nonnull
	public static List<ItemStack> generateLootWithLuck(ResourceLocation lootTable, EntityPlayerMP pl) {
		if (pl.world.isRemote)
			return Lists.<ItemStack>newArrayList();

		return pl.world.getLootTableManager().getLootTableFromLocation(lootTable).generateLootForPools(pl.world.rand, new LootContext.Builder(pl.getServerWorld()).withPlayer(pl).withLuck(pl.getLuck()).build());
	}
}