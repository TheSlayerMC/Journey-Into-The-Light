package net.journey.client.render;

import net.journey.JITL;
import net.journey.api.capability.EssenceStorage;
import net.journey.api.capability.JourneyPlayer;
import net.journey.api.item.IUsesEssence;
import net.journey.common.capability.JCapabilityManager;
import net.journey.init.items.JourneyWeapons;
import net.journey.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class RenderEssenceBar {
	private float transparency;
	public static final ResourceLocation TEXTURE = JITL.rl("textures/gui/misc.png");

	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent.Post event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
			if (Config.showManaBar) {
				Minecraft mc = Minecraft.getMinecraft();
				renderManaBar(mc, mc.player);
			}
		}
	}

	private void renderManaBar(Minecraft mc, EntityPlayer player) {
		ItemStack heldItemMainhand = player.getHeldItemMainhand();
		JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(player);
		EssenceStorage mana = journeyPlayer.getEssenceStorage();

		if (mc.currentScreen == null && instanceOfEssenceItem(heldItemMainhand.getItem()) || mana.isBeingUsed() && transparency < 1.0) {
			transparency += .02;
		} else if (transparency > 0) {
			transparency -= .02;
		}

		journeyPlayer.sendUpdates();

		if (transparency > 0) {

			if (!mana.isFull() && !instanceOfEssenceItem(heldItemMainhand.getItem())) {
				transparency = 0.35F;
			}

			if (!mc.gameSettings.hideGUI) {
				GlStateManager.pushMatrix();
				GlStateManager.enableBlend();
				GlStateManager.enableAlpha();

				GL11.glColor4f(1.0F, 1.0F, 1.0F, transparency);
				GuiIngame gig = mc.ingameGUI;
				ScaledResolution scaledresolution = new ScaledResolution(mc);
				mc.getTextureManager().bindTexture(TEXTURE);
				int y = scaledresolution.getScaledHeight() - 30;
				int x = 10;
				gig.drawTexturedModalRect(x - 10, y + 10, 0, 177, 117, 19);
				gig.drawTexturedModalRect(x - 6, y + 17, 0, 23, 109, 5);
				for (int i = 0; i < mana.getEssenceValue(); i++) {
					if (!(i >= mana.getMaxValue())) {
						x += 11;
						gig.drawTexturedModalRect(x - 17, y + 17, 0, 0, 10, 5);
					}
				}

				GlStateManager.disableAlpha();
				GlStateManager.disableBlend();
				GlStateManager.popMatrix();
			}
		}
	}
	
	public boolean instanceOfEssenceItem(Item isEssence) {
		return isEssence instanceof IUsesEssence ||
				isEssence == JourneyWeapons.darkEnforcer ||
				isEssence == JourneyWeapons.depthsBow ||
				isEssence == JourneyWeapons.coreExpender ||
				isEssence == JourneyWeapons.terralightBow ||
				isEssence == JourneyWeapons.essenceShuriken;
	}
}
