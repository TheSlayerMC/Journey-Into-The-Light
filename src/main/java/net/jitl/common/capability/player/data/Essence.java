package net.jitl.common.capability.player.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import ru.timeconqueror.timecore.common.capability.CallbackProperty;
import ru.timeconqueror.timecore.common.capability.property.IChangable;

public class Essence implements INBTSerializable<CompoundNBT>, IChangable {
    private final float maxEssence = 10.0F;

    public final CallbackProperty<Float> currentEssence = new CallbackProperty<>(this, maxEssence);

    private boolean changed = false;

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat("current_essence", currentEssence.get());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        currentEssence.set(nbt.getFloat("current_essence"));
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
        if (currentEssence.get() != value) {
            currentEssence.set(value);
            setChanged(true);
        }
    }

    public void addEssence(float add) {
        setEssence(Math.min(currentEssence.get() + add, maxEssence));
    }

    public boolean consumeEssence(float price) {
        float postValue = currentEssence.get() - price;
        if (postValue >= 0) {
            setEssence(postValue);
            return true;
        }
        //TODO overheat
        return false;
    }
}
