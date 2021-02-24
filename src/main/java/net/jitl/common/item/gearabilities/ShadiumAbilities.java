package net.jitl.common.item.gearabilities;

import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ShadiumAbilities implements IGearAbilities {
    @Override
    public double getSwordDamageModifier(LivingHurtEvent event) {
        System.out.println("Extra damage: " + event.getAmount() * (1 - event.getSource().getEntity().getBrightness()) / 2);
        return event.getAmount() * (1 - event.getSource().getEntity().getBrightness()) / 2;
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
        System.out.println("Darkness: " + (1 - event.getSource().getEntity().getBrightness()));
        System.out.println("Damage reduction: " + (event.getSource().isBypassArmor() ? 0 : (event.getAmount() / 5) * -(1 - event.getSource().getEntity().getBrightness())));
        return event.getSource().isBypassArmor() ? 0 : (event.getAmount() / 5) * -(1 - event.getSource().getEntity().getBrightness());
    }
}
