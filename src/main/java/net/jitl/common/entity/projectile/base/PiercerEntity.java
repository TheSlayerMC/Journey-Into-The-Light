package net.jitl.common.entity.projectile.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public abstract class PiercerEntity extends DamagingProjectileEntity implements IRendersAsItem {

    protected int bounces, maxBounces;
    private LivingEntity thrower;//FIXME memory leak

    public PiercerEntity(EntityType<? extends DamagingProjectileEntity> type, World world, LivingEntity thrower, float damage, int maxBounces) {
        super(type, world, thrower, damage);
        this.maxBounces = maxBounces;
        this.thrower = thrower;
    }

    public PiercerEntity(EntityType<? extends PiercerEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult ray) {
        Direction faceHit = ray.getDirection();
        Vector3d vector3d = this.getDeltaMovement();
        double x = this.getX() + vector3d.x;
        double y = this.getY() + vector3d.y;
        double z = this.getZ() + vector3d.z;

        if(faceHit == Direction.UP || faceHit == Direction.DOWN) {
            //this.motionY *= -1.0D;
            this.lerpMotion(0.0D, -1.0D, 0.0D);
        } else if (faceHit == Direction.SOUTH || faceHit == Direction.NORTH) {
            // this.motionZ *= -1.0D;
            this.lerpMotion(0.0D, 0.0D, -1.0D);
        } else if (faceHit == Direction.EAST || faceHit == Direction.WEST) {
            // this.motionX *= -1.0D;
            this.lerpMotion(-1.0D, 0.0D, 0.0D);
        }
        System.out.println("Hit: " + faceHit);
        this.bounces++;
        if(this.bounces == this.maxBounces) this.remove();
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult rt) {
        rt.getEntity();
        if (rt.getEntity() != thrower) {
            rt.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), this.getDamage());
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
