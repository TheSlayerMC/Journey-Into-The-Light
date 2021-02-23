package net.jitl.common.item.gearabilities;

import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public interface IGearAbilities {
    double getSwordDamageModifier(LivingHurtEvent event);

    float getModifiedPickSpeed(float ogSpeed);

    float getModifiedAxeSpeed(float ogSpeed);

    float getModifiedShovelSpeed(float ogSpeed);

    float getModifiedMultitoolSpeed(float ogSpeed);

    double getArmorReduction(LivingDamageEvent event);
}
