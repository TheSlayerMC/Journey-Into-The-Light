package net.jitl.common.item;

import net.jitl.common.helper.JArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

public class JItemArmor extends ArmorItem {

    public JItemArmor(JArmorMaterial materialIn, EquipmentSlotType slotIn, Properties builderIn) {
        super(materialIn, slotIn, builderIn);
    }
}