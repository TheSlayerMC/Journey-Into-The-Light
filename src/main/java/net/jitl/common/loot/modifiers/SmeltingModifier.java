package net.jitl.common.loot.modifiers;

import com.google.gson.JsonObject;
import net.jitl.core.init.JEnchantments;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SmeltingModifier extends LootModifier {

    public final LootItemCondition[] conditions;

    public SmeltingModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
        this.conditions = conditionsIn;
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        ItemStack tool = Objects.requireNonNull(context.getParamOrNull(LootContextParams.TOOL));
        if (EnchantmentHelper.getEnchantments(tool).containsKey(JEnchantments.HOT_TOUCH.get())) {
            ArrayList<ItemStack> ret = new ArrayList<>();
            generatedLoot.forEach((stack) -> ret.add(smelt(stack, context)));
            return ret;
        } else {
            return generatedLoot;
        }
    }

    private static ItemStack smelt(ItemStack stack, LootContext context) {
        return context.getLevel().getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), context.getLevel())
                .map(SmeltingRecipe::getResultItem)
                .filter(itemStack -> !itemStack.isEmpty())
                .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                .orElse(stack);
    }

    public static class Serializer extends GlobalLootModifierSerializer<SmeltingModifier> {
        @Override
        public SmeltingModifier read(ResourceLocation name, JsonObject json, LootItemCondition[] conditionsIn) {
            return new SmeltingModifier(conditionsIn);
        }

        @Override
        public JsonObject write(SmeltingModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}