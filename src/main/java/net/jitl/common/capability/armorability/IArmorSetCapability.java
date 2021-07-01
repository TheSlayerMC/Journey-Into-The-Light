package net.jitl.common.capability.armorability;

import net.jitl.common.item.gear.abilities.FullArmorAbility;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import java.util.ArrayList;
import java.util.Iterator;

public interface IArmorSetCapability {
    void setArmor(Iterator<ItemStack> iterator);

    ArrayList<ItemStack> getArmor();

    FullArmorAbility getFullArmor();

    CompoundNBT getNBT();

    void setNBT(CompoundNBT nbt);
}
