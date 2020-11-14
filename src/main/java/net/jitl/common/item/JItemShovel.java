package net.jitl.common.item;

import net.jitl.common.helper.JToolTiers;
import net.jitl.init.JTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;

public class JItemShovel extends ShovelItem {

    public JItemShovel(JToolTiers tier) {
        super(tier, tier.getShovelDam(), tier.getAttackSpeed(), new Item.Properties().tab(JTabs.TOOLS));
    }
}