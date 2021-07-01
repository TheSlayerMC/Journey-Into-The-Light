package net.jitl.common.item.gear.abilities.bloodcrust;

import net.jitl.common.item.gear.abilities.IAbility;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class BloodcrustSwordAbility implements IAbility {
    @Override
    public void attackTarget(LivingDamageEvent event) {
        System.out.println("Bloodcrust attack");
    }
}
