package ru.timeconqueror.timecore.client.render.animation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.client.render.TimeEntityModel;
import ru.timeconqueror.timecore.api.client.render.TimeModel;
import ru.timeconqueror.timecore.client.render.model.TimeModelRenderer;

import java.util.List;
import java.util.function.Consumer;

public interface IAnimation {
	void apply(TimeEntityModel model, int existingTime);

	String getName();

	/**
	 * in ms
	 */
	int getLength();

	boolean isLooped();

	/**
	 * Should return the factory, that can handle your IAnimation implementation class
	 */
	@NotNull
	IAnimation.TransitionFactory getTransitionFactory();

	/**
	 * Proceeds action for each bone.
	 *
	 * @param action action to call for every bone. Should consume bone name.
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
		protected IAnimation source;

		public TransitionFactory(IAnimation source) {
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
		public abstract List<Transition.TransitionBoneOption> createBoneOptions(IAnimation dest, TimeModel model, int existingTime, int transitionTime);

		/**
		 * TODO
		 * <p>
		 * Will be called only when {@link #source} is a destination animation.
		 *
		 * @param boneName
		 * @param optionType
		 * @param transitionTime
		 * @return
		 */
		@NotNull
		public abstract KeyFrame getDestKeyFrame(TimeModelRenderer piece, String boneName, OptionType optionType, int transitionTime);
	}
}
