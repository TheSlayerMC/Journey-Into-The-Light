package net.journey.entity.mob.boiling;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyWeapons;
import net.journey.entity.MobStats;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityBurningLight extends EntityModMob{

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
        if(this.world.isDaytime() && !this.world.isRemote) {
            float var1 = getBrightness();
        }
        
        List<Entity> e = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());        
        for(Entity entity : e) {
        	if(entity instanceof EntityPlayer && canEntityBeSeen(entity)) ((EntityPlayer)entity).setFire(5 + rand.nextInt(7));
        }        
    }
	
	@Override
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	public ItemStack getHeldItemMainhand() {
		return new ItemStack(JourneyWeapons.boilingBlade);
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		Item it = getItemDropped();
		this.dropItem(it, 1);
		if(rand.nextInt(14) == 0) dropItem(JourneyItems.boilPowder, rand.nextInt(4));
		if(rand.nextInt(40) == 0) dropItem(JourneyItems.blazingFireball, 1);
		super.dropFewItems(b, j); 
		
		}
	}