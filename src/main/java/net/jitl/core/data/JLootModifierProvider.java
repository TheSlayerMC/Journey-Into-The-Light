package net.jitl.core.data;

import net.jitl.common.loot.conditions.GetTablesCondition;
import net.jitl.common.loot.modifiers.InjectTableModifier;
import net.jitl.common.loot.modifiers.SmeltingModifier;
import net.jitl.core.JITL;
import net.jitl.core.init.JEnchantments;
import net.jitl.core.init.JLootModifiers;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class JLootModifierProvider extends GlobalLootModifierProvider {
    public JLootModifierProvider(DataGenerator gen) {
        super(gen, JITL.MODID);
    }

    @Override
    protected void start() {
        add("smelting", JLootModifiers.SMELTING.get(), new SmeltingModifier(
                new LootItemCondition[]{
                        MatchTool.toolMatches(
                                ItemPredicate.Builder.item().hasEnchantment(
                                        new EnchantmentPredicate(JEnchantments.HOT_TOUCH.get(), MinMaxBounds.Ints.atLeast(1)))).build()
                }));

        add("old_chests", JLootModifiers.INJECT_TABLE.get(), new InjectTableModifier(
                new GetTablesCondition[]{
                        new GetTablesCondition(new ResourceLocation[]{
                                BuiltInLootTables.SPAWN_BONUS_CHEST,
                                BuiltInLootTables.ABANDONED_MINESHAFT
                        })
                }, JITL.rl("chests/overworld_chests")));
    }
}