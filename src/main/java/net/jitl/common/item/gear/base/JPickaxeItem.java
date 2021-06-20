package net.jitl.common.item.gear.base;

import net.jitl.common.helper.JToolTiers;
import net.jitl.init.JTabs;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class JPickaxeItem extends PickaxeItem {

	public JPickaxeItem(JToolTiers tier) {
		super(tier, (int) tier.getShovelDam(), tier.getAttackSpeed(), new Item.Properties().tab(JTabs.TOOLS));
	}
}