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

	public static final RegistryObject<TimeSound> LUNIUM_ORE_BREAK = REGISTER.register("block.lunium_ore.break");
	public static final RegistryObject<TimeSound> LUNIUM_BLOCK_BREAK = REGISTER.register("block.lunium_block.break");
	public static final RegistryObject<TimeSound> LUNIUM_BLOCK_DIG = REGISTER.register("block.lunium_block.dig");

	public static final RegistryObject<TimeSound> SHADIUM_ORE_BREAK = REGISTER.register("block.shadium_ore.break");
	public static final RegistryObject<TimeSound> SHADIUM_BLOCK_BREAK = REGISTER.register("block.shadium_block.break");
	public static final RegistryObject<TimeSound> SHADIUM_BLOCK_DIG = REGISTER.register("block.shadium_block.dig");

	public static final RegistryObject<TimeSound> BRICK_BLOCK_BREAK = REGISTER.register("block.brick.break");
	public static final RegistryObject<TimeSound> BRICK_BLOCK_DIG = REGISTER.register("block.brick.dig");

}
