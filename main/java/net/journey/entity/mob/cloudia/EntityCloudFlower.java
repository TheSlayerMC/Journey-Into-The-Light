package net.journey.entity.mob.cloudia;

import java.util.List;

import net.journey.entity.MobStats;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityCloudFlower extends EntityModMob {

	public EntityCloudFlower(World par1World) {
		super(par1World);
		this.setSize(2.0F, 0.2145F);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource e, float a) {
		if(e.getImmediateSource() instanceof EntityPlayer)
			((EntityPlayer)e.getImmediateSource()).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.jump, 150, 10)));
		return super.attackEntityFrom(e, a);
	}
	
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(this.world.isDaytime() && !this.world.isRemote) {
            float var1 = getBrightness();
        }
        
        List<Entity> e = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());        
        for(Entity entity : e) {
        	if(entity instanceof EntityPlayer && canEntityBeSeen(entity)) ((EntityPlayer)entity).addVelocity(0.0D, 2.5D, 0.0D);
        }   
    }

	@Override
	public double setMovementSpeed() {
		return 0;
	}
	
	@Override
	public double setAttackDamage(MobStats s) {
		return 0;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return s.CloudFlyerHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return null;
	}

	@Override
	public SoundEvent setHurtSound() {
		return null;
	}

	@Override
	public SoundEvent setDeathSound() {
		return null;
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
}