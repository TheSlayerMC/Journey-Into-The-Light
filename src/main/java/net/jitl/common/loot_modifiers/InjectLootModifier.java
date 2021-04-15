package net.jitl.common.loot_modifiers;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;

public class InjectLootModifier extends LootModifier {

    private final ResourceLocation[] lootTables;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    protected InjectLootModifier(ILootCondition[] conditionsIn, ResourceLocation[] lootTables) {
        super(conditionsIn);
        this.lootTables = lootTables;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        for (ResourceLocation resourceLocation : lootTables) {
            LootTable lootTable = context.getLootTable(resourceLocation);
            List<ItemStack> lootItems = lootTable.getRandomItems(context);
            lootTable.getRandomItemsRaw(context, lootItems::add);
            if (!lootItems.isEmpty()) {
                return lootItems;
            } else {
                return generatedLoot;
            }
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<InjectLootModifier> {

        @Override
        public InjectLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            return null;
        }

        @Override
        public JsonObject write(InjectLootModifier instance) {
            return null;
        }
    }
}
