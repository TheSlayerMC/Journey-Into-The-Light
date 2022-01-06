package net.jitl.common.effect;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

public class FrostburnEffect extends MobEffect {
    public FrostburnEffect(MobEffectCategory type, int liquidColor) {
        super(type, liquidColor);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entityLivingBaseIn, int amplifier) {
        int playerArea = 4;
        AABB axisalignedbb = AABB.unitCubeFromLowerCorner(entityLivingBaseIn.position()).inflate(playerArea, playerArea, playerArea);
        for (LivingEntity livingEntity : entityLivingBaseIn.level.getEntitiesOfClass(LivingEntity.class, axisalignedbb)) {
            if (livingEntity != entityLivingBaseIn && !(livingEntity instanceof TamableAnimal)) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 3));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 2));
            }
        }
    }
}
