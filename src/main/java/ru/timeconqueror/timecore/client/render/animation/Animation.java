package ru.timeconqueror.timecore.client.render.animation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.util.vector.Vector3f;
import ru.timeconqueror.timecore.api.client.render.TimeEntityModel;
import ru.timeconqueror.timecore.api.client.render.TimeModel;
import ru.timeconqueror.timecore.api.util.Pair;
import ru.timeconqueror.timecore.client.render.model.TimeModelRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Animation implements IAnimation {
	private boolean loop;
	private String name;
	/**
	 * animation length in ms
	 */
	private int length;

	/**
	 * Unmodifiable map of bone options.
	 * Key - bone name.
	 */
	@Nullable
	private Map<String, BoneOption> options;

	public Animation(boolean loop, String name, int length, @Nullable Map<String, BoneOption> options) {
		this.loop = loop;
		this.name = name;
		this.length = length;
		this.options = options;
	}

	public void apply(TimeEntityModel model, int existingTime) {
		TimeModel baseModel = model.getBaseModel();
		if (options != null) {
			if (existingTime <= length) {
				options.forEach((s, boneOption) -> boneOption.apply(this, baseModel, existingTime));
			}
		}
	}

	public int getLength() {
		return length;
	}

	public String getName() {
		return name;
	}

	public boolean isLooped() {
		return loop;
	}

	public Map<String, BoneOption> getOptions() {
		return options;
	}

	@Override
	public @NotNull IAnimation.TransitionFactory getTransitionFactory() {
		return new TransitionFactory(this);
	}

	@Override
	public void forEachBone(Consumer<String> action) {
		if (getOptions() != null) {
			getOptions().forEach((s, option) -> action.accept(s));
		}
	}

	public static class TransitionFactory extends IAnimation.TransitionFactory {
		public TransitionFactory(Animation source) {
			super(source);
		}

		private static KeyFrame calcStartKeyFrame(Animation sourceAnimation, @Nullable List<KeyFrame> sourceKeyFrames, float modelIdleX, float modelIdleY, float modelIdleZ, int existingTime) {
			if (sourceKeyFrames != null) {
				Pair<KeyFrame, KeyFrame> keyPair = BoneOption.findKeyFrames(sourceKeyFrames, existingTime);
				if (keyPair != null) {
					Vector3f vec = BoneOption.calcCurrentVectorFor(sourceAnimation, keyPair, modelIdleX, modelIdleY, modelIdleZ, existingTime);
					return new KeyFrame(0, vec);
				}
			}

			return KeyFrame.createIdleKeyFrame(0, modelIdleX, modelIdleY, modelIdleZ);
		}

		private static KeyFrame calcEndKeyFrame(@Nullable List<KeyFrame> destKeyFrames, float modelIdleX, float modelIdleY, float modelIdleZ, int transitionTime /*may cause flicking? maybe -1?*/) {
			if (destKeyFrames != null && !destKeyFrames.isEmpty()) {
				KeyFrame keyFrame = destKeyFrames.get(0);
				if (keyFrame.getStartTime() == 0) {
					return keyFrame;
				}
			}

			return KeyFrame.createIdleKeyFrame(transitionTime, modelIdleX, modelIdleY, modelIdleZ);
		}

		@Override
		public @Nullable List<Transition.TransitionBoneOption> createBoneOptions(IAnimation dest, TimeModel model, int existingTime, int transitionTime) {
			Animation source = getSourceTyped();
			if (source.getOptions() == null || source.getOptions().isEmpty()) {
				return null;
			}

			IAnimation.TransitionFactory destFactory = dest.getTransitionFactory();

			List<Transition.TransitionBoneOption> transitionBones = new ArrayList<>();
			source.getOptions().forEach((name, sourceBone) -> {
				TimeModelRenderer piece = model.getPiece(name);
				if (piece != null) {
					// Rotations
					KeyFrame startKeyFrame = calcStartKeyFrame(source, sourceBone.getRotations(), 0, 0, 0, existingTime);
					KeyFrame endKeyFrame = destFactory.getDestKeyFrame(piece, name, OptionType.ROTATION, transitionTime);
					Pair<KeyFrame, KeyFrame> rotations = Pair.of(startKeyFrame, endKeyFrame);

					// Positions
					startKeyFrame = calcStartKeyFrame(source, sourceBone.getPositions(), piece.offsetX, piece.offsetY, piece.offsetZ, existingTime);
					endKeyFrame = destFactory.getDestKeyFrame(piece, name, OptionType.POSITION, transitionTime);
					Pair<KeyFrame, KeyFrame> positions = Pair.of(startKeyFrame, endKeyFrame);

					// Scales
					startKeyFrame = calcStartKeyFrame(source, sourceBone.getScales(), piece.getScaleFactor().getX(), piece.getScaleFactor().getY(), piece.getScaleFactor().getZ(), existingTime);
					endKeyFrame = destFactory.getDestKeyFrame(piece, name, OptionType.SCALE, transitionTime);
					Pair<KeyFrame, KeyFrame> scales = Pair.of(startKeyFrame, endKeyFrame);

					transitionBones.add(new Transition.TransitionBoneOption(name, rotations, positions, scales));
				}
			});

			return transitionBones;
		}

		@Override
		public @NotNull KeyFrame getDestKeyFrame(TimeModelRenderer piece, String boneName, OptionType optionType, int transitionTime) {
			Animation dest = getSourceTyped();
			boolean destContainsSameBone = dest.getOptions() != null && dest.getOptions().containsKey(boneName);
			BoneOption destBone = destContainsSameBone ? dest.getOptions().get(boneName) : null;

			if (optionType == OptionType.ROTATION) {
				if (destBone != null) {
					return calcEndKeyFrame(destBone.getRotations(), 0, 0, 0, transitionTime);
				} else {
					return KeyFrame.createIdleKeyFrame(transitionTime, 0, 0, 0);
				}
			} else if (optionType == OptionType.POSITION) {
				if (destBone != null) {
					return calcEndKeyFrame(destBone.getPositions(), piece.offsetX, piece.offsetY, piece.offsetZ, transitionTime);
				} else {
					return KeyFrame.createIdleKeyFrame(transitionTime, piece.offsetX, piece.offsetY, piece.offsetZ);
				}
			} else if (optionType == OptionType.SCALE) {
				if (destBone != null) {
					return calcEndKeyFrame(destBone.getScales(), piece.getScaleFactor().getX(), piece.getScaleFactor().getY(), piece.getScaleFactor().getZ(), transitionTime);
				} else {
					return KeyFrame.createIdleKeyFrame(transitionTime, piece.getScaleFactor().getX(), piece.getScaleFactor().getY(), piece.getScaleFactor().getZ());
				}
			}

			throw new UnsupportedOperationException("Can't handle " + optionType + " option type");
		}
	}
}
