package net.journey.eventhandler.migration;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.MissingMappings.Mapping;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Map;

@Mod.EventBusSubscriber
public class MigrationHandler {
	@SubscribeEvent
	public static void onMissingBlockMappings(RegistryEvent.MissingMappings<Block> event) {
		remap(event, new BlockMigrator());
	}

	@SubscribeEvent
	public static void onMissingItemMappings(RegistryEvent.MissingMappings<Item> event) {
		remap(event, new ItemMigrator());
	}

	private static <T extends IForgeRegistryEntry<T>> void remap(RegistryEvent.MissingMappings<T> event, Migrator<T> migrator) {
		ImmutableList<Mapping<T>> mappings = event.getMappings();

		if (!mappings.isEmpty()) {
			Map<ResourceLocation, Migrator.MappingEntryResolver<T>> remappers = migrator.getRemappers();

			if (!remappers.isEmpty()) {
				for (Mapping<T> mapping : mappings) {
					if (remappers.containsKey(mapping.key)) {
						remappers.get(mapping.key).process(mapping);
					}
				}
			}
		}
	}
}
