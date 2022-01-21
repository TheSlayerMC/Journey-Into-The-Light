package net.jitl.core.mixins.client;

import com.mojang.datafixers.util.Either;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BlockModel.Deserializer.class)
public interface UnlockedBlockModelDeserializer {
    @Invoker("parseTextureLocationOrReference")
    static Either<Material, String> parseTextureLocationOrReference(ResourceLocation locationIn, String nameIn) {
        throw new IllegalStateException("Mixin " + UnlockedBlockModelDeserializer.class + " doesn't work.");
    }
}
