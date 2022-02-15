package net.jitl.client.eventhandler;

import net.jitl.client.render.gui.button.ToggleMenuButton;
import net.jitl.client.render.gui.menu.JMainMenuGui;
import net.jitl.common.entity.base.IJourneyBoss;
import net.jitl.common.helper.JBossInfo;
import net.jitl.core.JITL;
import net.jitl.core.config.JClientConfig;
import net.jitl.core.config.JConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.event.ScreenOpenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class GuiEventHandler {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void overrideMainMenu(ScreenOpenEvent event) {
		if (JConfigs.CLIENT.guiCategory.isJITLMenuEnabled()) {
			if (event.getScreen() instanceof TitleScreen) {
				event.setScreen(new JMainMenuGui(false));
			}
		}
	}

	@SubscribeEvent()
	public static void onGuiInit(ScreenEvent.InitScreenEvent.Post event) {
		JClientConfig.GuiCategory guiConfig = JConfigs.CLIENT.guiCategory;
		Minecraft minecraft = Minecraft.getInstance();
		int x = event.getScreen().width / 1024;

		ToggleMenuButton buttonToggleMenu = new ToggleMenuButton(x, 0, (action) -> {
			guiConfig.setJITLMenu(!guiConfig.isJITLMenuEnabled());
			if (!guiConfig.isJITLMenuEnabled()) {
				minecraft.setScreen(new TitleScreen(false));
			} else {
				minecraft.setScreen(new JMainMenuGui(false));
			}
		});
		if (event.getScreen() instanceof TitleScreen) {
			if (guiConfig.isToggleMenuButtonEnabled()) {
				event.addListener(buttonToggleMenu);
			}
		}
	}

	@SubscribeEvent
	public static void renderBossBars(RenderGameOverlayEvent.BossInfo event) {
		if (!event.isCanceled()) {
			IJourneyBoss boss = JBossInfo.map.get(event.getBossEvent().getId());
			if (boss != null) {
				event.setCanceled(true);
				boss.getBossBar().render(event);
			}
		}
	}
}
