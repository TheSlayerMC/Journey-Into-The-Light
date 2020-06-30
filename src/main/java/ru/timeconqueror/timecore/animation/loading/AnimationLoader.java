package ru.timeconqueror.timecore.animation.loading;

import net.minecraft.util.ResourceLocation;
import ru.timeconqueror.timecore.TimeCore;
import ru.timeconqueror.timecore.api.animation.Animation;

import java.util.Map;

public class AnimationLoader {
	public static Map<String, Animation> loadAnimations(ResourceLocation location) {
		try {
			return new JsonAnimationParser().parseAnimations(location);
		} catch (Throwable e) {
			TimeCore.LOGGER.error("Can't load animation " + location.toString(), e);
		}

		throw new RuntimeException();//TODO add empty list with dummy animation
	}

	public static Animation loadAnimation(ResourceLocation location) {
		Map<String, Animation> animations = loadAnimations(location);

		if (animations.size() != 1) {
			throw new RuntimeException("Can't load animation " + location.toString() + " due to the file contains more than one animation. Use #loadAnimations method instead.");
		}

		return animations.values().iterator().next();
	}
}
