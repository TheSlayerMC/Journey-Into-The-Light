package net.jitl.common.eventhandler;

import net.jitl.core.JITL;
import net.jitl.core.init.JItems;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class LootTableEventHandler {

    @SubscribeEvent
    public static void onLootGenerated(LootTableLoadEvent event) {
        if (event.getName().equals(BuiltInLootTables.FISHING_TREASURE)) {
            LootPoolEntryContainer.Builder basicPouch = LootItem.lootTableItem(JItems.LOOT_POUCH_BASIC).setQuality(20).setWeight(15);
            LootPoolEntryContainer.Builder goldPouch = LootItem.lootTableItem(JItems.LOOT_POUCH_GOLD).setQuality(30).setWeight(5);
            LootPoolEntryContainer.Builder diamondPouch = LootItem.lootTableItem(JItems.LOOT_POUCH_DIAMOND).setQuality(40).setWeight(1);
            LootPool.Builder builder = new LootPool.Builder().name("loot_pouch").add(basicPouch).add(goldPouch).add(diamondPouch).when(LootItemRandomChanceCondition.randomChance(0.5F)).setRolls(UniformGenerator.between(0, 1));
            event.getTable().addPool(builder.build());
        }
    }
}
