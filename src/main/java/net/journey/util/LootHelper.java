package net.journey.util;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;

public class LootHelper {

	public static List<ItemStack> readFromLootTable(ResourceLocation lootTable, EntityPlayerMP player) {
		if (player.world.isRemote){
			return Lists.<ItemStack>newArrayList();
		}
		return player.world.getLootTableManager().getLootTableFromLocation(lootTable).generateLootForPools(player.world.rand, new LootContext.Builder(player.getServerWorld()).withPlayer(player).build());
	}
}