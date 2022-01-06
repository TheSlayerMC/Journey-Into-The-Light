package net.jitl.common.entity.projectile;

import net.jitl.init.JEntities;
import net.jitl.init.JItems;
import net.jitl.init.JSounds;
import net.minecraft.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.ItemSupplier;

public class PiercerEntity extends AbstractArrow implements ItemSupplier {
    private static final EntityDataAccessor<ItemStack> STACK = SynchedEntityData.defineId(PiercerEntity.class, EntityDataSerializers.ITEM_STACK);

    private float velocityMultiplier;
    private double rangeAddend;
    private int flameAddend;
    private int faithfulLevel;

    private boolean launch = false;
    private int currentBounces;
    private int maxBounces;
    public int soundTickCount;

    public PiercerEntity(LivingEntity shooter, Level worldIn, ItemStack stack, int maxBounces, float damage) {
        super(JEntities.PIERCER_TYPE, shooter, worldIn);
        setStack(stack.copy());
        this.setSoundEvent(JSounds.PIERCER.get());
        this.maxBounces = maxBounces;
        setBaseDamage(damage);
    }

    public PiercerEntity(EntityType<PiercerEntity> eucaPiercerEntityEntityType, Level world) {
        super(eucaPiercerEntityEntityType, world);
        this.setSoundEvent(JSounds.PIERCER.get());
    }

    public float setVelocityMultiplier(float velocityMultiplier) {
        this.velocityMultiplier = velocityMultiplier;
        return velocityMultiplier;
    }

    public float getVelocityMultiplier() {
        return velocityMultiplier;
    }

    public double setRangeAddend(double rangeAddend) {
        this.rangeAddend = rangeAddend;
        return rangeAddend;
    }

    public double getRangeAddend() {
        return rangeAddend;
    }

    public int setFlameAddend(int flameAddend) {
        this.flameAddend = flameAddend;
        return flameAddend;
    }

    public int setFaithfulLevel(int level) {
        this.faithfulLevel = level;
        return level;
    }

    public int getFlameAddend() {
        return flameAddend;
    }

    @Override
    public void tick() {
        super.tick();

        //counter some of arrow's hardcoded gravity
        if (!isNoPhysics() && !isInGround() && !isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, 0.025, 0));
        }

        if (launch) { //this piercer needs a new target and will change its motion before the next tick
            Entity bounceTo = null;
            if (++currentBounces <= maxBounces) {
                List<LivingEntity> entitiesNear = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4D + getRangeAddend()));
                for (LivingEntity e : entitiesNear) {
                    if (e != this.getOwner() && this.pathTo(e) && e.invulnerableTime == 0 && !e.isDeadOrDying() && e.getClassification(false) == MobCategory.MONSTER) { //check whether this entity is a valid target
                        if (bounceTo == null || this.distanceTo(e) < this.distanceTo(bounceTo)) { //compare new candidate to previous one
                            bounceTo = e;
                        }
                    }
                }
            }
            if (bounceTo == null) bounceTo = getOwner(); //default to owner if it's out of bounces

            Vec3 movement = new Vec3(bounceTo.getX(), bounceTo.getY(0.8), bounceTo.getZ()).subtract(this.getX(), this.getY(0.5), this.getZ());
            this.setDeltaMovement(movement.scale(((0.7 + getVelocityMultiplier() / 6.5) / movement.length()) * this.getDeltaMovement().length()));

            launch = false;
        }
        if (faithfulLevel > 0) {
            Entity entity = this.getOwner();
            if (isInGround() && isAcceptibleReturnOwner() && entity != null) {
                this.setNoPhysics(true);
                Vec3 vector3d = new Vec3(entity.getX() - this.getX(), entity.getEyeY() - this.getY(), entity.getZ() - this.getZ());
                this.setPosRaw(this.getX(), this.getY() + vector3d.y * 0.015D * (double) faithfulLevel, this.getZ());
                if (this.level.isClientSide) {
                    this.yOld = this.getY();
                }

                double d0 = 0.15D * (double) faithfulLevel;
                this.setDeltaMovement(this.getDeltaMovement().scale(0.95D).add(vector3d.normalize().scale(d0)));
                if (this.soundTickCount == 0) {
                    this.playSound(JSounds.PIERCER_RETURN.get(), 10.0F, Mth.nextFloat(random, 0.8F, 1.2F));
                }

                ++this.soundTickCount;
            }
        }
    }

    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    /**
     * Variant of LivingEntity's canSee modified to line up with the piercer's attempted path
     */
    private boolean pathTo(Entity entityIn) {
        Vec3 vector3d = new Vec3(this.getX(), this.getY(0.5), this.getZ());
        Vec3 vector3d1 = new Vec3(entityIn.getX(), entityIn.getY(0.8), entityIn.getZ());
        return this.level.clip(new ClipContext(vector3d, vector3d1, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this)).getType() == HitResult.Type.MISS;
    }

    @Override
    protected void onHitEntity(EntityHitResult entityRayTraceResult_) {
        Entity entity = entityRayTraceResult_.getEntity();
        if (entity instanceof LivingEntity && entity != this.getOwner()) {
            if (!level.isClientSide()) {
                if (getOwner() instanceof ServerPlayer) {
                    ServerPlayer player = (ServerPlayer) getOwner();
                    getStack().hurt(1, player.getRandom(), player);
                }

                if (entity.hurt(DamageSource.thrown(this, this.getOwner()), (float) getBaseDamage())) {
                    if (getFlameAddend() > 0) {
                        entity.setSecondsOnFire(getFlameAddend() * 4);
                    }
                    launch = true; //piercer can clip through nearby targets if movement is different than expected, so it'll wait until its position has been updated
                }
                this.playSound(JSounds.PIERCER.get(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            }
        }
    }

    @Override
    public void playerTouch(Player entityIn) {
        if (!this.level.isClientSide) {
            boolean isOwner = this.getOwner().getUUID() == entityIn.getUUID();
            if ((isOwner && currentBounces > 0) || ((this.inGround || this.isNoPhysics()) && this.shakeTime <= 0)) {
                boolean flag = this.pickup == Pickup.ALLOWED || this.pickup == Pickup.CREATIVE_ONLY && entityIn.abilities.instabuild || this.isNoPhysics() && isOwner;
                if (this.pickup == Pickup.ALLOWED && !entityIn.inventory.add(this.getPickupItem())) {
                    flag = false;
                }

                if (flag) {
                    entityIn.take(this, 1);
                    this.remove();
                }
            }
        }
    }

    public boolean isInGround() {
        return this.inGround;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.put("stack", getStack().save(new CompoundTag()));
        nbt.putInt("bounces", currentBounces);
        nbt.putInt("maxBounces", maxBounces);
        nbt.putFloat("velocityMultiplier", velocityMultiplier);
        nbt.putDouble("rangeAddend", rangeAddend);
        nbt.putInt("flameAddend", flameAddend);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        setStack(ItemStack.of(nbt.getCompound("stack")));
        if (getStack().isEmpty()) remove();
        currentBounces = nbt.getInt("bounces");
        maxBounces = nbt.getInt("maxBounces");
        velocityMultiplier = nbt.getFloat("velocityMultiplier");
        rangeAddend = nbt.getDouble("rangeAddend");
        flameAddend = nbt.getInt("flameAddend");
    }

    private void setStack(ItemStack stack) {
        this.getEntityData().set(STACK, stack);
    }

    private ItemStack getStack() {
        return this.getEntityData().get(STACK);
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return getStack().copy();
    }

    @Override
    public @NotNull ItemStack getItem() {
        ItemStack stack = getStack();
        return stack.isEmpty() ? new ItemStack(JItems.PIERCER) : stack;
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(STACK, ItemStack.EMPTY);
        super.defineSynchedData();
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (key == STACK) {
            getStack().setEntityRepresentation(this);
        }
        super.onSyncedDataUpdated(key);
    }
}
