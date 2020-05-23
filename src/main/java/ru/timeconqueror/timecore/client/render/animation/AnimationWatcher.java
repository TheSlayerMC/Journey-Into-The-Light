package ru.timeconqueror.timecore.client.render.animation;

import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.util.Requirements;

public class AnimationWatcher {
	private long startTime;
	private IAnimation animation;
	private TransitionData transitionData;

	public AnimationWatcher(IAnimation animation) {
		this.animation = animation;
		resetTimer();
	}

	public boolean isAnimationEnded(long time) {
		return time > startTime + animation.getLength();
	}

	public void resetTimer() {
		startTime = System.currentTimeMillis();
	}

	public IAnimation getAnimation() {
		return animation;
	}

	public int getExistingTime(long time) {
		return (int) (time - startTime);
	}

	public int getExistingTime() {
		return getExistingTime(System.currentTimeMillis());
	}

	public void enableTransitionMode(@Nullable IAnimation destination, int transitionTime) {
		Requirements.greaterOrEqualsThan(transitionTime, 0);
		transitionData = new TransitionData(transitionTime, destination);
	}

	public boolean requiresTransitionPreparation() {
		return transitionData != null;
	}

	@Nullable
	public IAnimation getTransitionDestination() {
		return transitionData.animation;
	}

	public int getTransitionTime() {
		return transitionData.transitionTime;
	}

	private static class TransitionData {
		private int transitionTime;
		@Nullable
		private IAnimation animation;

		public TransitionData(int transitionTime, @Nullable IAnimation animation) {
			this.transitionTime = transitionTime;
			this.animation = animation;
		}
	}
}
