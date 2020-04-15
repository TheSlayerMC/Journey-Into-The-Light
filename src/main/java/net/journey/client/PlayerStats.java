package net.journey.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.slayer.api.SlayerAPI;
import net.slayer.api.SlayerAPI.Colour;

public class PlayerStats {


	@SubscribeEvent
	public void tickEvent(RenderTickEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		GuiIngame gig = mc.ingameGUI;
		FontRenderer font = mc.fontRenderer;
		EntityPlayer player = mc.player;
		if(mc.currentScreen == null) {
			if(!mc.gameSettings.showDebugInfo) 
				if(SlayerAPI.BETA) mc.fontRenderer.drawString(Colour.DARK_GREEN + "Journey Into The Light: " + Colour.DARK_RED + SlayerAPI.MOD_VERSION + " PRE RELEASE", 5, 5, 0);

			if(mc.gameSettings.showDebugInfo) {
				String st = I18n.format("journey.time", new Object[0]) + " " + formatTime(getWorldTime(mc));
				font.drawString(st, 2, 200, 0xFFFFFF);
			}
		}
	}

	public static Long getWorldTime(Minecraft mc) {
		return Long.valueOf(mc.world.provider.getWorldTime());
	}

	public static String formatTime(Long time) {
		int hours24 = (int)(time.longValue() / 1000L + 6L) % 24;
		int hours = hours24 % 12;
		int minutes = (int)(time.longValue() / 16.666666F % 60.0F);
		String time1 = String.format("%02d:%02d %s", new Object[] { Integer.valueOf(hours < 1 ? 12 : hours), Integer.valueOf(minutes), hours24 < 12 ? "AM" : "PM" });
		return time1;
	}
}