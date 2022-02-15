package net.jitl.core.config;

import net.jitl.core.config.enums.EssencePosition;
import net.jitl.core.config.enums.HealthBarRendering;
import net.jitl.core.config.enums.IsometricAngleSnap;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.common.config.Config;
import ru.timeconqueror.timecore.api.common.config.ConfigSection;
import ru.timeconqueror.timecore.api.common.config.ImprovedConfigBuilder;

public class JClientConfig extends Config {

	public GuiCategory guiCategory;

	public JClientConfig() {
		super(ModConfig.Type.CLIENT, "client", "Client-Side Configuration");
	}

	@Override
	public @NotNull String getRelativePath() {
		return JConfigs.resolveConfigPath(getKey());
	}

	/**
	 * Sets up client-side config entries and categories
	 */
	@Override
	public void setup(ImprovedConfigBuilder builder) {
		guiCategory = new GuiCategory("gui", "Gui Category");
		builder.addAndSetupSection(guiCategory);
	}

	public static class GuiCategory extends ConfigSection {
		private ForgeConfigSpec.BooleanValue enableIsometricCamera;
		private ForgeConfigSpec.BooleanValue lockIsometricPerspective;
		private ForgeConfigSpec.EnumValue<IsometricAngleSnap> isometricAngleSnap;

		private ForgeConfigSpec.BooleanValue enableMenuToggleButton;
		private ForgeConfigSpec.BooleanValue enableJitlMenuScreen;
		private ForgeConfigSpec.EnumValue<HealthBarRendering> renderEntityHealth;
		private ForgeConfigSpec.EnumValue<EssencePosition> essencePosition;
		private ForgeConfigSpec.IntValue bigScreenshotWidth;
		private ForgeConfigSpec.IntValue bigScreenshotHeight;

		public GuiCategory(@NotNull String key, @Nullable String comment) {
			super(key, comment);
		}

		/**
		 * Register gui-based config options here
		 */
		@Override
		public void setup(ImprovedConfigBuilder builder) {
			enableIsometricCamera = builder
					.comment("If set to 'true', the camera will be locked to the isometric view.")
					.define("Enable Isometric Camera: ", false);

			lockIsometricPerspective = builder
					.comment("If set to 'true', the perspective will be locked for the isometric camera.")
					.define("Lock Isometric Perspective: ", false);

			isometricAngleSnap = builder
					.comment("Determines snap angle of the isometric camera view.")
					.defineEnum("Isometric Camera Angle: ", IsometricAngleSnap.NORTH_WEST);

			enableJitlMenuScreen = builder
					.comment("If set to 'true', the JITL main menu theme will be enabled by default. "
							+ "This can also be toggled in the main menu itself by pressing the top-left button if enabled.")
					.define("Enable JITL Menu Screen: ", true);

			enableMenuToggleButton = builder
					.comment("If set to 'true', the button that toggles the main menu theme will be visible. "
							+ "If set to 'false', the button will no longer appear on the main menu screen.")
					.define("Enable JITL Menu Screen Toggle Button: ", true);

			renderEntityHealth = builder
					.comment("Determines if/when living entity's health bar will be rendered")
					.defineEnum("Render health: ", HealthBarRendering.IN_DEBUG_MODE);

			essencePosition = builder
					.comment("Determines the position of the Essence bar in-game. ")
					.defineEnum("Essence Bar Position: ", EssencePosition.OVER_EXPERIENCE_BAR);

			bigScreenshotWidth = builder
					.comment("The width of any big screenshots you may take. ")
					.defineInRange("Width: ", 3840, 128, 7680);

			bigScreenshotHeight = builder
					.comment("The height of any big screenshots you may take. ")
					.defineInRange("Height: ", 2160, 72, 4320);
		}

		public boolean isIsometricFOVEnabled() {
			return enableIsometricCamera.get();
		}

		public void setIsometricFov(boolean enabled) {
			enableIsometricCamera.set(enabled);
		}

		public boolean isIsometricPerspectiveLocked() {
			return lockIsometricPerspective.get();
		}

		public void lockIsometricPerspective(boolean enabled) {
			lockIsometricPerspective.set(enabled);
		}

		public boolean isJITLMenuEnabled() {
			return enableJitlMenuScreen.get();
		}

		public void setJITLMenu(boolean enabled) {
			enableJitlMenuScreen.set(enabled);
		}

		public boolean isToggleMenuButtonEnabled() {
			return enableMenuToggleButton.get();
		}

		public int getEssenceXPos() {
			return essencePosition.get().getX();
		}

		public int getEssenceYPos() {
			return essencePosition.get().getY();
		}

		public EssencePosition getEssencePosition() {
			return essencePosition.get();
		}

		public void setEssencePosition(EssencePosition essencePosition) {
			this.essencePosition.set(essencePosition);
		}

		public HealthBarRendering getHealthBarRendering() {
			return renderEntityHealth.get();
		}

		public void setHealthBarRendering(HealthBarRendering healthBarRendering) {
			renderEntityHealth.set(healthBarRendering);
		}

		public IsometricAngleSnap getIsometricAngleSnap() {
			return isometricAngleSnap.get();
		}

		public void setIsometricAngleSnap(IsometricAngleSnap isometricAngleSnap) {
			this.isometricAngleSnap.set(isometricAngleSnap);
		}

		public int getBigScreenshotWidth() {
			return bigScreenshotWidth.get();
		}

		public int getBigScreenshotHeight() {
			return bigScreenshotHeight.get();
		}
	}
}
