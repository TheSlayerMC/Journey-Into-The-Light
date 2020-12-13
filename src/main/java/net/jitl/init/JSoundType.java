package net.jitl.init;

import net.minecraft.block.SoundType;
import ru.timeconqueror.timecore.common.block.TimeSoundType;

public class JSoundType {

	public static final SoundType MUD = new TimeSoundType(
			0.8F,
			1.3F,
			JSounds.MUD_BLOCK_BREAK::get,
			JSounds.MUD_BLOCK_DIG::get,
			JSounds.MUD_BLOCK_BREAK::get,
			JSounds.MUD_BLOCK_DIG::get,
			JSounds.MUD_BLOCK_BREAK::get);

	public static final SoundType LUNIUM_BLOCK = new TimeSoundType(
			0.8F,
			1.3F,
			JSounds.LUNIUM_BLOCK_BREAK::get,
			JSounds.LUNIUM_BLOCK_DIG::get,
			JSounds.LUNIUM_BLOCK_BREAK::get,
			JSounds.LUNIUM_BLOCK_DIG::get,
			JSounds.LUNIUM_BLOCK_BREAK::get);
}
