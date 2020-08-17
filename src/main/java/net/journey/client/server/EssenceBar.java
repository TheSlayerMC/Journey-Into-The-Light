package net.journey.client.server;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;

public class EssenceBar implements IEssence {

    private final int maxValue;

    private int essenceValue = 0;
    private int regenCooldown = 0;

    public EssenceBar(int maxValue) {
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
    public void update() {
        if (regenCooldown-- <= 0) regenCooldown = 30;
        if (regenCooldown >= 30) regen();
    }

    @Override
    public void regen() {
        addEssence(1);
    }

    public static NBTTagCompound writeToNBT(EssenceBar essence) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("regen_cooldown", essence.regenCooldown);
        compound.setInteger("essence_value", essence.getEssenceValue());

        return compound;
    }

    public static void readFromNBT(EssenceBar essence, NBTTagCompound tag) {
        essence.regenCooldown = tag.getInteger("regen_cooldown");
        essence.essenceValue = tag.getInteger("essence_value");

        coerceEssence(essence);
    }

    @Deprecated //remove in 1.13+
    public static void readFromOldNBT(EssenceBar essence, NBTTagInt tag) {
        essence.essenceValue = tag.getInt();
    }

    private static void coerceEssence(EssenceBar essenceBar){
        essenceBar.essenceValue = Math.min(essenceBar.getEssenceValue(), essenceBar.getMaxValue());
    }
}