package net.journey.event;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class LootTableEvent {

    /**
     * placeholder for future resource locations
     * TODO: replace with future resource locations for Journey loot tables to be injected into vanilla and 3rd party loot tables
     * ex:
     * ResourceLocation BATTLE_HEART_LOOT = new ResourceLocation("journey", "loot_tables/misc/battle_heart_loot");
     */
    public static final ResourceLocation TEST = new ResourceLocation("journey", "chests/test");
    /**
     * placeholder for LootTable pool names
     * TODO: replace with proper pool name
     * ex:
     * String DUNGEON_POOL = new String("journey_dungeon_loot");
     */
    public static final String TESTPOOL = "journey_dungeon_loot";

    /**
     * injects custom journey loot tables into vanilla or 3rd party loot tables
     * currently intects "nothing" into vanilla "simple_dungeon" loot table
     * TODO: finish loot table injection
     */
    @SubscribeEvent
    public static void addLootToTable(LootTableLoadEvent event) {
        if (event.getName().toString().equals("minecraft:chests/village_blacksmith")) {
            event.getTable().addPool(new LootPool(new LootEntry[]{new LootEntryTable(TEST, 1, 1, new LootCondition[0], TESTPOOL)}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), TESTPOOL));
        }
    }
}
