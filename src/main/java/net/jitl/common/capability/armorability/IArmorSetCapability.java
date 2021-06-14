package net.jitl.common.capability.armorability;

import net.jitl.common.item.armor.FullArmorAbility;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public interface IArmorSetCapability {
    void setArmor(ArrayList<ItemStack> stacks, boolean isFull);

    ArrayList<ItemStack> getArmor();

    FullArmorAbility getFullArmor();
}
