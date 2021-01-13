package net.jitl.common.entity.nether;

import net.jitl.init.JAnimations;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.animation.AnimationSystem;
import ru.timeconqueror.timecore.api.animation.AnimatedObject;
import ru.timeconqueror.timecore.api.animation.BlendType;
import ru.timeconqueror.timecore.api.animation.builders.AnimationSystemBuilder;

public class WitherspineEntity extends MonsterEntity implements IRangedAttackMob, AnimatedObject<WitherspineEntity> {

    private static final String LAYER_WALKING = "walking";

    private final AnimationSystem<WitherspineEntity> animationSystem;

    public WitherspineEntity(EntityType<? extends WitherspineEntity> type, World world) {
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

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.15D);
    }
}
