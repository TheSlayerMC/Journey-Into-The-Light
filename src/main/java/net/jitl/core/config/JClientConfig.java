package net.jitl.core.config;

import net.jitl.client.render.overlay.internal.EssencePosition;
import net.jitl.core.JITL;
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

		public ForgeConfigSpec.BooleanValue ENABLE_JITL_MENU_SCREEN;
		public ForgeConfigSpec.BooleanValue ENABLE_MENU_TOGGLE_BUTTON;
		public ForgeConfigSpec.EnumValue<EssencePosition> ESSENCE_POSITION;

		public GuiCategory(@NotNull String key, @Nullable String comment) {
			super(key, comment);
		}

		/**
		 * Register gui-based config options here
		 */
		@Override
		public void setup(ImprovedConfigBuilder builder) {
			ENABLE_JITL_MENU_SCREEN = builder
					.comment("If set to 'true', the JITL main menu theme will be enabled by default. "
							+ "This can also be toggled in the main menu itself by pressing the top-left button if enabled.")
					.define("Enable JITL Menu Screen", true);

			ENABLE_MENU_TOGGLE_BUTTON = builder
					.comment("If set to 'true', the button that toggles the main menu theme will be visible. "
							+ "If set to 'false', the button will no longer appear on the main menu screen.")
					.define("Enable JITL Menu Screen Toggle Button", true);

			ESSENCE_POSITION = builder
					.comment("Determines the position of the Essence bar in-game. ")
					.defineEnum("Essence Bar Position: ", EssencePosition.OVER_EXPERIENCE_BAR);
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
	}
}
