package net.jitl.config;

import net.jitl.JITL;
import net.minecraftforge.fml.config.ModConfig;
import ru.timeconqueror.timecore.api.common.config.Config;
import ru.timeconqueror.timecore.api.common.config.ImprovedConfigBuilder;

public class JCommonConfig extends Config {

	public static final JServerConfig INSTANCE = new JServerConfig();

	public JCommonConfig() {
		super(ModConfig.Type.SERVER, JITL.MODID + "_common_config", "Common Configuration");
	}

	/**
	 * Sets up config sections, entries, and categories that operate on both client and server
	 */
	@Override
	public void setup(ImprovedConfigBuilder builder) {
	}
}