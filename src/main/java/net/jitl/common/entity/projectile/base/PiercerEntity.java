package net.jitl.common.entity.projectile.base;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public abstract class PiercerEntity extends DamagingProjectileEntity implements IRendersAsItem {

    protected int bounces, maxBounces;

    public PiercerEntity(EntityType<? extends DamagingProjectileEntity> type, World world, LivingEntity thrower, float damage, int maxBounces) {
        super(type, world, thrower, damage);
        this.maxBounces = maxBounces;
    }

    public PiercerEntity(EntityType<? extends PiercerEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void onBlockImpact(BlockRayTraceResult result) {
        Direction faceHit = result.getDirection();
        Vector3d vector3d = this.getDeltaMovement();
        double x = this.getX() + vector3d.x;
        double y = this.getY() + vector3d.y;
        double z = this.getZ() + vector3d.z;

        if(faceHit == Direction.UP || faceHit == Direction.DOWN) {
            //this.motionY *= -1.0D;
            this.setDeltaMovement(getDeltaMovement().multiply(1.0F, -1.0F, 1.0F));
        } else if (faceHit == Direction.SOUTH || faceHit == Direction.NORTH) {
            // this.motionZ *= -1.0D;
            this.setDeltaMovement(getDeltaMovement().multiply(1.0F, 1.0F, -1.0F));
        } else if (faceHit == Direction.EAST || faceHit == Direction.WEST) {
            // this.motionX *= -1.0D;
            this.setDeltaMovement(getDeltaMovement().multiply(-1.0F, 1.0F, 1.0F));
        }
        System.out.println("Hit: " + faceHit);
        this.bounces++;
        if(this.bounces == this.maxBounces) this.remove();
    }

    @Override
    protected void onEntityImpact(RayTraceResult result, Entity entity) {
        if (entity instanceof LivingEntity) {
            entity.hurt(DamageSource.thrown(this, this.getOwner()), this.getDamage());
            if (!level.isClientSide) this.remove();
            System.out.println("Hit: ");
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected float getGravity() {
        return 0.03F;
    }

}
