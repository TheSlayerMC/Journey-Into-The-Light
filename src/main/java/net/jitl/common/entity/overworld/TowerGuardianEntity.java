package net.jitl.common.entity.overworld;

import net.jitl.JITL;
import net.jitl.client.render.gui.BossBarRenderer;
import net.jitl.common.entity.base.IJourneyBoss;
import net.jitl.common.helper.JBossInfo;
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
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
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

	private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.NOTCHED_6);
	private final BossBarRenderer BOSS_BAR = new BossBarRenderer(this, JITL.tl("gui/bossbars/tower_guardian.png").fullLocation());
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
				.add(Attributes.MAX_HEALTH, 200.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.15D);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.9F;
	}

	@Override
	public BossBarRenderer getBossBar() {
		return BOSS_BAR;
	}

	@Override
	public JMusic getBossMusic() {
		return BOSS_TRACK;
	}

	@Override
	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);
		JBossInfo.addInfo(player, bossInfo, this);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);
		JBossInfo.removeInfo(player, bossInfo, this);
	}

	private class SmashingGoal extends MeleeAttackGoal {
        private final TowerGuardianEntity entity;

        private SmashingGoal(TowerGuardianEntity entity, double speed, boolean useLongMemory) {
            super(entity, speed, useLongMemory);
            this.entity = entity;
        }

        @Override
        public void start() {
            super.start();
        }

        @Override
        public void stop() {
            super.stop();
            this.entity.setAggressive(false);
        }

		@Override
		public boolean canUse() {
			LivingEntity livingEntity = getTarget();
			return livingEntity != null && livingEntity.isAlive();
		}

        @Override
        protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
            double d0 = this.getAttackReachSqr(enemy);
            ActionManager<TowerGuardianEntity> actionManager = getActionManager();
            if (distToEnemySqr <= d0) {
                this.resetAttackCooldown();
                this.mob.doHurtTarget(enemy);
                if (!actionManager.isActionEnabled(SMASHING_ACTION.get())) {
                    actionManager.enableAction(SMASHING_ACTION.get(), null);
                }
            }
        }
	}
}
