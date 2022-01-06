package net.jitl.common.entity.projectile.base;

import net.jitl.init.JEntities;
import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import ru.timeconqueror.timecore.api.util.Pair;
import ru.timeconqueror.timecore.api.util.Requirements;

import java.util.*;

//TODO test effects
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;

import EntityDataAccessor;

public class JEffectCloudEntity extends Entity {
    public static final EntityDataAccessor<Float> RADIUS = SynchedEntityData.defineId(JEffectCloudEntity.class, EntityDataSerializers.FLOAT); //the current radius of the cloud
    public static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(JEffectCloudEntity.class, EntityDataSerializers.INT);
    /**
     * The list of size keys
     */
    public ArrayList<Pair<Integer, Float>> sizes = new ArrayList<>();
    /**
     * The entity who is considered the cloud's owner
     */
    public UUID ownerUUID;
    /**
     * The entities who won't get the common effect of the cloud
     */
    public Set<UUID> excludedEntities = new HashSet<>();
    /**
     * The effects that will be inflicted normally
     */
    public ArrayList<MobEffectInstance> primaryEffects = new ArrayList<>();
    /**
     * The effects that will be inflicted to enemies who are exceptions
     */
    public ArrayList<MobEffectInstance> secondaryEffects = new ArrayList<>();


    public JEffectCloudEntity(EntityType<? extends JEffectCloudEntity> entityType, Level world) {
        super(entityType, world);
        this.noPhysics = true;
    }

    public JEffectCloudEntity(LivingEntity cloudCreator, Level world, Vec3 pos, float initialRadius) {
        this(cloudCreator, world, pos.x(), pos.y(), pos.z(), initialRadius);
    }

    public JEffectCloudEntity(LivingEntity cloudCreator, Level world, double x, double y, double z, float initialRadius) {
        this(JEntities.EFFECT_CLOUD_TYPE, world);
        ownerUUID = cloudCreator.getUUID();
        this.setPos(x, y, z);
        this.addSizeKey(0, initialRadius);
    }

    /**
     * Adds a new size. Must be called in order to match the steps the cloud will follow in-game.
     *
     * @param time the time (in ticks) when the cloud will reach this size.
     *             Should be greater or equals to zero
     * @param size the size the cloud will have at this stage of its life
     * @throws IllegalArgumentException if {@code time} is less than zero
     */
    public void addSizeKey(int time, float size) {
        Requirements.greaterOrEquals(time, 0);
        sizes.add(Pair.of(time, size));
    }

    /**
     * Makes the owner receive secondary effects only
     */
    public void excludeOwner() {
        excludedEntities.add(ownerUUID);
    }

    public void exclude(LivingEntity mob) {
        excludedEntities.add(mob.getUUID());
    }

    public void addPrimaryEffect(MobEffectInstance effect) {
        primaryEffects.add(effect);
    }


    public void addSecondaryEffect(MobEffectInstance effect) {
        secondaryEffects.add(effect);
    }

    public void setColor(int color) {
        getEntityData().set(COLOR, color);
    }

    /**
     * Performs final actions and spawns the cloud
     */
    public void spawn() {
        sizes.trimToSize();
        primaryEffects.trimToSize();
        secondaryEffects.trimToSize();
        this.level.addFreshEntity(this);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level.isClientSide) { //Client
            //spawn particles (same behavior as vanilla effect cloud particles, but code edited for better readability)
            float radius = getEntityData().get(RADIUS);
            float loops = (float) Math.PI * radius * radius;
            for (float current = 0; current < loops; ++current) {
                float rotation = this.random.nextFloat() * ((float) Math.PI * 2F);
                float distance = this.random.nextFloat() * radius; //vanilla has a sqrt here but it seems pointless
                float xOffset = Mth.cos(rotation) * distance;
                float zOffset = Mth.sin(rotation) * distance;
                int color = getEntityData().get(COLOR);
                this.level.addAlwaysVisibleParticle(ParticleTypes.ENTITY_EFFECT, this.getX() + xOffset, this.getY(), this.getZ() + zOffset, (color >> 16 & 255) / 255.0, (color >> 8 & 255) / 255.0, (color & 255) / 255.0);
            }
        } else { //Server
            //adjust the radius or delete the entity
            Pair<Integer, Float> previousPair = null; //the last specified time-size value the cloud was at
            Pair<Integer, Float> nextPair = null; //the time-size value the cloud is moving towards
            for (int pairCount = 0; pairCount < sizes.size(); pairCount++) {
                if (sizes.get(pairCount).left() < this.tickCount) { //check if the cloud has already been at the specified time-size pair
                    if (pairCount == sizes.size() - 1) { //make sure there is actually another pair (otherwise the cloud has completed its life cycle)
                        this.remove();
                        return;
                    } else if (sizes.get(pairCount + 1).left() > this.tickCount) { //the cloud is between this pair and the previous one
                        previousPair = sizes.get(pairCount);
                        nextPair = sizes.get(pairCount + 1);
                    }
                }
            }
            if (previousPair != null && nextPair != null) {
                float timeFromLastPair = this.tickCount - previousPair.left();
                float timeBetweenPairs = nextPair.left() - previousPair.left();
                float sizeDifference = nextPair.right() - previousPair.right();
                //radius = ((Float) previousPair.getValue()).floatValue() + sizeDifference * (timeFromLastPair / timeBetweenPairs);
                getEntityData().set(RADIUS, (timeFromLastPair / timeBetweenPairs) * sizeDifference + (previousPair.right()));
            }

            //handle entity collisions
            List<LivingEntity> entityList = level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox());
            for (LivingEntity entity : entityList) {
                if (Math.sqrt(Math.pow(entity.getX() - this.getX(), 2) + Math.pow(entity.getZ() - this.getZ(), 2)) <= getEntityData().get(RADIUS)) { //better precision (makes real hitbox round)
                    for (MobEffectInstance effect : isEntityException(entity) ? secondaryEffects : primaryEffects) { //decide which effects to apply
                        entity.addEffect(effect);
                    }
                }
            }
        }
    }

    private boolean isEntityException(LivingEntity entity) {
        for (UUID currentUUID : excludedEntities) {
            Entity currentEntity = getEntityFromUUID(currentUUID);
            if (currentEntity != null && entity.getClass() == currentEntity.getClass()) return true;
        }
        return false;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(getEntityData().get(RADIUS) * 2F, 0.5F); //this gets the bounding box. Change second value to adjust height
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putInt("time", this.tickCount);
        nbt.putFloat("radius", getEntityData().get(RADIUS));
        nbt.putInt("color", getEntityData().get(COLOR));
        nbt.putUUID("owner", ownerUUID);

        ListTag primaryEffectTag = new ListTag();
        for (MobEffectInstance effect : primaryEffects) {
            primaryEffectTag.add(effect.save(new CompoundTag()));
        }
        nbt.put("primary_effects", primaryEffectTag);

        ListTag secondaryEffectTag = new ListTag();
        for (MobEffectInstance effect : secondaryEffects) {
            secondaryEffectTag.add(effect.save(new CompoundTag()));
        }
        nbt.put("secondary_effects", secondaryEffectTag);

        ListTag sizesTag = new ListTag();
        for (Pair<Integer, Float> pair : sizes) {
            CompoundTag pairTag = new CompoundTag();
            pairTag.putInt("time", pair.left());
            pairTag.putFloat("size", pair.right());
            sizesTag.add(pairTag);
        }
        nbt.put("sizes", sizesTag);

        ListTag exceptionList = new ListTag();
        for (UUID id : excludedEntities) {
            exceptionList.add(NbtUtils.createUUID(id));
        }
        nbt.put("exceptions", exceptionList);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        this.tickCount = nbt.getInt("time");
        getEntityData().set(RADIUS, nbt.getFloat("radius"));
        getEntityData().set(COLOR, nbt.getInt("color"));
        if (nbt.hasUUID("owner")) {
            ownerUUID = nbt.getUUID("owner");
        }

        ListTag primaryEffectsTag = nbt.getList("primary_effects", Constants.NBT.TAG_COMPOUND);
        primaryEffects.clear();
        for (int i = 0; i < primaryEffectsTag.size(); i++) {
            addPrimaryEffect(MobEffectInstance.load(primaryEffectsTag.getCompound(i)));
        }

        ListTag secondaryEffectsTag = nbt.getList("secondary_effects", Constants.NBT.TAG_COMPOUND);
        secondaryEffects.clear();
        for (int i = 0; i < secondaryEffectsTag.size(); i++) {
            addSecondaryEffect(MobEffectInstance.load(secondaryEffectsTag.getCompound(i)));
        }

        ListTag sizesNBT = nbt.getList("sizes", Constants.NBT.TAG_COMPOUND);
        sizes.clear();
        for (int current = 0; current < sizesNBT.size(); current++) {
            CompoundTag pairCompound = sizesNBT.getCompound(current);
            addSizeKey(pairCompound.getInt("time"), pairCompound.getFloat("size"));
        }

        ListTag exceptionList = nbt.getList("exceptions", Constants.NBT.TAG_INT_ARRAY);
        for (Tag uuidNbt : exceptionList) {
            excludedEntities.add(NbtUtils.loadUUID(uuidNbt));
        }
    }

    @Nullable
    private LivingEntity getEntityFromUUID(UUID id) {
        Requirements.onServer(level);
        ServerLevel world = (ServerLevel) level;

        Entity entity = world.getEntity(id);
        // instance of is here because some mobs could have the same uuid
        // but even if there's another entity with the same uuid,
        // it's a very rare case and it won't cause any big troubles
        return entity instanceof LivingEntity ? (LivingEntity) entity : null;
    }

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(RADIUS, 0F);
        this.getEntityData().define(COLOR, 0);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
        if (data == RADIUS) {
            double oldX = this.getX();
            double oldY = this.getY();
            double oldZ = this.getZ();
            this.refreshDimensions();
            this.setPos(oldX, oldY, oldZ);
        }
        super.onSyncedDataUpdated(data);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
