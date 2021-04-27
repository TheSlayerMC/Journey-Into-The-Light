package net.jitl.common.entity.projectile.base;

import com.sun.javafx.geom.Vec3d;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;
import java.util.Objects;

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
            this.setDeltaMovement(getDeltaMovement().multiply(1.0F, -1.0F, 1.0F));
        } else if (faceHit == Direction.SOUTH || faceHit == Direction.NORTH) {
            this.setDeltaMovement(getDeltaMovement().multiply(1.0F, 1.0F, -1.0F));
        } else if (faceHit == Direction.EAST || faceHit == Direction.WEST) {
            this.setDeltaMovement(getDeltaMovement().multiply(-1.0F, 1.0F, 1.0F));
        }
        this.bounces++;
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult result) { }

    @Override
    protected void onEntityImpact(RayTraceResult result, Entity entity) {
        LivingEntity target = null;
        if (entity instanceof LivingEntity) {
            if(entity != this.getOwner()) {
                entity.hurt(DamageSource.thrown(this, this.getOwner()), this.getDamage());
                List<LivingEntity> entitysNear = this.level.getNearbyEntities(LivingEntity.class, EntityPredicate.DEFAULT, target, this.getBoundingBox().expandTowards(20D, 20D, 20D));
                boolean needNewTarget = target == null || target.isDeadOrDying() || target != entity;
                for(LivingEntity e : entitysNear) {
                    if(e != this.getOwner() && this.canSee(e)) {
                        if(needNewTarget && (target == null || (target != null && this.distanceTo(e) < this.distanceTo(target)))) {
                            System.out.println("old target: " + target);
                            target = e;
                            System.out.println("new target: " + target);
                        }
                    }
                }
            }
            if(target != null) {
                Vec3d directionTo = new Vec3d(target.getX() - this.getX(), (target.getY() + target.getEyeHeight()) - this.getY(), target.getZ() - this.getY());
                double x = directionTo.x / 1.25D;
                double y = directionTo.y / 1.25D;
                double z = directionTo.z / 1.25D;
                this.moveTo(x, y, z);
                this.bounces++;
            }
        }
    }

    public boolean canSee(Entity entityIn) {
        Vector3d vector3d = new Vector3d(this.getX(), this.getEyeY(), this.getZ());
        Vector3d vector3d1 = new Vector3d(entityIn.getX(), entityIn.getEyeY(), entityIn.getZ());
        return this.level.clip(new RayTraceContext(vector3d, vector3d1, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this)).getType() == RayTraceResult.Type.MISS;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if(!this.level.isClientSide) {
            if(result.getType() == RayTraceResult.Type.ENTITY) {
                Entity target = ((EntityRayTraceResult) result).getEntity();
                if(!Objects.equals(target, getOwner())) {
                    onEntityImpact(result, target);
                }
            } else if(result.getType() == RayTraceResult.Type.BLOCK) {
                onBlockImpact((BlockRayTraceResult) result);
            }
            if(this.bounces == this.maxBounces) this.remove();
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected float getGravity() {
        return 0.01F;
    }

}
