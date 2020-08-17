package net.journey.client.server;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

import javax.annotation.Nullable;

public class EssenceSerializer implements IStorage<IEssence> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IEssence> capability, IEssence instance, EnumFacing side) {
        if (!(instance instanceof EssenceBar)) {
            throw new IllegalArgumentException("Can not serialize an instance that isn't the default implementation");
        }

        return EssenceBar.writeToNBT((EssenceBar) instance);
    }

    @Override
    public void readNBT(Capability<IEssence> capability, IEssence instance, EnumFacing side, NBTBase nbt) {
        if (!(instance instanceof EssenceBar)) {
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        }

        EssenceBar bar = (EssenceBar) instance;

        if(nbt instanceof NBTTagInt) {
            EssenceBar.readFromOldNBT(bar, (NBTTagInt) nbt);
        } else {
            EssenceBar.readFromNBT(bar, ((NBTTagCompound) nbt));
        }
    }
}