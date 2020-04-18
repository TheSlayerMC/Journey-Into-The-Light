package net.journey.event;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LootTableEvent {
	
	ResourceLocation resource;
	String poolName;
	
	@SubscribeEvent
	public void addLootToTable(LootTableLoadEvent event) {
		if (event.getName().toString().equals("minecraft:chests/simple_dungeon")) {
			event.getTable().addPool(new LootPool(new LootEntry[] {new LootEntryTable(resource, 1, 0, new LootCondition[0], poolName)}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), poolName));
		}
	}
}
