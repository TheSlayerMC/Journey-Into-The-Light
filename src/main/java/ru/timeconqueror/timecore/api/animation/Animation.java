package ru.timeconqueror.timecore.api.animation;

import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.animation.component.KeyFrame;
import ru.timeconqueror.timecore.animation.component.Transition;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;
import ru.timeconqueror.timecore.client.render.model.TimeModel;
import ru.timeconqueror.timecore.client.render.model.TimeModelRenderer;

import java.util.List;
import java.util.function.Consumer;

public interface Animation {
	void apply(TimeEntityModel model, AnimationLayer layer, int existingTime);

	/**
	 * Name of the animation, that is indicated in animation file.
	 */
	String getName();

	/**
	 * By default contains the path to the file, from which this animation was parsed,
	 * merged with the animation name from the file.
	 */
	ResourceLocation getId();

	/**
	 * Length in ms
	 */
	int getLength();

	boolean isLooped();

	/**
	 * Should return the factory, that can handle your IAnimation implementation class
	 */
	@NotNull
	Animation.TransitionFactory getTransitionFactory();

	/**
	 * Proceeds some action for each bone.
	 *
	 * @param action action to call for every bone. Consumes bone name.
	 */
	void forEachBone(Consumer<String> action);

	enum OptionType {
		ROTATION,
		POSITION,
		SCALE
	}

	abstract class TransitionFactory {
		/**
		 * Animation, from which transition will start.
		 */
		protected Animation source;

		public TransitionFactory(Animation source) {
			this.source = source;
		}

		@SuppressWarnings("unchecked")
		public <T> T getSourceTyped() {
			return ((T) source);
		}

		/**
		 * Returns list of bones with calculated change vectors.
		 * Returns null if list can't be created due to, for example, lack of bone option list.
		 * In this case transition will be created with more strong dependence to destination animation.
		 * <p>
		 * Will be called only when {@link #source} is a start animation.
		 *
		 * @param dest           animation, to which transition will lead.
		 * @param model          model, for which we apply animation.
		 * @param existingTime   source animation existing time
		 * @param transitionTime time of transition between source and destination animations.
		 */
		@Nullable
		public abstract List<Transition.TransitionBoneOption> createBoneOptions(Animation dest, TimeModel model, int existingTime, int transitionTime);

		/**
		 * Returns destination keyframe of provided type for transition animation.
		 * <p>
		 * Will be called only when {@link #source} is a destination animation.
		 *
		 * @param piece          piece for which destination keyframe should be calculated.
		 * @param boneName       name of bone/piece for which destination keyframe should be calculated.
		 * @param optionType     type of keyframe which should be calculated.
		 * @param transitionTime time of transition between source and destination animations.
		 */
		@NotNull
		public abstract KeyFrame getDestKeyFrame(TimeModelRenderer piece, String boneName, OptionType optionType, int transitionTime);
	}
}
