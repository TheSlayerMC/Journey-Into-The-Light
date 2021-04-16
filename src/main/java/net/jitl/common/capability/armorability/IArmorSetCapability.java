package net.jitl.common.capability.armorability;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public interface IArmorSetCapability {
    void setArmor(Iterable<ItemStack> stacks);

    ArrayList<Item> getArmor();

    Item getFullSet();
}
