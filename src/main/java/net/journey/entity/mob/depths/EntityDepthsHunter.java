package net.journey.entity.mob.depths;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyItems;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

import java.util.List;

public class EntityDepthsHunter extends EntityModMob {

    public EntityDepthsHunter(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(1.0F, 2.3F);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.world.isDaytime() && !this.world.isRemote) {
            float var1 = getBrightness();
        }

        List<Entity> e = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());
        for (Entity entity : e) {
            if (entity instanceof EntityPlayer && canEntityBeSeen(entity))
                ((EntityPlayer) entity).addPotionEffect(PotionEffects.setPotionEffect(PotionEffects.blindness, 60, 1));
        }
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.DepthsHunterDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.DepthsHunterHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.DEPTHS_HUNTER;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.DEPTHS_HUNTER_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.DEPTHS_HUNTER_HURT;
    }

    @Override
    public Item getItemDropped() {
        return JourneyItems.darkCrystal;
    }
}