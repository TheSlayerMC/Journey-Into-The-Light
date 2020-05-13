package net.journey.entity.mob.euca;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyItems;
import net.journey.util.JourneyLootTables;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityDynaster extends EntityModMob {


    public EntityDynaster(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(1.0F, 1.0F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.DynasterDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.DynasterHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.DYNASTER;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.DYNASTER_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.DYNASTER_DEATH;
    }

    @Override
    public boolean attackEntityAsMob(Entity e) {
        boolean attacked = super.attackEntityAsMob(e);
        if (attacked) {
            if (e instanceof EntityLivingBase)
                ((EntityLivingBase) e).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 50, 2)));
            ((EntityLivingBase) e).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.blindness, 50, 2)));
        }
        return attacked;
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.DYNASTER;
    }
}