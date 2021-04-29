package net.jitl.common.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;

public class EucaPiercerEntity extends AbstractArrowEntity implements IRendersAsItem {
    ItemStack stack;
    int bounces;

    public EucaPiercerEntity(EntityType<? extends AbstractArrowEntity> type, LivingEntity shooter, World worldIn, ItemStack stack) {
        super(type, shooter, worldIn);
        this.stack = stack;
    }

    public EucaPiercerEntity(EntityType<EucaPiercerEntity> eucaPiercerEntityEntityType, World world) {
        super(eucaPiercerEntityEntityType, world);
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult entityRayTraceResult_) {
        Entity entity = entityRayTraceResult_.getEntity();
        LivingEntity bounceTo = null;
        if (entity instanceof LivingEntity) {
            if (entity != this.getOwner()) {
                entity.hurt(DamageSource.thrown(this, this.getOwner()), 10); //TODO: better damage system
                if (getOwner() instanceof ServerPlayerEntity) {
                    ServerPlayerEntity player = (ServerPlayerEntity) getOwner();
                    stack.hurt(1, player.getRandom(), player);
                }
                if (++bounces <= 5) {
                    List<LivingEntity> entitiesNear = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(20D));
                    for (LivingEntity e : entitiesNear) {
                        if (e != this.getOwner() && this.canSee(e) && !e.isDeadOrDying() && e != entity) {
                            if (bounceTo == null || this.distanceTo(e) < this.distanceTo(bounceTo)) {
                                bounceTo = e;
                            }
                        }
                    }
                }
                Entity target = bounceTo != null ? bounceTo : getOwner();
                if (target != null) {
                    float velocity = (float) this.getDeltaMovement().length();
                    this.setDeltaMovement(0, 0, 0);
                    this.shoot(target.getX() - this.getX(), target.getEyeY() - this.getY(), target.getZ() - this.getZ(), velocity, 0);
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
    public void addAdditionalSaveData(CompoundNBT nbt) { //TODO: don't eat the world
        super.addAdditionalSaveData(nbt);
        nbt.put("stack", stack.save(nbt));
        nbt.putInt("bounces", bounces);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        stack = ItemStack.of(nbt.getCompound("stack"));
        bounces = nbt.getInt("bounces");
    }

    @Override
    protected ItemStack getPickupItem() {
        return stack;
    }

    @Override
    public ItemStack getItem() {
        return stack;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
