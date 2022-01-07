package net.jitl.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class LootHelper {

    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, @NotNull ServerPlayer player, Consumer<LootContext.Builder> builderModificator) {
        return genFromLootTable(lootTable, player, null, builderModificator);
    }

    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, @NotNull ServerPlayer player, @Nullable Random random, Consumer<LootContext.Builder> builderModificator) {
        return genFromLootTable(lootTable, player.getLevel(), random, builder -> {
            //builder.withPlayer(player);
            builder.withLuck(player.getLuck());
            builderModificator.accept(builder);
        });
    }

    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, ServerLevel world, Consumer<LootContext.Builder> builderModificator) {
        return genFromLootTable(lootTable, world, null, builderModificator);
    }

    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, ServerLevel world, @Nullable Random random, Consumer<LootContext.Builder> builderModificator) {
        LootContext.Builder builder = new LootContext.Builder(world);
        builderModificator.accept(builder);
        LootContext context = builder.create(LootContextParamSets.EMPTY);

        if (random == null) random = world.getRandom();

        return ServerLifecycleHooks.getCurrentServer().getLootTables().get(lootTable).getRandomItems(context);
    }

    public static Consumer<ItemStack> createStackSplitter(Consumer<ItemStack> stackConsumer) {
        return (stack) -> {
            if (stack.getCount() < stack.getMaxStackSize()) {
                stackConsumer.accept(stack);
            } else {
                int i = stack.getCount();

                while (i > 0) {
                    ItemStack itemstack = stack.copy();
                    itemstack.setCount(Math.min(stack.getMaxStackSize(), i));
                    i -= itemstack.getCount();
                    stackConsumer.accept(itemstack);
                }
            }
        };
    }
}
