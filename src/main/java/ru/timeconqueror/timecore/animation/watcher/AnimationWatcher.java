package ru.timeconqueror.timecore.animation.watcher;

import net.minecraft.network.PacketBuffer;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.animation.AnimationRegistry;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.animation.util.WatcherSerializer;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.animation.AnimationConstants;
import ru.timeconqueror.timecore.api.util.Requirements;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.Objects;

public class AnimationWatcher {
    protected final FreezableTime startTime;
    /**
     * Speed factor of the animation
     */
    protected final float speed;
    private boolean inited = false;
    protected Animation animation;
    @Nullable
    private final AnimationStarter.AnimationData nextAnimation;

    public AnimationWatcher(AnimationStarter.AnimationData currentAnimation) {
        this(currentAnimation.getAnimation(), currentAnimation.getSpeedFactor(), currentAnimation.getNextAnimationData());
    }

    public AnimationWatcher(Animation animation, float speed, @Nullable AnimationStarter.AnimationData nextAnimation) {
        Requirements.greaterThan(speed, 0);
        this.startTime = new FreezableTime(System.currentTimeMillis());
        this.animation = animation;
        this.speed = speed;
        this.nextAnimation = nextAnimation;
    }

    public boolean requiresInit() {
        return !inited;
    }

    @OverridingMethodsMustInvokeSuper
    public void init(TimeEntityModel model) {
        inited = true;
    }

    @Nullable
    public AnimationWatcher next() {
        if (getAnimation().isLooped()) {
            resetTimer();

            return this;
        } else if (nextAnimation != null) {
            return new AnimationWatcher(nextAnimation);
        } else {
            return new TransitionWatcher(getAnimation(), getExistingTime(), AnimationConstants.BASIC_TRANSITION_TIME, null);
        }
    }

    public boolean isAnimationEnded(long time) {
        return time > startTime.get() + Math.round(animation.getLength() / speed);
    }

    public void resetTimer() {
        startTime.set(System.currentTimeMillis());
    }

    public Animation getAnimation() {
        return animation;
    }

    public int getExistingTime(long time) {
        return (int) ((int) (time - (startTime.get())) * speed);
    }

    public int getExistingTime() {
        return getExistingTime(System.currentTimeMillis());
    }

    public void freeze() {
        startTime.freeze();
    }

    public void unfreeze() {
        startTime.unfreeze();
    }

    @Override
    public String toString() {
        return "AnimationWatcher {" +
                "animation=" + animation +
                ", existingTime=" + getExistingTime() +
                ", speed=" + speed +
                ", inited=" + inited +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnimationWatcher)) return false;
        AnimationWatcher watcher = (AnimationWatcher) o;
        return Float.compare(watcher.speed, speed) == 0 &&
                animation.equals(watcher.animation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speed, animation);
    }

    protected static class FreezableTime {
        private long time;
        private long freezingTime = -1;

        public FreezableTime(long time) {
            this.time = time;
        }

        public void freeze() {
            if (freezingTime == -1) {
                freezingTime = System.currentTimeMillis();
            }
        }

        public void unfreeze() {
            if (freezingTime != -1) {
                time += System.currentTimeMillis() - freezingTime;
                freezingTime = -1;
            }
        }

        public long get() {
            if (freezingTime != -1) {
                return time + (System.currentTimeMillis() - freezingTime);
            } else {
                return time;
            }
        }

        public void set(long time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "FreezableTime{" +
                    "startTime=" + time + "ms" +
                    ", beingFrozen=" + (System.currentTimeMillis() - freezingTime) + "ms" +
                    '}';
        }
    }

    public static class Serializer implements WatcherSerializer<AnimationWatcher> {
        public void serialize(AnimationWatcher watcher, PacketBuffer buffer) {
            buffer.writeResourceLocation(watcher.getAnimation().getId());
            buffer.writeInt(watcher.getExistingTime());
            buffer.writeFloat(watcher.speed);

            boolean hasNextAnimation = watcher.nextAnimation != null;
            buffer.writeBoolean(watcher.nextAnimation != null);
            if (hasNextAnimation) {
                AnimationStarter.AnimationData.encode(watcher.nextAnimation, buffer);
            }
        }

        public AnimationWatcher deserialize(PacketBuffer buffer) {
            Animation animation = AnimationRegistry.getAnimation(buffer.readResourceLocation());
            int existingTime = buffer.readInt();
            float speed = buffer.readFloat();

            AnimationStarter.AnimationData nextAnimationData = null;
            boolean hasNextAnimation = buffer.readBoolean();
            if (hasNextAnimation) {
                nextAnimationData = AnimationStarter.AnimationData.decode(buffer);
            }

            AnimationWatcher watcher = new AnimationWatcher(animation, speed, nextAnimationData);
            watcher.startTime.set(System.currentTimeMillis() - existingTime);

            return watcher;
        }
    }
}
