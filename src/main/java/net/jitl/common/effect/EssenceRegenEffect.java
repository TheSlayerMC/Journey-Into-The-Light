package net.jitl.common.effect;

import net.jitl.init.JAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import org.jetbrains.annotations.NotNull;

public class EssenceRegenEffect extends Effect {
    public EssenceRegenEffect(EffectType type, int liquidColor) {
        super(type, liquidColor);
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entityLivingBaseIn, @NotNull AttributeModifierManager attributeMapIn, int amplifier) {
        entityLivingBaseIn.setAbsorptionAmount((float) entityLivingBaseIn.getAttributeValue(JAttributes.ESSENCE_REGEN_SPEED.get()) - (float) (4 * (amplifier + 1)));
        super.removeAttributeModifiers(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entityLivingBaseIn, @NotNull AttributeModifierManager attributeMapIn, int amplifier) {
        entityLivingBaseIn.setAbsorptionAmount((float) entityLivingBaseIn.getAttributeValue(JAttributes.ESSENCE_REGEN_SPEED.get()) + (float) (4 * (amplifier + 1)));
        super.addAttributeModifiers(entityLivingBaseIn, attributeMapIn, amplifier);
    }
}
