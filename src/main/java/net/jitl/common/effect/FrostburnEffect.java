package net.jitl.common.effect;

import net.jitl.JITL;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class FrostburnEffect extends Effect {
    private static final UUID FROSTBURN_UUID = UUID.fromString("00795fc8-af20-4374-a0a2-3d63123145c6");

    public FrostburnEffect(EffectType type, int liquidColor) {
        super(type, liquidColor);
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity entityLivingBaseIn, @NotNull AttributeModifierManager attributeMapIn, int amplifier) {
        if (entityLivingBaseIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
            Objects.requireNonNull(player.getAttribute(Attributes.MOVEMENT_SPEED)).removeModifier(FROSTBURN_UUID);
        }
        super.removeAttributeModifiers(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity entityLivingBaseIn, @NotNull AttributeModifierManager attributeMapIn, int amplifier) {
        if (entityLivingBaseIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
            Objects.requireNonNull(player.getAttribute(Attributes.MOVEMENT_SPEED)).addTransientModifier(
                    new AttributeModifier(FROSTBURN_UUID, JITL.MODID + ":frostburn_modifier", player.getAttributeValue(Attributes.MOVEMENT_SPEED) + (float) (0.01 * (amplifier + 1)), AttributeModifier.Operation.ADDITION));
        }
        super.addAttributeModifiers(entityLivingBaseIn, attributeMapIn, amplifier);
    }
}
