package net.journey.entity.projectile.throwable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyWeapons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class EntityMagicBomb extends EntityTippedArrow implements IProjectile {

	private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, new Predicate<Entity>() {
		public boolean apply(@Nullable Entity e) {
			return e.canBeCollidedWith();
		}
	});

	public EntityMagicBomb(World worldIn) {
		super(worldIn);
		this.pickupStatus = EntityArrow.PickupStatus.ALLOWED;

	}
	
	public EntityMagicBomb(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
		this.pickupStatus = EntityArrow.PickupStatus.ALLOWED;

	}

	public EntityMagicBomb(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		this.pickupStatus = EntityArrow.PickupStatus.ALLOWED;

	}

	public EntityMagicBomb(World worldIn, EntityLivingBase e, EntityLivingBase eb, float f, float f1) {
		super(worldIn);
		Entity.setRenderDistanceWeight(10.0D);
		this.shootingEntity = e;

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

	@Override
	protected void onHit(RayTraceResult target) {
		super.onHit(target);
		Entity hitEntity = target.entityHit;
		if(hitEntity != null && shootingEntity != null && hitEntity instanceof EntityLivingBase) {
			if (!world.isRemote) {
				world.createExplosion(this, posX, posY, posZ, 2.0F, true);
				this.setDead();
			}
		}
		this.playSound(JourneySounds.BOTTLE_PLUG, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		float size = 0.3F;
		collideWithEntity(this.world.getEntitiesWithinAABBExcludingEntity(this.shootingEntity, this.getEntityBoundingBox()));
	}

	private void collideWithEntity(List<Entity> entitys) {
		for(Entity entity : entitys) {
			if(entity instanceof EntityLivingBase) {
				if(!world.isRemote) {
					world.createExplosion(this, posX, posY, posZ, 2.0F, true);
					this.setDead();
				}
			}
		}
	}

    @Override
    protected ItemStack getArrowStack() {
	    return new ItemStack(JourneyWeapons.magicBomb);
    }
}