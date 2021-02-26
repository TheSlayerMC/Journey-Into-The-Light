package net.jitl.common.item.gearabilities;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class ShadiumAbilities implements IGearAbilities {
    @Override
    public void doTickAbility(LivingEvent.LivingUpdateEvent event) {

    }

    @Override
    public double getSwordDamageModifier(LivingHurtEvent event) {
        System.out.println("Extra damage: " + event.getAmount() * (1 - event.getSource().getEntity().getBrightness()) / 2);
        return event.getAmount() * (1 - event.getSource().getEntity().getBrightness()) / 2;
    }

    @Override
    public float getModifiedPickSpeed(float ogSpeed, ItemStack stack, BlockState state) {
        return 0;
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
