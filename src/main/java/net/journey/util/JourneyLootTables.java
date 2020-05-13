package net.journey.util;

import com.google.common.collect.Lists;
import net.journey.JITL;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nonnull;
import java.util.List;

public class JourneyLootTables {

    public static final ResourceLocation TEST_CHEST = register("chests/test");

    public static final ResourceLocation BOIL_CHEST = register("chests/boilportal");
    public static final ResourceLocation NETHER_DUNGEON_CHEST = register("chests/netherdungeon");
    public static final ResourceLocation EUCA_SMITH_CHEST = register("chests/eucasmith");
    public static final ResourceLocation FROZEN_MERCH_CHEST = register("chests/frozenmerchant");

    public static final ResourceLocation LOOT_POUCH = register("loot/loot_pouch");
    public static final ResourceLocation LOOT_PRESENT = register("loot/loot_present");

    public static final ResourceLocation DEPTHS_SHRINE_CHEST = register("chests/depthsshrine");
    public static final ResourceLocation TERRANIA_TREE_HUT_CHEST = register("chests/terraniatreehut");
    public static final ResourceLocation ROCKITE_CHEST_LOOT = register("chests/overworld/rockite_loot");
    public static final ResourceLocation ANCIENT_CHEST_LOOT = register("chests/overworld/ancient_loot");
    public static final ResourceLocation SENTRY_BASE_CHEST_LOOT = register("chests/senterian/sentry_dungeon");
    
    //Overworld
    public static final ResourceLocation BIG_HONGO = register("mobs/big_hongo");
    public static final ResourceLocation BOOM_BOOM = register("mobs/boom_boom");
    public static final ResourceLocation DUNEWORM = register("mobs/duneworm");
    public static final ResourceLocation FIRE_MAGE = register("mobs/fire_mage");
    public static final ResourceLocation FLORO = register("mobs/floro");
    public static final ResourceLocation ICE_MAGE = register("mobs/ice_mage");
    public static final ResourceLocation MEDIUM_HONGO = register("mobs/medium_hongo");
    public static final ResourceLocation ROBOT = register("mobs/robot");
    public static final ResourceLocation SAND_CRAWLER = register("mobs/sand_crawler");
    public static final ResourceLocation SMALL_HONGO = register("mobs/small_hongo");
    public static final ResourceLocation SPECTRE = register("mobs/spectre");
    public static final ResourceLocation SPYCLOPSE = register("mobs/spyclopse");
    public static final ResourceLocation SWAMP_FLY = register("mobs/swamp_fly");
    public static final ResourceLocation TURDUCKEN = register("mobs/turducken");
    public static final ResourceLocation WRAITH = register("mobs/wraith");

    
    
    public static final ResourceLocation VANILLA_SIMPLE_DUNGEON = LootTableList.CHESTS_SIMPLE_DUNGEON;

    private static ResourceLocation register(String path) {
	    return LootTableList.register(new ResourceLocation(JITL.MOD_ID, path));
    }
    
    public static void generateLootTableForPlayer(EntityPlayerMP player, ItemStack... items) {
    	LootTable table = player.world.getLootTableManager().getLootTableFromLocation(JourneyLootTables.BOIL_CHEST);
    	LootContext lootcontext = new LootContext.Builder(player.getServerWorld()).withPlayer(player).withLuck(player.getLuck()).build();
    	List<ItemStack> stacks = table.generateLootForPools(player.world.rand, lootcontext);
    }
    
	@Nonnull
	public static List<ItemStack> generateLootWithLuck(ResourceLocation lootTable, EntityPlayerMP pl) {
		if (pl.world.isRemote)
			return Lists.newArrayList();

		return pl.world.getLootTableManager().getLootTableFromLocation(lootTable).generateLootForPools(pl.world.rand, new LootContext.Builder(pl.getServerWorld()).withPlayer(pl).withLuck(pl.getLuck()).build());
	}
}