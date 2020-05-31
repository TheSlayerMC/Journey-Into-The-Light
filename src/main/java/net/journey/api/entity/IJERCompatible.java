package net.journey.api.entity;

import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public interface IJERCompatible {
	@Nullable
	ResourceLocation getLootTable();
}
