package net.jitl.common.item.curios.ring;

import net.jitl.common.item.curios.JCurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;

import java.util.function.Supplier;

public class JRingItem extends JCurioItem {
    private Effect potion;

    public JRingItem(Properties properties, String ability, String negativeEffects) {
        super(properties, ability, negativeEffects);
    }

    public JRingItem effect(Supplier<Effect> effectSupplier) {
        this.potion = effectSupplier.get();
        return this;
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if (livingEntity.hasEffect(potion)) {
            livingEntity.removeEffect(potion);
        }
    }
}
