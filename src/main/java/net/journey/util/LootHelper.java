package net.journey.util;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;

public class LootHelper {

	public static List<ItemStack> readFromLootTable(ResourceLocation lootTable, EntityPlayerMP player) {
		return player.world.getLootTableManager().getLootTableFromLocation(lootTable)
				.generateLootForPools(player.world.rand, new LootContext.Builder(player.getServerWorld()).withPlayer(player).build());
	}
	
	
}