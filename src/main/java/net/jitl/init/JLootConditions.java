package net.jitl.init;

import net.jitl.JITL;
import net.jitl.common.loot.conditions.GetTablesCondition;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.registry.Registry;

public class JLootConditions {
    public static void init() {
    }

    public static final LootConditionType GET_TABLE = register("get_tables", new GetTablesCondition.Serializer());

    private static LootConditionType register(String id, ILootSerializer<? extends ILootCondition> serializer) {
        JITL.LOGGER.info("registered loot conditions");
        return Registry.register(Registry.LOOT_CONDITION_TYPE, JITL.rl(id), new LootConditionType(serializer));
    }
}
