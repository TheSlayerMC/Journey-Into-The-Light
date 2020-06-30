package ru.timeconqueror.timecore.animation;

/**
 * Used in animation manager builder to determine in which layer animation will be played
 */
public class AnimationSetting {
	private final String layerName;
	private final AnimationStarter animationStarter;

	public AnimationSetting(String layerName, AnimationStarter animationStarter) {
		this.layerName = layerName;
		this.animationStarter = animationStarter;
	}

	public String getLayerName() {
		return layerName;
	}

	public AnimationStarter getAnimationStarter() {
		return animationStarter;
	}
}
