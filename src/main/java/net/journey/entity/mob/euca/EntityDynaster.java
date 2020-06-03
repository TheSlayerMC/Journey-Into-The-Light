package net.journey.entity.mob.euca;

import net.journey.entity.MobStats;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.JEntityMob;

import org.jetbrains.annotations.NotNull;

public class EntityDynaster extends JEntityMob {


    public EntityDynaster(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(1.0F, 1.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.DYNASTER;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.DYNASTER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
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
	public ResourceLocation getLootTable() {
		return JourneyLootTables.DYNASTER;
	}

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.DYNASTER;
    }
}