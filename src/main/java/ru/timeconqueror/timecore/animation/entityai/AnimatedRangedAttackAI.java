package ru.timeconqueror.timecore.animation.entityai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;
import ru.timeconqueror.timecore.animation.component.DelayedAction;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;

import java.util.function.BiConsumer;

public class AnimatedRangedAttackAI<T extends EntityLiving & AnimationProvider<T>> extends EntityAIBase {
	public static final BiConsumer<IRangedAttackMob, ActionData> STANDARD_RUNNER = (entity, actionData) -> entity.attackEntityWithRangedAttack(actionData.getAttackTarget(), actionData.getDistanceFactor());

	/**
	 * The entity the AI instance has been applied to
	 */
	private final T entity;
	private EntityLivingBase attackTarget;
	/**
	 * A decrementing tick that spawns a ranged attack once this value reaches 0. It is then set back to the
	 * maxRangedAttackTime.
	 */
	private int rangedAttackTime;
	private final double entityMoveSpeed;
	private int seeTime;
	/**
	 * The minimum time the AI has to wait before performing another ranged attack.
	 */
	private final int attackIntervalMin;
	/**
	 * The maximum time the AI has to wait before performing another ranged attack.
	 */
	private final int attackIntervalMax;
	private final float attackRadius;
	private final float maxAttackDistance;

	private final DelayedAction<T, ActionData> attackAction;

	public AnimatedRangedAttackAI(T attacker, DelayedAction<T, ActionData> attackAction, double moveSpeed, int attackInterval, float maxAttackDistanceIn) {
		this(attacker, attackAction, moveSpeed, attackInterval, attackInterval, maxAttackDistanceIn);
	}

	public AnimatedRangedAttackAI(T attacker, DelayedAction<T, ActionData> attackAction, double moveSpeed, int attackIntervalMin, int attackIntervalMax, float maxAttackDistanceIn) {
		this.rangedAttackTime = -1;

		this.entity = attacker;
		this.entityMoveSpeed = moveSpeed;
		this.attackIntervalMin = attackIntervalMin;
		this.attackIntervalMax = attackIntervalMax;
		this.attackRadius = maxAttackDistanceIn;
		this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
		this.setMutexBits(3);

		this.attackAction = attackAction;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = this.entity.getAttackTarget();

		if (entitylivingbase == null) {
			return false;
		} else {
			this.attackTarget = entitylivingbase;
			return true;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean shouldContinueExecuting() {
		return this.shouldExecute() || !this.entity.getNavigator().noPath();
	}

	/**
	 * Reset the task's internal state. Called when this task is interrupted by another one
	 */
	public void resetTask() {
		this.attackTarget = null;
		this.seeTime = 0;
		this.rangedAttackTime = -1;
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void updateTask() {
		double distanceSqToTarget = this.entity.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ);
		boolean canSeeTarget = this.entity.getEntitySenses().canSee(this.attackTarget);

		if (canSeeTarget) {
			++this.seeTime;
		} else {
			this.seeTime = 0;
		}

		if (distanceSqToTarget <= this.maxAttackDistance && this.seeTime >= 20) {
			this.entity.getNavigator().clearPath();
		} else {
			this.entity.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.entityMoveSpeed);
		}

		this.entity.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);

		if (--this.rangedAttackTime == 0) {
			if (!canSeeTarget) {
				return;
			}

			float f = MathHelper.sqrt(distanceSqToTarget) / this.attackRadius;
			float distanceFactor = MathHelper.clamp(f, 0.1F, 1.0F);

			entity.getActionManager().enableAction(attackAction, new ActionData(distanceFactor, attackTarget));

			this.rangedAttackTime = MathHelper.floor(f * (this.attackIntervalMax - this.attackIntervalMin) + this.attackIntervalMin);
		} else if (this.rangedAttackTime < 0) {
			float f2 = MathHelper.sqrt(distanceSqToTarget) / this.attackRadius;
			this.rangedAttackTime = MathHelper.floor(f2 * (this.attackIntervalMax - this.attackIntervalMin) + this.attackIntervalMin);
		}
	}

	public static class ActionData {
		private final float distanceFactor;
		private final EntityLivingBase attackTarget;

		public ActionData(float distanceFactor, EntityLivingBase attackTarget) {
			this.distanceFactor = distanceFactor;
			this.attackTarget = attackTarget;
		}

		public EntityLivingBase getAttackTarget() {
			return attackTarget;
		}

		public float getDistanceFactor() {
			return distanceFactor;
		}
	}
}
