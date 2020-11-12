package net.journey.entity.projectile;

//import com.google.common.base.Predicate;
//import com.google.common.base.Predicates;
//import net.journey.init.JourneySounds;
import net.journey.entity.mob.boss.EntityCalcia;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.IProjectile;
//import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
//import net.minecraft.entity.projectile.EntityArrow;
//import net.minecraft.entity.projectile.EntityTippedArrow;
//import net.minecraft.entity.projectile.EntityArrow.PickupStatus;
import net.minecraft.init.MobEffects;
//import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
//import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

//import javax.annotation.Nullable;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

//import java.util.List;
import java.util.Objects;

public class EntityEnchantedShot extends EntityThrowable {
	
	@ApiStatus.Internal
	public EntityEnchantedShot(World world) {
		super(world);
	}
	
	public EntityEnchantedShot(World world, EntityLivingBase thrower) {
		super(world, thrower);
	}
	
	@Override
	public void onUpdate() {
		for (int times = 0; times < 3; times++) {
			this.world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, this.posX, this.posY, this.posZ, 0, 0, 0, 1);
		}
		float f1 = 0.99F; //counters EntityThrowable's speed decay
		if (this.isInWater()) f1 = 0.8F;
		motionX /= f1;
		motionY /= f1;
		motionZ /= f1;
		if (this.ticksExisted > 1000) this.setDead();
		super.onUpdate();
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0F;
	}
	
	@Override
	protected void onImpact(@NotNull RayTraceResult rayTraceResult) {
		if (!world.isRemote && !Objects.equals(thrower, rayTraceResult.entityHit)) {
			if (this.getThrower() instanceof EntityCalcia && rayTraceResult.typeOfHit == RayTraceResult.Type.ENTITY && rayTraceResult.entityHit != null) {
				if (rayTraceResult.entityHit instanceof EntityLivingBase) {
					((EntityCalcia) this.getThrower()).possess((EntityLivingBase) rayTraceResult.entityHit, 80, 120);
				}
			}
		}
	}
} 