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
    public static final ResourceLocation BIG_HONGO = register("mobs/overworld/big_hongo");
    public static final ResourceLocation BOOM_BOOM = register("mobs/overworld/boom_boom");
    public static final ResourceLocation DUNEWORM = register("mobs/overworld/duneworm");
    public static final ResourceLocation FIRE_MAGE = register("mobs/overworld/fire_mage");
    public static final ResourceLocation FLORO = register("mobs/overworld/floro");
    public static final ResourceLocation ICE_MAGE = register("mobs/overworld/ice_mage");
    public static final ResourceLocation MEDIUM_HONGO = register("mobs/overworld/medium_hongo");
    public static final ResourceLocation ROBOT = register("mobs/overworld/robot");
    public static final ResourceLocation SAND_CRAWLER = register("mobs/overworld/sand_crawler");
    public static final ResourceLocation SMALL_HONGO = register("mobs/overworld/small_hongo");
    public static final ResourceLocation SPECTRE = register("mobs/overworld/spectre");
    public static final ResourceLocation SPYCLOPSE = register("mobs/overworld/spyclopse");
    public static final ResourceLocation SWAMP_FLY = register("mobs/overworld/swamp_fly");
    public static final ResourceLocation TURDUCKEN = register("mobs/overworld/turducken");
    public static final ResourceLocation WRAITH = register("mobs/overworld/wraith");
    public static final ResourceLocation BLIZZARD = register("mobs/overworld/blizzard");
    public static final ResourceLocation JUNGLE_GOLEM = register("mobs/overworld/jungle_golem");
    public static final ResourceLocation JUNGLE_SPIDER = register("mobs/overworld/jungle_spider");
    public static final ResourceLocation JUNGLE_TURTLE = register("mobs/overworld/jungle_turtle");
    public static final ResourceLocation BLUE_HONGLOW = register("mobs/overworld/blue_honglow");
    public static final ResourceLocation CAVELING = register("mobs/overworld/caveling");
    public static final ResourceLocation CAVE_MAGE = register("mobs/overworld/cave_mage");
    public static final ResourceLocation CAVURN = register("mobs/overworld/cavurn");
    public static final ResourceLocation GREEN_HONGLOW = register("mobs/overworld/green_honglow");
    public static final ResourceLocation RED_HONGLOW = register("mobs/overworld/red_honglow");
    public static final ResourceLocation ROCKITE = register("mobs/overworld/rockite");
    public static final ResourceLocation STONEWALKER = register("mobs/overworld/stonewalker");

    //Boil
    public static final ResourceLocation BURNING_LIGHT = register("mobs/boil/burning_light");
    public static final ResourceLocation EXPOSED_FLAME = register("mobs/boil/exposed_flame");
    public static final ResourceLocation FLAME_LOTUS = register("mobs/boil/flame_lotus");
    public static final ResourceLocation FRIGHTENER = register("mobs/boil/frightener");
    public static final ResourceLocation HELLWING = register("mobs/boil/hellwing");
    public static final ResourceLocation MAGMA_BLAZE = register("mobs/boil/magma_blaze");
    public static final ResourceLocation MAGMA_GIANT = register("mobs/boil/magma_giant");
    public static final ResourceLocation OBSERVER = register("mobs/boil/observer");
    public static final ResourceLocation SCREAMER = register("mobs/boil/screamer");

    
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