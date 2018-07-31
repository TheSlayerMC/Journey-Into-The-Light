package net.journey.client;

import org.lwjgl.opengl.GL11;

import net.journey.client.server.EssenceProvider;
import net.journey.client.server.IEssence;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class BarTickHandler {
	
	private int ticks = 10;

	@SubscribeEvent
	public void onTick(PlayerTickEvent event) {
		if(event.phase == Phase.END) tickEnd(event.player);
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderEvent(RenderTickEvent event) {
		onTickRender(Minecraft.getMinecraft().player);
	}

	@SideOnly(Side.CLIENT)
	private void onTickRender(EntityPlayer player) {
		Minecraft mc = Minecraft.getMinecraft();
		if(mc.currentScreen == null) {
			if(!player.capabilities.isCreativeMode) {
				IEssence mana = player.getCapability(EssenceProvider.ESSENCE_CAP, null);
				GL11.glPushMatrix();
				GlStateManager.enableBlend();
				GlStateManager.enableAlpha();
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GuiIngame gig = mc.ingameGUI;
				ScaledResolution scaledresolution = new ScaledResolution(mc);
				mc.getTextureManager().bindTexture(new ResourceLocation(SlayerAPI.MOD_ID, "textures/gui/misc.png"));
				//int sw = scaledresolution.getScaledWidth(), sh = scaledresolution.getScaledHeight();
				int y = scaledresolution.getScaledHeight() - 30, x = 10, x1 = 10, x2 = 10;
				gig.drawTexturedModalRect(x - 10, y + 10, 0, 177, 117, 19);
				gig.drawTexturedModalRect(x - 10, y - 5, 0, 177, 117, 19);
				gig.drawTexturedModalRect(x - 10, y - 20, 0, 177, 117, 19);

				gig.drawTexturedModalRect(x - 6, y - 13, 0, 23, 109, 5);
				for(int i = 0; i < mana.getEssenceValue(); i++) {
					if(!(i >= 10)) {
						x += 11;
						gig.drawTexturedModalRect(x - 17, y - 13, 0, 0, 10, 5);
					}
				}
				GlStateManager.disableAlpha();
				GlStateManager.disableBlend();
				GL11.glPopMatrix();
			}
		}
	}

	private void tickEnd(EntityPlayer player) {
		IEssence mana = player.getCapability(EssenceProvider.ESSENCE_CAP, null);
		if(ticks-- <= 0) ticks = 10;
		
		if(ticks >= 10) 
			mana.regen();
		
		mana.update();
	}
}