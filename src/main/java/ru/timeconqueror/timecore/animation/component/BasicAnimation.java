package ru.timeconqueror.timecore.animation.component;

import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.util.vector.Vector3f;
import ru.timeconqueror.timecore.TimeCore;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.animation.AnimationLayer;
import ru.timeconqueror.timecore.api.util.Pair;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;
import ru.timeconqueror.timecore.client.render.model.TimeModel;
import ru.timeconqueror.timecore.client.render.model.TimeModelRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BasicAnimation implements Animation {
	private final boolean loop;
	private final String name;
	private final ResourceLocation id;
	/**
	 * animation length in ms
	 */
	private final int length;

	/**
	 * Unmodifiable map of bone options.
	 * Key - bone name.
	 */
	@Nullable
	private final Map<String, BoneOption> options;

	public BasicAnimation(boolean loop, ResourceLocation id, String name, int length, @Nullable Map<String, BoneOption> options) {
		this.loop = loop;
		this.name = name;
		this.id = id;
		this.length = length;
		this.options = options;
	}

	public void apply(TimeEntityModel model, AnimationLayer layer, int existingTime) {
		TimeModel baseModel = model.getBaseModel();
		if (options != null) {
			if (existingTime <= length) {
				options.forEach((s, boneOption) -> {
					TimeModelRenderer piece = baseModel.getPiece(boneOption.getName());

					if (piece != null) {
						boneOption.apply(this, layer, piece, existingTime);
					} else {
						TimeCore.LOGGER.error("Can't find bone with name " + boneOption.getName() + " in animation " + getName() + " applied for model " + baseModel.getName());
					}
				});
			}
		}
	}

	public int getLength() {
		return length;
	}

	public String getName() {
		return name;
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	public boolean isLooped() {
		return loop;
	}

	public Map<String, BoneOption> getOptions() {
		return options;
	}

	@Override
	public @NotNull Animation.TransitionFactory getTransitionFactory() {
		return new TransitionFactory(this);
	}

	@Override
	public void forEachBone(Consumer<String> action) {
		if (getOptions() != null) {
			getOptions().forEach((s, option) -> action.accept(s));
		}
	}

	public static class TransitionFactory extends Animation.TransitionFactory {
		public TransitionFactory(BasicAnimation source) {
			super(source);
		}

		private static KeyFrame calcStartKeyFrame(BasicAnimation sourceAnimation, @Nullable List<KeyFrame> sourceKeyFrames, float modelIdleX, float modelIdleY, float modelIdleZ, int existingTime) {
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
					return keyFrame.withNewStartTime(transitionTime);
				}
			}

			return KeyFrame.createIdleKeyFrame(transitionTime, modelIdleX, modelIdleY, modelIdleZ);
		}

		@Override
		public @Nullable List<Transition.TransitionBoneOption> createBoneOptions(Animation dest, TimeModel model, int existingTime, int transitionTime) {
			BasicAnimation source = getSourceTyped();
			if (source.getOptions() == null || source.getOptions().isEmpty()) {
				return null;
			}

			Animation.TransitionFactory destFactory = dest.getTransitionFactory();

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
			BasicAnimation dest = getSourceTyped();
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
