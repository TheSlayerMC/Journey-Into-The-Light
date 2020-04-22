package net.journey.entity.projectile.piercer;

import net.journey.init.JourneySounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBoilingPiercer extends EntityThrowable {

    public float damage;
    public EntityLivingBase thrower;
    protected int bounces, maxBounces;

    public EntityBoilingPiercer(World var1) {
        super(var1);
    }

    public EntityBoilingPiercer(World var1, EntityLivingBase var3, float dam, int max) {
        super(var1, var3);
        this.damage = dam;
        this.thrower = var3;
        this.maxBounces = max;
    }

    @Override
    protected void onImpact(RayTraceResult par1) {
        if (par1.entityHit != null && par1.entityHit != this.thrower) {
            par1.entityHit.setFire(15);
            par1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), this.damage);
            this.playSound(JourneySounds.KNIFE, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
            if (!this.world.isRemote)
                this.setDead();
            return;
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0.031F;
    }
}