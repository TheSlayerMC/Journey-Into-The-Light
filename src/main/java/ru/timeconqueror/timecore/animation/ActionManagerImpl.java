package ru.timeconqueror.timecore.animation;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import ru.timeconqueror.timecore.TimeCore;
import ru.timeconqueror.timecore.animation.component.DelayedAction;
import ru.timeconqueror.timecore.animation.watcher.AnimationWatcher;
import ru.timeconqueror.timecore.api.animation.ActionManager;
import ru.timeconqueror.timecore.api.animation.Animation;
import ru.timeconqueror.timecore.api.animation.AnimationManager;

import java.util.ArrayList;
import java.util.List;

public class ActionManagerImpl<T extends EntityMob> implements ActionManager<T> {
	private final List<ActionWatcher<T>> actionWatchers = new ArrayList<>();
	private final BaseAnimationManager animationManager;
	private final T entity;

	public ActionManagerImpl(BaseAnimationManager animationManager, T entity) {
		this.animationManager = animationManager;
		this.entity = entity;
	}

	@Override
	public void enableAction(DelayedAction<T> action) {
		actionWatchers.add(new ActionWatcher<>(action));
		action.getAnimationStarter().startAt(animationManager, action.getAnimationLayer());
	}

	public boolean isActionEnabled(DelayedAction<T> action) {
		for (ActionWatcher<T> actionWatcher : actionWatchers) {
			if (actionWatcher.stores(action)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void disableAction(DelayedAction<T> action) {
		actionWatchers.removeIf(watcher -> watcher.action.equals(action));

		animationManager.removeAnimation(action.getAnimationLayer());
	}

	@Override
	public void onTick() {
		if (entity.world.isRemote) {
			AnimationSetting walkingAnimationSetting = animationManager.getWalkingAnimationSetting();

			if (walkingAnimationSetting != null) {
				if (animationManager.containsLayer(walkingAnimationSetting.getLayerName())) {
					boolean posChanged = entity.posX != entity.prevPosX || entity.posY != entity.prevPosY || entity.posZ != entity.prevPosZ;

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

	public List<ActionWatcher<T>> getActionWatchers() {
		return actionWatchers;
	}

	public T getEntity() {
		return entity;
	}

	public static class ActionWatcher<T extends Entity> {
		private final DelayedAction<T> action;
		private boolean done;

		public ActionWatcher(DelayedAction<T> action) {
			this.action = action;
		}

		public boolean isBound(Animation animation) {
			return action.isBound(animation);
		}

		public boolean stores(DelayedAction<T> action) {
			return this.action.equals(action);
		}

		public boolean shouldBeExecuted(AnimationWatcher watcherWithBoundAnimation) {
			return !done && action.getActionDelayPredicate().test(watcherWithBoundAnimation);
		}

		public void runAction(T entity) {
			action.getAction().accept(entity);
			done = true;
		}
	}
}
