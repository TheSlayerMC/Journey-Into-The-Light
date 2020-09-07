package net.journey.items.bauble.ring;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.items.base.JItem;
import net.minecraft.item.ItemStack;

public class ItemRingBase extends JItem implements IBauble {

    public ItemRingBase() {
        setMaxStackSize(1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.RING;
    }
}
