package net.jitl.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlurredCubeMap extends CubeMap {
    public BlurredCubeMap(ResourceLocation baseImageLocation_) {
        super(baseImageLocation_);
    }

    @Override
    public void render(Minecraft mc_, float pitch_, float yaw_, float alpha_) {
        super.render(mc_, pitch_, yaw_, alpha_);
        //TODO: bluur
    }
}