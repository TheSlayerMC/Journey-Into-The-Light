package ru.timeconqueror.timecore.api.animation;

import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.animation.watcher.AnimationWatcher;

public interface AnimationLayer extends Cloneable {
	float getWeight();

	void setWeight(float weight);

	BlendType getBlendType();

	void setBlendType(BlendType type);

	@Nullable
	Animation getCurrentAnimation();

	boolean hasAnimation();

	@Nullable
	AnimationWatcher getAnimationWatcher();

	String getName();
}
