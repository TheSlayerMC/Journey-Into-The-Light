package ru.timeconqueror.timecore.animation.util;

import ru.timeconqueror.timecore.animation.watcher.AnimationWatcher;
import ru.timeconqueror.timecore.api.util.Requirements;

import java.util.function.Predicate;

public class StandardDelayPredicates {
	public static Predicate<AnimationWatcher> onStart() {
		return watcher -> true;
	}

	public static Predicate<AnimationWatcher> onEnd() {
		return watcher -> watcher.getExistingTime() >= watcher.getAnimation().getLength();
	}

	public static Predicate<AnimationWatcher> whenPassed(int animationTime) {
		return watcher -> watcher.getExistingTime() >= animationTime;
	}

	public static Predicate<AnimationWatcher> whenPassed(float percents) {
		Requirements.inRangeInclusive(percents, 0, 1);

		return watcher -> {
			float length = watcher.getAnimation().getLength();
			float existingTime = watcher.getExistingTime();

			return existingTime >= length * percents;
		};
	}
}
