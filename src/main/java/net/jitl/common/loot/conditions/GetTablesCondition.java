package net.jitl.common.loot.conditions;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.jitl.init.JLootConditions;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GetTablesCondition implements ILootCondition {
    private final ResourceLocation[] tables;

    public GetTablesCondition(ResourceLocation[] tables) {
        this.tables = tables;
    }

    @Override
    public @NotNull LootConditionType getType() {
        return JLootConditions.GET_TABLE;
    }

    @Override
    public boolean test(LootContext lootContext) {
        for (ResourceLocation table : tables) {
            return (lootContext.getQueriedLootTableId().equals(table));
        }
        return false;
    }

    public static class Serializer implements ILootSerializer<GetTablesCondition> {

        @Override
        public void serialize(@NotNull JsonObject jsonObject, GetTablesCondition getTableCondition, @NotNull JsonSerializationContext jsonSerializationContext) {
            JsonArray oldTablesArray = new JsonArray();
            for (ResourceLocation table : getTableCondition.tables) {
                oldTablesArray.add(table.toString());
            }
            jsonObject.add("old_tables", oldTablesArray);
        }

        @Override
        public @NotNull GetTablesCondition deserialize(JsonObject jsonObject, @NotNull JsonDeserializationContext jsonDeserializationContext) {
            JsonArray object = jsonObject.get("old_tables").getAsJsonArray();
            ResourceLocation[] oldTables = new ResourceLocation[object.size()];
            for (int i = 0; i < object.size(); i++) {
                oldTables[i] = new ResourceLocation(object.get(i).getAsString());
            }
            return new GetTablesCondition(oldTables);
        }
    }
}
