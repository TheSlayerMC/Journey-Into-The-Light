package net.jitl.common.entity.overworld;

import net.jitl.common.entity.projectile.FloroMudEntity;
import net.jitl.core.init.JAnimations;
import net.jitl.core.init.JSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.TimeCore;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.animation.AnimationSystem;
import ru.timeconqueror.timecore.animation.component.DelayedAction;
import ru.timeconqueror.timecore.animation.entityai.AnimatedRangedAttackGoal;
import ru.timeconqueror.timecore.animation.util.StandardDelayPredicates;
import ru.timeconqueror.timecore.api.animation.ActionManager;
import ru.timeconqueror.timecore.api.animation.AnimatedObject;
import ru.timeconqueror.timecore.api.animation.AnimationAPI;
import ru.timeconqueror.timecore.api.animation.BlendType;
import ru.timeconqueror.timecore.api.animation.builders.AnimationSystemBuilder;

import java.util.EnumSet;
import java.util.Random;

/**
 * How vanilla tasks work:
 * If flags, from which task's mutex consists of, are disabled, then tasks with this mutex won't be run.
 * <p>
 * If task has higher priority (lower number), than system will check if current task with lower priority is interruptible.
 * If it is so, then it will be finished, and new task will task its place
 * <p>
 * If task has a lower priority (higher number), it's checked by system if it can work in parallel (if mutex isn't the same).
 */
public class FloroEntity extends Monster implements RangedAttackMob, AnimatedObject<FloroEntity> {
    private static final EntityDataAccessor<Boolean> HIDDEN = SynchedEntityData.defineId(FloroEntity.class, EntityDataSerializers.BOOLEAN);

    private static final Lazy<DelayedAction<FloroEntity, AnimatedRangedAttackGoal.ActionData>> RANGED_ATTACK_ACTION;
    private static final Lazy<DelayedAction<FloroEntity, Object>> REVEALING_ACTION;
    private static final Lazy<DelayedAction<FloroEntity, Void>> HIDING_ACTION;

    private static final String LAYER_SHOWING = "showing";
    private static final String LAYER_WALKING = "walking";
    private static final String LAYER_ATTACK = "attack";

    static {
        RANGED_ATTACK_ACTION = Lazy.of(() -> new DelayedAction<FloroEntity, AnimatedRangedAttackGoal.ActionData>(TimeCore.rl("floro/shoot"), new AnimationStarter(JAnimations.floroShoot).setSpeed(1.5F), "attack")
                .setDelayPredicate(StandardDelayPredicates.whenPassed(0.5F))
                .setOnCall(AnimatedRangedAttackGoal.STANDARD_RUNNER));
        REVEALING_ACTION = Lazy.of(() -> new DelayedAction<FloroEntity, Object>(TimeCore.rl("floro/reveal"), new AnimationStarter(JAnimations.floroReveal).setTransitionTime(0), LAYER_SHOWING)
                .setDelayPredicate(StandardDelayPredicates.onEnd())
                .setOnCall((floroEntity, o) -> floroEntity.setHidden(false)));
        HIDING_ACTION = Lazy.of(() -> new DelayedAction<FloroEntity, Void>(TimeCore.rl("floro/hiding"), new AnimationStarter(JAnimations.floroHide).setNextAnimation(AnimationAPI.createStarter(JAnimations.floroHidden).setTransitionTime(0)), LAYER_SHOWING)
                .setDelayPredicate(StandardDelayPredicates.onEnd())
                .setOnCall((floroEntity, nothing) -> floroEntity.setHidden(true)));
    }

    private final AnimationSystem<FloroEntity> animationSystem;
    //server side only
    private boolean isHiding = false;

    public FloroEntity(EntityType<? extends FloroEntity> type, Level world) {
        super(type, world);

        animationSystem = AnimationSystemBuilder.forEntity(this, world, builder -> {
            builder.addLayer(LAYER_SHOWING, BlendType.OVERRIDE, 1F);
            builder.addLayer(LAYER_WALKING, BlendType.ADDING, 1F);
            builder.addLayer(LAYER_ATTACK, BlendType.ADDING, 0.9F);
        }, predefinedAnimations -> {
            predefinedAnimations.setWalkingAnimation(new AnimationStarter(JAnimations.floroWalk).setSpeed(3F), LAYER_WALKING);
            predefinedAnimations.setIdleAnimation(new AnimationStarter(JAnimations.floroIdle), LAYER_WALKING);
        });
    }

    public static boolean canSpawn(EntityType<? extends Monster> entityType, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random random) {
        return !worldIn.getBlockState(pos).is(Blocks.WATER) && worldIn.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(HIDDEN, true);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        compound.putBoolean("hidden", isHidden());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("hidden")) {
            setHidden(compound.getBoolean("hidden"));
        }
    }

    @Override
    public void onAddedToWorld() {
        if (isEffectiveAi() && isHidden()) {
            startHiddenAnimation();
        }
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloroRevealingGoal()); //mutex 1
        goalSelector.addGoal(1, new FloroHidingGoal()); //mutex 1
        goalSelector.addGoal(2, new FloroHiddenGoal()); //mutex 1
        goalSelector.addGoal(3, new FloatGoal(this));//mutex 4
        goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Wolf.class, 6.0F, 1.0D, 1.2D));//mutex 1

        goalSelector.addGoal(5, new AnimatedRangedAttackGoal<>(this, RANGED_ATTACK_ACTION.get(), 1.0F, 16.0F));//mutex 3

        goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));//mutex 1
        goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));//mutex 2
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));//mutex 3

        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 5/*will target if rand.next(chance) == 0*/, true, false, null));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 25)
                .add(Attributes.FOLLOW_RANGE, 25)
                .add(Attributes.MOVEMENT_SPEED, 0.26);
    }

    @Override
    public void aiStep() {
        if (isEffectiveAi()) {
            if (isHidden()) {
                isHiding = false;
            }

            if (canMove()) {
                goalSelector.enableControlFlag(Flag.LOOK);
                goalSelector.enableControlFlag(Flag.JUMP);
            } else {
                goalSelector.disableControlFlag(Flag.LOOK);
                goalSelector.disableControlFlag(Flag.JUMP); //actually doesn't help in some situations as these tasks still can be activated, if executingTasks list in EntityAITasks is empty.
            }
        }

        super.aiStep();
    }

    @Override
    public void push(double x, double y, double z) {
        if (canMove()) {
            super.push(x, y, z);
        } else {
            super.push(0, 0, 0);
        }
    }

    @Override
    public void knockback(double strength, double ratioX, double ratioZ) {
        if (canMove()) {
            super.knockback(strength, ratioX, ratioZ);
        }
    }

    private boolean canMove() {
        return !isHidden() && !getActionManager().isActionEnabled(REVEALING_ACTION.get()) && !isHiding;
    }

    private void startHiddenAnimation() {
        AnimationAPI.createStarter(JAnimations.floroHidden).setTransitionTime(0)
                .startAt(getActionManager().getAnimationManager(), LAYER_SHOWING);
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        FloroMudEntity projectile = new FloroMudEntity(this.level, this, 0.5F);
        double dX = target.getX() - this.getX();
        double dY = target.getY(0.3333333333333333D) - projectile.getY();
        double dZ = target.getZ() - this.getZ();
        double distortion = Mth.sqrt((float) (dX * dX + dZ * dZ));
        projectile.shoot(dX, dY + distortion * 0.20000000298023224D, dZ, 1.6F, (float) (7 - this.level.getDifficulty().getId()));
        this.playSound(JSounds.MUD_BLOCK_BREAK.get(), 1.0F, 0.85F);

        this.level.addFreshEntity(projectile);
    }

    public boolean isHidden() {
        return getEntityData().get(HIDDEN);
    }

    public void setHidden(boolean value) {
        getEntityData().set(HIDDEN, value);
    }


    public @NotNull AnimationSystem<FloroEntity> getSystem() {
        return animationSystem;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return sizeIn.height * 0.78F;
    }

    private class FloroRevealingGoal extends Goal {
        public FloroRevealingGoal() {
            setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return !canMove() && getTarget() != null;
        }

        @Override
        public void start() {
            ActionManager<FloroEntity> actionManager = getActionManager();
            if (isHidden()) {
                actionManager.enableAction(REVEALING_ACTION.get(), null);
            } else {
                actionManager.disableAction(HIDING_ACTION.get());
            }
        }

        @Override
        public boolean isInterruptable() {
            return false;
        }
    }

    private class FloroHiddenGoal extends Goal {
        public FloroHiddenGoal() {
            setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return isHidden();
        }
    }

    private class FloroHidingGoal extends Goal {
        public FloroHidingGoal() {
            setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return !isHidden() && getTarget() == null;
        }

        @Override
        public boolean canContinueToUse() {
            return !isHidden();
        }

        @Override
        public void start() {
            isHiding = true;
        }

        @Override
        public void tick() {
            ActionManager<FloroEntity> actionManager = getActionManager();
            if (!actionManager.isActionEnabled(HIDING_ACTION.get()) && tickCount % (12 * 20) == 0) {
                actionManager.enableAction(HIDING_ACTION.get(), null);
            }
        }

        @Override
        public void stop() {
            isHiding = false;
        }
    }
}