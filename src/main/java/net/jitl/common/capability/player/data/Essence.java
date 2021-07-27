package net.jitl.common.capability.player.data;

import net.jitl.init.JAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import ru.timeconqueror.timecore.common.capability.CallbackProperty;
import ru.timeconqueror.timecore.common.capability.property.IChangable;

import java.util.Objects;

public class Essence implements INBTSerializable<CompoundNBT>, IChangable {
    public final CallbackProperty<Float> currentEssence = new CallbackProperty<>(this, 0.0F);
    public final CallbackProperty<Float> burnoutTime = new CallbackProperty<>(this, 0.0F);

    public int timeout = 0;

    private boolean changed = false;

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat("current_essence", getCurrentEssence());
        nbt.putFloat("burnout", getBurnout());
        nbt.putInt("timeout", getTimeout());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        currentEssence.set(nbt.getFloat("current_essence"));
        setBurnout(nbt.getFloat("burnout"));
        setTimeout(nbt.getInt("timeout"));

    }

    public static float getMaxEssence(PlayerEntity player) {
        return (float) Objects.requireNonNull(player.getAttribute(JAttributes.MAX_ESSENCE.get())).getValue();
    }

    public float getCurrentEssence() {
        return currentEssence.get();
    }

    public void setBurnout(float value) {
        burnoutTime.set(Math.max(value, 0.0F));
    }

    public void setTimeout(int value) {
        timeout = value;
    }

    public float getBurnout() {
        return burnoutTime.get();
    }

    public int getTimeout() {
        return timeout;
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

    public boolean isRegenReady() {
        if (timeout <= 0) return getBurnout() <= 0;
        timeout--;
        return false;
    }

    public void addEssence(PlayerEntity player, float add) {
        setEssence(Math.min(getCurrentEssence() + add, getMaxEssence(player)));
    }

    public boolean consumeEssence(PlayerEntity player, float price) {
        if (!player.isCreative()) {
            if (hasEssence(price)) {
                setEssence(getCurrentEssence() - price);
                setTimeout(20);
                return true;
            }
            float attributeValue = (float) player.getAttribute(JAttributes.ESSENCE_BURNOUT.get()).getValue();
            setBurnout(Math.min(getBurnout() + attributeValue, attributeValue * 5));
            return false;
        }
        return true;
    }

    public boolean hasEssence(float price) {
        return getCurrentEssence() >= price;
    }

    public boolean checkEssenceEitherSide(boolean client, PlayerEntity player, float price) {
        if (client) {
            return player.isCreative() || hasEssence(price);
        }
        return consumeEssence(player, price);
    }
}
