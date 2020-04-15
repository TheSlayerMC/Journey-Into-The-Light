package net.journey.entity.projectile;

import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityDemonicBomb extends EntityThrowable {

	public float damage;
    
	public EntityDemonicBomb(World var1) {
		super(var1);
	}

	public EntityDemonicBomb(World var1, EntityLivingBase var3, float dam) {
		super(var1, var3);
		damage = dam;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	@Override
	protected void onImpact(RayTraceResult j) {
		float f = (this.rand.nextFloat() - 0.0F) * 0.0F;
		float f1 = (this.rand.nextFloat() - 0.0F) * 0.0F;
		float f2 = (this.rand.nextFloat() - 0.0F) * 0.0F;
		
		if (j.entityHit == null) {
			if (!this.world.isRemote) {
				float x = (rand.nextFloat() + 3.0F) * 1.0F, y = 1, z = (rand.nextFloat() + 3.0F) * 1.0F;
				this.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, this.posX + f, this.posY + 0.0D + f1, this.posZ + f2, 0.0D, 0.0D, 0.0D, new int[0]);
				this.world.playEvent(2002, new BlockPos(this), PotionUtils.getPotionColor(PotionTypes.HARMING));
				this.setDead();
			}
		}

		if(j.entityHit != null && j.entityHit != this.thrower) {			
			
	        if(!this.world.isRemote) {
				j.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), this.damage);
	        	this.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, this.posX + f, this.posY + 0.0D + f1, this.posZ + f2, 0.0D, 0.0D, 0.0D, new int[0]);
		        this.world.playEvent(2002, new BlockPos(this), PotionUtils.getPotionColor(PotionTypes.HARMING));
	        }
			if(!this.world.isRemote) this.setDead();
			return;
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.031F;
	}
}
