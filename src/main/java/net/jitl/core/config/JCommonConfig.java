package net.jitl.core.config;

import net.jitl.core.JITL;
import net.minecraftforge.fml.config.ModConfig;
import ru.timeconqueror.timecore.api.common.config.Config;
import ru.timeconqueror.timecore.api.common.config.ImprovedConfigBuilder;

public class JCommonConfig extends Config {

	public JCommonConfig() {
		super(ModConfig.Type.COMMON, JITL.MODID + "_common_config", "Common Configuration");
	}

	/**
	 * Sets up config entries and categories that operate on both client and server
	 */
	@Override
	public void setup(ImprovedConfigBuilder builder) {
	}
}