package net.journey.entity.projectile;

import java.util.UUID;
//import com.google.common.base.Predicate;
//import com.google.common.base.Predicates;
//import net.journey.init.JourneySounds;
import net.journey.entity.mob.boss.EntityCalcia;
import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.projectile.EntityArrow;
//import net.minecraft.entity.projectile.EntityTippedArrow;
//import net.minecraft.entity.projectile.EntityArrow.PickupStatus;
import net.minecraft.init.MobEffects;
//import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
//import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
//import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
//import net.minecraft.world.EnumDifficulty;

//import javax.annotation.Nullable;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

//import java.util.List;
//import java.util.Objects;

public class EntityRealityShot extends EntityDamagingProjectile {
	
	private EntityLivingBase trackedEntity;
	private UUID trackedEntityID;
	
	@ApiStatus.Internal
	public EntityRealityShot(World world) {
		super(world);
	}

	public EntityRealityShot(World world, EntityLivingBase thrower, float damage) {
		super(world, thrower, damage);
		if (thrower instanceof EntityCalcia) {
			trackedEntity = ((EntityCalcia) thrower).getAttackTarget();
		}
	}
	
	public EntityRealityShot(World world, EntityLivingBase thrower, EntityLivingBase target, float damage) {
		super(world, thrower, damage);
		trackedEntity = target;
	}
	
	@Override
	public void onUpdate() {
		if (!this.world.isRemote) {
			if (trackedEntity!= null) {
				double x = trackedEntity.posX - this.posX;
				double y = trackedEntity.posY - this.posY + trackedEntity.height / 2;
				double z = trackedEntity.posZ - this.posZ;
				double f = (x * x + y * y + z * z);
				x = (x / f) * 0.1;
				y = (y / f) * 0.1;
				z = (z / f) * 0.1;
				addVelocity(x, y, z);
				velocityChanged = true;
			} else if (trackedEntityID != null) {
				trackedEntity = (EntityLivingBase) ((WorldServer) world).getEntityFromUuid(trackedEntityID);
			}
		}
		for (int times = 0; times < 10; times++) {
			this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX, this.posY - 1, this.posZ, 0, 0, 0, 0);
		}
		if (!this.world.isRemote && this.ticksExisted >= 100) {
			burst();
		}
		super.onUpdate();
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0;
	}
	
	@Override
	protected void onImpact(@NotNull RayTraceResult rayTraceResult) {
		if (!world.isRemote) {
			if (rayTraceResult.typeOfHit != RayTraceResult.Type.ENTITY) {
				burst();
			}
		}
		super.onImpact(rayTraceResult);
	}
	
	@Override
	protected void onEntityImpact(RayTraceResult rayTraceResult, Entity target) {
		float damageDealt = damage;
		if (target instanceof EntityPlayer) {
			((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 1));
			damageDealt = (damageDealt / 2) * world.getDifficulty().getId();
		}
		target.attackEntityFrom(DamageSource.MAGIC, damageDealt);
	    this.setDead();
	}
	
	@Override
	public void readEntityFromNBT(@NotNull NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		trackedEntityID = compound.getUniqueId("trackedEntityID");
	}

	@Override
	public void writeEntityToNBT(@NotNull NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		if (trackedEntityID == null) {
			trackedEntityID = trackedEntity.getUniqueID();
		}
		compound.setUniqueId("trackedEntityID", trackedEntityID);
	}
	
	private void burst() {
		if (this.getThrower() != null) {
			for (int shotCount = 0; shotCount < 15; shotCount++) {
				double rotation = Math.toRadians(rand.nextInt(360));
	       		EntityEnchantedShot shot = new EntityEnchantedShot(this.world, this.getThrower());
	       		shot.posX = this.posX;
	       		shot.posY = this.posY;
	       		shot.posZ = this.posZ;
	            shot.shoot(Math.cos(rotation), 0, Math.sin(rotation), 0.30f, 0f);
	            this.world.spawnEntity(shot);
			}
			this.setDead();
		}
	}
} 