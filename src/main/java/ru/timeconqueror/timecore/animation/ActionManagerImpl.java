package ru.timeconqueror.timecore.animation;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import ru.timeconqueror.timecore.TimeCore;
import ru.timeconqueror.timecore.animation.component.DelayedAction;
import ru.timeconqueror.timecore.animation.watcher.AnimationWatcher;
import ru.timeconqueror.timecore.api.animation.ActionManager;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.animation.AnimationManager;
import ru.timeconqueror.timecore.api.util.MathUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ActionManagerImpl<T extends EntityLivingBase> implements ActionManager<T> {
	private final Set<ActionWatcher<T, ?>> actionWatchers = new HashSet<>();
	private final BaseAnimationManager animationManager;
	private final T entity;

	public ActionManagerImpl(BaseAnimationManager animationManager, T entity) {
		this.animationManager = animationManager;
		this.entity = entity;
	}

	@Override
	public <EXTRA_DATA> void enableAction(DelayedAction<T, EXTRA_DATA> action, EXTRA_DATA actionData) {
		if (actionWatchers.add(new ActionWatcher<>(action, actionData))) {
			action.getAnimationStarter().startAt(animationManager, action.getAnimationLayer());
		}
	}

	@Override
	public boolean isActionEnabled(DelayedAction<T, ?> action) {
		for (ActionWatcher<T, ?> actionWatcher : actionWatchers) {
			if (actionWatcher.stores(action)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public <EXTRA_DATA> void disableAction(DelayedAction<T, EXTRA_DATA> action) {
		if (actionWatchers.removeIf(watcher -> watcher.action.equals(action))) {
			animationManager.removeAnimation(action.getAnimationLayer());
		}
	}

	@Override
	public void onTick() {
		if (entity.world.isRemote) {
			AnimationSetting walkingAnimationSetting = animationManager.getWalkingAnimationSetting();

			if (walkingAnimationSetting != null) {
				if (animationManager.containsLayer(walkingAnimationSetting.getLayerName())) {
					// floats of movement can be almost the same (like 0 and 0.000000001), so entity moves a very short distance, which is invisible for eyes.
					// this can be because of converting coords to bytes to send them to client.
					// so checking if it's more than 1/256 of the block will fix the issue
					boolean posChanged = MathUtils.calcDifference(entity.posX, entity.prevPosX) >= 1 / 256F
							|| MathUtils.calcDifference(entity.posY, entity.prevPosY) >= 1 / 256F
							|| MathUtils.calcDifference(entity.posZ, entity.prevPosZ) >= 1 / 256F;

					if (posChanged) {
						walkingAnimationSetting.getAnimationStarter().startAt(animationManager, walkingAnimationSetting.getLayerName());
					} else {
						animationManager.removeAnimation(walkingAnimationSetting.getLayerName());
					}
				} else {
					TimeCore.LOGGER.error("Walking animation for entity {} is set up to be displayed on layer '{}', but this layer doesn't exist.", entity.getClass(), walkingAnimationSetting.getLayerName());
				}
			}
		}
	}

	@Override
	public AnimationManager getAnimationManager() {
		return animationManager;
	}

	public Set<ActionWatcher<T, ?>> getActionWatchers() {
		return actionWatchers;
	}

	public T getEntity() {
		return entity;
	}

	public static class ActionWatcher<T extends Entity, EXTRA_DATA> {
		private final DelayedAction<T, EXTRA_DATA> action;
		private final EXTRA_DATA actionData;
		private boolean done;

		public ActionWatcher(DelayedAction<T, EXTRA_DATA> action, EXTRA_DATA actionData) {
			this.action = action;
			this.actionData = actionData;
		}

		public boolean isBound(Animation animation) {
			return action.isBound(animation);
		}

		public boolean stores(DelayedAction<T, ?> action) {
			return this.action.equals(action);
		}

		public boolean shouldBeExecuted(AnimationWatcher watcherWithBoundAnimation) {
			return !done && action.getActionDelayPredicate().test(watcherWithBoundAnimation);
		}

		public void runAction(T entity) {
			action.getAction().accept(entity, actionData);
			done = true;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof ActionWatcher)) return false;
			ActionWatcher<?, ?> that = (ActionWatcher<?, ?>) o;
			return action.equals(that.action);
		}

		@Override
		public int hashCode() {
			return Objects.hash(action);
		}
	}
}
