package net.jitl.common.loot.modifiers;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.jitl.util.LootHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class InjectTableModifier extends LootModifier {

    private final ResourceLocation newTable;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    protected InjectTableModifier(LootItemCondition[] conditionsIn, ResourceLocation lootTable) {
        super(conditionsIn);
        this.newTable = lootTable;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        LootTable lootTable = context.getLootTable(getNewTable());
        lootTable.getRandomItemsRaw(context, LootHelper.createStackSplitter(generatedLoot::add));
        return generatedLoot;
    }

    public LootItemCondition[] getConditions() {
        return conditions;
    }

    public ResourceLocation getNewTable() {
        return newTable;
    }

    public static class Serializer extends GlobalLootModifierSerializer<InjectTableModifier> {

        @Override
        public InjectTableModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
            JsonPrimitive newTableProperty = object.getAsJsonPrimitive("new_table");
            ResourceLocation newTable = new ResourceLocation(newTableProperty.getAsString());
            return new InjectTableModifier(ailootcondition, newTable);
        }

        @Override
        public JsonObject write(InjectTableModifier instance) {
            LootItemCondition[] conditions = instance.getConditions();
            ResourceLocation newTable = instance.getNewTable();

            JsonObject jsonObject = this.makeConditions(conditions);

            jsonObject.addProperty("new_table", newTable.toString());
            if (getRegistryName() != null) {
                jsonObject.addProperty("type", getRegistryName().toString());
            }
            return null;
        }
    }
}
