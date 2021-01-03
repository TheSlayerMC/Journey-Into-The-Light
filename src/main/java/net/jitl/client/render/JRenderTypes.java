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
                false,
                RenderType.State.builder()
                        .setTextureState(new TextureState(location, false/*blur*/, false/*mipmap*/))
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                        .setAlphaState(RenderState.DEFAULT_ALPHA)
                        .setShadeModelState(SMOOTH_SHADE)
                        .createCompositeState(false));
    }
}
