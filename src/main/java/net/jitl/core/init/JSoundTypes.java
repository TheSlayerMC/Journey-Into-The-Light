package net.jitl.core.init;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import ru.timeconqueror.timecore.api.common.block.TimeSoundType;

public class JSoundTypes {

	public static final SoundType MUD = new TimeSoundType(
			1.0F,
			1.0F,
			JSounds.MUD_BLOCK_BREAK,
			JSounds.MUD_BLOCK_DIG,
			JSounds.MUD_BLOCK_BREAK,
			JSounds.MUD_BLOCK_DIG,
			JSounds.MUD_BLOCK_BREAK);

	public static final SoundType POTTERY = new TimeSoundType(
			1.0F,
			1.0F,
			JSounds.POTTERY_BREAK,
			() -> SoundEvents.GLASS_STEP,
			JSounds.POTTERY_DIG,
			JSounds.POTTERY_DIG,
			JSounds.POTTERY_BREAK);

	public static final SoundType LUNIUM_BLOCK = new TimeSoundType(
			1.0F,
			1.0F,
			JSounds.LUNIUM_BLOCK_BREAK,
			JSounds.LUNIUM_BLOCK_DIG,
			JSounds.LUNIUM_BLOCK_BREAK,
			JSounds.LUNIUM_BLOCK_DIG,
			JSounds.LUNIUM_BLOCK_BREAK);

	public static final SoundType LUNIUM_ORE = new TimeSoundType(
			1.0F,
			1.0F,
			JSounds.LUNIUM_ORE_BREAK,
			() -> SoundEvents.STONE_HIT,
			JSounds.LUNIUM_ORE_BREAK,
			() -> SoundEvents.STONE_HIT,
			JSounds.LUNIUM_ORE_BREAK);

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
			JSounds.SHADIUM_BLOCK_BREAK,
			JSounds.SHADIUM_BLOCK_DIG,
			JSounds.SHADIUM_BLOCK_BREAK,
			JSounds.SHADIUM_BLOCK_DIG,
			JSounds.SHADIUM_BLOCK_BREAK);

	public static final SoundType SHADIUM_ORE = new TimeSoundType(
			1.0F,
			1.0F,
			JSounds.SHADIUM_ORE_BREAK,
			() -> SoundEvents.STONE_HIT,
			JSounds.SHADIUM_ORE_BREAK,
			() -> SoundEvents.STONE_HIT,
			JSounds.SHADIUM_ORE_BREAK);

	public static final SoundType BRICK = new TimeSoundType(
			1.0F,
			1.0F,
			JSounds.BRICK_BLOCK_BREAK,
			JSounds.BRICK_BLOCK_DIG,
			JSounds.BRICK_BLOCK_BREAK,
			JSounds.BRICK_BLOCK_DIG,
			JSounds.BRICK_BLOCK_BREAK);

	public static final SoundType FUMICE = new TimeSoundType(
			1.0F,
			1.0F,
			JSounds.FUMICE_BLOCK_BREAK,
			JSounds.FUMICE_BLOCK_DIG,
			JSounds.FUMICE_BLOCK_BREAK,
			JSounds.FUMICE_BLOCK_DIG,
            JSounds.FUMICE_BLOCK_BREAK);

    public static final SoundType ICE_CRYSTAL = new TimeSoundType(
            1.0F,
            1.0F,
			JSounds.ICE_CRYSTAL_BREAK,
            () -> SoundEvents.GLASS_HIT,
            () -> SoundEvents.GLASS_PLACE,
            () -> SoundEvents.GLASS_HIT,
            () -> SoundEvents.GLASS_PLACE);

    public static final SoundType GRASSY_PERMAFROST = new TimeSoundType(
            1.0F,
            1.0F,
			JSounds.GRASSY_PERMAFROST_BREAK,
            JSounds.GRASSY_PERMAFROST_DIG,
            JSounds.GRASSY_PERMAFROST_BREAK,
            JSounds.GRASSY_PERMAFROST_DIG,
            JSounds.GRASSY_PERMAFROST_BREAK);

    public static final SoundType CRUMBLED_PERMAFROST = new TimeSoundType(
            1.0F,
            1.0F,
            JSounds.CRUMBLED_PERMAFROST_BREAK,
            JSounds.CRUMBLED_PERMAFROST_DIG,
            JSounds.CRUMBLED_PERMAFROST_BREAK,
            JSounds.CRUMBLED_PERMAFROST_DIG,
            JSounds.CRUMBLED_PERMAFROST_BREAK);

    public static final SoundType PERMAFROST = new TimeSoundType(
            1.0F,
            1.0F,
            JSounds.PERMAFROST_BREAK,
            JSounds.PERMAFROST_DIG,
            JSounds.PERMAFROST_BREAK,
            JSounds.PERMAFROST_DIG,
            JSounds.PERMAFROST_BREAK);
}
