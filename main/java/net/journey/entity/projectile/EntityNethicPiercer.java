package net.journey.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityNethicPiercer extends EntityThrowable {

	public float damage;

	public EntityNethicPiercer(World var1) {
		super(var1);
	}

	public EntityNethicPiercer(World var1, EntityLivingBase var3, float dam) {
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
	protected void onImpact(RayTraceResult var1) {
		if(var1.entityHit != null) var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
		if(!world.isRemote) this.setDead();
	}

	@Override
	protected float getGravityVelocity() {
		return 0.032F;
	}
}