package net.jitl.common.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import net.jitl.init.JLootConditions;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GetTableCondition implements ILootCondition {
    private final ResourceLocation table;

    public GetTableCondition(ResourceLocation table) {
        this.table = table;
    }

    @Override
    public @NotNull LootConditionType getType() {
        return JLootConditions.GET_TABLE;
    }

    @Override
    public boolean test(LootContext lootContext) {
        return lootContext.getQueriedLootTableId().equals(table);
    }

    public static class Serializer implements ILootSerializer<GetTableCondition> {

        @Override
        public void serialize(JsonObject jsonObject, GetTableCondition getTableCondition, @NotNull JsonSerializationContext jsonSerializationContext) {
            JsonPrimitive newTableProperty = new JsonPrimitive(getTableCondition.table.toString());
            jsonObject.add("old_table", newTableProperty);
        }

        @Override
        public @NotNull GetTableCondition deserialize(JsonObject jsonObject, @NotNull JsonDeserializationContext jsonDeserializationContext) {
            JsonPrimitive object = jsonObject.get("old_table").getAsJsonPrimitive();
            ResourceLocation location = new ResourceLocation(object.getAsString());
            return new GetTableCondition(location);
        }
    }
}
