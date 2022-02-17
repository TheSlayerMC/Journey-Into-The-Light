package net.jitl.common.eventhandler;

import com.google.gson.JsonObject;
import net.jitl.core.JITL;
import net.jitl.core.init.JEnchantments;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = JITL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SmeltingEnchantHandler {

    private static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, JITL.MODID);
    private static final RegistryObject<SmeltingEnchantHandler.Serializer> SMELTING = GLM.register("smelting", SmeltingEnchantHandler.Serializer::new);

    public static void register() {
        GLM.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @SubscribeEvent
    public static void runData(GatherDataEvent event) {
        event.getGenerator().addProvider(new DataProvider(event.getGenerator(), JITL.MODID));
    }

    private static class DataProvider extends GlobalLootModifierProvider {
        public DataProvider(DataGenerator gen, String modid) {
            super(gen, modid);
        }

        @Override
        protected void start() {
            add("smelting", SMELTING.get(), new SmeltingModifier(
                    new LootItemCondition[]{
                            MatchTool.toolMatches(
                                    ItemPredicate.Builder.item().hasEnchantment(
                                            new EnchantmentPredicate(JEnchantments.HOT_TOUCH.get(), MinMaxBounds.Ints.atLeast(1)))).build()
                    }));
        }
    }

    private static class SmeltingModifier extends LootModifier {

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
                    ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
                    generatedLoot.forEach((stack) -> ret.add(smelt(stack, context)));
                    return ret;
                } else {
                    return generatedLoot;
                }
            }
        }

        private static ItemStack smelt(ItemStack stack, LootContext context) {
            return context.getLevel().getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), context.getLevel())
                    .map(SmeltingRecipe::getResultItem)
                    .filter(itemStack -> !itemStack.isEmpty())
                    .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                    .orElse(stack);
        }

        private static class Serializer extends GlobalLootModifierSerializer<SmeltingModifier> {
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
