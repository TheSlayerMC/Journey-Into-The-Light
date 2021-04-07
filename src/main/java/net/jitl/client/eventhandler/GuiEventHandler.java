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
import net.jitl.init.JItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.item.Item;
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

	private static float transparency;

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
				boolean isEssenceUsed = cap.essence.get().getChanged();

				if (instanceOfEssenceItem(minecraft.player.getMainHandItem().getItem()) || isEssenceUsed && transparency < 1.0) {
					transparency += .02;
				} else if (transparency > 0) {
					transparency -= .02;
				}
				if (!minecraft.options.hideGui) {
					int l = event.getWindow().getGuiScaledHeight() - 32 + 3;
					int w = event.getWindow().getGuiScaledWidth() / 2 - 91;

					RenderSystem.color4f(1.0F, 1.0F, 1.0F, transparency);
					//JITL.LOGGER.info(transparency);
					minecraft.getTextureManager().bind(JITL.tl("gui/essence.png").fullLocation());
					RenderUtils.blit(matrixStack, w, l, 0, 5, 71, 5, 71, 10);

					int i = (int) (essence.get() * 7.1);
					RenderUtils.blit(matrixStack, w, l, 0, 0, i, 5, 71, 10);
				}
			}
		}
	}

	public static boolean instanceOfEssenceItem(Item isEssence) {
		return isEssence == JItems.STAFF_OF_CONJURING;
	}
}
