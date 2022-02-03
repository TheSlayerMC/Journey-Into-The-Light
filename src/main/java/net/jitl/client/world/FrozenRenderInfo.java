package net.jitl.client.world;

import net.jitl.client.render.CustomCloudRenderer;
import net.jitl.client.render.FrozenSkyRenderer;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class FrozenRenderInfo extends DimensionSpecialEffects {

    public FrozenRenderInfo() {
        super(164.0F, true, SkyType.NORMAL, false, false);
        setCloudRenderHandler(new CustomCloudRenderer());
        setSkyRenderHandler(new FrozenSkyRenderer());
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
}
