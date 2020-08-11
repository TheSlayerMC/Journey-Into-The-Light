package net.journey.util;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class LootHelper {
    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, @NotNull EntityPlayerMP player, Consumer<LootContext.Builder> builderModificator) {
        return genFromLootTable(lootTable, player, null, builderModificator);
    }

    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, @NotNull EntityPlayerMP player, @Nullable Random random, Consumer<LootContext.Builder> builderModificator) {
        return genFromLootTable(lootTable, player.getServerWorld(), random, builder -> {
            builder.withPlayer(player);
            builder.withLuck(player.getLuck());
            builderModificator.accept(builder);
        });
    }

    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, WorldServer world, Consumer<LootContext.Builder> builderModificator) {
        return genFromLootTable(lootTable, world, null, builderModificator);
    }

    public static List<ItemStack> genFromLootTable(ResourceLocation lootTable, WorldServer world, @Nullable Random random, Consumer<LootContext.Builder> builderModificator) {
        LootContext.Builder builder = new LootContext.Builder(world);

        builderModificator.accept(builder);

        LootContext context = builder.build();

        if (random == null) {
            random = world.rand;
        }

        //TODO what if lootTable wasn't found
        return world.getLootTableManager().getLootTableFromLocation(lootTable).generateLootForPools(random, context);
    }
}