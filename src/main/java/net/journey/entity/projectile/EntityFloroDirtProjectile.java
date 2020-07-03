package net.journey.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.ApiStatus;

public class EntityFloroDirtProjectile extends EntityDamagingProjectile {

	@ApiStatus.Internal
	public EntityFloroDirtProjectile(World world) {
		super(world);
	}

	public EntityFloroDirtProjectile(World world, EntityLivingBase thrower, float damage) {
		super(world, thrower, damage);
	}

	@Override
	protected void onEntityImpact(RayTraceResult rayTraceResult, Entity target) {
		super.onEntityImpact(rayTraceResult, target);
		if (target instanceof EntityLivingBase) {
//			((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 1));
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.032F;
	}
}