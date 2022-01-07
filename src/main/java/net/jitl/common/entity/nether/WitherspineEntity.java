package net.jitl.common.entity.nether;

import net.jitl.init.JAnimations;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.animation.AnimationSystem;
import ru.timeconqueror.timecore.api.animation.AnimatedObject;
import ru.timeconqueror.timecore.api.animation.BlendType;
import ru.timeconqueror.timecore.api.animation.builders.AnimationSystemBuilder;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.RangedAttackMob;

public class WitherspineEntity extends Monster implements RangedAttackMob, AnimatedObject<WitherspineEntity> {

    private static final String LAYER_WALKING = "walking";

    private final AnimationSystem<WitherspineEntity> animationSystem;

    public WitherspineEntity(EntityType<? extends WitherspineEntity> type, Level world) {
        super(type, world);

        animationSystem = AnimationSystemBuilder.forEntity(this, world, builder -> {
            builder.addLayer(LAYER_WALKING, BlendType.ADDING, 1F);
        }, predefinedAnimations -> {
            predefinedAnimations.setWalkingAnimation(new AnimationStarter(JAnimations.witherspineWalk).setSpeed(3F), LAYER_WALKING);
            predefinedAnimations.setIdleAnimation(new AnimationStarter(JAnimations.witherspineIdle), LAYER_WALKING);
        });
    }

    @Override
    public @NotNull AnimationSystem<WitherspineEntity> getSystem() {
        return animationSystem;
    }

    @Override
    public void performRangedAttack(@NotNull LivingEntity target, float distanceFactor) {

    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return sizeIn.height * 0.9F; //TODO: change once texture gets added
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.15D);
    }
}
