package net.jitl.common.item.gear.base;

import net.jitl.common.helper.JToolTiers;
import net.jitl.init.JTabs;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class JSwordItem extends SwordItem {

	public JSwordItem(JToolTiers tier) {
		super(tier, (int) tier.getSwordDamage(), tier.getAttackSpeed(), new Item.Properties().tab(JTabs.WEAPONS));
	}
}