package net.jitl.common.capability.player.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import ru.timeconqueror.timecore.common.capability.CallbackProperty;
import ru.timeconqueror.timecore.common.capability.property.IChangable;

public class Essence implements INBTSerializable<CompoundNBT>, IChangable {
    public final CallbackProperty<Float> maxEssence = new CallbackProperty<>(this, 0.0F);
    public final CallbackProperty<Float> currentEssence = new CallbackProperty<>(this, getMaxEssence());

    private boolean changed = false;

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat("current_essence", getCurrentEssence());
        nbt.putFloat("max_essence", getMaxEssence());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        currentEssence.set(nbt.getFloat("current_essence"));
        maxEssence.set(nbt.getFloat("max_essence"));
    }

    public float getMaxEssence() {
        return maxEssence.get();
    }

    public float getCurrentEssence() {
        return currentEssence.get();
    }

    @Override
    public boolean getChanged() {
        return changed;
    }

    @Override
    public void setChanged(boolean state) {
        this.changed = state;
    }

    public void setEssence(float value) {
        if (getCurrentEssence() != value) {
            currentEssence.set(value);
        }
    }

    public void setMaxEssence(float value) {
        maxEssence.set(value);
    }

    public void addEssence(float add) {
        setEssence(Math.min(getCurrentEssence() + add, getMaxEssence()));
    }

    public boolean consumeEssence(float price) {
        if (hasEssence(price)) {
            setEssence(getCurrentEssence() - price);
            return true;
        }
        //TODO overheat
        return false;
    }

    public boolean hasEssence(float price) {
        return getCurrentEssence() >= price;
    }

    public boolean checkEssenceEitherSide(boolean client, float price) {
        if (client) {
            return hasEssence(price);
        }
        return consumeEssence(price);
    }
}
