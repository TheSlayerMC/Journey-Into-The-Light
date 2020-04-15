package net.journey.entity.projectile;

import java.util.Random;

import net.journey.JITL;
import net.journey.client.render.particles.EntityFireballFX;
import net.journey.enums.EnumParticlesClasses;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFireBall extends EntityBasicProjectile {

	public EntityFireBall(World var1) {
		super(var1);
	}
	
	public EntityFireBall(World var1, EntityLivingBase var3, float dam) {
		super(var1, var3, dam);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		Random rand = new Random();
		super.onUpdate();
		for(int i = 0; i < 20; ++i) {
			JITL.proxy.spawnParticle(EnumParticlesClasses.FIRE_BALL, this.world, this.posX, this.posY - 1.0F, this.posZ, false);
		}
	}
	
	@Override
	protected void onImpact(RayTraceResult var1) {
		if(var1.entityHit != null) { 
			var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), getDamage());
			var1.entityHit.setFire(5);
		}
		if(!world.isRemote) this.setDead();
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.032F;
	}
}