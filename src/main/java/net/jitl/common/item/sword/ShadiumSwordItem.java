package net.jitl.common.item.sword;

import net.jitl.common.helper.JToolTiers;
import net.jitl.common.item.IEquipUpdateItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class ShadiumSwordItem extends JSwordItem implements IEquipUpdateItem {

    public ShadiumSwordItem(JToolTiers tier) {
        super(tier);
    }

    @Override
    public void equip(LivingEntity entity, EquipmentSlotType slot) {
    }

    @Override
    public void unEquip(LivingEntity entity, EquipmentSlotType slot) {
        if (slot == EquipmentSlotType.MAINHAND) {
            //remove bonus damage
        }
    }
}
