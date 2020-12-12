package net.jitl.init.internal;

import net.jitl.JITL;
import ru.timeconqueror.timecore.registry.AutoRegistrable;
import ru.timeconqueror.timecore.registry.newreg.SoundRegister;

public class SoundRegistrator {

	@AutoRegistrable
	private static final SoundRegister REGISTER = new SoundRegister(JITL.MODID);

	@AutoRegistrable.InitMethod
	private static void register() {
		registerSound("test_sound");
	}

	private static void registerSound(String location) {
		REGISTER.register(location);
	}
}
