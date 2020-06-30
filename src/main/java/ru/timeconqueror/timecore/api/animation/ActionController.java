package ru.timeconqueror.timecore.api.animation;

import net.minecraft.entity.Entity;
import ru.timeconqueror.timecore.animation.component.DelayedAction;

public interface ActionController<T extends Entity> {
    void enableAction(DelayedAction<T> action);

    boolean isActionEnabled(DelayedAction<T> action);

    /**
     * Removes action from watched ones and stops an animation on the animation layer, represented by {@link DelayedAction#getAnimationLayer()}
     */
    void disableAction(DelayedAction<T> action);

    AnimationManager getAnimationManager();

    void onTick();
}
