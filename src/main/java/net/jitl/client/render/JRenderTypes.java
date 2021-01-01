package net.jitl.client.render;

import net.jitl.JITL;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class JRenderTypes extends RenderType {
    public JRenderTypes(String nameIn, VertexFormat formatIn, int drawModeIn, int bufferSizeIn, boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
        super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
    }

    public static RenderType laserBeam(ResourceLocation location) {
        return RenderType.create(JITL.rl("laser_beam").toString(),
                DefaultVertexFormats.POSITION_TEX,
                GL11.GL_QUADS,
                256,
                false,
                true,
                RenderType.State.builder()
                        .setTextureState(new TextureState(location, false/*blur*/, false/*mipmap*/))
                        .setAlphaState(RenderState.MIDWAY_ALPHA)
                        .createCompositeState(false));
    }

    public static RenderType essenciaBolt() {
        return RenderType.create(JITL.rl("essencia_bolt").toString(),
                DefaultVertexFormats.POSITION_COLOR,
                GL11.GL_QUADS,
                256,
                false,
                true,
                RenderType.State.builder()
                        .setTransparencyState(RenderState.LIGHTNING_TRANSPARENCY)
                        .setWriteMaskState(RenderState.COLOR_DEPTH_WRITE)
                        .setOutputState(RenderState.WEATHER_TARGET)
                        .setShadeModelState(RenderState.SMOOTH_SHADE)
                        .createCompositeState(false));
    }
}
