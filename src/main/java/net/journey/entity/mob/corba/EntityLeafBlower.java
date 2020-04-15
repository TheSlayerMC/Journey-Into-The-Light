package net.journey.entity.mob.corba;

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
    public Item getItemDropped() {
        return null;

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

    @Override
    protected void dropFewItems(boolean b, int j) {
        Item it = getItemDropped();
        this.dropItem(it, 1);
        if (rand.nextInt(6) == 0) dropItem(JourneyItems.corbaStick, rand.nextInt(4));
        if (rand.nextInt(6) == 0) dropItem(JourneyItems.enchantedLeaf, rand.nextInt(4));
        if (rand.nextInt(24) == 0) dropItem(JourneyItems.natureTablet, rand.nextInt(4));
        super.dropFewItems(b, j);
    }
}