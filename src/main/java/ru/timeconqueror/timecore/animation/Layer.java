package ru.timeconqueror.timecore.animation;

import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.animation.watcher.AnimationWatcher;
import ru.timeconqueror.timecore.animation.watcher.TransitionWatcher;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.animation.AnimationLayer;
import ru.timeconqueror.timecore.api.animation.BlendType;
import ru.timeconqueror.timecore.api.util.MathUtils;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

public class Layer implements AnimationLayer {
	private String name;

	@Nullable
	private AnimationWatcher animationWatcher;
	private BlendType blendType;
	private float weight;

	public Layer(String name, BlendType blendType, float weight) {
		this.name = name;
		this.weight = MathUtils.coerceInRange(weight, 0, 1);
		this.blendType = blendType;
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
			animationWatcher = new AnimationWatcher(data.getAnimation(), data.getSpeedFactor(), data.getNextAnimationData());
		} else {
			if (animationWatcher == null) {
				animationWatcher = new TransitionWatcher(data.getTransitionTime(), data);
			} else {
				animationWatcher = new TransitionWatcher(animationWatcher.getAnimation(), animationWatcher.getExistingTime(), data.getTransitionTime(), data);
			}
		}
	}

	void removeAnimation(int transitionTime) {
		if (animationWatcher != null) {
			if (transitionTime == 0) {
				animationWatcher = null;
			} else {
				if (!(animationWatcher instanceof TransitionWatcher && ((TransitionWatcher) animationWatcher).getDestination() == null)) {
					animationWatcher = new TransitionWatcher(animationWatcher.getAnimation(), animationWatcher.getExistingTime(), transitionTime, null);
				}
			}
		}
	}

	void update(BaseAnimationManager manager, TimeEntityModel model, long currentTime) {
		boolean paused = manager.isGamePaused();

		AnimationWatcher watcher = getAnimationWatcher();

		if (watcher != null) {
			if (paused) {
				watcher.freeze();
			} else {
				watcher.unfreeze();

				if (watcher.requiresInit()) {
					watcher.init(model);
				}

				if (watcher.isAnimationEnded(currentTime)) {
					manager.onAnimationEnd(model, this, watcher);

					watcher = watcher.next();

					if (watcher != null && watcher.requiresInit()) {
						watcher.init(model);
					}

					setAnimationWatcher(watcher);//here we update current watcher
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
		clone.blendType = blendType;
		clone.weight = weight;

		return clone;
	}
}