package net.journey.entity.mob.overworld;

import net.journey.JITL;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityFloroWater;
import net.journey.init.JAnimations;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
import ru.timeconqueror.timecore.api.animation.AnimationProvider;
import ru.timeconqueror.timecore.api.animation.BlendType;

public class EntityFloro extends JEntityMob implements IRangedAttackMob, AnimationProvider<EntityFloro> {
	private static final DelayedAction<EntityFloro, AnimatedRangedAttackAI.ActionData> RANGED_ATTACK_ACTION;

	static {
		RANGED_ATTACK_ACTION = new DelayedAction<EntityFloro, AnimatedRangedAttackAI.ActionData>(JITL.rl("floro/shoot"), new AnimationStarter(JAnimations.FLORO_SHOOT),"attack")
				.setDelayPredicate(StandardDelayPredicates.whenPassed(0.5F))
				.setOnCall(AnimatedRangedAttackAI.STANDARD_RUNNER);
	}

	private final ActionManager<EntityFloro> actionManager;

	public EntityFloro(World world) {
		super(world);

		actionManager = ActionManagerBuilder.<EntityFloro>create(
				AnimationManagerBuilder.create()
						.addLayer(LayerReference.WALKING)
						.addLayer("attack", 1, BlendType.ADDING, 0.9F)
						.addWalkingAnimationHandling(new AnimationStarter(JAnimations.FLORO_WALK).setSpeed(3F), LayerReference.WALKING)
		).build(this, world);
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();

		this.tasks.addTask(-1, new AnimatedRangedAttackAI<>(this, RANGED_ATTACK_ACTION, 0.27F, 50, 8.0F));

		this.tasks.addTask(3, new EntityAIAvoidEntity<>(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(6, new EntityAILookIdle(this));

		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
		EntityFloroWater b = new EntityFloroWater(this.world, this, 1.0F);
		double d0 = target.posX - this.posX;
		double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - b.posY;
		double d2 = target.posZ - this.posZ;
		double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
		b.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
		JourneySounds.playSound(JourneySounds.MAGIC_SPARKLE, world, this);
		this.world.spawnEntity(b);
	}

	@Override
	public boolean getCanSpawnHere() {
		return
				dimension == 0
						&& (this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.GRASS ||
						this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.LEAVES ||
						this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.SAND ||
						this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.DIRT);
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
	public @NotNull EntitySettings getEntitySettings() {
		return MobStats.FLORO;
	}

	@Override
	public @NotNull ActionManager<EntityFloro> getActionManager() {
		return actionManager;
	}
}