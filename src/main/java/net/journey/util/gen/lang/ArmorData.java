package net.journey.util.gen.lang;

import net.journey.enums.EnumArmor;
import net.minecraft.item.ItemArmor;

public class ArmorData {
	private ItemArmor item;
	private EnumArmor type;

	public ArmorData(ItemArmor item, EnumArmor type) {
		this.item = item;
		this.type = type;
	}

	public EnumArmor getType() {
		return type;
	}

	public ItemArmor getItem() {
		return item;
	}
}
