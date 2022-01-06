package net.jitl.common.effect;

import net.jitl.JITL;
import net.jitl.init.JAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class EssenceRegenEffect extends MobEffect {
    private static final UUID ESSENCE_REGEN = UUID.fromString("fa05d47a-9821-11eb-a8b3-0242ac130003");

    public EssenceRegenEffect(MobEffectCategory type, int liquidColor) {
        super(type, liquidColor);
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity entityLivingBaseIn, @NotNull AttributeMap attributeMapIn, int amplifier) {
        if (entityLivingBaseIn instanceof Player) {
            Player player = (Player) entityLivingBaseIn;
            Objects.requireNonNull(player.getAttribute(JAttributes.ESSENCE_REGEN_SPEED.get())).removeModifier(ESSENCE_REGEN);
            JITL.LOGGER.info("removeMod");
        }
        super.removeAttributeModifiers(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity entityLivingBaseIn, @NotNull AttributeMap attributeMapIn, int amplifier) {
        if (entityLivingBaseIn instanceof Player) {
            Player player = (Player) entityLivingBaseIn;
            Objects.requireNonNull(player.getAttribute(JAttributes.ESSENCE_REGEN_SPEED.get())).addTransientModifier(
                    new AttributeModifier(ESSENCE_REGEN, JITL.MODID + ":essence_regen_modifier", player.getAttributeValue(JAttributes.ESSENCE_REGEN_SPEED.get()) + (float) (0.01 * (amplifier + 1)), AttributeModifier.Operation.ADDITION));
            JITL.LOGGER.info("addMod");
        }
        super.addAttributeModifiers(entityLivingBaseIn, attributeMapIn, amplifier);
    }
}
