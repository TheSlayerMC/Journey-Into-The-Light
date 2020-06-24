package net.journey.util;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;

import java.util.List;

public class LootHelper {

	public static List<ItemStack> readFromLootTable(ResourceLocation lootTable, EntityPlayerMP player) {
		return player.world.getLootTableManager().getLootTableFromLocation(lootTable).generateLootForPools(player.world.rand, new LootContext.Builder(player.getServerWorld()).withPlayer(player).build());
	}

	public static List<ItemStack> readFromLootTable(ResourceLocation lootTable, World world) {
		return world.getLootTableManager().getLootTableFromLocation(lootTable).generateLootForPools(world.rand, new LootContext.Builder((WorldServer) world).build());
	}
}