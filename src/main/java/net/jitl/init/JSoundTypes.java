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

	public static final SoundType POTTERY = new TimeSoundType(
			1.0F,
			1.0F,
			JSounds.POTTERY_BREAK::get,
			JSounds.POTTERY_DIG::get,
			JSounds.POTTERY_DIG::get,
			JSounds.POTTERY_DIG::get,
			JSounds.POTTERY_BREAK::get);

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

	public static final SoundType CRYSTAL_FRUIT = new TimeSoundType(
			1.0F,
			1.25F,
			() -> SoundEvents.SHROOMLIGHT_BREAK,
			() -> SoundEvents.SHROOMLIGHT_STEP,
			() -> SoundEvents.SHROOMLIGHT_PLACE,
			() -> SoundEvents.SHROOMLIGHT_HIT,
			() -> SoundEvents.SHROOMLIGHT_FALL);

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

	public static final SoundType FUMICE = new TimeSoundType(
			1.0F,
			1.0F,
			JSounds.FUMICE_BLOCK_BREAK::get,
			JSounds.FUMICE_BLOCK_DIG::get,
			JSounds.FUMICE_BLOCK_BREAK::get,
			JSounds.FUMICE_BLOCK_DIG::get,
            JSounds.FUMICE_BLOCK_BREAK::get);

    public static final SoundType ICE_CRYSTAL = new TimeSoundType(
            1.0F,
            1.0F,
            JSounds.ICE_CRYSTAL_BREAK::get,
            () -> SoundEvents.GLASS_HIT,
            () -> SoundEvents.GLASS_PLACE,
            () -> SoundEvents.GLASS_HIT,
            () -> SoundEvents.GLASS_PLACE);

    public static final SoundType GRASSY_PERMAFROST = new TimeSoundType(
            1.0F,
            1.0F,
            JSounds.GRASSY_PERMAFROST_BREAK::get,
            JSounds.GRASSY_PERMAFROST_DIG::get,
            JSounds.GRASSY_PERMAFROST_BREAK::get,
            JSounds.GRASSY_PERMAFROST_DIG::get,
            JSounds.GRASSY_PERMAFROST_BREAK::get);

    public static final SoundType CRUMBLED_PERMAFROST = new TimeSoundType(
            1.0F,
            1.0F,
            JSounds.CRUMBLED_PERMAFROST_BREAK::get,
            JSounds.CRUMBLED_PERMAFROST_DIG::get,
            JSounds.CRUMBLED_PERMAFROST_BREAK::get,
            JSounds.CRUMBLED_PERMAFROST_DIG::get,
            JSounds.CRUMBLED_PERMAFROST_BREAK::get);

    public static final SoundType PERMAFROST = new TimeSoundType(
            1.0F,
            1.0F,
            JSounds.PERMAFROST_BREAK::get,
            JSounds.PERMAFROST_DIG::get,
            JSounds.PERMAFROST_BREAK::get,
            JSounds.PERMAFROST_DIG::get,
            JSounds.PERMAFROST_BREAK::get);
}
