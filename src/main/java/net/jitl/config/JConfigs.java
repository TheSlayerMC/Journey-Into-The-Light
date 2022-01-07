package net.jitl.config;

import net.jitl.JITL;
import ru.timeconqueror.timecore.api.registry.ConfigRegister;
import ru.timeconqueror.timecore.api.registry.util.AutoRegistrable;

public class JConfigs {
	public static final JClientConfig CLIENT = new JClientConfig();
	public static final JCommonConfig COMMON = new JCommonConfig();
	public static final JServerConfig SERVER = new JServerConfig();

	@AutoRegistrable
	private static final ConfigRegister CONFIG_REGISTER = new ConfigRegister(JITL.MODID);

	@AutoRegistrable.Init
	private static void register() {
		CONFIG_REGISTER.register(CLIENT);
		CONFIG_REGISTER.register(COMMON);
		CONFIG_REGISTER.register(SERVER);
	}
}
