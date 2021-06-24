package net.jitl.common.item.gear.base;

import net.jitl.common.helper.JToolTiers;
import net.jitl.init.JTabs;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;

public class JHoeItem extends HoeItem {

	public JHoeItem(JToolTiers tier) {
		super(tier, 1, tier.getAttackSpeed(), new Item.Properties().tab(JTabs.TOOLS));
	}
}