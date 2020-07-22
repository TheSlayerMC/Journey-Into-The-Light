package ru.timeconqueror.timecore.animation;

import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.animation.watcher.AnimationWatcher;
import ru.timeconqueror.timecore.animation.watcher.TransitionWatcher;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.animation.AnimationLayer;
import ru.timeconqueror.timecore.api.animation.BlendType;
import ru.timeconqueror.timecore.api.util.MathUtils;

public class Layer implements AnimationLayer {
	private int priority;
	private String name;

	@Nullable
	private AnimationWatcher animationWatcher;
	private BlendType blendType;
	private float weight;

	public Layer(String name, int priority, BlendType blendType, float weight) {
		this.name = name;
		this.weight = MathUtils.coerceInRange(weight, 0, 1);
		this.blendType = blendType;
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

	@Override
	public float getWeight() {
		return weight;
	}

	@Override
	public void setWeight(float weight) {
		this.weight = weight;
	}

	@Override
	public BlendType getBlendType() {
		return blendType;
	}

	@Override
	public void setBlendType(BlendType type) {
		blendType = type;
	}

	@Override
	public @Nullable Animation getCurrentAnimation() {
		return hasAnimation() ? getAnimationWatcher().getAnimation() : null;
	}

	@Override
	public boolean hasAnimation() {
		return getAnimationWatcher() != null && getAnimationWatcher().getAnimation() != null;
	}

	void setAnimation(AnimationStarter.AnimationData data) {
		if (data.getTransitionTime() == 0) {
			animationWatcher = new AnimationWatcher(data.getAnimation(), data.getSpeedFactor());
		} else {
			if (animationWatcher == null) {
				animationWatcher = new TransitionWatcher(data.getTransitionTime(), data.getAnimation(), data.getSpeedFactor());
			} else {
				animationWatcher = new TransitionWatcher(animationWatcher.getAnimation(), animationWatcher.getExistingTime(), data.getTransitionTime(), data.getAnimation(), data.getSpeedFactor());
			}
		}
	}

	void removeAnimation(int transitionTime) {
		if (animationWatcher != null) {
			if (transitionTime == 0) {
				animationWatcher = null;
			} else {
				if (!(animationWatcher instanceof TransitionWatcher && ((TransitionWatcher) animationWatcher).getDestination() == null)) {
					animationWatcher = new TransitionWatcher(animationWatcher.getAnimation(), animationWatcher.getExistingTime(), transitionTime, null, -1);
				}
			}
		}
	}

	@Nullable
	public AnimationWatcher getAnimationWatcher() {
		return animationWatcher;
	}

	public void setAnimationWatcher(@Nullable AnimationWatcher animationWatcher) {
		this.animationWatcher = animationWatcher;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	protected Layer clone() throws CloneNotSupportedException {
		if (this.animationWatcher != null) {
			throw new CloneNotSupportedException("Can't clone this layer, it's already in work.");
		}

		Layer clone = (Layer) super.clone();
		clone.name = name;
		clone.priority = priority;
		clone.blendType = blendType;
		clone.weight = weight;

		return clone;
	}
}