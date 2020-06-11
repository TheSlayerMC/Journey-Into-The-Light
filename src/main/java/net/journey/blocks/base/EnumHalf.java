package net.journey.blocks.base;

import net.minecraft.util.IStringSerializable;
import org.jetbrains.annotations.NotNull;

public enum EnumHalf implements IStringSerializable {
	TOP("top"),
	BOTTOM("bottom");

	private final String name;

	EnumHalf(String name) {
		this.name = name;
	}

	public @NotNull String getName() {
		return this.name;
	}
}