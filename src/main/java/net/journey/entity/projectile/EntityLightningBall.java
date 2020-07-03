package net.journey.entity.projectile;

import net.journey.JITL;
import net.journey.enums.EnumParticlesClasses;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EntityLightningBall extends EntityDamagingProjectile {

	public EntityLightningBall(World var1) {
		super(var1);
	}

	public EntityLightningBall(World var1, EntityLivingBase var3, float dam) {
		super(var1, var3, dam);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void onUpdate() {
        Random rand = new Random();
        super.onUpdate();
        for (int i = 0; i < 20; ++i) {
            JITL.proxy.spawnParticle(EnumParticlesClasses.ICE_BALL, this.world, this.posX, this.posY - 1.0F, this.posZ, false);
        }
    }

	@Override
	protected void onImpact(@NotNull RayTraceResult rayTraceResult) {
		if (rayTraceResult.entityHit != null) {
			rayTraceResult.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), getDamage());
		}
		EntityLightningBolt bolt = new EntityLightningBolt(world, posX, posY, posZ, false);
		if (!world.isRemote) {
			world.addWeatherEffect(bolt);
			world.createExplosion(this, posX, posY, posZ, 2.0F, true);
			this.setDead();
		}
	}

    @Override
    protected float getGravityVelocity() {
        return 0.032F;
    }
}