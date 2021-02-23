package net.jitl.common.item.gearabilities;

import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class BaseAbilities implements IGearAbilities {
    public double getSwordDamageModifier(LivingHurtEvent event) {
        return 0;
    }

    public float getModifiedPickSpeed(float ogSpeed) {
        return ogSpeed;
    }

    public float getModifiedAxeSpeed(float ogSpeed) {
        return ogSpeed;
    }

    public float getModifiedShovelSpeed(float ogSpeed) {
        return ogSpeed;
    }

    public float getModifiedMultitoolSpeed(float ogSpeed) {
        return ogSpeed;
    }

    public double getArmorReduction(LivingDamageEvent event) {
        return 0;
    }
}
