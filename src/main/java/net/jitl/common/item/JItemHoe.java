package net.jitl.common.item;

import net.jitl.common.helper.JToolTiers;
import net.jitl.init.JTabs;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;

public class JItemHoe extends HoeItem {

    public JItemHoe(JToolTiers tier) {
        super(tier, 0, tier.getAttackSpeed(), new Item.Properties().tab(JTabs.TOOLS));
    }
}