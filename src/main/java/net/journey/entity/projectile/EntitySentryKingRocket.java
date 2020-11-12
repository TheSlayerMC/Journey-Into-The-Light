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
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class EntitySentryKingRocket extends EntityDamagingProjectile {
	@ApiStatus.Internal
	public EntitySentryKingRocket(World world) {
		super(world);
	}

	public EntitySentryKingRocket(World world, EntityLivingBase thrower, float damage) {
		super(world, thrower, damage);
	}
	
	@Override
	public void onUpdate() {
		this.world.spawnParticle(EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ, this.motionX * -1, this.motionY * -1, this.motionZ * -1, 0);
		super.onUpdate();
	}
	
	@Override
	protected void onImpact(@NotNull RayTraceResult rayTraceResult) {
		if (!world.isRemote) {
			world.createExplosion(this, posX, posY, posZ, 5.0F, true);
			this.setDead();
		}
	}
}