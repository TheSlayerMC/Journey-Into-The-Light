package net.journey.items.bauble.ring;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.init.JourneyTabs;
import net.journey.items.base.JItem;
import net.journey.util.PotionEffects;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class ItemRingBase extends JItem implements IBauble {

    public ItemRingBase(String name, String enName) {
        super(name, enName);
        setCreativeTab(JourneyTabs.UTIL);
        setMaxStackSize(1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.RING;
    }
}
