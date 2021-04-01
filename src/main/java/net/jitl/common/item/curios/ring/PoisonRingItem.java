package net.jitl.common.item.curios.ring;

import net.jitl.common.item.curios.JCurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;

public class PoisonRingItem extends JCurioItem {
    public PoisonRingItem(Properties properties) {
        super(properties);
    }

    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        Effect potion = Effects.POISON.getEffect();
        if (livingEntity.hasEffect(potion)) {
            livingEntity.removeEffect(potion);
        }
    }
}
