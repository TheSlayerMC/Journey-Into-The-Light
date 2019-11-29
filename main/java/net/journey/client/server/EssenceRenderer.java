package net.journey.client.server;

import java.lang.ref.Reference;

import org.lwjgl.opengl.GL11;

import net.journey.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.slayer.api.SlayerAPI;

public class EssenceRenderer {

	Minecraft mc = Minecraft.getMinecraft();

	public static float percantage = 100;
	public static boolean regen;
	public static final ResourceLocation TEXTURE  = new ResourceLocation(SlayerAPI.MOD_ID, "textures/gui/misc.png"); 

	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent.Text event) {
		onTickRender();
	}

	private void onTickRender() {
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.currentScreen == null) {
			if (!mc.player.capabilities.isCreativeMode) {
				GL11.glPushMatrix();
				GlStateManager.enableBlend();
				GlStateManager.enableAlpha();
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GuiIngame gig = mc.ingameGUI;
				ScaledResolution scaledresolution = new ScaledResolution(mc);
				mc.getTextureManager().bindTexture(TEXTURE);
				int y = scaledresolution.getScaledHeight() - 30, x = 10, x1 = 10, x2 = 10;
				gig.drawTexturedModalRect(x - 10, y + 10, 0, 177, 117, 19);
				gig.drawTexturedModalRect(x - 6, y + 17, 0, 23, 109, 5);
				for (int i = 0; i < (int)percantage; i++) {
					if (!(i >= 10)) {
						x += 11;
						gig.drawTexturedModalRect(x - 17, y + 17, 0, 0, 10, 5);
					}
				}
				GlStateManager.disableAlpha();
				GlStateManager.disableBlend();
				GL11.glPopMatrix();
			}
		}
	}

}