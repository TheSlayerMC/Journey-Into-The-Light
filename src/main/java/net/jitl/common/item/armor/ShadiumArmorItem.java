package net.jitl.common.item.armor;

import net.jitl.common.helper.JArmorMaterial;
import net.jitl.common.item.IEquipUpdateItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class ShadiumArmorItem extends JArmorItem implements IEquipUpdateItem {
    
    public ShadiumArmorItem(JArmorMaterial materialIn, EquipmentSlotType slotIn) {
        super(materialIn, slotIn);
    }

    @Override
    public void equip(LivingEntity entity, EquipmentSlotType slot) {

    }

    @Override
    public void unEquip(LivingEntity entity, EquipmentSlotType slot) {

    }
}
