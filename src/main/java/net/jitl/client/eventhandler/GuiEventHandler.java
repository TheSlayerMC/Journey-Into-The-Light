package net.jitl.client.eventhandler;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jitl.client.render.gui.button.ToggleMenuButton;
import net.jitl.client.render.gui.menu.JMainMenuGui;
import net.jitl.client.render.overlay.RenderFrostburnOverlay;
import net.jitl.client.util.RenderUtils;
import net.jitl.common.capability.player.JPlayer;
import net.jitl.common.capability.player.data.Essence;
import net.jitl.common.entity.base.IJourneyBoss;
import net.jitl.common.helper.JBossInfo;
import net.jitl.core.JITL;
import net.jitl.core.config.JClientConfig;
import net.jitl.core.config.JConfigs;
import net.jitl.core.init.JEffects;
import net.jitl.core.util.IEssenceItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.event.ScreenOpenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID, value = Dist.CLIENT)
public class GuiEventHandler {
	private static float transparency;
	private static float burnoutTransparency;

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void overrideMainMenu(ScreenOpenEvent event) {
		if (JConfigs.CLIENT.GUI_CATEGORY.isJITLMenuEnabled()) {
			if (event.getScreen() instanceof TitleScreen) {
				event.setScreen(new JMainMenuGui(false));
			}
		}
	}

	@SubscribeEvent()
	public static void onGuiInit(ScreenEvent.InitScreenEvent.Post event) {
		JClientConfig.GuiCategory guiConfig = JConfigs.CLIENT.GUI_CATEGORY;
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

	@SubscribeEvent()
	public static void renderFrostburnOverlay(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.LAYER) { //FIXME: thank you for deleting the element type, forge! ~ Dizzle
			Minecraft minecraft = Minecraft.getInstance();
			Player player = minecraft.player;
			if (player != null && player.hasEffect(JEffects.FROSTBURN.get())) {
				RenderFrostburnOverlay.render(minecraft);
			}
		}
	}

	@SubscribeEvent()
	public static void renderEssenceBar(RenderGameOverlayEvent.Post event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
			Minecraft minecraft = Minecraft.getInstance();
			Player player = minecraft.player;
			if (player != null && !player.isCreative() && !player.isSpectator()) {
				PoseStack matrixStack = event.getMatrixStack();
				JPlayer cap = JPlayer.from(player);
				if (cap != null) {
					float currentEssence = cap.essence.getCurrentEssence();
					float maxEssence = Essence.getMaxEssence(player);
					float cooldown = cap.essence.getBurnout();

					boolean isEssenceUsed = currentEssence < maxEssence;
					if ((instanceOfEssenceItem(player.getMainHandItem().getItem()) || isEssenceUsed) && transparency <= 1.0) {
						transparency += .02;
					} else if (transparency > 0) {
						transparency -= .02;
					}

					boolean cooldownActive = cooldown > 1.0F;

					if (cooldownActive && burnoutTransparency < 1) {
						burnoutTransparency += .02;
					} else if (burnoutTransparency > 0) {
						burnoutTransparency -= .02;
					}

					if (!minecraft.options.hideGui && transparency > 0) {
						int l = event.getWindow().getGuiScaledHeight() - 32 + 3;
						int w = event.getWindow().getGuiScaledWidth() / 2 - 91;

						RenderSystem.setShader(GameRenderer::getPositionTexShader);
						RenderSystem.setShaderTexture(0, JITL.tl("gui/essence.png").fullLocation());
						RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, transparency);

						RenderUtils.blit(matrixStack, w, l, 0, 5, 81, 5, 81, 15);

						if (cooldownActive) {
							float sin = (float) Math.sin((float) player.tickCount / 5F) / 2F + 0.5F; //sin function ranging from 0 to 1
							float cooldownFade = Math.min(cooldown, 10) / 10; //when the cooldown starts getting close to zero, it fades out

							RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, sin * cooldownFade); //TODO: test me ~ Dizzle
							RenderUtils.blit(matrixStack, w, l, 0, 0, 81, 5, 81, 15);
						} else {
							int i = (int) ((currentEssence / maxEssence) * 81);
							RenderUtils.blit(matrixStack, w, l, 0, 0, i, 5, 81, 15);
						}


						if (burnoutTransparency > 0) {
							RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, burnoutTransparency);
							RenderUtils.blit(matrixStack, w, l, 0, 10, 81, 5, 81, 15);
						}
					}
				}
			}
		}
	}

	public static boolean instanceOfEssenceItem(Item isEssence) {
		return isEssence instanceof IEssenceItem;
	}
}
