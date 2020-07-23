package net.journey.entity.mob.overworld;

import net.journey.JITL;
import net.journey.entity.base.EntitySettingsHelper;
import net.journey.entity.projectile.EntityFloroDirtProjectile;
import net.journey.init.JAnimations;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.JEntityMob;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.ActionManagerBuilder;
import ru.timeconqueror.timecore.animation.AnimationManagerBuilder;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.animation.component.DelayedAction;
import ru.timeconqueror.timecore.animation.entityai.AnimatedRangedAttackAI;
import ru.timeconqueror.timecore.animation.util.LayerReference;
import ru.timeconqueror.timecore.animation.util.StandardDelayPredicates;
import ru.timeconqueror.timecore.api.animation.ActionManager;
import ru.timeconqueror.timecore.api.animation.AnimationAPI;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;
import ru.timeconqueror.timecore.api.animation.BlendType;

/**
 * How tasks work:
 * If flags, from which mutex consists of, are disabled, then tasks with this mutex won't be run.
 * <p>
 * If task has higher priority (lower number), than system will check if current task with lower priority is interruptible.
 * If it is so, then it will be finished, and new task will task its place
 * <p>
 * If task has a lower priority (higher number), it's checked by system if it can work in parallel (if mutex is the same).
 * <p>
 * How mutex works:
 * If combination of numbers, that are power of two, completely coincides with the other tasks' ones
 * they can't work in parallel
 * Example:
 * task with mutex 1 | 2 | 4 (7) cannot be run in parallel with task with mutex 1 | 2 | 4 (7)
 * task with mutex 1 | 2 | 4 (7) can be run in parallel with task with mutex 1 | 2 (3)
 */
public class EntityFloro extends JEntityMob implements IRangedAttackMob, AnimationProvider<EntityFloro> {
	private static final DelayedAction<EntityFloro, AnimatedRangedAttackAI.ActionData> RANGED_ATTACK_ACTION;
	private static final DelayedAction<EntityFloro, Object> REVEALING_ACTION;

	private static final String LAYER_SHOWING = "showing";
	private static final String LAYER_ATTACK = "attack";

	static {
		RANGED_ATTACK_ACTION = new DelayedAction<EntityFloro, AnimatedRangedAttackAI.ActionData>(JITL.rl("floro/shoot"), new AnimationStarter(JAnimations.FLORO_SHOOT).setSpeed(1.5F), "attack")
				.setDelayPredicate(StandardDelayPredicates.whenPassed(0.5F))
				.setOnCall(AnimatedRangedAttackAI.STANDARD_RUNNER);

		REVEALING_ACTION = new DelayedAction<EntityFloro, Object>(JITL.rl("floro/reveal"), new AnimationStarter(JAnimations.FLORO_REVEAL).setTransitionTime(0), LAYER_SHOWING)
				.setDelayPredicate(StandardDelayPredicates.onEnd())
				.setOnCall((entityFloro, o) -> entityFloro.hidden = false);
	}

	private final ActionManager<EntityFloro> actionManager;
	private boolean hidden = true;

	public EntityFloro(World world) {
		super(world);

		actionManager = ActionManagerBuilder.<EntityFloro>create(
				AnimationManagerBuilder.create()
						.addLayer(LAYER_SHOWING, -1, BlendType.OVERRIDE, 1F)
						.addLayer(LayerReference.WALKING)
						.addLayer(LAYER_ATTACK, 1, BlendType.ADDING, 0.9F)
						.addWalkingAnimationHandling(new AnimationStarter(JAnimations.FLORO_WALK).setSpeed(3F), LayerReference.WALKING)
		).build(this, world);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new FloroRevealingTask()); //mutex 8
		tasks.addTask(1, new FloroHiddenTask()); //mutex 8
		tasks.addTask(2, new EntityAISwimming(this));//mutex 4
		tasks.addTask(3, new EntityAIAvoidEntity<>(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));//mutex 1

		tasks.addTask(4, new AnimatedRangedAttackAI<>(this, RANGED_ATTACK_ACTION, 1.0F, 40, 16.0F));//mutex 3

		tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));//mutex 1
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));//mutex 2
		tasks.addTask(6, new EntityAILookIdle(this));//mutex 3

		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		EntitySettingsHelper.setMaxHealth(this, 25);
	}

	@Override
	public void onLivingUpdate() {
		int vanillaActions = 1 | 2 | 4;

		if (isServerWorld()) {
			if (hidden) {
				tasks.disableControlFlag(vanillaActions); //actually doesn't help in some situations as these tasks still can be activated, if executingTasks list in EntityAITasks is empty.
			} else {
				tasks.enableControlFlag(vanillaActions);
			}
		}

		super.onLivingUpdate(); //this method should be the last, because here we update AI task. If it is before our own code, then disabling control flags won't work
	}

	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
		if (!hidden) {
			super.knockBack(entityIn, strength, xRatio, zRatio);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		compound.setBoolean("hidden", hidden);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		hidden = compound.getBoolean("hidden");
	}

	@Override
	public void onAddedToWorld() {
		if (hidden) {
			AnimationAPI.createStarter(JAnimations.FLORO_HIDDEN)
					.setTransitionTime(0)
					.startAt(getActionManager().getAnimationManager(), LAYER_SHOWING);
		}
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
		EntityFloroDirtProjectile projectile = new EntityFloroDirtProjectile(this.world, this, 0.0F);
		double dX = target.posX - this.posX;
		double dY = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - projectile.posY;
		double dZ = target.posZ - this.posZ;
		double distortion = MathHelper.sqrt(dX * dX + dZ * dZ);
		projectile.shoot(dX, dY + distortion * 0.20000000298023224D, dZ, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));

		playSound(JourneySounds.FLORO_SHOOT, 1.0F, 0.7F);

		this.world.spawnEntity(projectile);
	}

	@Override
	public boolean getCanSpawnHere() {
		Block groundBlock = world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock();
		return dimension == 0
				&& (groundBlock == Blocks.GRASS
				|| groundBlock == Blocks.LEAVES
				|| groundBlock == Blocks.SAND
				|| groundBlock == Blocks.DIRT);
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
	public ResourceLocation getLootTable() {
		return JourneyLootTables.FLORO;
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
	}

	@Override
	public EntitySettings getEntitySettings() {
		return null;
	}

	@Override
	public @NotNull ActionManager<EntityFloro> getActionManager() {
		return actionManager;
	}

	@Override
	public float getEyeHeight() {
		return this.height * 0.78F;
	}

	private class FloroRevealingTask extends EntityAIBase {
		public FloroRevealingTask() {
			setMutexBits(8);
		}

		@Override
		public boolean shouldExecute() {
			return hidden && getAttackTarget() != null;
		}

		@Override
		public void startExecuting() {
			getActionManager().enableAction(REVEALING_ACTION, null);
		}

		@Override
		public boolean isInterruptible() {
			return false;
		}
	}

	private class FloroHiddenTask extends EntityAIBase {
		public FloroHiddenTask() {
			setMutexBits(8);
		}

		@Override
		public boolean shouldExecute() {
			return hidden;
		}

		@Override
		public boolean isInterruptible() {
			return true;
		}
	}
}