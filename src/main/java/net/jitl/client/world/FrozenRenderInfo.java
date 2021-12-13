package net.jitl.client.world;

import net.jitl.client.render.FrozenCloudsRenderer;
import net.jitl.client.render.FrozenSkyRenderer;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.ICloudRenderHandler;
import net.minecraftforge.client.ISkyRenderHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class FrozenRenderInfo extends DimensionRenderInfo {

    public FrozenRenderInfo() {
        super(90.0F, true, FogType.NORMAL, false, false);
    }

    @Override
    public @NotNull Vector3d getBrightnessDependentFogColor(Vector3d vector3d, float float_) {
        float color = 0.95F + 0.05F;
        return vector3d.multiply((float_ * color), (float_ * color), (float_ * color));
    }

    @Override
    public boolean isFoggyAt(int int_, int int1_) {
        return false;
    }

    @Nullable
    @Override
    public ISkyRenderHandler getSkyRenderHandler() {
        return new FrozenSkyRenderer();
    }

    @Nullable
    @Override
    public ICloudRenderHandler getCloudRenderHandler() {
        return new FrozenCloudsRenderer();
    }
}
