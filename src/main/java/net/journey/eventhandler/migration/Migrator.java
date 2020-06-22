package net.journey.eventhandler.migration;

import net.journey.JITL;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.HashMap;
import java.util.Map;

public abstract class Migrator<T extends IForgeRegistryEntry<T>> {
	protected Map<ResourceLocation, T> remappers;

	public abstract void regRemappers();

	public void regRemapper(String missingKey, T newEntry) {
		remappers.put(new ResourceLocation(JITL.MOD_ID, missingKey), newEntry);
	}

	public Map<ResourceLocation, T> getRemappers() {
		if (remappers == null) {
			remappers = new HashMap<>();
			regRemappers();
		}
		return remappers;
	}
}
