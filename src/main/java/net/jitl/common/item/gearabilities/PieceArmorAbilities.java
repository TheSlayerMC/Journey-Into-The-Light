package net.jitl.common.item.gearabilities;

import net.jitl.common.helper.TooltipFiller;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

public abstract class PieceArmorAbilities {
    private ItemStack stack;

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }

    public void fillArmorTooltip(TooltipFiller filler) {

    }
}
