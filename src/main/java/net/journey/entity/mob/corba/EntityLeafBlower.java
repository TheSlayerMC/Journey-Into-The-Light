package net.journey.entity.mob.corba;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyItems;
import net.journey.util.JourneyLootTables;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

import java.util.List;

public class EntityLeafBlower extends EntityModMob {

    public EntityLeafBlower(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(1.0F, 2.0F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.LeafBlowerDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.LeafBlowerHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.BUSH;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.BUSH_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.BUSH_DEATH;
    }
    
    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.LEAF_BLOWER;
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
                ((EntityPlayer) entity).addPotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 60, 1));
        }
    }
}