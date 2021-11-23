package net.jitl.common.entity.overworld;

import net.jitl.JITL;
import net.jitl.client.eventhandler.music.JMusicTicker;
import net.jitl.common.entity.base.IJourneyBoss;
import net.jitl.common.helper.JMusic;
import net.jitl.init.JAnimations;
import net.jitl.init.JSounds;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.animation.AnimationSystem;
import ru.timeconqueror.timecore.animation.component.DelayedAction;
import ru.timeconqueror.timecore.animation.util.StandardDelayPredicates;
import ru.timeconqueror.timecore.api.animation.ActionManager;
import ru.timeconqueror.timecore.api.animation.AnimatedObject;
import ru.timeconqueror.timecore.api.animation.BlendType;
import ru.timeconqueror.timecore.api.animation.builders.AnimationSystemBuilder;

public class TowerGuardianEntity extends MonsterEntity implements AnimatedObject<TowerGuardianEntity>, IJourneyBoss {

	private static final JMusic BOSS_TRACK = new JMusic(JSounds.TEMPLE_GUARDIAN_MUSIC.get(), 2, 0, 0);
	private static final Lazy<DelayedAction<TowerGuardianEntity, Object>> SMASHING_ACTION;

	private static final String LAYER_WALKING = "walking";
	private static final String LAYER_SMASHING = "smashing";

	static {
		SMASHING_ACTION = Lazy.of(() -> new DelayedAction<TowerGuardianEntity, Object>(JITL.rl("tower_guardian/smash"), new AnimationStarter(JAnimations.towerGuardianSmash).setTransitionTime(0), LAYER_SMASHING)
				.setDelayPredicate(StandardDelayPredicates.onEnd()));
	}

	private final AnimationSystem<TowerGuardianEntity> animationSystem;

	public TowerGuardianEntity(EntityType<? extends TowerGuardianEntity> type, World world) {
		super(type, world);

		animationSystem = AnimationSystemBuilder.forEntity(this, world, builder -> {
			builder.addLayer(LAYER_WALKING, BlendType.ADDING, 1F);
			builder.addLayer(LAYER_SMASHING, BlendType.OVERRIDE, 1F);
		}, predefinedAnimations ->
				predefinedAnimations.setWalkingAnimation(new AnimationStarter(JAnimations.towerGuardianWalk).setSpeed(1F), LAYER_WALKING));
	}

	@Override
	public @NotNull AnimationSystem<TowerGuardianEntity> getSystem() {
		return animationSystem;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SmashingGoal(this, 1.0D, false)); //mutex 1
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.addBehaviourGoals();
	}

	protected void addBehaviourGoals() {
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglinEntity.class));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.15D);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.9F;
	}

	@Override
	public ResourceLocation getBarTexture() {
		return null;
	}

	@Override
	public JMusic getBossMusic() {
		return BOSS_TRACK;
	}

	@Override
	public void onAddedToWorld() {
		super.onAddedToWorld();
		if (this.level.isClientSide()); //TODO: store this boss somewhere
	}

	@Override
	public void onRemovedFromWorld() {
		super.onRemovedFromWorld();
		if (this.level.isClientSide()); //TODO: remove boss
	}

	private class SmashingGoal extends MeleeAttackGoal {
		private final TowerGuardianEntity guardianEntity;
		private int animateTicks;

		private SmashingGoal(TowerGuardianEntity guardianEntity, double speed, boolean useLongMemory) {
			super(guardianEntity, speed, useLongMemory);
			this.guardianEntity = guardianEntity;
		}

		@Override
		public void start() {
			super.start();
			this.animateTicks = 0;
		}

		@Override
		public void stop() {
			super.stop();
			this.guardianEntity.setAggressive(false);
		}

		@Override
		public boolean canUse() {
			LivingEntity livingEntity = getTarget();
			return livingEntity != null && livingEntity.isAlive();
		}

		@Override
		public void tick() {
			super.tick();
			++this.animateTicks;
			ActionManager<TowerGuardianEntity> actionManager = getActionManager();
			if (!actionManager.isActionEnabled(SMASHING_ACTION.get()) && this.animateTicks >= 15 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
				actionManager.enableAction(SMASHING_ACTION.get(), null);
				this.guardianEntity.setAggressive(true);
			} else {
				this.guardianEntity.setAggressive(false);
			}
		}
	}
}
