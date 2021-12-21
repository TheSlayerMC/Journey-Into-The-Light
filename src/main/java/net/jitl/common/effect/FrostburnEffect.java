package net.jitl.common.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import org.jetbrains.annotations.NotNull;

public class FrostburnEffect extends Effect {
    public FrostburnEffect(EffectType type, int liquidColor) {
        super(type, liquidColor);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entityLivingBaseIn, int amplifier) {
        int playerArea = 4;
        AxisAlignedBB axisalignedbb = AxisAlignedBB.unitCubeFromLowerCorner(entityLivingBaseIn.position()).inflate(playerArea, playerArea, playerArea);
        for (LivingEntity livingEntity : entityLivingBaseIn.level.getEntitiesOfClass(LivingEntity.class, axisalignedbb)) {
            if (livingEntity != entityLivingBaseIn && !(livingEntity instanceof TameableEntity)) {
                livingEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100 * (amplifier / 2), amplifier + 1));
                livingEntity.addEffect(new EffectInstance(Effects.WEAKNESS, 100 * (amplifier), amplifier));
            }
        }
    }
}
