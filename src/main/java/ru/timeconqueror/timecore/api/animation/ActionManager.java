package ru.timeconqueror.timecore.api.animation;

import net.minecraft.entity.Entity;
import ru.timeconqueror.timecore.animation.component.DelayedAction;

public interface ActionManager<T extends Entity> {
	<EXTRA_DATA> void enableAction(DelayedAction<T, EXTRA_DATA> action, EXTRA_DATA actionData);

	boolean isActionEnabled(DelayedAction<T, ?> action);

	/**
	 * Removes action from watched ones and stops an animation on the animation layer, represented by {@link DelayedAction#getAnimationLayer()}
	 */
	<EXTRA_DATA> void disableAction(DelayedAction<T, EXTRA_DATA> action);

	AnimationManager getAnimationManager();

	void onTick();
}
