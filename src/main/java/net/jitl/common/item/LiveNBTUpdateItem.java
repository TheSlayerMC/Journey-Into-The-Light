package net.jitl.common.item;

import net.jitl.common.capability.morphingnbt.IMorphingNBTCapability;
import net.jitl.common.capability.morphingnbt.MorphingNBTCapability;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public interface LiveNBTUpdateItem {
    IMorphingNBTCapability getCap(ItemStack stack);
}
