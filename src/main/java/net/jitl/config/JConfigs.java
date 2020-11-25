package net.jitl.config;

import net.jitl.JITL;
import ru.timeconqueror.timecore.registry.AutoRegistrable;
import ru.timeconqueror.timecore.registry.newreg.ConfigRegister;

public class JConfigs {
	public static final JClientConfig CLIENT = new JClientConfig();
	public static final JCommonConfig COMMON = new JCommonConfig();
	public static final JServerConfig SERVER = new JServerConfig();

	@AutoRegistrable
	private static final ConfigRegister CONFIG_REGISTER = new ConfigRegister(JITL.MODID);

	@AutoRegistrable.InitMethod
	private static void register() {
		CONFIG_REGISTER.register(CLIENT);
		CONFIG_REGISTER.register(COMMON);
		CONFIG_REGISTER.register(SERVER);
	}
}
