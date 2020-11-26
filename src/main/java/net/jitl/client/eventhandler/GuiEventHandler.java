package net.jitl.client.eventhandler;

import net.jitl.JITL;
import net.jitl.client.render.gui.button.JImageButton;
import net.jitl.client.render.gui.button.ToggleMenuButton;
import net.jitl.client.render.gui.menu.JMainMenuGui;
import net.jitl.config.JClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class GuiEventHandler {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void overrideMainMenu(GuiOpenEvent event) {
		JClientConfig clientConfig = JClientConfig.INSTANCE;
		if (clientConfig.GUI_CATEGORY.isJITLMenuEnabled()) {
			if (event.getGui() instanceof MainMenuScreen) {
				event.setGui(new JMainMenuGui());
			}
		}
	}

	@SubscribeEvent()
	public static void onGuiInit(GuiScreenEvent.InitGuiEvent.Post event) {
		JClientConfig clientConfig = JClientConfig.INSTANCE;
		JClientConfig.GuiCategory guiConfig = clientConfig.GUI_CATEGORY;
		Minecraft minecraft = Minecraft.getInstance();
		int toggleButtonX = event.getGui().width / 1024;

		ToggleMenuButton buttonToggleMenu = new ToggleMenuButton(toggleButtonX, 0, (action) -> {
			guiConfig.setJITLMenu(!guiConfig.isJITLMenuEnabled());
			if (!guiConfig.isJITLMenuEnabled()) {
				minecraft.setScreen(new MainMenuScreen());
			} else {
				minecraft.setScreen(new JMainMenuGui());
			}
		});
		if (event.getGui() instanceof MainMenuScreen) {
			event.addWidget(buttonToggleMenu);
		}

		//TODO: fix position with crafting book GUI
		int trinketButtonX = event.getGui().width / 2 - 24;
		int trinketButtonY = event.getGui().height / 2 - 16;
		JImageButton trinketButton = new JImageButton(trinketButtonX, trinketButtonY, 8, 8, 0, 0, 8, JITL.rl("textures/gui/trinket_icon.png"), 8, 16, (action) -> {
			//add button functionality here
			//TODO: create special inventory
		});
		if (event.getGui() instanceof InventoryScreen) {
			event.addWidget(trinketButton);
		}
	}
}
