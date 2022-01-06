package net.jitl.client.render.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.resources.ResourceLocation;

public class RenderFrostburnOverlay extends GuiComponent {
    protected static final ResourceLocation FROSTBURN_LOCATION = JITL.rl("textures/gui/overlay/frostburn.png");

    public static void render(Minecraft minecraft) {
        int screenWidth = minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableAlphaTest();
        minecraft.getTextureManager().bind(FROSTBURN_LOCATION);
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(0.0D, screenHeight, -90.0D).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.vertex(screenWidth, screenHeight, -90.0D).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(screenWidth, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
        tessellator.end();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableAlphaTest();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        RenderSystem.enableAlphaTest();
    }
}