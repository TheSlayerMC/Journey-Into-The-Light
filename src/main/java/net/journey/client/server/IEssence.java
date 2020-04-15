package net.journey.client.server;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public interface IEssence {

    boolean useEssence(int points);

    void addEssence(int points);

    void setEssence(int essence);

    int getEssenceValue();

    void update();

    void regen();

    NBTBase writeNBT(IEssence essence, NBTTagCompound tag);

    void readNBT(NBTBase nbt, IEssence essence, NBTTagCompound tag);

}