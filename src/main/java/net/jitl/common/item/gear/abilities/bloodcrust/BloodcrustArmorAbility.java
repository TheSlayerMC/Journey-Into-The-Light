package net.jitl.common.item.gear.abilities.bloodcrust;

import net.jitl.common.item.gear.abilities.FullArmorAbility;
import net.jitl.common.item.gear.abilities.IAbility;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class BloodcrustArmorAbility implements IAbility {
    @Override
    public FullArmorAbility getFullAbility(CompoundNBT nbt) {
        return new BloodcrustFullAbility(nbt);
    }
}

