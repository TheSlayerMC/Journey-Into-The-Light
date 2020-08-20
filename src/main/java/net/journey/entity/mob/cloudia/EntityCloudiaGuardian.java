package net.journey.entity.mob.cloudia;

import net.journey.JITL;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.init.JAnimations;
import net.journey.init.JourneySounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.JEntityMob;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.ActionManagerBuilder;
import ru.timeconqueror.timecore.animation.AnimationManagerBuilder;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.animation.component.DelayedAction;
import ru.timeconqueror.timecore.animation.util.StandardDelayPredicates;
import ru.timeconqueror.timecore.api.animation.ActionManager;
import ru.timeconqueror.timecore.api.animation.AnimationAPI;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;
import ru.timeconqueror.timecore.api.animation.BlendType;

public class EntityCloudiaGuardian extends JEntityMob implements AnimationProvider<EntityCloudiaGuardian> {
	private static final DataParameter<Boolean> HIDDEN = EntityDataManager.createKey(EntityCloudiaGuardian.class, DataSerializers.BOOLEAN);

	private static final DelayedAction<EntityCloudiaGuardian, Object> REVEALING_ACTION;
	private static final DelayedAction<EntityCloudiaGuardian, Void> HIDING_ACTION;

	private static final String LAYER_SHOWING = "showing";
	private static final String LAYER_WALKING = "walking";

	static {
		REVEALING_ACTION = new DelayedAction<EntityCloudiaGuardian, Object>(JITL.rl("cloudia_guardian/reveal"), new AnimationStarter(JAnimations.CLOUDIA_GUARDIAN_REVEAL).setTransitionTime(0), LAYER_SHOWING)
				.setDelayPredicate(StandardDelayPredicates.onEnd())
				.setOnCall((entityCloudiaGuardian, o) -> entityCloudiaGuardian.setHidden(false));
		HIDING_ACTION = new DelayedAction<EntityCloudiaGuardian, Void>(JITL.rl("cloudia_guardian/hiding"), new AnimationStarter(JAnimations.CLOUDIA_GUARDIAN_HIDE).setNextAnimation(AnimationAPI.createStarter(JAnimations.CLOUDIA_GUARDIAN_HIDDEN).setTransitionTime(0)), LAYER_SHOWING)
				.setDelayPredicate(StandardDelayPredicates.onEnd())
				.setOnCall((entityCloudiaGuardian, nothing) -> entityCloudiaGuardian.setHidden(true));
	}

	private final ActionManager<EntityCloudiaGuardian> actionManager;
	//server side only
	private boolean isHiding = false;

	public EntityCloudiaGuardian(World world) {
		super(world);
		addMeleeAttackingAI();
		actionManager = ActionManagerBuilder.<EntityCloudiaGuardian>create(
				AnimationManagerBuilder.create()
						.addLayer(LAYER_SHOWING, BlendType.OVERRIDE, 1F)
						.addLayer(LAYER_WALKING, BlendType.ADDING, 1F)
						.addWalkingAnimationHandling(new AnimationStarter(JAnimations.CLOUDIA_GUARDIAN_WALK).setSpeed(3F), LAYER_WALKING)
		).build(this, world);
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		getDataManager().register(HIDDEN, true);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		compound.setBoolean("hidden", isHidden());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		if (compound.hasKey("hidden")) {
			setHidden(compound.getBoolean("hidden"));
		}
	}

	@Override
	public void onAddedToWorld() {
		if (isServerWorld() && isHidden()) {
			startHiddenAnimation();
		}
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityCloudiaGuardian.CloudiaGuardianRevealingTask()); //mutex 1
		tasks.addTask(1, new EntityCloudiaGuardian.CloudiaGuardianHidingTask()); //mutex 1
		tasks.addTask(2, new EntityCloudiaGuardian.CloudiaGuardianHiddenTask()); //mutex 1
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		EntityAttributesHelper.setMaxHealth(this, 25);
		EntityAttributesHelper.setFollowRange(this, 25);
	}

	@Override
	public void onLivingUpdate() {
		int vanillaActions = 2 | 4;

		if (isServerWorld()) {
			if (isHidden()) {
				isHiding = false;
			}

			if (canMove()) {
				tasks.enableControlFlag(vanillaActions);
			} else {
				tasks.disableControlFlag(vanillaActions); //actually doesn't help in some situations as these tasks still can be activated, if executingTasks list in EntityAITasks is empty.
			}
		}

		super.onLivingUpdate(); //this method should be the last, because here we update AI task. If it is before our own code, then disabling control flags won't work
	}

	@Override
	public void addVelocity(double x, double y, double z) {
		if (canMove()) {
			super.addVelocity(x, y, z);
		} else {
			super.addVelocity(0, 0, 0);
		}
	}

	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
		if (canMove()) {
			super.knockBack(entityIn, strength, xRatio, zRatio);
		}
	}

	private boolean canMove() {
		return !isHidden() && !getActionManager().isActionEnabled(REVEALING_ACTION) && !isHiding;
	}

	private void startHiddenAnimation() {
		AnimationAPI.createStarter(JAnimations.CLOUDIA_GUARDIAN_HIDDEN).setTransitionTime(0)
				.startAt(getActionManager().getAnimationManager(), LAYER_SHOWING);
	}

	public boolean isHidden() {
		return getDataManager().get(HIDDEN);
	}

	public void setHidden(boolean value) {
		getDataManager().set(HIDDEN, value);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return JourneySounds.HONGO;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return JourneySounds.HONGO_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return JourneySounds.HONGO_HURT;
	}

	@Override
	public @NotNull ActionManager<EntityCloudiaGuardian> getActionManager() {
		return actionManager;
	}

	@Override
	public float getEyeHeight() {
		return this.height * 1.75F;
	}

	private class CloudiaGuardianRevealingTask extends EntityAIBase {
		public CloudiaGuardianRevealingTask() {
			setMutexBits(1);
		}

		@Override
		public boolean shouldExecute() {
			return !canMove() && getAttackTarget() != null;
		}

		@Override
		public void startExecuting() {
			if (isHidden()) {
				getActionManager().enableAction(REVEALING_ACTION, null);
			} else {
				getActionManager().disableAction(HIDING_ACTION);
			}
		}

		@Override
		public boolean isInterruptible() {
			return false;
		}
	}

	private class CloudiaGuardianHiddenTask extends EntityAIBase {
		public CloudiaGuardianHiddenTask() {
			setMutexBits(1);
		}

		@Override
		public boolean shouldExecute() {
			return isHidden();
		}
	}

	private class CloudiaGuardianHidingTask extends EntityAIBase {
		public CloudiaGuardianHidingTask() {
			setMutexBits(1);
		}

		@Override
		public boolean shouldExecute() {
			return !isHidden() && getAttackTarget() == null;
		}

		@Override
		public boolean shouldContinueExecuting() {
			return !isHidden();
		}

		@Override
		public void startExecuting() {
			isHiding = true;
		}

		@Override
		public void updateTask() {
			if (!getActionManager().isActionEnabled(HIDING_ACTION) && ticksExisted % (12 * 20) == 0) {
				getActionManager().enableAction(HIDING_ACTION, null);
			}
		}

		@Override
		public void resetTask() {
			isHiding = false;
		}
	}
}