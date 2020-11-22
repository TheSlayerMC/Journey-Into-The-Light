package net.jitl.client.eventhandler;

import net.jitl.JITL;
import net.jitl.client.render.gui.menu.JMainMenuGui;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class ReplaceMainMenuEventHandler {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void overrideMainMenu(GuiOpenEvent event) {
		if (event.getGui() instanceof MainMenuScreen) {
			event.setGui(new JMainMenuGui());
		}
	}
}
