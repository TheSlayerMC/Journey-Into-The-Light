package net.jitl.common.capability.player.data;

import ru.timeconqueror.timecore.common.capability.property.CoffeeProperty;
import ru.timeconqueror.timecore.common.capability.property.container.PropertyContainer;

public class Sentacoins extends PropertyContainer {
    public final CoffeeProperty<Integer> amount = prop("coins", 0);

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
}
