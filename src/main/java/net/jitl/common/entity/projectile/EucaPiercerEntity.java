package net.jitl.common.entity.projectile;

import net.jitl.common.entity.projectile.base.DamagingProjectileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class EucaPiercerEntity extends DamagingProjectileEntity implements IRendersAsItem {

    protected int bounces, maxBounces;
    private LivingEntity thrower;
    private float dam;

    public EucaPiercerEntity(EntityType<? extends DamagingProjectileEntity> type, World world, LivingEntity thrower, float damage, int maxBounces) {
        super(type, world, thrower, damage);
        this.maxBounces = maxBounces;
        this.thrower = thrower;
        this.dam = damage;
    }

    @Override
    public ItemStack getItem() {
        return null;
    }

    @Override
    protected void onEntityImpact(RayTraceResult result, Entity target) {
        Vector3d vector3d = this.getDeltaMovement();
        double x = this.getX() + vector3d.x;
        double y = this.getY() + vector3d.y;
        double z = this.getZ() + vector3d.z;

        if(target != null && target != thrower) {
            target.hurt(DamageSource.thrown(this, this.getOwner()), getDamage());
            if(!level.isClientSide) this.remove();
            return;
        }

        //Add the bounces

        this.bounces ++;
        if(this.bounces == this.maxBounces) this.remove();
    }
}
