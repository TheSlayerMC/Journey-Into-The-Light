package net.journey.client.server;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class BarTickHandler {

	public static final ResourceLocation TEXTURE  = new ResourceLocation(SlayerAPI.MOD_ID, "textures/gui/misc.png"); 
	public static final ResourceLocation ESSENCE_CAP  = new ResourceLocation(SlayerAPI.MOD_ID, "essence_mana"); 

	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
		if(!(event.getObject() instanceof EntityPlayer))
			return;
		event.addCapability(ESSENCE_CAP, new EssenceProvider());
	}
	
	@SubscribeEvent
	public void onTick(PlayerTickEvent event) {
		if(event.phase == Phase.END) {
			if(event.player != null) {
				IEssence mana = event.player.getCapability(EssenceProvider.ESSENCE_CAP, null);
				mana.update();
			}
		}
	}

	@SubscribeEvent
	public void renderEvent(RenderTickEvent event) {
		onTickRender(Minecraft.getMinecraft().player);
	}

	private void onTickRender(EntityPlayer player) {
		Minecraft mc = Minecraft.getMinecraft();
		if (player != null) {
			if (mc.currentScreen == null) {
				if (!player.capabilities.isCreativeMode) {
					IEssence mana = player.getCapability(EssenceProvider.ESSENCE_CAP, null);
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
					for (int i = 0; i < mana.getEssenceValue(); i++) {
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
}