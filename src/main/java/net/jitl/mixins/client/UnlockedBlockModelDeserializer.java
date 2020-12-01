package net.jitl.mixins.client;

import com.mojang.datafixers.util.Either;
import net.minecraft.client.renderer.model.BlockModel;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BlockModel.Deserializer.class)
public interface UnlockedBlockModelDeserializer {
    @Invoker("parseTextureLocationOrReference")
    static Either<RenderMaterial, String> parseTextureLocationOrReference(ResourceLocation locationIn, String nameIn) {
        throw new IllegalStateException("Mixin " + UnlockedBlockModelDeserializer.class + " doesn't work.");
    }
}
