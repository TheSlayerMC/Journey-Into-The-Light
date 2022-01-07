package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.loot.conditions.GetTablesCondition;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class JLootConditions {
    public static void init() {
    }

    public static final LootItemConditionType GET_TABLE = register("get_tables", new GetTablesCondition.LootSerializer());

    private static LootItemConditionType register(String id, Serializer<? extends LootItemCondition> serializer) {
        JITL.LOGGER.info("registered loot conditions");
        return Registry.register(Registry.LOOT_CONDITION_TYPE, JITL.rl(id), new LootItemConditionType(serializer));
    }
}
