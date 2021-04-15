package net.jitl.common.item.gearabilities;

import net.jitl.common.helper.TooltipFiller;
import net.minecraft.item.ItemStack;

public abstract class FullArmorAbilities {
    private ItemStack[] stacks = new ItemStack[4];

    public void setStacks(ItemStack boots, ItemStack pants, ItemStack chest, ItemStack helm) {
        stacks[0] = boots;
        stacks[1] = pants;
        stacks[2] = chest;
        stacks[3] = helm;
    }

    public void fillArmorTooltip(TooltipFiller filler) {

    }
}
