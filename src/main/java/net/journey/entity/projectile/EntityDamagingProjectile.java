package net.journey.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class EntityDamagingProjectile extends EntityThrowable {
	public float damage;

	@ApiStatus.Internal
	public EntityDamagingProjectile(World world) {
		super(world);
	}

	public EntityDamagingProjectile(World world, EntityLivingBase thrower, float damage) {
		super(world, thrower);
		this.damage = damage;
	}

	public float getDamage() {
		return damage;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (!world.isRemote) {
			if (ticksExisted >= 20 * 10) {
				setDead();
			}
		} else {
			onClientUpdate();
		}
	}

	@SideOnly(Side.CLIENT)
	public void onClientUpdate() {

	}

	@Override
	protected void onImpact(@NotNull RayTraceResult rayTraceResult) {
		if (!world.isRemote) {
			if (!Objects.equals(thrower, rayTraceResult.entityHit)) {
				if (rayTraceResult.typeOfHit == RayTraceResult.Type.ENTITY && rayTraceResult.entityHit != null) {
					onEntityImpact(rayTraceResult, rayTraceResult.entityHit);
				}

				setDead();
			}
		}
	}

	/**
	 * Called, when projectile touches target entity.
	 *
	 * @param rayTraceResult result of projectile ray-tracing
	 * @param target         target of projectile. Never equals to the thrower.
	 */
	protected void onEntityImpact(RayTraceResult rayTraceResult, Entity target) {
		target.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
	}

	@Override
	protected float getGravityVelocity() {
		return 0.001F;
	}

	@Override
	public void readEntityFromNBT(@NotNull NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		damage = compound.getFloat("damage");
	}

	@Override
	public void writeEntityToNBT(@NotNull NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		compound.setFloat("damage", damage);
	}
}