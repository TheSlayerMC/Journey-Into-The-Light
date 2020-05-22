package ru.timeconqueror.timecore.api.client.render.animation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.client.render.TimeEntityModel;
import ru.timeconqueror.timecore.api.util.Requirements;
import ru.timeconqueror.timecore.client.render.animation.AnimationWatcher;
import ru.timeconqueror.timecore.client.render.animation.IAnimation;
import ru.timeconqueror.timecore.client.render.animation.Transition;

public class AnimationManager {
    @Nullable
    private AnimationWatcher currentAnimationWatcher;

    public void startAnimationIgnorable(@NotNull IAnimation animation, int transitionTime) {
        if (currentAnimationWatcher != null && animation.equals(currentAnimationWatcher.getAnimation())) return;

        startAnimationWithTransition(animation, transitionTime);
    }

    public void startAnimationWithTransition(@NotNull IAnimation animation, int transitionTime) {
        Requirements.greaterOrEqualsThan(transitionTime, 0);

        if (currentAnimationWatcher == null) {
            currentAnimationWatcher = new AnimationWatcher(null);
        }

        currentAnimationWatcher.enableTransitionMode(animation, transitionTime);
    }

    public void removeAnimation(int transitionTime) {
        Requirements.greaterOrEqualsThan(transitionTime, 0);
        if (currentAnimationWatcher != null) {
            if (transitionTime == 0) {
                currentAnimationWatcher = null;
            } else {
                currentAnimationWatcher.enableTransitionMode(null, transitionTime);
            }
        }
    }

    public void processAnimations(TimeEntityModel model) {
        if (currentAnimationWatcher != null && currentAnimationWatcher.requiresTransitionPreparation()) {
            IAnimation transition = Transition.create(currentAnimationWatcher, currentAnimationWatcher.getTransitionDestination(), model.getBaseModel(), currentAnimationWatcher.getTransitionTime());
            currentAnimationWatcher = new AnimationWatcher(transition);
        }

        long time = System.currentTimeMillis();

        if (currentAnimationWatcher != null) {
            if (currentAnimationWatcher.isAnimationEnded(time)) {
                if (currentAnimationWatcher.getAnimation() instanceof Transition) {
                    IAnimation anim = ((Transition) currentAnimationWatcher.getAnimation()).getDestAnimation();
                    currentAnimationWatcher = anim != null ? new AnimationWatcher(anim) : null;
                } else if (currentAnimationWatcher.getAnimation().isLooped()) {
                    currentAnimationWatcher.resetTimer();
                } else {
                    currentAnimationWatcher = null;
                }
            }
        }

        if (currentAnimationWatcher != null) {
            IAnimation animation = currentAnimationWatcher.getAnimation();
            animation.apply(model, currentAnimationWatcher.getExistingTime(time));
        }
    }
}
