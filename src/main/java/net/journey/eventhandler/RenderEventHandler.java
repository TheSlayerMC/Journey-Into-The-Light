package net.journey.eventhandler;

import net.journey.JITL;
import net.journey.api.capability.JourneyPlayer;
import net.journey.client.render.gui.GuiPortalOverlay;
import net.journey.common.capability.JCapabilityManager;
import net.journey.common.capability.JourneyPlayerImpl;
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

		if (event.getType() == RenderGameOverlayEvent.ElementType.PORTAL) {

			float timeInPortal = ((JourneyPlayerImpl) journeyPlayer).portalOverlayTime * 1.2F + (((JourneyPlayerImpl) journeyPlayer).oldPortalOverlayTime - ((JourneyPlayerImpl) journeyPlayer).portalOverlayTime);

			//JITL.LOGGER.info("RenderEventHandler " + timeInPortal);

			if (timeInPortal > 0.0F) {
				GuiPortalOverlay.renderPortalOverlay(timeInPortal, mc, scaledResolution, ((JourneyPlayerImpl) journeyPlayer).portalBlockToRender);
			}
		}
	}
}
