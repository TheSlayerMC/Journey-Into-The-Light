package ru.timeconqueror.timecore.animation.watcher;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.animation.AnimationRegistry;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.animation.component.Transition;
import ru.timeconqueror.timecore.animation.util.WatcherSerializer;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.util.Requirements;
import ru.timeconqueror.timecore.client.render.model.TimeEntityModel;

import java.util.Objects;

public class TransitionWatcher extends AnimationWatcher {
	private final int transitionTime;
	@Nullable
	private final AnimationStarter.AnimationData destination;
	@Nullable
	private final Animation source;
	private final int sourceExistingTime;

	//from null source
	public TransitionWatcher(int transitionTime, @Nullable AnimationStarter.AnimationData destination) {
		this(null, 0, transitionTime, destination);
	}

	public TransitionWatcher(@Nullable Animation source, int sourceExistingTime, int transitionTime, @Nullable AnimationStarter.AnimationData destination) {
		super(null, 1.0F, destination);

		Requirements.greaterOrEqualsThan(transitionTime, 0);

		this.transitionTime = transitionTime;
		this.destination = destination;
		this.source = source;
		this.sourceExistingTime = sourceExistingTime;
	}

	@Override
	public void init(TimeEntityModel model) {
		super.init(model);

		if (model != null) {
			animation = Transition.create(source, sourceExistingTime, getDestination(), model.getBaseModel(), transitionTime);
		} else {
			animation = Transition.createForServer(source, getDestination(), transitionTime);
		}
	}

	@Override
	@Nullable
	public AnimationWatcher next() {
		return destination != null ? new AnimationWatcher(destination) : null;
	}

	public @Nullable Animation getDestination() {
		return destination != null ? destination.getAnimation() : null;
	}

	@Override
	public int getAnimationLength() {
		return transitionTime;
	}

	@Override
	public String toString() {
		return "TransitionWatcher{" +
				"animation=" + animation +
				", existingTime=" + getExistingTime() +
				", speed=" + speed +
				", transitionTime=" + transitionTime +
				", source=" + source +
				", sourceExistingTime=" + sourceExistingTime +
				", destination=" + destination +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TransitionWatcher)) return false;
		TransitionWatcher that = (TransitionWatcher) o;

		boolean destEquals = false;
		if (destination == null && that.destination == null) {
			destEquals = true;
		} else if (destination != null && that.destination != null) {
			if (destination.getAnimation().equals(that.destination.getAnimation())
					&& Float.compare(destination.getSpeedFactor(), that.destination.getSpeedFactor()) == 0) {
				destEquals = true;
			}
		}

		return destEquals &&
				transitionTime == that.transitionTime &&
				Objects.equals(source, that.source);
	}

	@Override
	public int hashCode() {
		Animation dest = getDestination();
		float destSpeed = destination != null ? destination.getSpeedFactor() : 0;

		return Objects.hash(super.hashCode(), transitionTime, dest, destSpeed, source);
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
				AnimationStarter.AnimationData.encode(watcher.destination, buffer);
			}

			int transitionTime = Math.max(watcher.getAnimationLength() - watcher.getExistingTime(), 0);
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

			AnimationStarter.AnimationData destination = null;
			boolean hasDestination = buffer.readBoolean();
			if (hasDestination) {
				destination = AnimationStarter.AnimationData.decode(buffer);
			}

			int transitionTime = buffer.readInt();

			return new TransitionWatcher(source, sourceExistingTime, transitionTime, destination);
		}
	}
}
