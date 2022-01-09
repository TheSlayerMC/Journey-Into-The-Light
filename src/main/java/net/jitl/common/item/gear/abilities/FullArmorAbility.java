package net.jitl.common.item.gear.abilities;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public abstract class FullArmorAbility {
    protected CompoundTag tag;

    public FullArmorAbility(CompoundTag nbt) {
        tag = nbt;
    }

    public void tick(LivingEntity entity) {}

    public void hit(LivingDamageEvent event) {}

    public void keyPressed(LivingEntity entity) {}
}
