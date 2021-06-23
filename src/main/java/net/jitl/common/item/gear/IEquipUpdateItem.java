package net.jitl.common.item.gear;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public interface IEquipUpdateItem {
    void equip(LivingEntity entity, EquipmentSlotType slot);

    void unEquip(LivingEntity entity, EquipmentSlotType slot);
}
