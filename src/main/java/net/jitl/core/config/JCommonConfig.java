package net.jitl.core.config;

import net.minecraftforge.fml.config.ModConfig;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.api.common.config.Config;
import ru.timeconqueror.timecore.api.common.config.ImprovedConfigBuilder;

public class JCommonConfig extends Config {

	public JCommonConfig() {
		super(ModConfig.Type.COMMON, "common", "Common Configuration");
	}

	@Override
	public @NotNull String getRelativePath() {
		return JConfigs.resolveConfigPath(getKey());
	}

	/**
	 * Sets up config entries and categories that operate on both client and server
	 */
	@Override
	public void setup(ImprovedConfigBuilder builder) {
	}
}