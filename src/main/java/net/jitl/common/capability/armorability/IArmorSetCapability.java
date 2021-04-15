package net.jitl.common.capability.armorability;

import net.jitl.common.item.gearabilities.FullArmorAbilities;
import net.jitl.common.item.gearabilities.PieceArmorAbilities;

public interface IArmorSetCapability {
    void setArmor(int slot, PieceArmorAbilities ability);

    PieceArmorAbilities[] getArmor();

    FullArmorAbilities getFullAbilities();
}
