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
     * Resource Location for loot table to be injected into vanilla loot table
     */
    public static final ResourceLocation DUNGEON_LOOT = new ResourceLocation("journey", "chests/overworld/dungeon_loot");
    
    /**
     * Name of pool to be selected from said loot table
     */
    public static final String DUNGEON_POOL = "journey_dungeon_loot";

    /**
     * Gets names of loot tables from resource location, then adds new loot tables and pools to said loot tables
     */
    @SubscribeEvent
    public static void addLootToTable(LootTableLoadEvent event) {
        if (
        event.getName().toString().equals("minecraft:chests/simple_dungeon") || 
        event.getName().toString().equals("minecraft:chests/abandoned_mineshaft") || 
        event.getName().toString().equals("minecraft:chests/desert_pyramid") || 
        event.getName().toString().equals("minecraft:chests/jungle_temple") || 
        event.getName().toString().equals("minecraft:chests/stronghold_corridor") || 
        event.getName().toString().equals("minecraft:chests/stronghold_crossing") || 
        event.getName().toString().equals("minecraft:chests/stronghold_library") || 
        event.getName().toString().equals("cqrepoured:chest/cq_treasure") || 
        event.getName().toString().equals("cqrepoured:chest/cq_material")) {
            event.getTable().addPool(new LootPool(new LootEntry[]{new LootEntryTable(DUNGEON_LOOT, 1, 1, new LootCondition[0], DUNGEON_POOL)}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), DUNGEON_POOL));
        }
    }
}
