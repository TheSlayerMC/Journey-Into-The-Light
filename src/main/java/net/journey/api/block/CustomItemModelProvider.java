package net.journey.api.block;

import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public interface CustomItemModelProvider {
    @NotNull
    ResourceLocation getItemModelResourceLocation();
}
