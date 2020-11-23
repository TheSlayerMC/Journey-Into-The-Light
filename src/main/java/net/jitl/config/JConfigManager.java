package net.jitl.config;

import net.jitl.JITL;
import ru.timeconqueror.timecore.registry.AutoRegistrable;
import ru.timeconqueror.timecore.registry.newreg.ConfigRegister;

public class JConfigManager {

	@AutoRegistrable
	private static final ConfigRegister CONFIG_REGISTER = new ConfigRegister(JITL.MODID);

	@AutoRegistrable.InitMethod
	private static void register() {
		CONFIG_REGISTER.register(JClientConfig.INSTANCE);

		//CONFIG_REGISTER.register(JServerConfig.INSTANCE);
		//CONFIG_REGISTER.register(JCommonConfig.INSTANCE);
	}
}
