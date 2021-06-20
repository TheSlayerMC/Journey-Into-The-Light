package net.jitl.common.item.gear.base;

import net.jitl.common.helper.JToolTiers;
import net.jitl.init.JTabs;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public class JAxeItem extends AxeItem {

	public JAxeItem(JToolTiers tier) {
		super(tier, tier.getAxeDam(), tier.getAttackSpeed(), new Item.Properties().tab(JTabs.TOOLS));
	}
}