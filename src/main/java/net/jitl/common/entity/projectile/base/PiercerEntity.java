package net.jitl.common.entity.projectile.base;

import com.sun.javafx.geom.Vec3d;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import net.minecraftforge.registries.ForgeRegistries;

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
    public void playerTouch(PlayerEntity entityIn) {
        super.playerTouch(entityIn);
        if (tickCount > 5 && (entityIn == this.getOwner() || this.getDeltaMovement().length() < 0.01)) {
            entityIn.addItem(new ItemStack(Items.DIAMOND));
            this.remove();
        }
    }

    @Override
    protected void onBlockImpact(BlockRayTraceResult result) {
        Direction faceHit = result.getDirection();
        Vector3d speed = getDeltaMovement();
        System.out.println("(" + speed.x() + ", " + speed.y() + ", " + speed.z() + ")");

        if(faceHit == Direction.UP || faceHit == Direction.DOWN) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.8, 0, 0.8));
        } else if (faceHit == Direction.SOUTH || faceHit == Direction.NORTH) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1, 1, 0));
        } else if (faceHit == Direction.EAST || faceHit == Direction.WEST) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0, 1, 1));
        }
        speed = getDeltaMovement();
        System.out.println("(" + speed.x() + ", " + speed.y() + ", " + speed.z() + ")");
    }

    @Override
    protected void onEntityImpact(RayTraceResult result, Entity entity) {
        System.out.println("Impact");
        LivingEntity bounceTo = null;
        if (entity instanceof LivingEntity) {
            if(entity != this.getOwner()) {
                entity.hurt(DamageSource.thrown(this, this.getOwner()), this.getDamage());
                if (bounces <= maxBounces) {
                    List<LivingEntity> entitiesNear = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(20D));
                    for(LivingEntity e : entitiesNear) {
                        if(e != this.getOwner() && this.canSee(e) && !e.isDeadOrDying() && e != entity) {
                            if(bounceTo == null || this.distanceTo(e) < this.distanceTo(bounceTo)) {
                                bounceTo = e;
                            }
                        }
                    }
                }
                Entity target = bounceTo != null ? bounceTo : getOwner();
                if(target != null) {
                    float velocity = (float) this.getDeltaMovement().length();
                    this.setDeltaMovement(0, 0, 0);
                    this.shoot(target.getX() - this.getX(), target.getY() + target.getBbHeight() * 0.5 - this.getY(), target.getZ() - this.getZ(), velocity, 0);
                    this.bounces++;
                }
            }
        }
    }

    public boolean canSee(Entity entityIn) {
        Vector3d vector3d = new Vector3d(this.getX(), this.getEyeY(), this.getZ());
        Vector3d vector3d1 = new Vector3d(entityIn.getX(), entityIn.getEyeY(), entityIn.getZ());
        return this.level.clip(new RayTraceContext(vector3d, vector3d1, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this)).getType() == RayTraceResult.Type.MISS;
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if(result.getType() == RayTraceResult.Type.ENTITY) {
            onEntityImpact(result, ((EntityRayTraceResult) result).getEntity());
        } else if(result.getType() == RayTraceResult.Type.BLOCK) {
            onBlockImpact((BlockRayTraceResult) result);
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

    @Override
    protected boolean shouldDespawn() {
        return false;
    }
}
