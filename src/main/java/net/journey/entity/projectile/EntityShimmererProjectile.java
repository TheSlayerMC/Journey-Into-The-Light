package net.journey.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityShimmererProjectile extends EntitySmallFireball {

    public EntityShimmererProjectile(World w) {
        super(w);
    }

    public EntityShimmererProjectile(World w, EntityLivingBase e, double x, double y, double z) {
        super(w, e, x, y, z);
        this.setSize(0.3125F, 0.3125F);
        extinguish();
    }

    @Override
    protected void onImpact(RayTraceResult var1) {
        if (var1.entityHit != null)
            var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, shootingEntity), 7.0F);
        if (!world.isRemote) this.setDead();
    }

    public void setThrowableHeading(double d, double e, double f, float g, int i) {

    }
}