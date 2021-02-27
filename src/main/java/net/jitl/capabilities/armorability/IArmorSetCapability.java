package net.jitl.capabilities.armorability;

import net.jitl.common.item.gearabilities.BaseAbilities;

public interface IArmorSetCapability {
    void setArmor(BaseAbilities material);

    BaseAbilities getArmor();
}
