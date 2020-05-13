package net.journey.entity.mob.boiling;

import net.journey.entity.MobStats;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.journey.util.JourneyLootTables;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

import java.util.List;

public class EntityBurningLight extends EntityModMob {

    public EntityBurningLight(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(0.7F, 2.0F);
        isImmuneToFire = true;
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.BurningLightDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.BurningLightHealth;
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
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.BURNING_LIGHT;
    }

    @Override
    public ItemStack getHeldItemMainhand() {
        return new ItemStack(JourneyWeapons.boilingBlade);
    }
}