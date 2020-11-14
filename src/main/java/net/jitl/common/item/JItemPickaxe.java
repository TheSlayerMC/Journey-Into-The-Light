package net.jitl.common.item;

import net.jitl.common.helper.JToolTiers;
import net.jitl.init.JTabs;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class JItemPickaxe extends PickaxeItem {

    public JItemPickaxe(JToolTiers tier) {
        super(tier, 0, tier.getAttackSpeed(), new Item.Properties().tab(JTabs.TOOLS));
    }
}