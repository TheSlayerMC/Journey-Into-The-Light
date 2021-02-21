package net.jitl.capabilities;

import net.jitl.common.helper.JArmorMaterial;

public interface IArmorManager {
    void setArmor(JArmorMaterial material);

    JArmorMaterial getArmor();
}
