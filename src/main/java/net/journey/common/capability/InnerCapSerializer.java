package net.journey.common.capability;

import net.minecraft.nbt.NBTBase;

public abstract class InnerCapSerializer<T, NBT extends NBTBase> {

    public abstract NBT writeToNBT(T instance);

    public final void readFromNBT(T instance, NBTBase nbt) {
        readFromNBTCasted(instance, (NBT) nbt);
    }

    protected abstract void readFromNBTCasted(T instance, NBT nbt);
}