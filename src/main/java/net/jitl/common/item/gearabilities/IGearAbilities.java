package net.jitl.common.item.gearabilities;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public interface IGearAbilities {

    void doTickAbility(LivingEvent.LivingUpdateEvent event);

    double getSwordDamageModifier(LivingHurtEvent event);

    float getModifiedPickSpeed(float ogSpeed, ItemStack stack, BlockState state);

    float getModifiedAxeSpeed(float ogSpeed);

    float getModifiedShovelSpeed(float ogSpeed);

    float getModifiedMultitoolSpeed(float ogSpeed);

    double getArmorReduction(LivingDamageEvent event);
}
