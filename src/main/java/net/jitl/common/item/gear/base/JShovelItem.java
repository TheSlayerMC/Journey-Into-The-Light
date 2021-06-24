package net.jitl.common.item.gear.base;

import net.jitl.common.helper.JToolTiers;
import net.jitl.init.JTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;

public class JShovelItem extends ShovelItem {

	public JShovelItem(JToolTiers tier) {
		super(tier, tier.getShovelDam(), tier.getAttackSpeed(), new Item.Properties().tab(JTabs.TOOLS));
	}
}