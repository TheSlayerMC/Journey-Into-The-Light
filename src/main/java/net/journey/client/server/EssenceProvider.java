package net.journey.client.server;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class EssenceProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(IEssence.class)
	public static final Capability<IEssence> ESSENCE_CAP = null;
	
	private IEssence INSTANCE = ESSENCE_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == ESSENCE_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == ESSENCE_CAP ? ESSENCE_CAP.<T>cast(this.INSTANCE) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return ESSENCE_CAP.getStorage().writeNBT(ESSENCE_CAP, INSTANCE, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		ESSENCE_CAP.getStorage().readNBT(ESSENCE_CAP, INSTANCE, null, nbt);
	}

}