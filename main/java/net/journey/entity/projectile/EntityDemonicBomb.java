package net.journey.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
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
		this.world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, this.posX + f, this.posY + 0.0D + f1,
				this.posZ + f2, 0.0D, 0.0D, 0.0D, new int[0]);
		if (j.entityHit != null)
			j.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
		this.world.playBroadcastSound(2002, new BlockPos(this), 5);
		int i = 3 + this.world.rand.nextInt(5) + this.world.rand.nextInt(5);
		this.setDead();
	}

	@Override
	protected float getGravityVelocity() {
		return 0.031F;
	}
}
