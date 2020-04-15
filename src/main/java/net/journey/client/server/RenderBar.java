package net.journey.client.server;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.slayer.api.SlayerAPI;

public class RenderBar {

	
	@SubscribeEvent
	public void onTick(PlayerTickEvent event) {
		if(event.phase == Phase.END) {
			if(event.player != null) {
				IEssence mana = event.player.getCapability(EssenceProvider.ESSENCE_CAP, null);
				mana.update();
			}
		}
	}
}