package net.jitl.common.capability.player.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import ru.timeconqueror.timecore.common.capability.CallbackProperty;
import ru.timeconqueror.timecore.common.capability.property.IChangable;

public class Sentacoins implements INBTSerializable<CompoundNBT>, IChangable {
    public final CallbackProperty<Integer> amount = new CallbackProperty<>(this, 0);
    private boolean changed = false;

    public void addCoins(int amount) {
        setAmount(amount + getAmount());
    }

    public boolean useSentacoins(int price) {
        int amount = getAmount();
        if (price <= amount) {
            setAmount(amount - price);
            return true;
        }
        return false;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public int getAmount() {
        return this.amount.get();
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("coins", getAmount());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        setAmount(nbt.getInt("coins"));
    }

    @Override
    public boolean getChanged() {
        return changed;
    }

    @Override
    public void setChanged(boolean b) {
        changed = b;
    }
}
