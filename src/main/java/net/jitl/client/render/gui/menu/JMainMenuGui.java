package net.jitl.client.render.gui.menu;

import com.google.common.util.concurrent.Runnables;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.jitl.JITL;
import net.jitl.client.render.gui.button.JButton;
import net.jitl.client.render.gui.button.JImageButton;
import net.minecraft.client.gui.AccessibilityScreen;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.toasts.SystemToast;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.RenderSkybox;
import net.minecraft.client.renderer.RenderSkyboxCube;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.realms.RealmsBridgeScreen;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
import net.minecraft.world.storage.SaveFormat;
import net.minecraft.world.storage.WorldSummary;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@OnlyIn(Dist.CLIENT)
public class JMainMenuGui extends MainMenuScreen {

	private static final Logger LOGGER = LogManager.getLogger();
	public static final RenderSkyboxCube CUBE_MAP = new RenderSkyboxCube(JITL.rl("textures/gui/title/background/panorama"));
	private static final ResourceLocation PANORAMA_OVERLAY = new ResourceLocation("textures/gui/title/background/panorama_overlay.png");
	private static final ResourceLocation ACCESSIBILITY_TEXTURE = new ResourceLocation("textures/gui/accessibility.png");
	private static final ResourceLocation LANGUAGE_TEXTURE = JITL.rl("textures/gui/title/language_button.png");
	private final boolean minceraftEasterEgg;
	@Nullable
	private String splash;
	private Button resetDemoButton;
	private static final ResourceLocation MINECRAFT_LOGO = JITL.rl("textures/gui/title/jitl.png");
	private static final ResourceLocation MINECRAFT_EDITION = JITL.rl("textures/gui/title/edition.png");
	/**
	 * Has the check for a realms notification screen been performed?
	 */
	private boolean realmsNotificationsInitialized;
	/**
	 * A screen generated by realms for notifications; drawn in adition to the main menu (buttons and such from both are
	 * drawn at the same time). May be null.
	 */
	private Screen realmsNotificationsScreen;
	private int copyrightWidth;
	private int copyrightX;
	private final RenderSkybox panorama = new RenderSkybox(CUBE_MAP);
	private final boolean fading;
	private long fadeInStart;
	private net.minecraftforge.client.gui.NotificationModUpdateScreen modUpdateNotification;

	public JMainMenuGui() {
		this(false);
	}

	public JMainMenuGui(boolean fadeIn) {
		super(true);
		this.fading = fadeIn;
		this.minceraftEasterEgg = (double) (new Random()).nextFloat() < 1.0E-4D;
	}

	/**
	 * Is there currently a realms notification screen, and are realms notifications enabled?
	 */
	private boolean realmsNotificationsEnabled() {
		return this.minecraft.options.realmsNotifications && this.realmsNotificationsScreen != null;
	}

	public void tick() {
		if (this.realmsNotificationsEnabled()) {
			this.realmsNotificationsScreen.tick();
		}

	}

	public static CompletableFuture<Void> preloadResources(TextureManager texMngr, Executor backgroundExecutor) {
		return CompletableFuture.allOf(texMngr.preload(MINECRAFT_LOGO, backgroundExecutor), texMngr.preload(MINECRAFT_EDITION, backgroundExecutor), texMngr.preload(PANORAMA_OVERLAY, backgroundExecutor), CUBE_MAP.preload(texMngr, backgroundExecutor));
	}

	public boolean isPauseScreen() {
		return false;
	}

	public boolean shouldCloseOnEsc() {
		return false;
	}

	protected void init() {
		if (this.splash == null) {
			this.splash = this.minecraft.getSplashManager().getSplash();
		}

		this.copyrightWidth = this.font.width("Copyright Mojang AB. Do not distribute!");
		this.copyrightX = this.width - this.copyrightWidth - 2;
		int j = this.height / 4 + 48;
		JButton modButton = null;
		if (this.minecraft.isDemo()) {
			this.createDemoMenuOptions(j, 24);
		} else {
			this.createNormalMenuOptions(j);
			modButton = this.addButton(new JButton(this.width / 2 - 206, 30 + j - 15, 200, 20, new TranslationTextComponent("fml.menu.mods"), button -> {
				this.minecraft.setScreen(new net.minecraftforge.fml.client.gui.screen.ModListScreen(this));
			}, false));
		}
		modUpdateNotification = net.minecraftforge.client.gui.NotificationModUpdateScreen.init(this, modButton);

		this.addButton(new JImageButton(this.width / 2 - 206, j + 75, 20, 20, 0, 0, 20, LANGUAGE_TEXTURE, 20, 40, (button9_) -> {
			this.minecraft.setScreen(new LanguageScreen(this, this.minecraft.options, this.minecraft.getLanguageManager()));
		}, new TranslationTextComponent("narrator.button.language")));
		this.addButton(new JButton(this.width / 2 - 206, j + 72 + 12 - 39, 200, 20, new TranslationTextComponent("menu.options"), (button8_) -> {
			this.minecraft.setScreen(new OptionsScreen(this, this.minecraft.options));
		}, false));
		this.addButton(new JButton(this.width / 2 + 5, j + 72 + 12 - 39, 200, 20, new TranslationTextComponent("menu.quit"), (button7_) -> {
			this.minecraft.stop();
		}, true));
		this.addButton(new JImageButton(this.width / 2 + 185, j + 75, 20, 20, 0, 0, 20, ACCESSIBILITY_TEXTURE, 32, 64, (button6_) -> {
			this.minecraft.setScreen(new AccessibilityScreen(this, this.minecraft.options));
		}, new TranslationTextComponent("narrator.button.accessibility")));
		this.minecraft.setConnectedToRealms(false);
		if (this.minecraft.options.realmsNotifications && !this.realmsNotificationsInitialized) {
			RealmsBridgeScreen realmsbridgescreen = new RealmsBridgeScreen();
			this.realmsNotificationsScreen = realmsbridgescreen.getNotificationScreen(this);
			this.realmsNotificationsInitialized = true;
		}

		if (this.realmsNotificationsEnabled()) {
			this.realmsNotificationsScreen.init(this.minecraft, this.width, this.height);
		}

	}

	/**
	 * Adds Singleplayer and Multiplayer buttons on Main Menu for players who have bought the game.
	 */
	private void createNormalMenuOptions(int yIn) {
		this.addButton(new JButton(this.width / 2 - 206, yIn - 15, 200, 20, new TranslationTextComponent("menu.singleplayer"), (button5_) -> {
			assert this.minecraft != null;
			this.minecraft.setScreen(new WorldSelectionScreen(this));
		}, false));
		assert this.minecraft != null;
		boolean flag = this.minecraft.allowsMultiplayer();
		Button.ITooltip button$itooltip = flag ? Button.NO_TOOLTIP : (button1_, matrixStack1_, int_, int1_) -> {
			if (!button1_.active) {
				this.renderTooltip(matrixStack1_, this.minecraft.font.split(new TranslationTextComponent("title.multiplayer.disabled"), Math.max(this.width / 2 - 43, 170)), int_, int1_);
			}

		};
		(this.addButton(new JButton(this.width / 2 + 5, yIn - 15, 200, 20, new TranslationTextComponent("menu.multiplayer"), (button4_) -> {
			Screen screen = this.minecraft.options.skipMultiplayerWarning ? new MultiplayerScreen(this) : new MultiplayerWarningScreen(this);
			this.minecraft.setScreen(screen);
		}, button$itooltip, true))).active = flag;
		(this.addButton(new JButton(this.width / 2 + 5, yIn + 15, 200, 20, new TranslationTextComponent("menu.online"), (button3_) -> {
			this.realmsButtonClicked();
		}, button$itooltip, true))).active = flag;
	}

	/**
	 * Adds Demo buttons on Main Menu for players who are playing Demo.
	 */
	private void createDemoMenuOptions(int yIn, int rowHeightIn) {
		boolean flag = this.checkDemoWorldPresence();
		this.addButton(new Button(this.width / 2 - 100, yIn, 200, 20, new TranslationTextComponent("menu.playdemo"), (button2_) -> {
			if (flag) {
				this.minecraft.loadLevel("Demo_World");
			} else {
				DynamicRegistries.Impl dynamicregistries$impl = DynamicRegistries.builtin();
				this.minecraft.createLevel("Demo_World", MinecraftServer.DEMO_SETTINGS, dynamicregistries$impl, DimensionGeneratorSettings.demoSettings(dynamicregistries$impl));
			}

		}));
		this.resetDemoButton = this.addButton(new Button(this.width / 2 - 100, yIn + rowHeightIn * 1, 200, 20, new TranslationTextComponent("menu.resetdemo"), (button_) -> {
			SaveFormat saveformat = this.minecraft.getLevelSource();

			try (SaveFormat.LevelSave saveformat$levelsave = saveformat.createAccess("Demo_World")) {
				WorldSummary worldsummary = saveformat$levelsave.getSummary();
				if (worldsummary != null) {
					this.minecraft.setScreen(new ConfirmScreen(this::confirmDemo, new TranslationTextComponent("selectWorld.deleteQuestion"), new TranslationTextComponent("selectWorld.deleteWarning", worldsummary.getLevelName()), new TranslationTextComponent("selectWorld.deleteButton"), DialogTexts.GUI_CANCEL));
				}
			} catch (IOException ioexception) {
				SystemToast.onWorldAccessFailure(this.minecraft, "Demo_World");
				LOGGER.warn("Failed to access demo world", ioexception);
			}

		}));
		this.resetDemoButton.active = flag;
	}

	private boolean checkDemoWorldPresence() {
		try (SaveFormat.LevelSave saveformat$levelsave = this.minecraft.getLevelSource().createAccess("Demo_World")) {
			return saveformat$levelsave.getSummary() != null;
		} catch (IOException ioexception) {
			SystemToast.onWorldAccessFailure(this.minecraft, "Demo_World");
			LOGGER.warn("Failed to read demo world data", ioexception);
			return false;
		}
	}

	private void realmsButtonClicked() {
		RealmsBridgeScreen realmsbridgescreen = new RealmsBridgeScreen();
		realmsbridgescreen.switchToRealms(this);
	}

	@Override
	public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		if (this.fadeInStart == 0L && this.fading) {
			this.fadeInStart = Util.getMillis();
		}

		float f = this.fading ? (float) (Util.getMillis() - this.fadeInStart) / 1000.0F : 1.0F;
		fill(matrixStack, 0, 0, this.width, this.height, -1);
		this.panorama.render(partialTicks, MathHelper.clamp(f, 0.0F, 1.0F));
		int i = 274;
		int j = this.width / 2 - 137;
		int k = 30;
		assert this.minecraft != null;
		this.minecraft.getTextureManager().bind(PANORAMA_OVERLAY);
		RenderSystem.enableBlend();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.fading ? (float) MathHelper.ceil(MathHelper.clamp(f, 0.0F, 1.0F)) : 1.0F);
		blit(matrixStack, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
		float f1 = this.fading ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
		int l = MathHelper.ceil(f1 * 255.0F) << 24;
		if ((l & -67108864) != 0) {
			this.minecraft.getTextureManager().bind(MINECRAFT_LOGO);
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, f1);
			if (this.minceraftEasterEgg) {
				this.blitOutlineBlack(j, 30, (integer2_, integer3_) -> {
					this.blit(matrixStack, integer2_, integer3_, 0, 0, 99, 44);
					this.blit(matrixStack, integer2_ + 99, integer3_, 129, 0, 27, 44);
					this.blit(matrixStack, integer2_ + 99 + 26, integer3_, 126, 0, 3, 44);
					this.blit(matrixStack, integer2_ + 99 + 26 + 3, integer3_, 99, 0, 26, 44);
					this.blit(matrixStack, integer2_ + 155, integer3_, 0, 45, 155, 44);
				});
			} else {
				this.blitOutlineBlack(j, 30, (integer_, integer1_) -> {
					this.blit(matrixStack, integer_, integer1_, 0, 0, 155, 44);
					this.blit(matrixStack, integer_ + 155, integer1_, 0, 45, 155, 44);
				});
			}

			this.minecraft.getTextureManager().bind(MINECRAFT_EDITION);
			blit(matrixStack, j + 88, 67, 0.0F, 0.0F, 98, 14, 128, 16);
			net.minecraftforge.client.ForgeHooksClient.renderMainMenu(this, matrixStack, this.font, this.width, this.height);
			if (this.splash != null) {
				RenderSystem.pushMatrix();
				RenderSystem.translatef((float) (this.width / 2 + 80), 45.0F, 0.0F);
				RenderSystem.rotatef(10.0F, 0.0F, 0.0F, 1.0F);
				float f2 = 1.8F - MathHelper.abs(MathHelper.sin((float) (Util.getMillis() % 1000L) / 1000.0F * ((float) Math.PI * 2F)) * 0.1F);
				f2 = f2 * 100.0F / (float) (this.font.width(this.splash) + 32);
				RenderSystem.scalef(f2, f2, f2);
				drawCenteredString(matrixStack, this.font, this.splash, 0, -8, 16776960 | l);
				RenderSystem.popMatrix();
			}

			String s = "Minecraft " + SharedConstants.getCurrentVersion().getName();
			if (this.minecraft.isDemo()) {
				s = s + " Demo";
			} else {
				s = s + ("release".equalsIgnoreCase(this.minecraft.getVersionType()) ? "" : "/" + this.minecraft.getVersionType());
			}

			if (this.minecraft.isProbablyModded()) {
				s = s + I18n.get("menu.modded");
			}

			net.minecraftforge.fml.BrandingControl.forEachLine(true, true, (brdline, brd) ->
					drawString(matrixStack, this.font, brd, 2, this.height - (10 + brdline * (this.font.lineHeight + 1)), 16777215 | l)
			);

			net.minecraftforge.fml.BrandingControl.forEachAboveCopyrightLine((brdline, brd) ->
					drawString(matrixStack, this.font, brd, this.width - font.width(brd), this.height - (10 + (brdline + 1) * (this.font.lineHeight + 1)), 16777215 | l)
			);

			drawString(matrixStack, this.font, "Copyright Mojang AB. Do not distribute!", this.copyrightX, this.height - 10, 16777215 | l);
			if (mouseX > this.copyrightX && mouseX < this.copyrightX + this.copyrightWidth && mouseY > this.height - 10 && mouseY < this.height) {
				fill(matrixStack, this.copyrightX, this.height - 1, this.copyrightX + this.copyrightWidth, this.height, 16777215 | l);
			}

			for (Widget widget : this.buttons) {
				widget.setAlpha(f1);
			}

			int buttonSize;
			for (buttonSize = 0; buttonSize < this.buttons.size(); ++buttonSize) {
				(this.buttons.get(buttonSize)).renderButton(matrixStack, mouseX, mouseY, partialTicks);
			}

			for (buttonSize = 0; buttonSize < this.buttons.size(); ++buttonSize) {
				(this.buttons.get(buttonSize)).renderToolTip(matrixStack, mouseX, mouseY);
			}

			if (this.realmsNotificationsEnabled() && f1 >= 1.0F) {
				this.realmsNotificationsScreen.render(matrixStack, mouseX, mouseY, partialTicks);
			}
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int buttonIn) {
		if (super.mouseClicked(mouseX, mouseY, buttonIn)) {
			return true;
		} else if (this.realmsNotificationsEnabled() && this.realmsNotificationsScreen.mouseClicked(mouseX, mouseY, buttonIn)) {
			return true;
		} else {
			if (mouseX > (double) this.copyrightX && mouseX < (double) (this.copyrightX + this.copyrightWidth) && mouseY > (double) (this.height - 10) && mouseY < (double) this.height) {
				this.minecraft.setScreen(new WinGameScreen(false, Runnables.doNothing()));
			}

			return false;
		}
	}

	@Override
	public void removed() {
		if (this.realmsNotificationsScreen != null) {
			this.realmsNotificationsScreen.removed();
		}
	}

	private void confirmDemo(boolean boolean_) {
		if (boolean_) {
			assert this.minecraft != null;
			try (SaveFormat.LevelSave saveformat$levelsave = this.minecraft.getLevelSource().createAccess("Demo_World")) {
				saveformat$levelsave.deleteLevel();
			} catch (IOException ioexception) {
				SystemToast.onWorldDeleteFailure(this.minecraft, "Demo_World");
				LOGGER.warn("Failed to delete demo world", ioexception);
			}
		}

		assert this.minecraft != null;
		this.minecraft.setScreen(this);
	}
}
