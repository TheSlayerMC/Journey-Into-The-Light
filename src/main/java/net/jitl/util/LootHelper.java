package net.jitl.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class LootHelper {

    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, @NotNull ServerPlayerEntity player, Consumer<LootContext.Builder> builderModificator) {
        return genFromLootTable(lootTable, player, null, builderModificator);
    }

    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, @NotNull ServerPlayerEntity player, @Nullable Random random, Consumer<LootContext.Builder> builderModificator) {
        return genFromLootTable(lootTable, player.getLevel(), random, builder -> {
            //builder.withPlayer(player);
            builder.withLuck(player.getLuck());
            builderModificator.accept(builder);
        });
    }

    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, ServerWorld world, Consumer<LootContext.Builder> builderModificator) {
        return genFromLootTable(lootTable, world, null, builderModificator);
    }

    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, ServerWorld world, @Nullable Random random, Consumer<LootContext.Builder> builderModificator) {
        LootContext.Builder builder = new LootContext.Builder(world);
        builderModificator.accept(builder);
        LootContext context = builder.create(LootParameterSets.EMPTY);

        if(random == null) random = world.getRandom();

        return ServerLifecycleHooks.getCurrentServer().getLootTables().get(lootTable).getRandomItems(context);
    }
}
