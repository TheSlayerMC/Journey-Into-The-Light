package net.jitl.common.capability.player.data;

import ru.timeconqueror.timecore.common.capability.property.CoffeeProperty;
import ru.timeconqueror.timecore.common.capability.property.container.PropertyContainer;

public class FrozenBlizzard extends PropertyContainer {
    public final CoffeeProperty<Boolean> blizzardDisabled = prop("blizzardDisabled", false).synced();

    public boolean isBlizzardDisabled() {
        return blizzardDisabled.get();
    }

    public void setBlizzardDisabled(boolean blizzardDisabled) {
        this.blizzardDisabled.set(blizzardDisabled);
    }
}
