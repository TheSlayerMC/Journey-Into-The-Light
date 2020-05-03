package net.journey.entity.projectile.throwable;

import net.journey.init.JourneySounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityMagicPot extends EntityThrowable {

	public float damage;
	public EntityLivingBase thrower;
	protected int bounces, maxBounces;

	public EntityMagicPot(World var1) {
		super(var1);
	}

	public EntityMagicPot(World var1, EntityLivingBase var3, float dam, int max) {
		super(var1, var3);
		this.damage = dam;
		this.thrower = var3;
		this.maxBounces = max;
	}

	@Override
	protected void onImpact(RayTraceResult par1) {
		if (par1.entityHit != null && par1.entityHit != this.thrower) {
			this.playSound(JourneySounds.ROCK, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
			par1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), this.damage);
			if (!this.world.isRemote) this.setDead();
			return;
		}
		if (par1.sideHit == EnumFacing.UP || par1.sideHit == EnumFacing.DOWN) {
			this.motionY *= -0.5D;
		} else if (par1.sideHit == EnumFacing.SOUTH || par1.sideHit == EnumFacing.NORTH) {
			this.motionZ *= -0.5D;
		} else if (par1.sideHit == EnumFacing.EAST || par1.sideHit == EnumFacing.WEST) {
			this.motionX *= -0.5D;
		}
		if (!world.isRemote) 
			world.createExplosion(this, this.posX, this.posY, this.posZ, 0.5F, true);
		this.bounces++;
		if (this.bounces == maxBounces) this.setDead();
	}

	@Override
	protected float getGravityVelocity() {
		return 0.031F;
	}
}