package net.jitl.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class JFuelItem extends Item {

	public final int burnTime;

	public JFuelItem(Properties properties, int burnTime) {
		super(properties);
		this.burnTime = burnTime;
	}

	@Override
	public int getBurnTime(ItemStack itemStack) {
		return burnTime;
	}
}
