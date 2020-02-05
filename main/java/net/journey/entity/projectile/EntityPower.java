package net.journey.entity.projectile;

import java.util.List;
import java.util.Random;

import net.journey.JITL;
import net.journey.client.render.particles.EntityHellstoneFX;
import net.journey.enums.EnumParticlesClasses;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityPower extends EntityThrowable {

	public float lifeTicks;

	public EntityPower(World var1) {
		super(var1);
		lifeTicks = 20;
	}

	public EntityPower(World var1, EntityLivingBase var3, float dam) {
		super(var1, var3);
		lifeTicks = 20;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate() {
		Random rand = new Random();
		super.onUpdate();
		lifeTicks--;
		if(lifeTicks >= 0) this.setDead();
		List<EntityLivingBase> list = this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().expand(10.0D, 10.0D, 10.0D));
		if(list != null) {
			for(int i = 0; i < list.size(); i++) {
				Entity entity1 = list.get(i);
				if(entity1 instanceof EntityLivingBase && entity1 != getThrower()) {
					EntityLivingBase hit = (EntityLivingBase)entity1;
					for(int i1 = 0; i1 < 6; i1++) {
						JITL.proxy.spawnParticle(EnumParticlesClasses.HELLSTONE, this.world, this.posX, this.posY - 1.0F, this.posZ, false);
					}
					hit.attackEntityFrom(new DamageSource("power"), 10F);
				}
			}
		}
	}

	@Override
	protected void onImpact(RayTraceResult var1) {
		if(!world.isRemote) this.setDead();
	}

	@Override
	protected float getGravityVelocity() {
		return 0.001F;
	}
}