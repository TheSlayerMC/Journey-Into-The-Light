package net.journey.entity.projectile;

import java.util.Random;

import net.journey.JITL;
import net.journey.client.render.particles.EntityDoomFX;
import net.journey.client.render.particles.EntityEnlightmentFX;
import net.journey.client.render.particles.EntityHellstoneFX;
import net.journey.client.render.particles.EntityRockFX;
import net.journey.enums.EnumParticlesClasses;
import net.journey.util.PotionEffects;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityEyeBlaster extends EntityBasicProjectile {

	public EntityEyeBlaster(World var1) {
		super(var1);
	}
	
	public EntityEyeBlaster(World var1, EntityLivingBase var3, float dam) {
		super(var1, var3, dam);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		Random rand = new Random();
		super.onUpdate();
		for(int i = 0; i < 6; ++i) {
			JITL.proxy.spawnParticle(EnumParticlesClasses.HELLSTONE, this.world, this.posX, this.posY - 1.0F, this.posZ, false);
		}
	}
	
	@Override
	protected void onImpact(RayTraceResult var1) {
		if(var1.entityHit != null) { 
			var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), getDamage());
			((EntityLivingBase) var1.entityHit).addPotionEffect(new PotionEffect(PotionEffects.getPotionFromID(PotionEffects.harm), 100, 1));
			((EntityLivingBase) var1.entityHit).setFire(10);
		}
		if(!world.isRemote) this.setDead();
	}
}