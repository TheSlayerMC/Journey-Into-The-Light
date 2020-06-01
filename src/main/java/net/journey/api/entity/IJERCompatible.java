package net.journey.api.entity;

import jeresources.api.drop.LootDrop;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

/**
 * Can be applied to any entity, that extends {@link EntityLivingBase} to provide Just Enough Resources compatibility.
 */
public interface IJERCompatible {
	/**
	 * Returns location of loot table.
	 * Should return null if no loot is dropped by that entity.
	 * <p>
	 * This method will be called if {@link #getJERDrops()} returned an empty list.
	 */
	@Nullable
	ResourceLocation getJERLootLocation();

	/**
	 * This method will be called before {@link #getJERLootLocation()}.
	 * If you have customised drops for some mob, return them here.
	 * <p>
	 * If this method return an empty list, then {@link #getJERLootLocation()} will be called.
	 */
	@NotNull
	default List<LootDrop> getJERDrops() {
		return Collections.emptyList();
	}
}
