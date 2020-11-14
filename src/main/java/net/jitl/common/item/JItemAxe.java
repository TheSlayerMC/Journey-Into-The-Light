package net.jitl.common.item;

import net.jitl.common.helper.JToolTiers;
import net.jitl.init.JTabs;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public class JItemAxe extends AxeItem {

    public JItemAxe(JToolTiers tier) {
        super(tier, 0, tier.getAttackSpeed(), new Item.Properties().tab(JTabs.TOOLS));
    }
}