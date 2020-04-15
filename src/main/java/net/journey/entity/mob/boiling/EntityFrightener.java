package net.journey.entity.mob.boiling;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

import java.util.List;

public class EntityFrightener extends EntityModMob {

    public EntityFrightener(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(0.7F, 2.0F);
        isImmuneToFire = true;
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.FrightenerDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.FrightenerHealth;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.HONGO;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return JourneySounds.HONGO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.CALCIA_HURT;
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
                entity.setFire(5 + rand.nextInt(7));
        }
    }

    @Override
    public Item getItemDropped() {
        return JourneyItems.boilPowder;

    }

    @Override
    protected void dropFewItems(boolean b, int j) {
        Item it = getItemDropped();
        this.dropItem(it, 1);
        if (rand.nextInt(20) == 0) dropItem(JourneyItems.sizzlingEye, 1);
        super.dropFewItems(b, j);
    }

    @Override
    public SoundEvent setLivingSound() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SoundEvent setHurtSound() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SoundEvent setDeathSound() {
        // TODO Auto-generated method stub
        return null;
    }
}