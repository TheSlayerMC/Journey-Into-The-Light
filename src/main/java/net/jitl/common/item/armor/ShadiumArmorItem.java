package net.jitl.common.item.armor;

import net.jitl.common.helper.JArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class ShadiumArmorItem extends JArmorItem implements FullArmorAbility {
    public ShadiumArmorItem(JArmorMaterial materialIn, EquipmentSlotType slotIn) {
        super(materialIn, slotIn);
    }


    @Override
    public void fullSetTick(ArrayList<ItemStack> stacks) {
        System.out.println(this.getMaterial().getName());
    }
}
