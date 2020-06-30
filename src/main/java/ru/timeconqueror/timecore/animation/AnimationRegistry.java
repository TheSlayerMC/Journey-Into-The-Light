package ru.timeconqueror.timecore.animation;

import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.animation.Animation;

import java.util.HashMap;
import java.util.Map;

public class AnimationRegistry {
	private static final Map<ResourceLocation, Animation> animationMap = new HashMap<>();

	public static Animation registerAnimation(Animation animation) {
		if (animationMap.put(animation.getId(), animation) != null) {
			throw new IllegalArgumentException("Animation with id " + animation.getId() + " is already registered.");
		}

		return animation;
	}

	@Nullable
	public static Animation getAnimation(ResourceLocation id) {
		return animationMap.get(id);
	}
}
