package net.journey.eventhandler;

import net.journey.JITL;
import net.journey.api.capability.JourneyPlayer;
import net.journey.api.capability.PlayerPortalOverlay;
import net.journey.client.render.gui.GuiPortalOverlay;
import net.journey.common.capability.JCapabilityManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = JITL.MOD_ID)
public class RenderEventHandler {

	@SubscribeEvent
	public static void renderPortalEvent(RenderGameOverlayEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution scaledResolution = new ScaledResolution(mc);
		EntityPlayerSP player = mc.player;

		JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(player);
		PlayerPortalOverlay playerPortalOverlay = journeyPlayer.getPlayerPortalOverlay();

		if (event.getType() == RenderGameOverlayEvent.ElementType.PORTAL) {

			float timeInPortal = playerPortalOverlay.getPortalOverlayTime() * 1.2F + playerPortalOverlay.getOldPortalOverlayTime() - playerPortalOverlay.getPortalOverlayTime();

			if (timeInPortal > 0.0F) {
				GuiPortalOverlay.renderPortalOverlay(timeInPortal, mc, scaledResolution, playerPortalOverlay.getPortalBlockToRender());
			}
		}
	}
}
