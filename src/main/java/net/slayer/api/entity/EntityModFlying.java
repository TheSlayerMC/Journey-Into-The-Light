package net.slayer.api.entity;

import net.journey.entity.MobStats;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public abstract class EntityModFlying extends EntityFlying {

    public EntityModFlying(World w) {
        super(w);
        setSize(0.5F, 0.5F);
    }

    public double getHP() {
        return getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue();
    }

    public double getMoveSpeed() {
        return getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
    }

    public double getAttackDamage() {
        return getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
    }

    public double getFollowRange() {
        return getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getAttributeValue();
    }

    public double getKnockbackResistance() {
        return getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(setFollowRange());
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(setMovementSpeed());
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(setKnockbackResistance());
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(setMaxHealth(new MobStats()));
    }

    public double setFollowRange() {
        return MobStats.flying_follow;
    }

    public double setMovementSpeed() {
        return MobStats.standardMovementSpeed;
    }

    public double setKnockbackResistance() {
        return MobStats.standardKnockBackResistance;
    }

    public abstract double setMaxHealth(MobStats s);

    public abstract SoundEvent setLivingSound();

    public abstract SoundEvent setHurtSound();

    public abstract SoundEvent setDeathSound();

    @Override
    protected SoundEvent getAmbientSound() {
        super.getAmbientSound();
        return setLivingSound();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        super.getHurtSound(d);
        return setHurtSound();
    }

    @Override
    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return setDeathSound();
    }
}
