package net.jitl.init;

import net.jitl.JITL;
import net.minecraftforge.fml.RegistryObject;
import ru.timeconqueror.timecore.api.common.sound.TimeSound;
import ru.timeconqueror.timecore.registry.AutoRegistrable;
import ru.timeconqueror.timecore.registry.newreg.SoundRegister;

public class JSounds {

	@AutoRegistrable
	private static final SoundRegister REGISTER = new SoundRegister(JITL.MODID);

	public static final RegistryObject<TimeSound> MUD_BLOCK_BREAK = REGISTER.register("block.mud.break");
	public static final RegistryObject<TimeSound> MUD_BLOCK_DIG = REGISTER.register("block.mud.dig");

	public static final RegistryObject<TimeSound> LUNIUM_BLOCK_BREAK = REGISTER.register("block.lunium.break");
	public static final RegistryObject<TimeSound> LUNIUM_BLOCK_DIG = REGISTER.register("block.lunium.dig");
}
