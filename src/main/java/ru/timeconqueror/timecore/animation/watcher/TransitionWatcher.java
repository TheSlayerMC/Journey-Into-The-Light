package ru.timeconqueror.timecore.animation.watcher;

import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.animation.component.Transition;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.util.Requirements;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

public class TransitionWatcher extends AnimationWatcher {
	private final int transitionTime;
	private final float destAnimSpeedFactor;
	@Nullable
	private final Animation destination;

	private final Animation source;
	private final int sourceExistingTime;

	//from null source
	public TransitionWatcher(int transitionTime, @Nullable Animation destination, float destAnimSpeedFactor) {
		this(null, 0, transitionTime, destination, destAnimSpeedFactor);
	}

	public TransitionWatcher(@Nullable Animation source, int sourceExistingTime, int transitionTime, @Nullable Animation destination, float destAnimSpeedFactor) {
		super(null, 1.0F);

		Requirements.greaterOrEqualsThan(transitionTime, 0);
		if (destination != null) Requirements.greaterThan(destAnimSpeedFactor, 0);

		this.transitionTime = transitionTime;
		this.destAnimSpeedFactor = destAnimSpeedFactor;
		this.destination = destination;
		this.source = source;
		this.sourceExistingTime = sourceExistingTime;
	}

	@Override
	public void init(TimeEntityModel model) {
		super.init(model);

		if (model != null) {
			animation = Transition.create(source, sourceExistingTime, destination, model.getBaseModel(), transitionTime);
		} else {
			animation = Transition.createForServer(source, destination, transitionTime);
		}
	}

    @Override
    @Nullable
    public AnimationWatcher next() {
        return destination != null ? new AnimationWatcher(destination, destAnimSpeedFactor) : null;
    }

    public @Nullable Animation getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "TransitionWatcher{" +
                "startTime=" + startTime +
                ", animation=" + animation +
                ", speed=" + speed +
                ", transitionTime=" + transitionTime +
                ", source=" + source +
                ", sourceExistingTime=" + sourceExistingTime +
                ", destAnimSpeedFactor=" + destAnimSpeedFactor +
                ", destination=" + destination +
                '}';
    }
}
