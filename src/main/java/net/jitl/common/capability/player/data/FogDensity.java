package net.jitl.common.capability.player.data;

import ru.timeconqueror.timecore.common.capability.property.CoffeeProperty;
import ru.timeconqueror.timecore.common.capability.property.container.PropertyContainer;

public class FogDensity extends PropertyContainer {
    public final CoffeeProperty<Boolean> densityEnabled = prop("densityEnabled", false).synced();

    public boolean isDensityEnabled() {
        return densityEnabled.get();
    }

    public void setDensityEnabled(boolean densityEnabled) {
        this.densityEnabled.set(densityEnabled);
    }
}
