package net.journey.entity.mob.overworld.jungle;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityJungleTurtle extends JEntityMob {

    public EntityJungleTurtle(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        this.setSize(2.0F, 2.0F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 30);
        EntityAttributesHelper.setAttackDamage(this, 4);
        EntityAttributesHelper.setKnockbackResistance(this, 1);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.SMALL_HONGO;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.SMALL_HONGO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.SMALL_HONGO_HURT;
    }

    @Override
    public boolean attackEntityAsMob(Entity e) {
        boolean attacked = super.attackEntityAsMob(e);
        if (attacked) {
            if (e instanceof EntityLivingBase)
                ((EntityLivingBase) e).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 100, 2)));
            ((EntityLivingBase) e).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 100, 2)));
            e.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F)) * 2, 0.1D, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F)) * 2);
        }
        return attacked;
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.JUNGLE_TURTLE;
    }
}