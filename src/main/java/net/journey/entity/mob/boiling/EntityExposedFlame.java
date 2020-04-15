package net.journey.entity.mob.boiling;

import net.journey.JourneyItems;
import net.journey.JourneyWeapons;
import net.journey.entity.MobStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

import java.util.List;

public class EntityExposedFlame extends EntityModMob {

    public EntityExposedFlame(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(0.7F, 2.0F);
        isImmuneToFire = true;
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.ExposedFlameDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.ExposedFlameHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return SoundEvents.ENTITY_BLAZE_AMBIENT;
    }

    @Override
    public SoundEvent setHurtSound() {
        return SoundEvents.ENTITY_BLAZE_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return SoundEvents.ENTITY_BLAZE_DEATH;
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
    public ItemStack getHeldItemMainhand() {
        return new ItemStack(JourneyWeapons.boilingBlade);
    }

    @Override
    protected void dropFewItems(boolean b, int j) {
        Item it = getItemDropped();
        this.dropItem(it, 1);
        if (rand.nextInt(20) == 0) dropItem(JourneyItems.blazingFireball, 1);
        super.dropFewItems(b, j);
    }
}