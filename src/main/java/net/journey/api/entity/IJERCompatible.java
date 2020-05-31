package net.journey.api.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

/**
 * Can be applied to any entity, that extends {@link EntityLivingBase} to provide Just Enough Resources compatibility.
 */
public interface IJERCompatible {
	@Nullable
	ResourceLocation getLootTable();
}
