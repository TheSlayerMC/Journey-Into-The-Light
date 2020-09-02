package net.journey.common.capability.innercaps;

import net.journey.api.capability.EssenceStorage;
import net.journey.common.capability.InnerCapSerializer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;

public class EssenceStorageImpl implements EssenceStorage {

    private final int maxValue;

    private int essenceValue = 0;
    private int regenCooldown = 0;

    public EssenceStorageImpl(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public boolean useEssence(int points) {
        if (essenceValue < points)
            return false;
        essenceValue -= points;
        return true;
    }

    @Override
    public void addEssence(int points) {
        essenceValue += points;

        coerceEssence(this);
    }

    @Override
    public int getEssenceValue() {
        return essenceValue;
    }

    @Override
    public int getMaxValue() {
        return maxValue;
    }

    @Override
    public boolean isFull() {
        return getEssenceValue() == getMaxValue();
    }

    @Override
    public void onTick() {
        if (regenCooldown-- <= 0) regenCooldown = 30;
        if (regenCooldown >= 30) regen();
    }

    @Override
    public void regen() {
        addEssence(1);
    }

    private static void coerceEssence(EssenceStorageImpl essenceBar) {
        essenceBar.essenceValue = Math.min(essenceBar.getEssenceValue(), essenceBar.getMaxValue());
    }

    public static class Serializer extends InnerCapSerializer<EssenceStorageImpl, NBTBase> {

        public NBTBase writeToNBT(EssenceStorageImpl essence) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setInteger("regen_cooldown", essence.regenCooldown);
            compound.setInteger("essence_value", essence.getEssenceValue());

            return compound;
        }

        @Override
        protected void readFromNBTCasted(EssenceStorageImpl instance, NBTBase nbt) {
            if (nbt instanceof NBTTagInt) {
                readFromOldNBT(instance, (NBTTagInt) nbt);
            } else {
                readFromNBT(instance, ((NBTTagCompound) nbt));
            }
        }

        private static void readFromNBT(EssenceStorageImpl essence, NBTTagCompound tag) {
            essence.regenCooldown = tag.getInteger("regen_cooldown");
            essence.essenceValue = tag.getInteger("essence_value");

            coerceEssence(essence);
        }

        @Deprecated //remove in 1.13+
        private static void readFromOldNBT(EssenceStorageImpl essence, NBTTagInt tag) {
            essence.essenceValue = tag.getInt();
        }
    }
}