package net.jitl.common.capability.player.data;

import net.jitl.init.JAttributes;
import net.minecraft.world.entity.player.Player;
import ru.timeconqueror.timecore.common.capability.property.CoffeeProperty;
import ru.timeconqueror.timecore.common.capability.property.container.PropertyContainer;

import java.util.Objects;

public class Essence extends PropertyContainer {
    public final CoffeeProperty<Float> currentEssence = prop("current_essence", 0F).synced();
    public final CoffeeProperty<Float> burnoutTime = prop("burnout", 0F).synced();
    public final CoffeeProperty<Integer> timeout = prop("timeout", 0);

    public static float getMaxEssence(Player player) {
        return (float) Objects.requireNonNull(player.getAttribute(JAttributes.MAX_ESSENCE.get())).getValue();
    }

    public float getCurrentEssence() {
        return currentEssence.get();
    }

    public void setBurnout(float value) {
        burnoutTime.set(Math.max(value, 0.0F));
    }

    public void setTimeout(int value) {
        timeout.set(value);
    }

    public float getBurnout() {
        return burnoutTime.get();
    }

    public int getTimeout() {
        return timeout.get();
    }

    public void setEssence(float value) {
        if (getCurrentEssence() != value) {
            currentEssence.set(value);
        }
    }

    public boolean isRegenReady() {
        if (getTimeout() <= 0) return getBurnout() <= 0;
        setTimeout(getTimeout() - 1);
        return false;
    }

    public void addEssence(Player player, float add) {
        setEssence(Math.min(getCurrentEssence() + add, getMaxEssence(player)));
    }

    public boolean consumeEssence(Player player, float price) {
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

    public boolean checkEssenceEitherSide(boolean client, Player player, float price) {
        if (client) {
            return player.isCreative() || hasEssence(price);
        }
        return consumeEssence(player, price);
    }
}
