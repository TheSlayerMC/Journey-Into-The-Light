package net.jitl.common.item.gear.abilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class BloodcrustAbility implements IAbility {
    @Override
    public FullArmorAbility getFullAbility(CompoundNBT nbt) {
        return new BloodcrustFullAbility(nbt);
    }

    public class BloodcrustFullAbility extends FullArmorAbility {
        BloodcrustFullAbility(CompoundNBT nbt) {
            super(nbt);
        }

        @Override
        public void hit(LivingDamageEvent event) {
            System.out.println("Bloodcrust armor hit");
        }
    }
}
