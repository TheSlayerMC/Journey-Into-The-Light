package ru.timeconqueror.timecore.animation.component;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.animation.util.StandardDelayPredicates;
import ru.timeconqueror.timecore.animation.watcher.AnimationWatcher;
import ru.timeconqueror.timecore.api.animation.Animation;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DelayedAction<T extends Entity> {
    private final AnimationStarter animationStarter;
    private final String animationLayer;
    private final ResourceLocation id;
    private Predicate<AnimationWatcher> actionDelayPredicate = StandardDelayPredicates.onStart();
    private Consumer<? super T> action = t -> {
    };

    public DelayedAction(ResourceLocation id, AnimationStarter animationStarter, String animationLayer) {
        this.id = id;
        this.animationStarter = animationStarter;
        this.animationLayer = animationLayer;
    }

    public DelayedAction<T> setOnCall(Consumer<? super T> action) {
        this.action = action;

        return this;
    }

    public DelayedAction<T> setDelayPredicate(Predicate<AnimationWatcher> delayPredicate) {
        this.actionDelayPredicate = delayPredicate;

        return this;
    }

    public Consumer<? super T> getAction() {
        return action;
    }

    public AnimationStarter getAnimationStarter() {
        return animationStarter;
    }

    public Predicate<AnimationWatcher> getActionDelayPredicate() {
        return actionDelayPredicate;
    }

    public String getAnimationLayer() {
        return animationLayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DelayedAction)) return false;
        DelayedAction<?> action = (DelayedAction<?>) o;
        return id.equals(action.id);
    }

    public boolean isBound(Animation animation) {
        return animationStarter.getData().getAnimation().equals(animation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
