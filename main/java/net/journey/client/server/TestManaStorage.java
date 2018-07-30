package net.journey.client.server;

import net.journey.util.IJourneyMana;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class TestManaStorage implements IStorage<IJourneyMana> {
	
	@Override
	public NBTBase writeNBT(Capability<IJourneyMana> capability, IJourneyMana instance, EnumFacing side) {
		return new NBTTagFloat(instance.getMana());
	}

	@Override
	public void readNBT(Capability<IJourneyMana> capability, IJourneyMana instance, EnumFacing side, NBTBase nbt) {
		instance.set(((NBTPrimitive) nbt).getFloat());
	}
}