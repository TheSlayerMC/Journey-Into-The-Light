package net.jitl.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class EssenciaBoltEntity extends LightningBoltEntity {
	private static final DataParameter<Integer> DATA_COLOR_ID = EntityDataManager.defineId(EssenciaBoltEntity.class, DataSerializers.INT);

	public EssenciaBoltEntity(EntityType<? extends LightningBoltEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	@Override
	public @NotNull IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public void setRGBA(int rgba) {
		this.entityData.set(DATA_COLOR_ID, rgba);
	}

	public int getRGBA() {
		return this.entityData.get(DATA_COLOR_ID);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_COLOR_ID, 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("Color", getRGBA());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.setRGBA(compound.getInt("Color"));
	}
}
