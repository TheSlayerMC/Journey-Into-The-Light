package net.jitl.datagen;

import net.jitl.JITL;
import net.jitl.init.JStructures;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.ExplorationMap;
import net.minecraft.world.storage.MapDecoration;
import ru.timeconqueror.timecore.api.devtools.gen.loottable.LootTableSet;

public class JChestLootTableSet extends LootTableSet {
    @Override
    public LootParameterSet getParameterSet() {
        return LootParameterSets.CHEST;
    }


    @Override
    public void register() {
        registerLootTable(JITL.rl("chests/test"),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(RandomValueRange.between(2.0F, 8.0F))
                        .add(ItemLootEntry.lootTableItem(Items.MAP).setWeight(10).apply(ExplorationMap.makeExplorationMap().setDestination(JStructures.ILlAGER_BUNKER.getStructure())
                                .setMapDecoration(MapDecoration.Type.BANNER_LIGHT_GRAY).setZoom((byte) 1).setSkipKnownStructures(false)))));

        registerLootTable(JITL.rl("chests/ancient_ruins"),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(RandomValueRange.between(2.0F, 8.0F))
                        .add(ItemLootEntry.lootTableItem(Items.MAP).setWeight(10).apply(ExplorationMap.makeExplorationMap().setDestination(JStructures.ANCIENT_RUINS.getStructure())
                                .setMapDecoration(MapDecoration.Type.BANNER_LIGHT_GRAY).setZoom((byte) 1).setSkipKnownStructures(false)))));
    }
}
