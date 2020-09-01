package net.journey.entity.base;

import net.journey.api.entity.IJERCompatible;
import net.journey.entity.MobStats;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class JEntityFlyingMob extends EntityFlying implements IJERCompatible {

    public JEntityFlyingMob(World w) {
        super(w);
        setSize(0.5F, 0.5F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setFollowRange(this, MobStats.STANDARD_FLYING_FOLLOW_RANGE);
        EntityAttributesHelper.setMovementSpeed(this, MobStats.STANDARD_MOVEMENT_SPEED);
        EntityAttributesHelper.setKnockbackResistance(this, MobStats.STANDARD_KNOCKBACK_RESISTANCE);
        EntityAttributesHelper.setMaxHealth(this, getEntityMaxHealth());
    }

    /**
     * Value in this method will be applied later in {@link #applyEntityAttributes()} for {@link SharedMonsterAttributes#MAX_HEALTH}
     */
    protected abstract double getEntityMaxHealth();

    @Override
    public abstract SoundEvent getAmbientSound();

    @Override
    public abstract SoundEvent getHurtSound(DamageSource source);

    @Override
    public abstract SoundEvent getDeathSound();

    public final double getMoveSpeed() {
        return getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
    }

    public final double getAttackDamage() {
        return getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
    }

    public final double getFollowRange() {
        return getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getAttributeValue();
    }

    public final double getKnockbackResistance() {
        return getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue();
    }

    @Override
    public @Nullable ResourceLocation getJERLootLocation() {
        return getLootTable();
    }
}

