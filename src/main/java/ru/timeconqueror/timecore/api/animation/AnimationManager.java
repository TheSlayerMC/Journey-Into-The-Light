package ru.timeconqueror.timecore.api.animation;

import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

public interface AnimationManager {

	/**
	 * Returns true, if this animation manager contains the layer with provided name,
	 * otherwise returns false.
	 */
	boolean containsLayer(String name);

	/**
	 * Returns layer object by its name.
	 *
	 * @throws RuntimeException if layer is not found,
	 *                          so you should check existing of the layer in {@link #containsLayer(String)} firstly.
	 */
	@NotNull
	AnimationLayer getLayer(String name);

	/**
	 * On client: called on every frame for model from the renderer of entity, which contains this manager.
	 * On server: called on every tick and with null model as a param,
	 * since no operations with model shouldn't be done on server (because no model exists on server side).
	 *
	 * @param model model to perform calculations on it.
	 *              <p>
	 *              on client: model of the bound entity;<p>
	 *              on server: null
	 */
	void applyAnimations(TimeEntityModel model);

	/**
	 * Sets animation data to start new animation in the layer with provided name.
	 *
	 * @see AnimationStarter#startAt(AnimationManager, String)
	 */
	void setAnimation(AnimationStarter animationStarter, String layerName);

	/**
	 * Removes animation from the layer with provided name.
	 * Transition name is default here: {@link AnimationConstants#BASIC_TRANSITION_TIME}
	 *
	 * @param layerName name of the layer, where you need to remove animation.
	 */
	void removeAnimation(String layerName);

	/**
	 * Removes animation from the layer with provided name.
	 *
	 * @param layerName      name of the layer, where you need to remove animation.
	 * @param transitionTime time of transition to the idle state.
	 *                       If this value is bigger than 0, then transition will be created, which will smoothly end current animation.
	 */
	void removeAnimation(String layerName, int transitionTime);
}
