package net.jitl.config;

import net.jitl.JITL;
import net.minecraftforge.fml.config.ModConfig;
import ru.timeconqueror.timecore.api.common.config.Config;
import ru.timeconqueror.timecore.api.common.config.ImprovedConfigBuilder;

public class JServerConfig extends Config {

	public JServerConfig() {
		super(ModConfig.Type.SERVER, JITL.MODID + "_server_config", "Server-Side Configuration");
	}

	/**
	 * Sets up server-side config entries and categories
	 */
	@Override
	public void setup(ImprovedConfigBuilder builder) {
	}
}