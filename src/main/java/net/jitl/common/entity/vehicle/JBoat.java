package net.jitl.common.entity.vehicle;

import com.google.common.collect.Lists;
import net.jitl.core.init.JBlocks;
import net.jitl.core.init.JEntities;
import net.jitl.core.init.JItems;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ServerboundPaddleBoatPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JBoat extends Entity {

    private static final EntityDataAccessor<Integer> DATAIDHURT = SynchedEntityData.defineId(JBoat.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATAIDHURTDIR = SynchedEntityData.defineId(JBoat.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> DATAIDDAMAGE = SynchedEntityData.defineId(JBoat.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> DATAIDTYPE = SynchedEntityData.defineId(JBoat.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATAIDPADDLELEFT = SynchedEntityData.defineId(JBoat.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATAIDPADDLERIGHT = SynchedEntityData.defineId(JBoat.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATAIDBUBBLETIME = SynchedEntityData.defineId(JBoat.class, EntityDataSerializers.INT);
    private final float[] paddlePositions = new float[2];
    private float invFriction;
    private float outOfControlTicks;
    private float deltaRotation;
    private int lerpSteps;
    private double lerpX;
    private double lerpY;
    private double lerpZ;
    private double lerpYRot;
    private double lerpXRot;
    private boolean inputLeft;
    private boolean inputRight;
    private boolean inputUp;
    private boolean inputDown;
    private double waterLevel;
    private float landFriction;
    private JBoat.Status status;
    private JBoat.Status oldStatus;
    private double lastYd;
    private boolean isAboveBubbleColumn;
    private boolean bubbleColumnDirectionIsDown;
    private float bubbleMultiplier;
    private float bubbleAngle;
    private float bubbleAngleO;

    public JBoat(EntityType<? extends JBoat> entityType, Level level) {
        super(entityType, level);
        this.blocksBuilding = true;
    }

    public JBoat(Level world, double x, double y, double z) {
        super(JEntities.JBOAT_TYPE, world);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected float getEyeHeight(@NotNull Pose pose, EntityDimensions size) {
        return size.height;
    }

    @Override
    protected Entity.@NotNull MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATAIDHURT, 0);
        this.entityData.define(DATAIDHURTDIR, 1);
        this.entityData.define(DATAIDDAMAGE, 0.0F);
        this.entityData.define(DATAIDTYPE, Type.GOLD_EUCA.ordinal());
        this.entityData.define(DATAIDPADDLELEFT, false);
        this.entityData.define(DATAIDPADDLERIGHT, false);
        this.entityData.define(DATAIDBUBBLETIME, 0);
    }

    @Override
    public boolean canCollideWith(@NotNull Entity entity) {
        return canVehicleCollide(this, entity);
    }

    public static boolean canVehicleCollide(Entity entity, Entity other) {
        return (other.canBeCollidedWith() || other.isPushable()) && !entity.isPassengerOfSameVehicle(other);
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public @NotNull Vec3 getRelativePortalPosition(Direction.@NotNull Axis axis, BlockUtil.@NotNull FoundRectangle portal) {
        return LivingEntity.resetForwardDirectionOfRelativePortalPosition(super.getRelativePortalPosition(axis, portal));
    }

    @Override
    public double getPassengersRidingOffset() {
        return -0.1D;
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        if(this.isInvulnerableTo(source)) {
            return false;
        } else if(!this.level.isClientSide && !this.isRemoved()) {
            this.setHurtDir(-this.getHurtDir());
            this.setHurtTime(10);
            this.setDamage(this.getDamage() + amount * 10.0F);
            this.markHurt();
            this.gameEvent(GameEvent.ENTITY_DAMAGED, source.getEntity());
            boolean flag = source.getEntity() instanceof Player && ((Player)source.getEntity()).getAbilities().instabuild;
            if(flag || this.getDamage() > 40.0F) {
                if(!flag && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                    this.spawnAtLocation(this.getDropItem());
                }
                this.discard();
            }
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void onAboveBubbleCol(boolean downwards) {
        if(!this.level.isClientSide) {
            this.isAboveBubbleColumn = true;
            this.bubbleColumnDirectionIsDown = downwards;
            if(this.getBubbleTime() == 0) {
                this.setBubbleTime(60);
            }
        }

        this.level.addParticle(ParticleTypes.SPLASH, this.getX() + (double)this.random.nextFloat(), this.getY() + 0.7D, this.getZ() + (double)this.random.nextFloat(), 0.0D, 0.0D, 0.0D);
        if(this.random.nextInt(20) == 0) {
            this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), this.getSwimSplashSound(), this.getSoundSource(), 1.0F, 0.8F + 0.4F * this.random.nextFloat(), false);
        }

        this.gameEvent(GameEvent.SPLASH, this.getControllingPassenger());
    }

    @Override
    public void push(@NotNull Entity entity) {
        if(entity instanceof JBoat) {
            if(entity.getBoundingBox().minY < this.getBoundingBox().maxY) {
                super.push(entity);
            }
        } else if(entity.getBoundingBox().minY <= this.getBoundingBox().minY) {
            super.push(entity);
        }

    }

    public Item getDropItem() {
        return switch (this.getJBoatType()) {
            case GOLD_EUCA -> JItems.GOLD_EUCA_BOAT;
            case BROWN_EUCA -> JItems.BROWN_EUCA_BOAT;
            case FROZEN -> JItems.FROZEN_BOAT;
            case BURNED -> JItems.BURNED_BOAT;
        };
    }

    @Override
    public void animateHurt() {
        this.setHurtDir(-this.getHurtDir());
        this.setHurtTime(10);
        this.setDamage(this.getDamage() * 11.0F);
    }

    @Override
    public boolean isPickable() {
        return !this.isRemoved();
    }

    @Override
    public void lerpTo(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.lerpX = x;
        this.lerpY = y;
        this.lerpZ = z;
        this.lerpYRot = yaw;
        this.lerpXRot = pitch;
        this.lerpSteps = 10;
    }

    @Override
    public @NotNull Direction getMotionDirection() {
        return this.getDirection().getClockWise();
    }

    @Override
    public void tick() {
        this.oldStatus = this.status;
        this.status = this.getStatus();
        if(this.status != JBoat.Status.UNDERWATER && this.status != JBoat.Status.UNDER_FLOWING_WATER) 
            this.outOfControlTicks = 0.0F;
         else 
            this.outOfControlTicks++;
        
        if(!this.level.isClientSide && this.outOfControlTicks >= 60.0F) 
            this.ejectPassengers();

        if(this.getHurtTime() > 0) 
            this.setHurtTime(this.getHurtTime() - 1);

        if(this.getDamage() > 0.0F) 
            this.setDamage(this.getDamage() - 1.0F);

        super.tick();
        this.tickLerp();
        if(this.isControlledByLocalInstance()) {
            if(!(this.getFirstPassenger() instanceof Player)) {
                this.setPaddleState(false, false);
            }
            this.floatJBoat();
            if(this.level.isClientSide) {
                this.controlJBoat();
                this.level.sendPacketToServer(new ServerboundPaddleBoatPacket(this.getPaddleState(0), this.getPaddleState(1)));
            }
            this.move(MoverType.SELF, this.getDeltaMovement());
        } else {
            this.setDeltaMovement(Vec3.ZERO);
        }
        this.tickBubbleColumn();
        for(int i = 0; i <= 1; ++i) {
            if(this.getPaddleState(i)) {
                if(!this.isSilent() && (double)(this.paddlePositions[i] % ((float)Math.PI * 2F)) <= (double)((float)Math.PI / 4F) && ((double)this.paddlePositions[i] + (double)((float)Math.PI / 8F)) % (double)((float)Math.PI * 2F) >= (double)((float)Math.PI / 4F)) {
                    SoundEvent soundevent = this.getPaddleSound();
                    if(soundevent != null) {
                        Vec3 vec3 = this.getViewVector(1.0F);
                        double d0 = i == 1 ? -vec3.z : vec3.z;
                        double d1 = i == 1 ? vec3.x : -vec3.x;
                        this.level.playSound(null, this.getX() + d0, this.getY(), this.getZ() + d1, soundevent, this.getSoundSource(), 1.0F, 0.8F + 0.4F * this.random.nextFloat());
                        this.level.gameEvent(this.getControllingPassenger(), GameEvent.SPLASH, new BlockPos(this.getX() + d0, this.getY(), this.getZ() + d1));
                    }
                }

                this.paddlePositions[i] = (float)((double)this.paddlePositions[i] + (double)((float)Math.PI / 8F));
            } else {
                this.paddlePositions[i] = 0.0F;
            }
        }

        this.checkInsideBlocks();
        List<Entity> list = this.level.getEntities(this, this.getBoundingBox().inflate(0.2F, -0.01F, 0.2F), EntitySelector.pushableBy(this));
        if(!list.isEmpty()) {
            boolean flag = !this.level.isClientSide && !(this.getControllingPassenger() instanceof Player);
            for(Entity entity : list) {
                if (!entity.hasPassenger(this)) {
                    if (flag && this.getPassengers().size() < 2 && !entity.isPassenger() && entity.getBbWidth() < this.getBbWidth() && entity instanceof LivingEntity && !(entity instanceof WaterAnimal) && !(entity instanceof Player)) {
                        entity.startRiding(this);
                    } else {
                        this.push(entity);
                    }
                }
            }
        }

    }

    private void tickBubbleColumn() {
        if(this.level.isClientSide) {
            int i = this.getBubbleTime();
            if(i > 0) {
                this.bubbleMultiplier += 0.05F;
            } else {
                this.bubbleMultiplier -= 0.1F;
            }
            this.bubbleMultiplier = Mth.clamp(this.bubbleMultiplier, 0.0F, 1.0F);
            this.bubbleAngleO = this.bubbleAngle;
            this.bubbleAngle = 10.0F * (float)Math.sin(0.5F * (float)this.level.getGameTime()) * this.bubbleMultiplier;
        } else {
            if(!this.isAboveBubbleColumn) {
                this.setBubbleTime(0);
            }
            int k = this.getBubbleTime();
            if(k > 0) {
                --k;
                this.setBubbleTime(k);
                int j = 60 - k - 1;
                if(j > 0 && k == 0) {
                    this.setBubbleTime(0);
                    Vec3 vec3 = this.getDeltaMovement();
                    if(this.bubbleColumnDirectionIsDown) {
                        this.setDeltaMovement(vec3.add(0.0D, -0.7D, 0.0D));
                        this.ejectPassengers();
                    } else {
                        this.setDeltaMovement(vec3.x, this.hasPassenger((entity1) -> entity1 instanceof Player) ? 2.7D : 0.6D, vec3.z);
                    }
                }
                this.isAboveBubbleColumn = false;
            }
        }

    }

    @Nullable
    protected SoundEvent getPaddleSound() {
        switch(this.getStatus()) {
            case IN_WATER:
            case UNDERWATER:
            case UNDER_FLOWING_WATER:
                return SoundEvents.BOAT_PADDLE_WATER;
            case ON_LAND:
                return SoundEvents.BOAT_PADDLE_LAND;
            case IN_AIR:
            default:
                return null;
        }
    }

    private void tickLerp() {
        if(this.isControlledByLocalInstance()) {
            this.lerpSteps = 0;
            this.setPacketCoordinates(this.getX(), this.getY(), this.getZ());
        }

        if(this.lerpSteps > 0) {
            double d0 = this.getX() + (this.lerpX - this.getX()) / (double)this.lerpSteps;
            double d1 = this.getY() + (this.lerpY - this.getY()) / (double)this.lerpSteps;
            double d2 = this.getZ() + (this.lerpZ - this.getZ()) / (double)this.lerpSteps;
            double d3 = Mth.wrapDegrees(this.lerpYRot - (double)this.getYRot());
            this.setYRot(this.getYRot() + (float)d3 / (float)this.lerpSteps);
            this.setXRot(this.getXRot() + (float)(this.lerpXRot - (double)this.getXRot()) / (float)this.lerpSteps);
            --this.lerpSteps;
            this.setPos(d0, d1, d2);
            this.setRot(this.getYRot(), this.getXRot());
        }
    }

    public void setPaddleState(boolean left, boolean right) {
        this.entityData.set(DATAIDPADDLELEFT, left);
        this.entityData.set(DATAIDPADDLERIGHT, right);
    }

    public float getRowingTime(int side, float limbSwing) {
        return this.getPaddleState(side) ? (float)Mth.clampedLerp((double)this.paddlePositions[side] - (double)((float)Math.PI / 8F), (double)this.paddlePositions[side], (double)limbSwing) : 0.0F;
    }

    private JBoat.Status getStatus() {
        JBoat.Status s = this.isUnderwater();
        if(s != null) {
            this.waterLevel = this.getBoundingBox().maxY;
            return s;
        } else if(this.checkInWater()) {
            return JBoat.Status.IN_WATER;
        } else {
            float f = this.getGroundFriction();
            if(f > 0.0F) {
                this.landFriction = f;
                return JBoat.Status.ON_LAND;
            } else {
                return JBoat.Status.IN_AIR;
            }
        }
    }

    public float getWaterLevelAbove() {
        AABB aabb = this.getBoundingBox();
        int i = Mth.floor(aabb.minX);
        int j = Mth.ceil(aabb.maxX);
        int k = Mth.floor(aabb.maxY);
        int l = Mth.ceil(aabb.maxY - this.lastYd);
        int i1 = Mth.floor(aabb.minZ);
        int j1 = Mth.ceil(aabb.maxZ);
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        label39:
        for(int k1 = k; k1 < l; ++k1) {
            float f = 0.0F;

            for(int l1 = i; l1 < j; ++l1) {
                for(int i2 = i1; i2 < j1; ++i2) {
                    pos.set(l1, k1, i2);
                    FluidState fluidstate = this.level.getFluidState(pos);
                    if(fluidstate.is(FluidTags.WATER)) {
                        f = Math.max(f, fluidstate.getHeight(this.level, pos));
                    }

                    if(f >= 1.0F) {
                        continue label39;
                    }
                }
            }

            if(f < 1.0F) {
                return (float)pos.getY() + f;
            }
        }

        return (float)(l + 1);
    }

    /**
     * Decides how much the JBoat should be gliding on the land (based on any slippery blocks)
     */
    public float getGroundFriction() {
        AABB aabb = this.getBoundingBox();
        AABB aabb1 = new AABB(aabb.minX, aabb.minY - 0.001D, aabb.minZ, aabb.maxX, aabb.minY, aabb.maxZ);
        int i = Mth.floor(aabb1.minX) - 1;
        int j = Mth.ceil(aabb1.maxX) + 1;
        int k = Mth.floor(aabb1.minY) - 1;
        int l = Mth.ceil(aabb1.maxY) + 1;
        int i1 = Mth.floor(aabb1.minZ) - 1;
        int j1 = Mth.ceil(aabb1.maxZ) + 1;
        VoxelShape voxelshape = Shapes.create(aabb1);
        float f = 0.0F;
        int k1 = 0;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for(int l1 = i; l1 < j; ++l1) {
            for(int i2 = i1; i2 < j1; ++i2) {
                int j2 = (l1 != i && l1 != j - 1 ? 0 : 1) + (i2 != i1 && i2 != j1 - 1 ? 0 : 1);
                if(j2 != 2) {
                    for(int k2 = k; k2 < l; ++k2) {
                        if(j2 <= 0 || k2 != k && k2 != l - 1) {
                            pos.set(l1, k2, i2);
                            BlockState blockstate = this.level.getBlockState(pos);
                            if(!(blockstate.getBlock() instanceof WaterlilyBlock) && Shapes.joinIsNotEmpty(blockstate.getCollisionShape(this.level, pos).move(l1, k2, i2), voxelshape, BooleanOp.AND)) {
                                f += blockstate.getFriction(this.level, pos, this);
                                ++k1;
                            }
                        }
                    }
                }
            }
        }

        return f / (float)k1;
    }

    private boolean checkInWater() {
        AABB aabb = this.getBoundingBox();
        int i = Mth.floor(aabb.minX);
        int j = Mth.ceil(aabb.maxX);
        int k = Mth.floor(aabb.minY);
        int l = Mth.ceil(aabb.minY + 0.001D);
        int i1 = Mth.floor(aabb.minZ);
        int j1 = Mth.ceil(aabb.maxZ);
        boolean flag = false;
        this.waterLevel = -Double.MAX_VALUE;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for(int k1 = i; k1 < j; ++k1) {
            for(int l1 = k; l1 < l; ++l1) {
                for(int i2 = i1; i2 < j1; ++i2) {
                    pos.set(k1, l1, i2);
                    FluidState fluidstate = this.level.getFluidState(pos);
                    if(fluidstate.is(FluidTags.WATER)) {
                        float f = (float)l1 + fluidstate.getHeight(this.level, pos);
                        this.waterLevel = Math.max(f, this.waterLevel);
                        flag |= aabb.minY < (double)f;
                    }
                }
            }
        }

        return flag;
    }

    @Nullable
    private JBoat.Status isUnderwater() {
        AABB aabb = this.getBoundingBox();
        double d0 = aabb.maxY + 0.001D;
        int i = Mth.floor(aabb.minX);
        int j = Mth.ceil(aabb.maxX);
        int k = Mth.floor(aabb.maxY);
        int l = Mth.ceil(d0);
        int i1 = Mth.floor(aabb.minZ);
        int j1 = Mth.ceil(aabb.maxZ);
        boolean flag = false;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for(int k1 = i; k1 < j; ++k1) {
            for(int l1 = k; l1 < l; ++l1) {
                for(int i2 = i1; i2 < j1; ++i2) {
                    pos.set(k1, l1, i2);
                    FluidState fluidstate = this.level.getFluidState(pos);
                    if(fluidstate.is(FluidTags.WATER) && d0 < (double)((float)pos.getY() + fluidstate.getHeight(this.level, pos))) {
                        if(!fluidstate.isSource()) {
                            return JBoat.Status.UNDER_FLOWING_WATER;
                        }
                        flag = true;
                    }
                }
            }
        }
        return flag ? JBoat.Status.UNDERWATER : null;
    }

    private void floatJBoat() {
        double d1 = this.isNoGravity() ? 0.0D : (double)-0.04F;
        double d2 = 0.0D;
        this.invFriction = 0.05F;
        if(this.oldStatus == JBoat.Status.IN_AIR && this.status != JBoat.Status.IN_AIR && this.status != JBoat.Status.ON_LAND) {
            this.waterLevel = this.getY(1.0D);
            this.setPos(this.getX(), (double)(this.getWaterLevelAbove() - this.getBbHeight()) + 0.101D, this.getZ());
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
            this.lastYd = 0.0D;
            this.status = JBoat.Status.IN_WATER;
        } else {
            if(this.status == JBoat.Status.IN_WATER) {
                d2 = (this.waterLevel - this.getY()) / (double)this.getBbHeight();
                this.invFriction = 0.9F;
            } else if(this.status == JBoat.Status.UNDER_FLOWING_WATER) {
                d1 = -7.0E-4D;
                this.invFriction = 0.9F;
            } else if(this.status == JBoat.Status.UNDERWATER) {
                d2 = 0.01F;
                this.invFriction = 0.45F;
            } else if(this.status == JBoat.Status.IN_AIR) {
                this.invFriction = 0.9F;
            } else if(this.status == JBoat.Status.ON_LAND) {
                this.invFriction = this.landFriction;
                if(this.getControllingPassenger() instanceof Player) {
                    this.landFriction /= 2.0F;
                }
            }

            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(vec3.x * (double)this.invFriction, vec3.y + d1, vec3.z * (double)this.invFriction);
            this.deltaRotation *= this.invFriction;
            if(d2 > 0.0D) {
                Vec3 vec31 = this.getDeltaMovement();
                this.setDeltaMovement(vec31.x, (vec31.y + d2 * 0.06153846016296973D) * 0.75D, vec31.z);
            }
        }

    }

    private void controlJBoat() {
        if(this.isVehicle()) {
            float f = 0.0F;
            if(this.inputLeft)
                this.deltaRotation--;
            if(this.inputRight)
                this.deltaRotation++;
            if(this.inputRight != this.inputLeft && !this.inputUp && !this.inputDown) 
                f += 0.005F;
            this.setYRot(this.getYRot() + this.deltaRotation);
            if(this.inputUp)
                f += 0.04F;
            if(this.inputDown)
                f -= 0.005F;

            this.setDeltaMovement(this.getDeltaMovement().add(Mth.sin(-this.getYRot() * ((float)Math.PI / 180F)) * f, 0.0D, Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * f));
            this.setPaddleState(this.inputRight && !this.inputLeft || this.inputUp, this.inputLeft && !this.inputRight || this.inputUp);
        }
    }

    @Override
    public void positionRider(@NotNull Entity passenger) {
        if(this.hasPassenger(passenger)) {
            float f = 0.0F;
            float f1 = (float)((this.isRemoved() ? (double)0.01F : this.getPassengersRidingOffset()) + passenger.getMyRidingOffset());
            if(this.getPassengers().size() > 1) {
                int i = this.getPassengers().indexOf(passenger);
                if(i == 0) {
                    f = 0.2F;
                } else {
                    f = -0.6F;
                }
                if(passenger instanceof Animal) {
                    f = (float)((double)f + 0.2D);
                }
            }
            Vec3 vec3 = (new Vec3(f, 0.0D, 0.0D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
            passenger.setPos(this.getX() + vec3.x, this.getY() + (double)f1, this.getZ() + vec3.z);
            passenger.setYRot(passenger.getYRot() + this.deltaRotation);
            passenger.setYHeadRot(passenger.getYHeadRot() + this.deltaRotation);
            this.clampRotation(passenger);
            if(passenger instanceof Animal && this.getPassengers().size() > 1) {
                int j = passenger.getId() % 2 == 0 ? 90 : 270;
                passenger.setYBodyRot(((Animal)passenger).yBodyRot + (float)j);
                passenger.setYHeadRot(passenger.getYHeadRot() + (float)j);
            }

        }
    }

    @Override
    public @NotNull Vec3 getDismountLocationForPassenger(LivingEntity livingEntity) {
        Vec3 vec3 = getCollisionHorizontalEscapeVector(this.getBbWidth() * Mth.SQRT_OF_TWO, livingEntity.getBbWidth(), livingEntity.getYRot());
        double d0 = this.getX() + vec3.x;
        double d1 = this.getZ() + vec3.z;
        BlockPos blockpos = new BlockPos(d0, this.getBoundingBox().maxY, d1);
        BlockPos blockpos1 = blockpos.below();
        if(!this.level.isWaterAt(blockpos1)) {
            List<Vec3> list = Lists.newArrayList();
            double d2 = this.level.getBlockFloorHeight(blockpos);
            if(DismountHelper.isBlockFloorValid(d2)) {
                list.add(new Vec3(d0, (double)blockpos.getY() + d2, d1));
            }

            double d3 = this.level.getBlockFloorHeight(blockpos1);
            if(DismountHelper.isBlockFloorValid(d3)) {
                list.add(new Vec3(d0, (double)blockpos1.getY() + d3, d1));
            }

            for(Pose pose : livingEntity.getDismountPoses()) {
                for(Vec3 vec31 : list) {
                    if(DismountHelper.canDismountTo(this.level, vec31, livingEntity, pose)) {
                        livingEntity.setPose(pose);
                        return vec31;
                    }
                }
            }
        }

        return super.getDismountLocationForPassenger(livingEntity);
    }

    protected void clampRotation(Entity entityToUpdate) {
        entityToUpdate.setYBodyRot(this.getYRot());
        float f = Mth.wrapDegrees(entityToUpdate.getYRot() - this.getYRot());
        float f1 = Mth.clamp(f, -105.0F, 105.0F);
        entityToUpdate.yRotO += f1 - f;
        entityToUpdate.setYRot(entityToUpdate.getYRot() + f1 - f);
        entityToUpdate.setYHeadRot(entityToUpdate.getYRot());
    }

    @Override
    public void onPassengerTurned(@NotNull Entity entityToUpdate) {
        this.clampRotation(entityToUpdate);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putString("Type", this.getJBoatType().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        if(compound.contains("Type", 8)) {
            this.setType(JBoat.Type.byName(compound.getString("Type")));
        }

    }

    @Override
    public @NotNull InteractionResult interact(Player player, @NotNull InteractionHand hand) {
        if(player.isSecondaryUseActive()) {
            return InteractionResult.PASS;
        } else if(this.outOfControlTicks < 60.0F) {
            if(!this.level.isClientSide) {
                return player.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
            } else {
                return InteractionResult.SUCCESS;
            }
        } else {
            return InteractionResult.PASS;
        }
    }
    
    @Override
    protected void checkFallDamage(double y, boolean onGround, @Nullable BlockState state, @Nullable BlockPos pos) {
        this.lastYd = this.getDeltaMovement().y;
        if(!this.isPassenger()) {
            if(onGround) {
                if(this.fallDistance > 3.0F) {
                    if(this.status != JBoat.Status.ON_LAND) {
                        this.resetFallDistance();
                        return;
                    }
                    this.causeFallDamage(this.fallDistance, 1.0F, DamageSource.FALL);
                    if(!this.level.isClientSide && !this.isRemoved()) {
                        this.kill();
                        if(this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                            for(int i = 0; i < 3; ++i) {
                                this.spawnAtLocation(this.getJBoatType().getPlanks());
                            }

                            for(int j = 0; j < 2; ++j) {
                                this.spawnAtLocation(Items.STICK);
                            }
                        }
                    }
                }

                this.resetFallDistance();
            } else if(!this.level.getFluidState(this.blockPosition().below()).is(FluidTags.WATER) && y < 0.0D) {
                this.fallDistance = (float)((double)this.fallDistance - y);
            }

        }
    }

    public boolean getPaddleState(int side) {
        return this.entityData.<Boolean>get(side == 0 ? DATAIDPADDLELEFT : DATAIDPADDLERIGHT) && this.getControllingPassenger() != null;
    }

    public void setDamage(float damageTaken) {
        this.entityData.set(DATAIDDAMAGE, damageTaken);
    }

    public float getDamage() {
        return this.entityData.get(DATAIDDAMAGE);
    }

    public void setHurtTime(int timeSinceHit) {
        this.entityData.set(DATAIDHURT, timeSinceHit);
    }
    
    public int getHurtTime() {
        return this.entityData.get(DATAIDHURT);
    }

    private void setBubbleTime(int ticks) {
        this.entityData.set(DATAIDBUBBLETIME, ticks);
    }

    private int getBubbleTime() {
        return this.entityData.get(DATAIDBUBBLETIME);
    }

    public float getBubbleAngle(float partialTicks) {
        return Mth.lerp(partialTicks, this.bubbleAngleO, this.bubbleAngle);
    }

    public void setHurtDir(int forwardDirection) {
        this.entityData.set(DATAIDHURTDIR, forwardDirection);
    }
    
    public int getHurtDir() {
        return this.entityData.get(DATAIDHURTDIR);
    }

    public void setType(JBoat.Type JBoatType) {
        this.entityData.set(DATAIDTYPE, JBoatType.ordinal());
    }

    public JBoat.Type getJBoatType() {
        return JBoat.Type.byId(this.entityData.get(DATAIDTYPE));
    }

    @Override
    protected boolean canAddPassenger(@NotNull Entity passenger) {
        return this.getPassengers().size() < 2 && !this.isEyeInFluid(FluidTags.WATER);
    }

    @Override
    public @Nullable Entity getControllingPassenger() {
        return this.getFirstPassenger();
    }

    public void setInput(boolean leftInputDown, boolean rightInputDown, boolean forwardInputDown, boolean backInputDown) {
        this.inputLeft = leftInputDown;
        this.inputRight = rightInputDown;
        this.inputUp = forwardInputDown;
        this.inputDown = backInputDown;
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public boolean isUnderWater() {
        return this.status == JBoat.Status.UNDERWATER || this.status == JBoat.Status.UNDER_FLOWING_WATER;
    }

    @Override
    protected void addPassenger(@NotNull Entity passenger) {
        super.addPassenger(passenger);
        if(this.isControlledByLocalInstance() && this.lerpSteps > 0) {
            this.lerpSteps = 0;
            this.absMoveTo(this.lerpX, this.lerpY, this.lerpZ, (float)this.lerpYRot, (float)this.lerpXRot);
        }
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(this.getDropItem());
    }

    public enum Status {
        IN_WATER,
        UNDERWATER,
        UNDER_FLOWING_WATER,
        ON_LAND,
        IN_AIR
    }

    public enum Type {
        GOLD_EUCA(JBlocks.EUCA_GOLD_PLANKS, "gold_euca"),
        BROWN_EUCA(JBlocks.EUCA_BROWN_PLANKS, "brown_euca"),
        FROZEN(JBlocks.FROZEN_PLANKS, "frozen"),
        BURNED(JBlocks.BURNED_BARK_PLANKS, "burned");

        private final String name;
        private final Block planks;

        Type(Block baseBlock, String name) {
            this.name = name;
            this.planks = baseBlock;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static JBoat.Type byId(int id) {
            JBoat.Type[] b = values();
            if(id < 0 || id >= b.length) {
                id = 0;
            }
            return b[id];
        }

        public static JBoat.Type byName(String name) {
            JBoat.Type[] b = values();
            for (Type type : b) {
                if (type.getName().equals(name)) {
                    return type;
                }
            }
            return b[0];
        }
    }
}
