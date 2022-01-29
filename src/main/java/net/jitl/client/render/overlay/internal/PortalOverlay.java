package net.jitl.client.render.overlay.internal;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.player.data.Portal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.entity.player.Player;

public class PortalOverlay {

    public static void render(float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        int screenWidth = minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();
        Player player = minecraft.player;
        if (player != null) {
            JPlayer jPlayer = JPlayer.from(player);
            if (jPlayer != null) {
                Portal playerPortalOverlay = jPlayer.portal;
                float timeInPortal = playerPortalOverlay.getOldPortalOverlayTime() * 1.45F + (playerPortalOverlay.getPortalOverlayTime() - playerPortalOverlay.getOldPortalOverlayTime()) * partialTicks;
                if (timeInPortal > 0.0F) {
                    if (timeInPortal < 1.0F) {
                        timeInPortal *= timeInPortal;
                        timeInPortal *= timeInPortal;
                        timeInPortal = timeInPortal * 0.8F + 0.2F;
                    }
                    RenderSystem.disableDepthTest();
                    RenderSystem.depthMask(false);
                    RenderSystem.defaultBlendFunc();
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, timeInPortal);
                    RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
                    RenderSystem.setShader(GameRenderer::getPositionTexShader);
                    TextureAtlasSprite textureatlassprite = minecraft.getBlockRenderer().getBlockModelShaper().getParticleIcon(playerPortalOverlay.getPortalBlockToRender().defaultBlockState());
                    float f = textureatlassprite.getU0();
                    float f1 = textureatlassprite.getV0();
                    float f2 = textureatlassprite.getU1();
                    float f3 = textureatlassprite.getV1();
                    Tesselator tesselator = Tesselator.getInstance();
                    BufferBuilder bufferbuilder = tesselator.getBuilder();
                    bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                    bufferbuilder.vertex(0.0D, screenHeight, -90.0D).uv(f, f3).endVertex();
                    bufferbuilder.vertex(screenWidth, screenHeight, -90.0D).uv(f2, f3).endVertex();
                    bufferbuilder.vertex(screenWidth, 0.0D, -90.0D).uv(f2, f1).endVertex();
                    bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(f, f1).endVertex();
                    tesselator.end();
                    RenderSystem.depthMask(true);
                    RenderSystem.enableDepthTest();
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                }

            }
        }
    }
}
