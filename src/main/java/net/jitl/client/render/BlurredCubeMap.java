package net.jitl.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class BlurredCubeMap extends CubeMap {

    public BlurredCubeMap(ResourceLocation baseImageLocation) {
        super(baseImageLocation);
    }

    @Override
    public void render(@NotNull Minecraft mc, float pitch, float yaw, float alpha) {
        super.render(mc, pitch, yaw, alpha);

        //TODO: blur
    }
}