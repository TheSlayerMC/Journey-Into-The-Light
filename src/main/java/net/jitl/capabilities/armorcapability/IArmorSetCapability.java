package net.jitl.capabilities.armorcapability;

import net.jitl.common.helper.JArmorMaterial;

public interface IArmorSetCapability {
    void setArmor(JArmorMaterial material);

    JArmorMaterial getArmor();
}
