package net.jitl.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class EssenciaBoltEntity extends LightningBolt {
    private static final EntityDataAccessor<Integer> DATA_COLOR_ID = SynchedEntityData.defineId(EssenciaBoltEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Float> DATA_STRIKE_SOUND_VOL = SynchedEntityData.defineId(EssenciaBoltEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_THUNDER_SOUND_VOL = SynchedEntityData.defineId(EssenciaBoltEntity.class, EntityDataSerializers.FLOAT);

    public EssenciaBoltEntity(EntityType<? extends LightningBolt> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void setARGB(int argb) {
        this.entityData.set(DATA_COLOR_ID, argb);
    }

    public int getARGB() {
        return this.entityData.get(DATA_COLOR_ID);
    }

    public void setStrikeVolume(float volume) {
        this.entityData.set(DATA_STRIKE_SOUND_VOL, volume);
    }

    public float getStrikeVolume() {
        return this.entityData.get(DATA_STRIKE_SOUND_VOL);
    }

    public void setThunderVolume(float volume) {
        this.entityData.set(DATA_THUNDER_SOUND_VOL, volume);
    }

    public float getThunderVolume() {
        return this.entityData.get(DATA_THUNDER_SOUND_VOL);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_COLOR_ID, 0);
        this.entityData.define(DATA_STRIKE_SOUND_VOL, 2.0F);
        this.entityData.define(DATA_THUNDER_SOUND_VOL, 10000.0F);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Color", getARGB());
        compound.putFloat("Strike Volume", getStrikeVolume());
        compound.putFloat("Thunder Volume", getThunderVolume());
    }

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setARGB(compound.getInt("Color"));
        this.setStrikeVolume(compound.getFloat("Strike Volume"));
        this.setThunderVolume(compound.getFloat("Thunder Volume"));
    }
}
