package net.journey.entity.mob.frozen;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityFrozenTroll extends EntityModMob {

    public EntityFrozenTroll(World par1World) {
        super(par1World);
        addAttackingAI();
        this.setSize(0.5F, 1.2F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1);
    }

    @Override
    public boolean attackEntityAsMob(Entity e) {
        boolean attacked = super.attackEntityAsMob(e);
        if (attacked) {
            if (e instanceof EntityLivingBase)
                ((EntityLivingBase) e).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 100, 2)));
            e.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F)) * 2, 0.1D, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F)) * 2);
        }
        return attacked;
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.FrozenTrollDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.FrozenTrollHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.SMALL_HONGO;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.SMALL_HONGO_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.SMALL_HONGO_HURT;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 60.0D && super.getCanSpawnHere();
    }

    @Override
    public Item getItemDropped() {
        return null;
    }
}