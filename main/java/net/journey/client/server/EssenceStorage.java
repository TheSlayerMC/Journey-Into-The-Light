package net.journey.client.server;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class EssenceStorage implements IStorage<IEssence> {

	@Override
	public NBTBase writeNBT(Capability<IEssence> capability, IEssence instance, EnumFacing side) {
		return new NBTTagInt(instance.getEssenceValue());
	}

	@Override
	public void readNBT(Capability<IEssence> capability, IEssence instance, EnumFacing side, NBTBase nbt) {
		instance.setEssence(((NBTTagInt)nbt).getInt());
	}
}