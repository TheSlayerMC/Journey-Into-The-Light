package net.jitl.common.item.gearabilities;

import net.jitl.common.helper.TooltipFiller;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public abstract class BaseToolAbilities {
    public double getSwordDamageModifier(LivingHurtEvent event) {
        return 0;
    }

    public void onSweep(ItemStack itemStack, Entity victim, LivingEntity attacker) {

    }

    public float getModifiedPickSpeed(float ogSpeed, ItemStack stack, BlockState state) {
        return 0;
    }

    public float getModifiedAxeSpeed(float ogSpeed) {
        return 0;
    }

    public float getModifiedShovelSpeed(float ogSpeed) {
        return 0;
    }

    public float getModifiedMultitoolSpeed(float ogSpeed) {
        return 0;
    }

    public void fillSwordTooltip(TooltipFiller filler) {

    }
}
