package net.jitl.client.world;

import net.jitl.client.render.BoilCloudsRenderer;
import net.jitl.client.render.BoilSkyRenderer;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class BoilRenderInfo extends DimensionSpecialEffects {

    public BoilRenderInfo() {
        super(140.0F, true, SkyType.NORMAL, false, false);
        setSkyRenderHandler(new BoilSkyRenderer());
        setCloudRenderHandler(new BoilCloudsRenderer());
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
