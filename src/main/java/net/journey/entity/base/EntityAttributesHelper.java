package net.journey.entity.base;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

import java.util.Optional;

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

    public static Optional<Double> getFollowRange(EntityLivingBase entity) {
        return getAttributeValue(entity, SharedMonsterAttributes.FOLLOW_RANGE);
    }

    public static Optional<Double> getMovementSpeed(EntityLivingBase entity) {
        return getAttributeValue(entity, SharedMonsterAttributes.MOVEMENT_SPEED);
    }

    public static Optional<Double> getKnockbackResistance(EntityLivingBase entity) {
        return getAttributeValue(entity, SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
    }

    public static Optional<Double> getMaxHealth(EntityLivingBase entity) {
        return getAttributeValue(entity, SharedMonsterAttributes.MAX_HEALTH);
    }

    public static Optional<Double> getAttackDamage(EntityLivingBase entity) {
        return getAttributeValue(entity, SharedMonsterAttributes.ATTACK_DAMAGE);
    }

    public static void setAttribute(EntityLivingBase entity, IAttribute attribute, double baseValue) {
        IAttributeInstance instance = entity.getAttributeMap().getAttributeInstance(attribute);
        //noinspection ConstantConditions
        if (instance == null) {
            instance = entity.getAttributeMap().registerAttribute(attribute);
        }

        instance.setBaseValue(baseValue);
    }

    public static Optional<Double> getAttributeValue(EntityLivingBase entity, IAttribute attribute) {
        IAttributeInstance entityAttribute = entity.getEntityAttribute(attribute);

        //noinspection ConstantConditions
        if (entityAttribute != null) {
            return Optional.of(entityAttribute.getAttributeValue());
        } else {
            return Optional.of(null);
        }
    }
}
