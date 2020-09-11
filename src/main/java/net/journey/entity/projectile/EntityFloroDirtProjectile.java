package net.journey.entity.projectile;

import net.journey.client.render.particles.ParticleFloroMud;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
	@SideOnly(Side.CLIENT)
	public void onClientUpdate() {
		super.onClientUpdate();
		int count = 6;
		for (int i = 0; i < count; ++i) {
			double posX = this.lastTickPosX - this.motionX * (double) i / count;
			double posY = this.lastTickPosY - this.motionY * (double) i / count;
			double posZ = this.lastTickPosZ - this.motionZ * (double) i / count;

			ParticleFloroMud effect = new ParticleFloroMud(this.world, posX, posY, posZ, -this.motionX, -(this.motionY + 0.2D), -this.motionZ);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(effect);
		}
	}

	@Override
	protected void onEntityImpact(RayTraceResult rayTraceResult, Entity target) {
		super.onEntityImpact(rayTraceResult, target);
		if (target instanceof EntityLivingBase) {
			((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 5 * 20, 1));
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.032F;
	}
}