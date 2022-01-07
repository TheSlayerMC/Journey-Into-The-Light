package net.jitl.common.item.curios.ring;

import net.jitl.common.item.curios.JCurioItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class JRingItem extends JCurioItem {
    private MobEffect potion;

    public JRingItem(Properties properties) {
        super(properties);
    }

    public JRingItem effect(Supplier<MobEffect> effectSupplier) {
        this.potion = effectSupplier.get();
        return this;
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if (!(livingEntity instanceof Player)) return;

        if (!livingEntity.level.isClientSide()) {
            if (!stack.hasTag()) stack.setTag(new CompoundTag());
            CompoundTag tag = stack.getTag();
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
        }
    }
}
