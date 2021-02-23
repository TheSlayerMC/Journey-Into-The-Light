package net.jitl.capabilities.armorability;

import net.jitl.common.item.gearabilities.IGearAbilities;

public interface IArmorSetCapability {
    void setArmor(IGearAbilities material);

    IGearAbilities getArmor();
}
