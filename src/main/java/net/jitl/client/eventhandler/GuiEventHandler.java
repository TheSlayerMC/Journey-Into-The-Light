package net.jitl.client.eventhandler;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.JITL;
import net.jitl.client.render.gui.button.ToggleMenuButton;
import net.jitl.client.render.gui.menu.JMainMenuGui;
import net.jitl.client.util.RenderUtils;
import net.jitl.common.capability.player.JPlayer;
import net.jitl.config.JClientConfig;
import net.jitl.config.JConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.timeconqueror.timecore.common.capability.CallbackProperty;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class GuiEventHandler {
	public static float maxEssence = 10F;

	public static float essence = 10F;

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void overrideMainMenu(GuiOpenEvent event) {
		if (JConfigs.CLIENT.GUI_CATEGORY.isJITLMenuEnabled()) {
			if (event.getGui() instanceof MainMenuScreen) {
				event.setGui(new JMainMenuGui());
			}
		}
	}

	@SubscribeEvent()
	public static void onGuiInit(GuiScreenEvent.InitGuiEvent.Post event) {
		JClientConfig.GuiCategory guiConfig = JConfigs.CLIENT.GUI_CATEGORY;
		Minecraft minecraft = Minecraft.getInstance();
		int x = event.getGui().width / 1024;

		ToggleMenuButton buttonToggleMenu = new ToggleMenuButton(x, 0, (action) -> {
			guiConfig.setJITLMenu(!guiConfig.isJITLMenuEnabled());
			if (!guiConfig.isJITLMenuEnabled()) {
				minecraft.setScreen(new MainMenuScreen());
			} else {
				minecraft.setScreen(new JMainMenuGui());
			}
		});
		if (event.getGui() instanceof MainMenuScreen) {
			if (guiConfig.isToggleMenuButtonEnabled()) {
				event.addWidget(buttonToggleMenu);
			}
		}
	}

	@SubscribeEvent()
	public static void renderEssenceBar(RenderGameOverlayEvent.Post event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
			Minecraft minecraft = Minecraft.getInstance();
			MatrixStack matrixStack = event.getMatrixStack();
			JPlayer cap = JPlayer.from(minecraft.player);
			if (cap != null) {
				CallbackProperty<Float> essence = cap.essence.get().currentEssence;
				JITL.LOGGER.info("Current essence: " + essence.get());
				RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
				minecraft.getTextureManager().bind(JITL.tl("gui/essence.png").fullLocation());
				RenderUtils.blit(matrixStack, 10, 10, 0, 5, 64, 5, 64, 5);

				int i = (int) essence.get().floatValue();
				RenderUtils.blit(matrixStack, 10, 16, 0, 5, i, 5, 64, 5);
			}
		}
	}
}
