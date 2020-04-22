package net.journey.entity.projectile.launcher;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityRockProjectile extends EntityThrowable {

    private float damage = 9.0F;

    public EntityRockProjectile(World w) {
        super(w);
    }

    public EntityRockProjectile(World worldIn, EntityLivingBase throwerIn, float damage) {
        super(worldIn, throwerIn);
        this.damage = damage;
    }

    @Override
    protected void onImpact(RayTraceResult mop) {
        if (mop.entityHit != null)
            mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
        if (!world.isRemote) this.setDead();
    }

    @Override
    protected float getGravityVelocity() {
        return 0.035F;
    }
}