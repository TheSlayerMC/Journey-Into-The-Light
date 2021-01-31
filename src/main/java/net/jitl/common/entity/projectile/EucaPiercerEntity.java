package net.jitl.common.entity.projectile;

import net.jitl.common.entity.projectile.base.DamagingProjectileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

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

    public EucaPiercerEntity(EntityType<EucaPiercerEntity> type, World world) {
        super(type, world);
    }

    @Override
    public ItemStack getItem() {
        return null;
    }

    @Override
    protected void onEntityImpact(RayTraceResult ray, Entity target) {

        BlockRayTraceResult result = (BlockRayTraceResult)ray;
        Direction faceHit = result.getDirection();

        Vector3d vector3d = this.getDeltaMovement();
        double x = this.getX() + vector3d.x;
        double y = this.getY() + vector3d.y;
        double z = this.getZ() + vector3d.z;

        if(target != null && target != thrower) {
            target.hurt(DamageSource.thrown(this, this.getOwner()), this.dam);
            if(!level.isClientSide) this.remove();
            return;
        }

        if(faceHit == Direction.UP || faceHit == Direction.DOWN) {
            //this.motionY *= -1.0D;
        } else if (faceHit == Direction.SOUTH || faceHit == Direction.NORTH) {
           // this.motionZ *= -1.0D;
        } else if (faceHit == Direction.EAST || faceHit == Direction.WEST) {
           // this.motionX *= -1.0D;
        }

        this.bounces ++;
        if(this.bounces == this.maxBounces) this.remove();
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
