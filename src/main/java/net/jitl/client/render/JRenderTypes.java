package net.jitl.client.render;

import net.jitl.JITL;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.timeconqueror.timecore.api.util.client.GLDrawMode;
import ru.timeconqueror.timecore.api.util.client.RenderHelper;

import net.minecraft.client.renderer.RenderStateShard.TextureStateShard;
import net.minecraft.client.renderer.RenderStateShard.TransparencyStateShard;

public class JRenderTypes extends RenderType {
    public JRenderTypes(String nameIn, VertexFormat formatIn, int drawModeIn, int bufferSizeIn, boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
        super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
    }

    public static RenderType laserBeam(ResourceLocation texture) {
        return RenderType.create(JITL.rl("laser_beam").toString(),
                DefaultVertexFormat.POSITION_TEX,
                GL11.GL_QUADS,
                256,
                false,
                false,
                CompositeState.builder()
                        .setTextureState(new TextureStateShard(texture, false/*blur*/, false/*mipmap*/))
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                        .setAlphaState(RenderStateShard.DEFAULT_ALPHA)
                        .setShadeModelState(SMOOTH_SHADE)
                        .createCompositeState(false));
    }

    public static RenderType fullbrightCutout(ResourceLocation texture) {
        return RenderHelper.rtTextured(JITL.rl("fullbright_cutout"),
                GLDrawMode.QUADS,
                DefaultVertexFormat.POSITION_TEX,
                texture,
                builder -> builder
                        .setShadeModelState(SMOOTH_SHADE)
                        .setAlphaState(MIDWAY_ALPHA));
    }

    public static RenderType transparentCutout(ResourceLocation texture) {
        return RenderHelper.rtTextured(JITL.rl("transparent_cutout"),
                GLDrawMode.QUADS,
                DefaultVertexFormat.POSITION_TEX,
                texture,
                builder -> builder
                        .setTransparencyState(TransparencyStateShard.ADDITIVE_TRANSPARENCY)
                        .setDiffuseLightingState(DIFFUSE_LIGHTING)
                        .setAlphaState(MIDWAY_ALPHA)
                        .setLightmapState(LIGHTMAP)
                        .createCompositeState(true));
    }
}
