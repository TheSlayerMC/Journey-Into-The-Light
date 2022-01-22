package net.jitl.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlurredCubeMap extends CubeMap {
    private final ResourceLocation resourceLocation;

    public BlurredCubeMap(ResourceLocation baseImageLocation_) {
        super(baseImageLocation_);
        this.resourceLocation = baseImageLocation_;
    }

    @Override
    public void render(Minecraft mc, float pitch_, float yaw_, float alpha_) {
        super.render(mc, pitch_, yaw_, alpha_);
        //TODO: FIXME blur
        /*RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, JITL.rl("textures/gui/title/background/panorama_0.png"));
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.texParameter(3553, 10241, 9729);
        RenderSystem.texParameter(3553, 10240, 9729);
        //GlStateManager._glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO);
        RenderSystem.colorMask(true, true, true, false);
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        for (int j = 0; j < 3; ++j) {
            float f = 1.0F / (float) (j + 1);
            assert mc.screen != null;
            int k = mc.screen.width;
            int l = mc.screen.height;
            float f1 = (float) (j - 1) / 256.0F;
            bufferbuilder.vertex(k, l, mc.screen.getBlitOffset()).uv(0.0F + f1, (float) 1.0D)
                    .color(1.0F, 1.0F, 1.0F, f).endVertex();
            bufferbuilder.vertex(k, 0.0D, mc.screen.getBlitOffset()).uv(1.0F + f1, (float) 1.0D)
                    .color(1.0F, 1.0F, 1.0F, f).endVertex();
            bufferbuilder.vertex(0.0D, 0.0D, mc.screen.getBlitOffset()).uv(1.0F + f1, (float) 0.0D)
                    .color(1.0F, 1.0F, 1.0F, f).endVertex();
            bufferbuilder.vertex(0.0D, l, mc.screen.getBlitOffset()).uv(0.0F + f1, (float) 0.0D)
                    .color(1.0F, 1.0F, 1.0F, f).endVertex();
        }
        tessellator.end();
        RenderSystem.colorMask(true, true, true, true);*/

        //tessellator.draw();
    }
}