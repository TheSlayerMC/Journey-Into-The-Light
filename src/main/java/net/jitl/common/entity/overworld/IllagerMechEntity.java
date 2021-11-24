package net.jitl.common.entity.overworld;

import net.jitl.JITL;
import net.jitl.init.JAnimations;
import net.jitl.init.JSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.*;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
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

public class IllagerMechEntity extends AbstractRaiderEntity implements AnimatedObject<IllagerMechEntity> {

    private static final Lazy<DelayedAction<IllagerMechEntity, Object>> THROWING_ACTION;

    private final AnimationSystem<IllagerMechEntity> animationSystem;
    private static final String LAYER_WALKING = "walking";
    private static final String LAYER_THROWING = "throwing";

    static {
        THROWING_ACTION = Lazy.of(() -> new DelayedAction<IllagerMechEntity, Object>(JITL.rl("illager_mech/throw"), new AnimationStarter(JAnimations.illagerMechThrow).setSpeed(2.0F).setTransitionTime(0), LAYER_THROWING)
                .setDelayPredicate(StandardDelayPredicates.onEnd()));
    }

    public IllagerMechEntity(EntityType<? extends IllagerMechEntity> entityType, World world) {
        super(entityType, world);
        setCanJoinRaid(true);
        animationSystem = AnimationSystemBuilder.forEntity(this, world,
                builder -> {
                    builder.addLayer(LAYER_WALKING, BlendType.ADDING, 1F);
                    builder.addLayer(LAYER_THROWING, BlendType.OVERRIDE, 1F);
                },
                predefinedAnimations -> {
                    predefinedAnimations.setWalkingAnimation(new AnimationStarter(JAnimations.illagerMechWalk).setSpeed(2.25F), LAYER_WALKING);
                });
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(2, new ThrowingGoal(this, 1.0D, false)); //mutex 1
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new AttackGoal());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (livingEntity_) -> livingEntity_ instanceof IMob && !(livingEntity_ instanceof CreeperEntity) && !(livingEntity_ instanceof AbstractRaiderEntity)));
    }

    @Override
    public void applyRaidBuffs(int wave, boolean boolean_) {
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.RAVAGER_CELEBRATE;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 75.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_DAMAGE, 12.0D)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.6F;
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
        return JSounds.ILLAGER_MECH_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return JSounds.ILLAGER_MECH_DEATH.get();
    }

    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
        this.playSound(JSounds.ILLAGER_MECH_STEP.get(), 0.5F, 1.0F);
    }

    private float getAttackDamage() {
        return (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    public boolean doHurtTarget(Entity entityIn) {
        this.level.broadcastEntityEvent(this, (byte) 4);
        float f = this.getAttackDamage();
        float f1 = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), f1);
        if (flag) {
            entityIn.setDeltaMovement(entityIn.getDeltaMovement().add(0.0D, 0.65F, 0.0D));
            this.doEnchantDamageEffects(this, entityIn);
        }
        return flag;
    }

    @Override
    protected @NotNull PathNavigator createNavigation(@NotNull World worldIn) {
        return new Navigator(this, worldIn);
    }

    @Override
    public @NotNull AnimationSystem<IllagerMechEntity> getSystem() {
        return animationSystem;
    }

    private class ThrowingGoal extends MeleeAttackGoal {
        private final IllagerMechEntity mechEntity;

        private ThrowingGoal(IllagerMechEntity mechEntity, double speed, boolean useLongMemory) {
            super(mechEntity, speed, useLongMemory);
            this.mechEntity = mechEntity;
        }

        @Override
        public void start() {
            super.start();
        }

        @Override
        public void stop() {
            super.stop();
            this.mechEntity.setAggressive(false);
        }

        @Override
        public boolean canUse() {
            LivingEntity livingEntity = getTarget();
            return livingEntity != null && livingEntity.isAlive();
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
            double d0 = this.getAttackReachSqr(enemy);
            ActionManager<IllagerMechEntity> actionManager = getActionManager();
            if (distToEnemySqr <= d0) {
                this.resetAttackCooldown();
                this.mob.doHurtTarget(enemy);
                if (!actionManager.isActionEnabled(THROWING_ACTION.get())) {
                    actionManager.enableAction(THROWING_ACTION.get(), null);
                    mechEntity.playSound(JSounds.ILLAGER_MECH_THROW.get(), 1.0F, 1.0F);
                }
            }
        }
    }

    class AttackGoal extends MeleeAttackGoal {
        public AttackGoal() {
            super(IllagerMechEntity.this, 1.0D, true);
        }

        protected double getAttackReachSqr(LivingEntity attackTarget) {
            float f = IllagerMechEntity.this.getBbWidth() - 0.1F;
            return f * 2.0F * f * 2.0F + attackTarget.getBbWidth();
        }
    }

    static class Navigator extends GroundPathNavigator {
        public Navigator(MobEntity mobEntity, World world) {
            super(mobEntity, world);
        }

        @Override
        protected @NotNull PathFinder createPathFinder(int int_) {
            this.nodeEvaluator = new Processor();
            return new PathFinder(this.nodeEvaluator, int_);
        }
    }

    static class Processor extends WalkNodeProcessor {
        private Processor() {
        }

        @Override
        protected @NotNull PathNodeType evaluateBlockPathType(@NotNull IBlockReader blockReader_, boolean boolean_, boolean boolean1_, @NotNull BlockPos blockPos_, @NotNull PathNodeType pathNodeType_) {
            return pathNodeType_ == PathNodeType.LEAVES ? PathNodeType.OPEN : super.evaluateBlockPathType(blockReader_, boolean_, boolean1_, blockPos_, pathNodeType_);
        }
    }
}
