package net.jitl.common.item.curios.ring;

import net.jitl.common.item.curios.JCurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;

import java.util.function.Supplier;

public class JRingItem extends JCurioItem {
    private Effect potion;

    public JRingItem(Properties properties) {
        super(properties);
    }

    public JRingItem effect(Supplier<Effect> effectSupplier) {
        this.potion = effectSupplier.get();
        return this;
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if (!livingEntity.level.isClientSide()) {
            CompoundNBT tag = stack.hasTag() ? stack.getTag() : new CompoundNBT();
            int cooldown = tag.getInt("cooldown");
            if (cooldown == 0) {
                if (livingEntity.hasEffect(potion)) {
                    livingEntity.removeEffect(potion);
                    tag.putInt("cooldown", 400);
                }
            } else {
                tag.putInt("cooldown", Math.max(0, cooldown - (livingEntity.hasEffect(potion) ? 1 : 4)));
            }
            System.out.println(cooldown);
            stack.setTag(tag);
        }
    }
}
