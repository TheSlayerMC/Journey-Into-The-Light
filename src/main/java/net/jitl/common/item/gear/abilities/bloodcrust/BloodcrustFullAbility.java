package net.jitl.common.item.gear.abilities.bloodcrust;

import net.jitl.common.item.gear.abilities.FullArmorAbility;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class BloodcrustFullAbility extends FullArmorAbility {
    BloodcrustFullAbility(CompoundNBT nbt) {
        super(nbt);
    }

    @Override
    public void hit(LivingDamageEvent event) {
        System.out.println("Bloodcrust armor hit");
    }
}
