package net.jitl.init;

import net.minecraft.block.SoundType;
import net.minecraft.util.SoundEvents;
import ru.timeconqueror.timecore.api.common.block.TimeSoundType;

public class JSoundTypes {

	public static final SoundType MUD = new TimeSoundType(
			1.0F,
			1.0F,
			JSounds.MUD_BLOCK_BREAK::get,
			JSounds.MUD_BLOCK_DIG::get,
			JSounds.MUD_BLOCK_BREAK::get,
			JSounds.MUD_BLOCK_DIG::get,
			JSounds.MUD_BLOCK_BREAK::get);

	public static final SoundType LUNIUM_BLOCK = new TimeSoundType(
			1.0F,
			1.0F,
			JSounds.LUNIUM_BLOCK_BREAK::get,
			JSounds.LUNIUM_BLOCK_DIG::get,
			JSounds.LUNIUM_BLOCK_BREAK::get,
			JSounds.LUNIUM_BLOCK_DIG::get,
			JSounds.LUNIUM_BLOCK_BREAK::get);

	public static final SoundType LUNIUM_ORE = new TimeSoundType(
			1.0F,
			1.0F,
			JSounds.LUNIUM_ORE_BREAK::get,
			() -> SoundEvents.STONE_HIT,
			JSounds.LUNIUM_ORE_BREAK::get,
			() -> SoundEvents.STONE_HIT,
			JSounds.LUNIUM_ORE_BREAK::get);

	public static final SoundType SHADIUM_BLOCK = new TimeSoundType(
			1.0F,
			1.0F,
			JSounds.SHADIUM_BLOCK_BREAK::get,
			JSounds.SHADIUM_BLOCK_DIG::get,
			JSounds.SHADIUM_BLOCK_BREAK::get,
			JSounds.SHADIUM_BLOCK_DIG::get,
			JSounds.SHADIUM_BLOCK_BREAK::get);

	public static final SoundType SHADIUM_ORE = new TimeSoundType(
			1.0F,
			1.0F,
			JSounds.SHADIUM_ORE_BREAK::get,
			() -> SoundEvents.STONE_HIT,
			JSounds.SHADIUM_ORE_BREAK::get,
			() -> SoundEvents.STONE_HIT,
			JSounds.SHADIUM_ORE_BREAK::get);

	public static final SoundType BRICK = new TimeSoundType(
			1.0F,
			1.0F,
			JSounds.BRICK_BLOCK_BREAK::get,
			JSounds.BRICK_BLOCK_DIG::get,
			JSounds.BRICK_BLOCK_BREAK::get,
			JSounds.BRICK_BLOCK_DIG::get,
			JSounds.BRICK_BLOCK_BREAK::get);
}
