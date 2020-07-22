package ru.timeconqueror.timecore.animation.watcher;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.animation.AnimationRegistry;
import ru.timeconqueror.timecore.animation.component.Transition;
import ru.timeconqueror.timecore.animation.util.WatcherSerializer;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.util.Requirements;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

public class TransitionWatcher extends AnimationWatcher {
	private final int transitionTime;
	private final float destAnimSpeedFactor;
	@Nullable
	private final Animation destination;
	@Nullable
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

	public static class Serializer implements WatcherSerializer<TransitionWatcher> {
		@Override
		public void serialize(TransitionWatcher watcher, PacketBuffer buffer) {
			boolean hasSource = watcher.source != null;
			buffer.writeBoolean(hasSource);
			if (hasSource) {
				buffer.writeResourceLocation(watcher.source.getId());
				buffer.writeInt(watcher.sourceExistingTime);
			}

			boolean hasDestination = watcher.destination != null;
			buffer.writeBoolean(hasDestination);
			if (hasDestination) {
				buffer.writeResourceLocation(watcher.destination.getId());
				buffer.writeFloat(watcher.destAnimSpeedFactor);
			}

			int transitionTime = Math.max(watcher.getAnimation().getLength() - watcher.getExistingTime(), 0);
			buffer.writeInt(transitionTime);
		}

		@Override
		public TransitionWatcher deserialize(PacketBuffer buffer) {
			boolean hasSource = buffer.readBoolean();

			Animation source = null;
			int sourceExistingTime = -1;
			if (hasSource) {
				ResourceLocation id = buffer.readResourceLocation();
				source = AnimationRegistry.getAnimation(id);
				sourceExistingTime = buffer.readInt();
			}

			boolean hasDestination = buffer.readBoolean();
			Animation destination = null;
			float destAnimSpeedFactor = -1;
			if (hasDestination) {
				ResourceLocation id = buffer.readResourceLocation();
				destination = AnimationRegistry.getAnimation(id);
				destAnimSpeedFactor = buffer.readFloat();
			}

			int transitionTime = buffer.readInt();

			return new TransitionWatcher(source, sourceExistingTime, transitionTime, destination, destAnimSpeedFactor);
		}
	}
}
