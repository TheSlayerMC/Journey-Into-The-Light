package net.journey.entity.projectile;

import java.util.Random;

import net.journey.client.render.particles.EntityBouncingFX;
import net.journey.client.render.particles.EntityConjuringFX;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBouncingProjectile extends EntityThrowable {
	
	public int damage;
	public EntityLivingBase thrower;
	protected int bounces, maxBounces;

	public EntityBouncingProjectile(World par1) {
		super(par1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		Random rand = new Random();
		super.onUpdate();
		for(int i = 0; i < 6; ++i) {
			Particle effect = new EntityBouncingFX(this.world, this.posX, this.posY - 1.0F, this.posZ, 0.0D, 0.0D, 0.0D);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(effect);
		}
	}
	
	public EntityBouncingProjectile(World par1, EntityLivingBase par2, int dam, int max) {
		super(par1, par2);
		this.damage = dam;
		this.thrower = par2;
		maxBounces = max;
	}

	public EntityBouncingProjectile(World par1, double par2, double par4, double par6) {
		super(par1, par2, par4, par6);
		this.setVelocity(this.motionX * 0.01D, this.motionY * 0.01D, this.motionZ * 0.01D);
	}

	@Override
	protected void onImpact(RayTraceResult par1) {
		if(par1.entityHit != null && par1.entityHit != this.thrower) {
			par1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), this.damage);
			if(!this.world.isRemote) this.setDead();
			return;
		}
		if(par1.sideHit == EnumFacing.UP || par1.sideHit == EnumFacing.DOWN) {
			this.motionY *= -0.5D;
		} else if(par1.sideHit == EnumFacing.SOUTH || par1.sideHit == EnumFacing.NORTH) {
			this.motionZ *= -0.5D;
		} else if(par1.sideHit == EnumFacing.EAST || par1.sideHit == EnumFacing.WEST) {
			this.motionX *= -0.5D;
		}		
		this.bounces++;
		this.maxBounces=6;
		float f = (this.rand.nextFloat() - 0.0F) * 0.0F;
        float f1 = (this.rand.nextFloat() - 0.0F) * 0.0F;
        float f2 = (this.rand.nextFloat() - 0.0F) * 0.0F;
        this.world.playBroadcastSound(1055, new BlockPos(this), 4);
		if(this.bounces == maxBounces) this.setDead();
		if(this.bounces == maxBounces) this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX + f, this.posY + 0.0D + f1, this.posZ + f2, 0.0D, 0.0D, 0.0D, new int[0]);
		else this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + f, this.posY + 0.0D + f1, this.posZ + f2, 0.0D, 0.0D, 0.0D, new int[0]);
	}
}