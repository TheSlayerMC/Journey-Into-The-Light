package net.jitl.common.item.curios;

import net.jitl.JITL;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.Objects;
import java.util.UUID;

public class HeartContainerItem extends JCurioItem {
    protected static final UUID HEALTH_MODIFIER = UUID.fromString("9769f32f-1489-40ab-aa87-2985aa3246fd");
    public double hearts;

    public HeartContainerItem(Properties properties) {
        super(properties);
    }

    public HeartContainerItem health(int hearts) {
        this.hearts = hearts;
        return this;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack) {
        LivingEntity livingEntity = slotContext.getWearer();
        if (livingEntity instanceof PlayerEntity) {
            Objects.requireNonNull(livingEntity.getAttribute(Attributes.MAX_HEALTH)).addTransientModifier(new AttributeModifier(HEALTH_MODIFIER, JITL.MODID + ":health_modifier", hearts, AttributeModifier.Operation.ADDITION));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack prevStack) {
        LivingEntity livingEntity = slotContext.getWearer();
        if (livingEntity instanceof PlayerEntity) {
            Objects.requireNonNull(livingEntity.getAttribute(Attributes.MAX_HEALTH)).removeModifier(new AttributeModifier(HEALTH_MODIFIER, JITL.MODID + ":health_modifier", hearts, AttributeModifier.Operation.ADDITION));
        }
    }
}
