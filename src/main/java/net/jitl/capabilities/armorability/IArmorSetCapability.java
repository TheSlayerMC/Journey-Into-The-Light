package net.jitl.capabilities.armorability;

import net.jitl.common.item.gearabilities.BaseArmorAbilities;

public interface IArmorSetCapability {
    void setArmor(BaseArmorAbilities material);

    BaseArmorAbilities getArmor();
}
