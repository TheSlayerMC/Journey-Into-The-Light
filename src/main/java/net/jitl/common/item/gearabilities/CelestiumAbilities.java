package net.jitl.common.item.gearabilities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class CelestiumAbilities implements IGearAbilities {
    private int cooldown = 0;
    private boolean isJumpReady = false;


    @Override
    public void doTickAbility(LivingEvent.LivingUpdateEvent event) {
        System.out.println("Dash cooldown: " + cooldown);
        System.out.println("Dash " + (isJumpReady ? "is" : "is not") + " ready");
        if (cooldown <= 0) {
            LivingEntity entity = event.getEntityLiving();
            if (entity.isOnGround() && !isJumpReady) {
                isJumpReady = true;
                //TODO: add particles
            }
        } else {
            cooldown--;
        }
    }

    @Override
    public double getSwordDamageModifier(LivingHurtEvent event) {
        return 0;
    }

    @Override
    public float getModifiedPickSpeed(float ogSpeed, ItemStack stack, BlockState state) {
        return 0;
    }

    @Override
    public float getModifiedAxeSpeed(float ogSpeed) {
        return 0;
    }

    @Override
    public float getModifiedShovelSpeed(float ogSpeed) {
        return 0;
    }

    @Override
    public float getModifiedMultitoolSpeed(float ogSpeed) {
        return 0;
    }

    @Override
    public double getArmorReduction(LivingDamageEvent event) {
        return 0;
    }

    public void doCharge(LivingEntity entity, double rotation) {
        if (isJumpReady) {
            entity.setDeltaMovement(Math.cos(rotation) * 0.75, 0, Math.sin(rotation) * 0.75);
            entity.hurtMarked = true;
            isJumpReady = false;
            cooldown = 40;
        }
    }
}
