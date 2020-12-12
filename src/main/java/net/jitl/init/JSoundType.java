package net.jitl.init;

import net.minecraft.block.SoundType;
import net.minecraft.util.SoundEvent;
import ru.timeconqueror.timecore.common.block.TimeSoundType;

import java.util.function.Supplier;

public class JSoundType extends TimeSoundType {

	public static final SoundType TEST = new TimeSoundType(
			1.0F,
			1.0F,
			() -> JSounds.TEST_SOUND,
			() -> JSounds.TEST_SOUND,
			() -> JSounds.TEST_SOUND,
			() -> JSounds.TEST_SOUND,
			() -> JSounds.TEST_SOUND);

	public JSoundType(float volumeIn, float pitchIn, Supplier<SoundEvent> breakSound, Supplier<SoundEvent> stepSound, Supplier<SoundEvent> placeSound, Supplier<SoundEvent> hitSound, Supplier<SoundEvent> fallSound) {
		super(volumeIn, pitchIn, breakSound, stepSound, placeSound, hitSound, fallSound);
	}
}
