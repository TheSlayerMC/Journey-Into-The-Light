package net.journey.client;

import ibxm.Player;
import net.journey.client.server.DarkEnergyBar;
import net.journey.client.server.EssenceBar;
import net.journey.client.server.PowerBar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

import org.lwjgl.opengl.GL11;

public class BarTickHandler {
	
	private EntityPlayer player;
	private int ticks = 10;

	public static int darkAmount, essenceAmount, powerAmount;
	public static boolean regenDark, regenEssence, regenPower;

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		/*if(event.getgetEntity()() instanceof EntityPlayer && DarkEnergyBar.getProperties((EntityPlayer)event.getEntity()) == null)
			DarkEnergyBar.addProperties((EntityPlayer)event.getEntity());
		if(event.getEntity() instanceof EntityPlayer && event.getEntity().getExtendedProperties(DarkEnergyBar.PROP) == null)
			event.getEntity().registerExtendedProperties(DarkEnergyBar.PROP, new DarkEnergyBar((EntityPlayer)event.getEntity()));
		
		if(event.getEntity() instanceof EntityPlayer && EssenceBar.getProperties((EntityPlayer)event.getEntity()) == null)
			EssenceBar.addProperties((EntityPlayer)event.getEntity());
		if(event.getEntity() instanceof EntityPlayer && event.getEntity().getExtendedProperties(EssenceBar.PROP) == null)
			event.getEntity().registerExtendedProperties(EssenceBar.PROP, new EssenceBar((EntityPlayer)event.getEntity()));
		
		if(event.getEntity() instanceof EntityPlayer && PowerBar.getProperties((EntityPlayer) event.getEntity()) == null)
			PowerBar.addProperties((EntityPlayer)event.getEntity());
		if(event.getEntity() instanceof EntityPlayer && event.getEntity().getExtendedProperties(PowerBar.PROP) == null)
			event.getEntity().registerExtendedProperties(PowerBar.PROP, new PowerBar((EntityPlayer)event.getEntity()));*/
	}

	@SubscribeEvent
	public void onTick(PlayerTickEvent event) {
		//if(event.phase == Phase.END) tickEnd(event.player);
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderEvent(RenderTickEvent event) {
		onTickRender(Minecraft.getMinecraft().player);
	}

	@SideOnly(Side.CLIENT)
	private void onTickRender(EntityPlayer player) {
		/*Minecraft mc = Minecraft.getMinecraft();
		if(mc.currentScreen == null) {
			if(!player.capabilities.isCreativeMode) {
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
				for(int i = 0; i < essenceAmount; i++) {
					if(!(i >= 10)) {
						x += 11;
						gig.drawTexturedModalRect(x - 17, y - 13, 0, 0, 10, 5);
					}
				}
				y += 15;
				gig.drawTexturedModalRect(x1 - 6, y - 13, 0, 36, 109, 5);
				for(int i = 0; i < darkAmount; i++) {
					x1 += 11;
					gig.drawTexturedModalRect(x1 - 17, y - 13, 0, 5, 10, 5);
				}
				gig.drawTexturedModalRect(x2 - 6, y + 2, 0, 49, 109, 5);
				for(int i = 0; i < powerAmount; i++) {
					x2 += 11;
					gig.drawTexturedModalRect(x2 - 17, y + 2, 0, 10, 10, 5);
				}
				GlStateManager.disableAlpha();
				GlStateManager.disableBlend();
				GL11.glPopMatrix();
			}
		}*/
	}

	private void tickEnd(EntityPlayer player) {
		if(ticks-- <= 0) ticks = 10;
		if(ticks >= 10) {
			DarkEnergyBar.getProperties(player).updateAllBars();
			EssenceBar.getProperties(player).updateAllBars();
			PowerBar.getProperties(player).updateAllBars();
		}
		DarkEnergyBar.getProperties(player).mainUpdate();
		EssenceBar.getProperties(player).mainUpdate();
		PowerBar.getProperties(player).mainUpdate();
	}
}