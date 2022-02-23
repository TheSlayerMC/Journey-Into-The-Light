package net.jitl.client.render;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import ru.timeconqueror.timecore.api.util.client.RenderHelper;

//FIXME fix render types!!!!
public class JRenderTypes extends RenderType {
    public JRenderTypes(String nameIn, VertexFormat formatIn, VertexFormat.Mode drawModeIn, int bufferSizeIn, boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
        super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
    }

    public static RenderType laserBeam(ResourceLocation texture) {
        return RenderType.create(JITL.rl("laser_beam").toString(),
                DefaultVertexFormat.POSITION_TEX,
                VertexFormat.Mode.QUADS,
                256,
                false,
                false,
                CompositeState.builder()
                        .setTextureState(new TextureStateShard(texture, false/*blur*/, false/*mipmap*/))
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                        .setShaderState(ShaderStateShard.RENDERTYPE_LIGHTNING_SHADER) //FIXME: subject to change ~ Dizzle
                        //.setShadeModelState(SMOOTH_SHADE) //FIXME ~ Dizzle
                        .createCompositeState(false));
    }

    public static RenderType fullbrightCutout(ResourceLocation texture) {
        return RenderHelper.rtTextured(JITL.rl("fullbright_cutout"),
                VertexFormat.Mode.QUADS,
                DefaultVertexFormat.POSITION_TEX,
                texture,
                builder -> builder
                        .setShaderState(RenderStateShard.POSITION_TEX_SHADER)
                        .setCullState(RenderStateShard.NO_CULL)); //Still has culling??
    }

    public static RenderType fullbrightEntity(ResourceLocation texture) {
        return RenderHelper.rtTextured(JITL.rl("fullbright_entity_cutout"),
                VertexFormat.Mode.QUADS,
                DefaultVertexFormat.NEW_ENTITY,
                texture,
                builder -> builder
                        .setShaderState(RenderStateShard.NEW_ENTITY_SHADER)
                        .setOverlayState(OVERLAY)
                        .setCullState(RenderStateShard.NO_CULL)
                        .createCompositeState(true));
    }

    public static RenderType transparentCutout(ResourceLocation texture) {
        return RenderHelper.rtTextured(JITL.rl("transparent_cutout"),
                VertexFormat.Mode.QUADS,
                DefaultVertexFormat.POSITION_TEX,
                texture,
                builder -> builder
                        .setTransparencyState(TransparencyStateShard.ADDITIVE_TRANSPARENCY)
                        //.setDiffuseLightingState(DIFFUSE_LIGHTING)
                        .setShaderState(RenderStateShard.POSITION_TEX_SHADER) //FIXME: subject to change ~ Dizzle
                        .setLightmapState(LIGHTMAP)
                        .createCompositeState(true));
    }
}
