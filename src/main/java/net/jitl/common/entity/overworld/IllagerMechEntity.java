package net.jitl.common.entity.overworld;

import com.google.common.collect.ImmutableList;
import net.jitl.core.JITL;
import net.jitl.core.init.JAnimations;
import net.jitl.core.init.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class IllagerMechEntity extends Raider implements AnimatedObject<IllagerMechEntity> {

    private static final Lazy<DelayedAction<IllagerMechEntity, Object>> THROWING_ACTION;

    private final AnimationSystem<IllagerMechEntity> animationSystem;
    private static final String LAYER_WALKING = "walking";
    private static final String LAYER_THROWING = "throwing";

    static {
        THROWING_ACTION = Lazy.of(() -> new DelayedAction<IllagerMechEntity, Object>(JITL.rl("illager_mech/throw"), new AnimationStarter(JAnimations.illagerMechThrow).setSpeed(2.0F).setTransitionTime(0), LAYER_THROWING)
                .setDelayPredicate(StandardDelayPredicates.onEnd()));
    }

    public IllagerMechEntity(EntityType<? extends IllagerMechEntity> entityType, Level world) {
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
        super.registerGoals();
        this.goalSelector.addGoal(2, new ThrowingGoal(this, 1.0D, false)); //mutex 1
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new AttackGoal());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Villager.class, true));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false,
                (livingEntity_) -> livingEntity_ instanceof Enemy &&
                        !(livingEntity_ instanceof Creeper) &&
                        !(livingEntity_ instanceof Raider) &&
                        !(livingEntity_ instanceof Vex)));
    }

    @Override
    public void applyRaidBuffs(int wave, boolean boolean_) {
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.RAVAGER_CELEBRATE;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 75.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_DAMAGE, 12.0D)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
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

    public boolean hurt(DamageSource source, float amount) {
        Cracks cracks = this.getCrackiness();
        boolean hurt = super.hurt(source, amount);
        if (hurt && this.getCrackiness() != cracks) {
            this.playSound(SoundEvents.IRON_GOLEM_DAMAGE, 1.0F, 0.5F);
        }

        return hurt;
    }

    public Cracks getCrackiness() {
        return Cracks.byFraction(this.getHealth() / this.getMaxHealth());
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level worldIn) {
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

    static class Navigator extends GroundPathNavigation {
        public Navigator(Mob mobEntity, Level world) {
            super(mobEntity, world);
        }

        @Override
        protected @NotNull PathFinder createPathFinder(int int_) {
            this.nodeEvaluator = new Processor();
            return new PathFinder(this.nodeEvaluator, int_);
        }
    }

    static class Processor extends WalkNodeEvaluator {
        private Processor() {
        }

        @Override
        protected @NotNull BlockPathTypes evaluateBlockPathType(@NotNull BlockGetter blockReader_, boolean boolean_, boolean boolean1_, @NotNull BlockPos blockPos_, @NotNull BlockPathTypes pathNodeType_) {
            return pathNodeType_ == BlockPathTypes.LEAVES ? BlockPathTypes.OPEN : super.evaluateBlockPathType(blockReader_, boolean_, boolean1_, blockPos_, pathNodeType_);
        }
    }

    public enum Cracks {
        NONE(1.0F),
        LOW(0.75F),
        MEDIUM(0.5F),
        HIGH(0.25F);

        private static final List<Cracks> BY_DAMAGE =
                Stream.of(values()).sorted(Comparator.comparingDouble((cracks_) -> (double) cracks_.fraction)).collect(ImmutableList.toImmutableList());
        private final float fraction;

        Cracks(float float_) {
            this.fraction = float_;
        }

        public static Cracks byFraction(float float_) {
            for (Cracks cracks : BY_DAMAGE) {
                if (float_ < cracks.fraction) {
                    return cracks;
                }
            }

            return NONE;
        }
    }
}
