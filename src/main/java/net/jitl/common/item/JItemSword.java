package net.jitl.common.item;

import net.jitl.common.helper.JToolTiers;
import net.jitl.init.JTabs;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class JItemSword extends SwordItem {

    public JItemSword(JToolTiers tier) {
        super(tier, 0, tier.getAttackSpeed(), new Item.Properties().tab(JTabs.WEAPONS));
    }
}