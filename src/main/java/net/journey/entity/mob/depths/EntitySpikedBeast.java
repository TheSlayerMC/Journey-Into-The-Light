package net.journey.entity.mob.depths;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

import java.util.List;

public class EntitySpikedBeast extends EntityModMob {

    public EntitySpikedBeast(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(0.7F, 1.5F);
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
        return MobStats.SpikedBeastDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.SpikedBeastHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.SPIKED_BEAST;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.SPIKED_BEAST_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.SPIKED_BEAST_HURT;
    }

    @Override
    public Item getItemDropped() {
        return JourneyItems.darkCrystal;
    }
}