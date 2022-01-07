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

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_COLOR_ID, 0);
    }

    @Override
	public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Color", getARGB());
    }

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setARGB(compound.getInt("Color"));
    }
}
