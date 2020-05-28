package net.journey.client.render;

import net.journey.JITL;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber()
public class RenderPortalOverlay {

	@SubscribeEvent
	public static void portalOverlayEvent(RenderGameOverlayEvent.Post event) {
		Minecraft minecraft = Minecraft.getMinecraft();
	    EntityPlayer player = minecraft.player;
		if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
			if (player.timeUntilPortal > 0) {
				renderPortalOverlay(player.timeUntilPortal, event.getResolution());
			}
		}
	}
	
	public static void renderPortalOverlay(float timeInPortal, ScaledResolution scaledRes) {
		if (timeInPortal < 1.0F) {
			timeInPortal = timeInPortal * timeInPortal;
			timeInPortal = timeInPortal * timeInPortal;
			timeInPortal = timeInPortal * 0.8F + 0.2F;
		}
		GlStateManager.disableAlpha();
		GlStateManager.disableDepth();
		GlStateManager.depthMask(false);
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,GlStateManager.DestFactor.ZERO);
		GlStateManager.color(1.0F, 1.0F, 1.0F, timeInPortal);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		TextureAtlasSprite textureatlassprite = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(JourneyBlocks.boilPortal.getDefaultState());
		float f = textureatlassprite.getMinU();
		float f1 = textureatlassprite.getMinV();
		float f2 = textureatlassprite.getMaxU();
		float f3 = textureatlassprite.getMaxV();
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexbuffer.pos(0.0D, (double) scaledRes.getScaledHeight(), -90.0D).tex((double) f, (double) f3).endVertex();
		vertexbuffer.pos((double) scaledRes.getScaledWidth(), (double) scaledRes.getScaledHeight(), -90.0D).tex((double) f2, (double) f3).endVertex();
		vertexbuffer.pos((double) scaledRes.getScaledWidth(), 0.0D, -90.0D).tex((double) f2, (double) f1).endVertex();
		vertexbuffer.pos(0.0D, 0.0D, -90.0D).tex((double) f, (double) f1).endVertex();
		tessellator.draw();
		GlStateManager.depthMask(true);
		GlStateManager.enableDepth();
		GlStateManager.enableAlpha();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
