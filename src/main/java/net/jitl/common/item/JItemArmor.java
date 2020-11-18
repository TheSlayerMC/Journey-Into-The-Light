package net.jitl.common.item;

import net.jitl.common.helper.JArmorMaterial;
import net.jitl.init.JTabs;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class JItemArmor extends ArmorItem {

    public JItemArmor(JArmorMaterial materialIn, EquipmentSlotType slotIn) {
        super(materialIn, slotIn, new Item.Properties().tab(JTabs.ARMOR));
    }

    //More will be in here not just a useless class

}