package net.jitl.core.config;

import net.jitl.core.JITL;
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

	public GuiCategory GUI_CATEGORY;

	public JClientConfig() {
		super(ModConfig.Type.CLIENT, JITL.MODID + "_client_config", "Client-Side Configuration");
	}

	/**
	 * Sets up client-side config entries and categories
	 */
	@Override
	public void setup(ImprovedConfigBuilder builder) {
		GUI_CATEGORY = new GuiCategory("gui_category", "Gui Category");
		builder.addAndSetupSection(GUI_CATEGORY);
	}

	public static class GuiCategory extends ConfigSection {

		public ForgeConfigSpec.BooleanValue ENABLE_ISOMETRIC_CAMERA;
		public ForgeConfigSpec.BooleanValue LOCK_ISOMETRIC_PERSPECTIVE;
		public ForgeConfigSpec.EnumValue<IsometricAngleSnap> ISOMETRIC_ANGLE_SNAP;

		public ForgeConfigSpec.BooleanValue ENABLE_MENU_TOGGLE_BUTTON;
		public ForgeConfigSpec.BooleanValue ENABLE_JITL_MENU_SCREEN;
		public ForgeConfigSpec.EnumValue<HealthBarRendering> RENDER_ENTITY_HEALTH;
		public ForgeConfigSpec.EnumValue<EssencePosition> ESSENCE_POSITION;
		public ForgeConfigSpec.IntValue BIG_SCREENSHOT_WIDTH;
		public ForgeConfigSpec.IntValue BIG_SCREENSHOT_HEIGHT;

		public GuiCategory(@NotNull String key, @Nullable String comment) {
			super(key, comment);
		}

		/**
		 * Register gui-based config options here
		 */
		@Override
		public void setup(ImprovedConfigBuilder builder) {
			ENABLE_ISOMETRIC_CAMERA = builder
					.comment("If set to 'true', the camera will be locked to the isometric view.")
					.define("Enable Isometric Camera: ", false);

			LOCK_ISOMETRIC_PERSPECTIVE = builder
					.comment("If set to 'true', the perspective will be locked for the isometric camera.")
					.define("Lock Isometric Perspective: ", false);

			ISOMETRIC_ANGLE_SNAP = builder
					.comment("Determines snap angle of the isometric camera view.")
					.defineEnum("Isometric Camera Angle: ", IsometricAngleSnap.NORTH_WEST);

			ENABLE_JITL_MENU_SCREEN = builder
					.comment("If set to 'true', the JITL main menu theme will be enabled by default. "
							+ "This can also be toggled in the main menu itself by pressing the top-left button if enabled.")
					.define("Enable JITL Menu Screen: ", true);

			ENABLE_MENU_TOGGLE_BUTTON = builder
					.comment("If set to 'true', the button that toggles the main menu theme will be visible. "
							+ "If set to 'false', the button will no longer appear on the main menu screen.")
					.define("Enable JITL Menu Screen Toggle Button: ", true);

			RENDER_ENTITY_HEALTH = builder
					.comment("Determines if/when living entity's health bar will be rendered")
					.defineEnum("Render health: ", HealthBarRendering.IN_DEBUG_MODE);

			ESSENCE_POSITION = builder
					.comment("Determines the position of the Essence bar in-game. ")
					.defineEnum("Essence Bar Position: ", EssencePosition.OVER_EXPERIENCE_BAR);

			BIG_SCREENSHOT_WIDTH = builder
					.comment("The width of any big screenshots you may take. ")
					.defineInRange("Width: ", 3840, 128, 7680);

			BIG_SCREENSHOT_HEIGHT = builder
					.comment("The height of any big screenshots you may take. ")
					.defineInRange("Height: ", 2160, 72, 4320);
		}

		public boolean isIsometricFOVEnabled() {
			return ENABLE_ISOMETRIC_CAMERA.get();
		}

		public void setIsometricFov(boolean enabled) {
			ENABLE_ISOMETRIC_CAMERA.set(enabled);
		}

		public boolean isIsometricPerspectiveLocked() {
			return LOCK_ISOMETRIC_PERSPECTIVE.get();
		}

		public void lockIsometricPerspective(boolean enabled) {
			LOCK_ISOMETRIC_PERSPECTIVE.set(enabled);
		}

		public boolean isJITLMenuEnabled() {
			return ENABLE_JITL_MENU_SCREEN.get();
		}

		public void setJITLMenu(boolean enabled) {
			ENABLE_JITL_MENU_SCREEN.set(enabled);
		}

		public boolean isToggleMenuButtonEnabled() {
			return ENABLE_MENU_TOGGLE_BUTTON.get();
		}

		public int getEssenceXPos() {
			return ESSENCE_POSITION.get().getX();
		}

		public int getEssenceYPos() {
			return ESSENCE_POSITION.get().getY();
		}

		public EssencePosition getEssencePosition() {
			return ESSENCE_POSITION.get();
		}

		public void setEssencePosition(EssencePosition essencePosition) {
			ESSENCE_POSITION.set(essencePosition);
		}

		public HealthBarRendering getHealthBarRendering() {
			return RENDER_ENTITY_HEALTH.get();
		}

		public void setHealthBarRendering(HealthBarRendering healthBarRendering) {
			RENDER_ENTITY_HEALTH.set(healthBarRendering);
		}

		public IsometricAngleSnap getIsometricAngleSnap() {
			return ISOMETRIC_ANGLE_SNAP.get();
		}

		public void setIsometricAngleSnap(IsometricAngleSnap isometricAngleSnap) {
			ISOMETRIC_ANGLE_SNAP.set(isometricAngleSnap);
		}

		public int getBigScreenshotWidth() {
			return BIG_SCREENSHOT_WIDTH.get();
		}

		public int getBigScreenshotHeight() {
			return BIG_SCREENSHOT_HEIGHT.get();
		}
	}
}
