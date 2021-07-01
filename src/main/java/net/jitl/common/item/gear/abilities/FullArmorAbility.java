package net.jitl.common.item.gear.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public abstract class FullArmorAbility {
    private CompoundNBT tag;

    public FullArmorAbility(CompoundNBT nbt) {
        tag = nbt;
    }

    public void tick(LivingEntity entity) {}

    public void hit(LivingDamageEvent event) {}

    public void keyPressed(LivingEntity entity) {}
}
