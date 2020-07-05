package net.journey.entity.projectile;

import net.journey.client.render.particles.ParticleFloroMud;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
		for (int i = 0; i < 6; ++i) {
			ParticleFloroMud effect = new ParticleFloroMud(this.world, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(effect);
		}
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