package net.journey.entity.projectile;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.journey.init.JourneySounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EntitySentryKingGrenade extends EntityTippedArrow implements IProjectile {
	private int grenadeTimer;

	private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, new Predicate<Entity>() {
		public boolean apply(@Nullable Entity e) {
			return e.canBeCollidedWith();
		}
	});

	public EntitySentryKingGrenade(World worldIn) {
		super(worldIn);
		this.pickupStatus = PickupStatus.DISALLOWED;
		this.grenadeTimer = 200;

	}
	
	public EntitySentryKingGrenade(World worldIn, int grenadeTime) {
		this(worldIn);
		this.grenadeTimer = grenadeTime;

	}

	public EntitySentryKingGrenade(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
		this.pickupStatus = PickupStatus.DISALLOWED;
		this.grenadeTimer = 200;

	}
	
	public EntitySentryKingGrenade(World worldIn, EntityLivingBase shooter, int grenadeTime) {
		this(worldIn, shooter);
		this.grenadeTimer = grenadeTime;

	}

	public EntitySentryKingGrenade(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		this.pickupStatus = PickupStatus.DISALLOWED;
		this.grenadeTimer = 200;

	}
	
	public EntitySentryKingGrenade(World worldIn, double x, double y, double z, int grenadeTime) {
		this(worldIn, x, y, z);
		this.grenadeTimer = grenadeTime;

	}

	public EntitySentryKingGrenade(World worldIn, EntityLivingBase e, EntityLivingBase eb, float f, float f1) {
		super(worldIn);
		Entity.setRenderDistanceWeight(10.0D);
		this.shootingEntity = e;
		this.grenadeTimer = 200;

		this.posY = e.posY + e.getEyeHeight() - 0.10000000149011612D;
		double d0 = eb.posX - e.posX;
		double d1 = eb.getEntityBoundingBox().minY + eb.height / 3.0F - this.posY;
		double d2 = eb.posZ - e.posZ;
		double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-7D) {
			float f2 = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI));
			double d4 = d0 / d3;
			double d5 = d2 / d3;
			this.setLocationAndAngles(e.posX + d4, this.posY, e.posZ + d5, f2, f3);
			float f4 = (float) (d3 * 0.20000000298023224D);
			this.shoot(d0, d1 + f4, d2, f, f1);
		}
		this.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
	}
	
	public EntitySentryKingGrenade(World worldIn, EntityLivingBase e, EntityLivingBase eb, float f, float f1, int grenadeTime) {
		this(worldIn, e, eb, f, f1);
		grenadeTimer = grenadeTime;
	}

	@Override
	protected void onHit(RayTraceResult target) {
		super.onHit(target);
		Entity hitEntity = target.entityHit;
		if (hitEntity != null && shootingEntity != null && hitEntity instanceof EntityPlayer) {
			if (!world.isRemote) {
				explode();
			}
		}
		this.playSound(JourneySounds.BOTTLE_PLUG, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
	}
	
	@Override
	public void onUpdate() {
		if (!world.isRemote && ticksExisted > grenadeTimer) {
			explode();
		}
		super.onUpdate();
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		float size = 0.3F;
		collideWithEntity(this.world.getEntitiesWithinAABBExcludingEntity(this.shootingEntity, this.getEntityBoundingBox()));
	}

	private void collideWithEntity(List<Entity> entitys) {
		for (Entity entity : entitys) {
			if (entity instanceof EntityLivingBase) {
				if (!world.isRemote) {
					explode();
				}
			}
		}
	}

	@Override
	protected ItemStack getArrowStack() {
		return null;
	}
	
	@Override
	public void readEntityFromNBT(@NotNull NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		grenadeTimer = compound.getInteger("explosionTime");
	}

	@Override
	public void writeEntityToNBT(@NotNull NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setFloat("explosionTime", grenadeTimer);
	}
	
	private void explode() {
		world.createExplosion(this, posX, posY, posZ, 2.0F, false);
		this.setDead();
	}
}