package net.journey.eventhandler;

import net.journey.JITL;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = JITL.MOD_ID)
public class RenderEventHandler {

	@SubscribeEvent()
	public static void renderPortalEvent(RenderGameOverlayEvent.Pre event) {
		Minecraft mc = Minecraft.getMinecraft();

		JITL.LOGGER.debug("Portal Render L27");

		if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {

			JITL.LOGGER.debug("Portal Render L31");

			ScaledResolution scaledRes = new ScaledResolution(mc);
			EntityPlayerSP player = mc.player;

			float partialTicks = event.getPartialTicks();
			float timeInPortal = player.prevTimeInPortal + player.timeInPortal - player.prevTimeInPortal * partialTicks;

			if (timeInPortal > 0.0F) {

				JITL.LOGGER.debug("Portal Render L40");

				if (timeInPortal > 0.0F) {

					JITL.LOGGER.debug("Portal Render L42");

					if (timeInPortal < 1.0F) {
						timeInPortal *= timeInPortal;
						timeInPortal *= timeInPortal;
						timeInPortal = timeInPortal * 0.8F + 0.2F;
					}

					JITL.LOGGER.debug("Portal Render L48");

					GlStateManager.disableAlpha();
					GlStateManager.disableDepth();
					GlStateManager.depthMask(false);
					GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
					GlStateManager.color(1.0F, 1.0F, 1.0F, timeInPortal);
					mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
					TextureAtlasSprite textureatlassprite = mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(Blocks.PORTAL.getDefaultState());
					float f = textureatlassprite.getMinU();
					float f1 = textureatlassprite.getMinV();
					float f2 = textureatlassprite.getMaxU();
					float f3 = textureatlassprite.getMaxV();
					Tessellator tessellator = Tessellator.getInstance();
					BufferBuilder bufferbuilder = tessellator.getBuffer();
					bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
					bufferbuilder.pos(0.0D, scaledRes.getScaledHeight(), -90.0D).tex(f, f3).endVertex();
					bufferbuilder.pos(scaledRes.getScaledWidth(), scaledRes.getScaledHeight(), -90.0D).tex(f2, f3).endVertex();
					bufferbuilder.pos(scaledRes.getScaledWidth(), 0.0D, -90.0D).tex(f2, f1).endVertex();
					bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(f, f1).endVertex();
					tessellator.draw();
					GlStateManager.depthMask(true);
					GlStateManager.enableDepth();
					GlStateManager.enableAlpha();
					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				}
			}
		}
	}
}
