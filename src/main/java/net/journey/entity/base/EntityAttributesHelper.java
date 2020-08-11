package net.journey.entity.base;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

public class EntityAttributesHelper {

    public static void setFollowRange(EntityLivingBase entity, double baseValue) {
        setAttribute(entity, SharedMonsterAttributes.FOLLOW_RANGE, baseValue);
    }

    public static void setMovementSpeed(EntityLivingBase entity, double baseValue) {
        setAttribute(entity, SharedMonsterAttributes.MOVEMENT_SPEED, baseValue);
    }

    public static void setKnockbackResistance(EntityLivingBase entity, double baseValue) {
        setAttribute(entity, SharedMonsterAttributes.KNOCKBACK_RESISTANCE, baseValue);
    }

    public static void setMaxHealth(EntityLivingBase entity, double baseValue) {
        setAttribute(entity, SharedMonsterAttributes.MAX_HEALTH, baseValue);
    }

    public static void setAttackDamage(EntityLivingBase entity, double baseValue) {
        setAttribute(entity, SharedMonsterAttributes.ATTACK_DAMAGE, baseValue);
    }

    public static void setAttribute(EntityLivingBase entity, IAttribute attribute, double baseValue) {
        IAttributeInstance instance = entity.getAttributeMap().getAttributeInstance(attribute);
        //noinspection ConstantConditions
        if (instance == null) {
            instance = entity.getAttributeMap().registerAttribute(attribute);
        }

        instance.setBaseValue(baseValue);
    }
}
