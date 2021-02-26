package net.jitl.common.item.gearabilities;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class BaseAbilities implements IGearAbilities {
    public void doTickAbility(LivingEvent.LivingUpdateEvent event) {

    }

    public double getSwordDamageModifier(LivingHurtEvent event) {
        return 0;
    }

    public float getModifiedPickSpeed(float ogSpeed, ItemStack stack, BlockState state) {
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
