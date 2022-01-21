package net.jitl.client.world;

import net.jitl.client.render.EucaCloudsRenderer;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.ICloudRenderHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class EucaRenderInfo extends DimensionSpecialEffects {

    public EucaRenderInfo() {
        super(160.0F, true, SkyType.NORMAL, false, false);
    }

    @Override
    public @NotNull Vec3 getBrightnessDependentFogColor(Vec3 vector3d, float float_) {
        float color = 0.95F + 0.05F;
        return vector3d.multiply((float_ * color), (float_ * color), (float_ * color));
    }

    @Override
    public boolean isFoggyAt(int int_, int int1_) {
        return false;
    }

    @Nullable
    @Override
    public ICloudRenderHandler getCloudRenderHandler() {
        return new EucaCloudsRenderer();
    }
}
