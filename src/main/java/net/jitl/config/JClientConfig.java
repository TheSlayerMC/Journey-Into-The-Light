package net.jitl.config;

import net.jitl.JITL;
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
							+ "This can also be toggled in the main menu itself by toggling the top-left button.")
					.define("enable_jitl_menu_screen", true);
		}

		public boolean isJITLMenuEnabled() {
			return ENABLE_JITL_MENU_SCREEN.get();
		}

		public void setJITLMenu(boolean enabled) {
			ENABLE_JITL_MENU_SCREEN.set(enabled);
		}
	}
}
