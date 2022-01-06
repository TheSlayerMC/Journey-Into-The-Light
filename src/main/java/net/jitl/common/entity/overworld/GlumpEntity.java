package net.jitl.common.entity.overworld;

import net.jitl.init.JSounds;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.AnimationSystem;
import ru.timeconqueror.timecore.api.animation.AnimatedObject;
import ru.timeconqueror.timecore.api.animation.builders.AnimationSystemBuilder;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;

public class GlumpEntity extends PathfinderMob implements AnimatedObject<GlumpEntity> {

    private final AnimationSystem<GlumpEntity> animationSystem;

    public GlumpEntity(EntityType<? extends GlumpEntity> entityType, Level world) {
        super(entityType, world);
        animationSystem = AnimationSystemBuilder.forEntity(this, world,
                builder -> {
                },
                predefinedAnimations -> {
                });
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return sizeIn.height * 0.6F;
    }

    protected SoundEvent getAmbientSound() {
        return JSounds.GLUMP_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
        return JSounds.GLUMP_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return JSounds.GLUMP_DEATH.get();
    }

    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
        this.playSound(SoundEvents.PIG_STEP, 0.15F, 1.0F);
    }

    @Override
    public @NotNull AnimationSystem<GlumpEntity> getSystem() {
        return animationSystem;
    }
}
