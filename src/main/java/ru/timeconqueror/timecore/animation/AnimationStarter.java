package ru.timeconqueror.timecore.animation;

import net.minecraft.network.PacketBuffer;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.animation.AnimationConstants;
import ru.timeconqueror.timecore.api.animation.AnimationManager;

public class AnimationStarter {
	private final AnimationData data;

	public AnimationStarter(Animation animation) {
		this.data = new AnimationData(animation);
	}

	private AnimationStarter(AnimationData animationData) {
		this.data = animationData.copy();
	}

	public static AnimationStarter fromAnimationData(AnimationData data) {
		return new AnimationStarter(data);
	}

	public AnimationStarter setIgnorable(boolean ignorable) {
		this.data.ignorable = ignorable;
		return this;
	}

	public AnimationStarter setTransitionTime(int transitionTime) {
		data.transitionTime = Math.max(transitionTime, 0);
		return this;
	}

	public AnimationStarter setSpeed(float speedFactor) {
		data.speedFactor = Math.max(speedFactor, 0.0001F);
		return this;
	}

	public AnimationStarter setNextAnimation(AnimationStarter nextAnimationStarter) {
		data.nextAnimationData = nextAnimationStarter.getData();
		return this;
	}

	public void startAt(AnimationManager manager, String layerName) {
		manager.setAnimation(this, layerName);
	}

	public AnimationData getData() {
		return data;
	}

	public static class AnimationData {
		private final Animation animation;
		@Nullable
		private AnimationData nextAnimationData;
		private boolean ignorable = true;
		private int transitionTime = AnimationConstants.BASIC_TRANSITION_TIME;
		private float speedFactor = 1F;

		private AnimationData(Animation animation) {
			this.animation = animation;
		}

		public static void encode(AnimationData animationData, PacketBuffer buffer) {
			buffer.writeResourceLocation(animationData.getAnimation().getId());
			buffer.writeFloat(animationData.getSpeedFactor());
			buffer.writeInt(animationData.getTransitionTime());
			buffer.writeBoolean(animationData.isIgnorable());

			boolean hasNextAnim = animationData.nextAnimationData != null;
			buffer.writeBoolean(hasNextAnim);
			if (hasNextAnim) {
				encode(animationData.nextAnimationData, buffer);
			}
		}

		public static AnimationData decode(PacketBuffer buffer) {
			Animation animation = AnimationRegistry.getAnimation(buffer.readResourceLocation());

			AnimationData animationData = new AnimationData(animation);

			animationData.speedFactor = buffer.readFloat();
			animationData.transitionTime = buffer.readInt();
			animationData.ignorable = buffer.readBoolean();

			boolean hasNextAnim = buffer.readBoolean();
			if (hasNextAnim) {
				animationData.nextAnimationData = decode(buffer);
			}

			return animationData;
		}

		public Animation getAnimation() {
			return animation;
		}

		public float getSpeedFactor() {
			return speedFactor;
		}

		public int getTransitionTime() {
			return transitionTime;
		}

		public boolean isIgnorable() {
			return ignorable;
		}

		public AnimationData copy() {
			AnimationData animationData = new AnimationData(animation);
			animationData.speedFactor = this.speedFactor;
			animationData.ignorable = this.ignorable;
			animationData.transitionTime = this.transitionTime;
			animationData.nextAnimationData = this.nextAnimationData != null ? this.nextAnimationData.copy() : null;

			return animationData;
		}

		@Nullable
		public AnimationData getNextAnimationData() {
			return nextAnimationData;
		}
	}
}
