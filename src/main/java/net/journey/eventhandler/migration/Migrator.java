package net.journey.eventhandler.migration;

import net.journey.JITL;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.MissingMappings.Mapping;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public abstract class Migrator<T extends IForgeRegistryEntry<T>> {
	protected Map<ResourceLocation, MappingEntryResolver<T>> remappers;

	public abstract void regRemappers();

	public void remap(String missingKey, T newEntry) {
		remappers.put(JITL.rl(missingKey), new MappingEntryResolver<T>(newEntry) {
			@Override
			public void process(Mapping<T> mapping) {
				mapping.remap(newEntry);
			}
		});
	}

	public void ignore(String missingKey) {
		remappers.put(JITL.rl(missingKey), new MappingEntryResolver<T>(null) {
			@Override
			public void process(Mapping<T> mapping) {
				mapping.ignore();
			}
		});
	}

	public Map<ResourceLocation, MappingEntryResolver<T>> getRemappers() {
		if (remappers == null) {
			remappers = new HashMap<>();
			regRemappers();
		}
		return remappers;
	}

	public abstract static class MappingEntryResolver<T extends IForgeRegistryEntry<T>> {
		@Nullable
		private final T entry;

		public MappingEntryResolver(@Nullable T entry) {
			this.entry = entry;
		}

		@Nullable
		public T getNewEntry() {
			return entry;
		}

		public abstract void process(Mapping<T> mapping);
	}
}
