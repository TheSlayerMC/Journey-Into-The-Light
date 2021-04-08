package net.jitl.common.effect;

import net.jitl.JITL;
import net.jitl.init.JAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class EssenceRegenEffect extends Effect {
    private static final UUID ESSENCE_REGEN = UUID.fromString("fa05d47a-9821-11eb-a8b3-0242ac130003");

    public EssenceRegenEffect(EffectType type, int liquidColor) {
        super(type, liquidColor);
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity entityLivingBaseIn, @NotNull AttributeModifierManager attributeMapIn, int amplifier) {
        if (entityLivingBaseIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
            Objects.requireNonNull(player.getAttribute(JAttributes.ESSENCE_REGEN_SPEED.get())).removeModifier(ESSENCE_REGEN);
            JITL.LOGGER.info("removeMod");
        }
        super.removeAttributeModifiers(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity entityLivingBaseIn, @NotNull AttributeModifierManager attributeMapIn, int amplifier) {
        if (entityLivingBaseIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
            Objects.requireNonNull(player.getAttribute(JAttributes.ESSENCE_REGEN_SPEED.get())).addTransientModifier(
                    new AttributeModifier(ESSENCE_REGEN, JITL.MODID + ":essence_regen_modifier", player.getAttributeValue(JAttributes.ESSENCE_REGEN_SPEED.get()) + (float) (0.01 * (amplifier + 1)), AttributeModifier.Operation.ADDITION));
            JITL.LOGGER.info("addMod");
        }
        super.addAttributeModifiers(entityLivingBaseIn, attributeMapIn, amplifier);
    }
}
