package net.jitl.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class JFuelItem extends Item {

	public final int burnTime;

	public JFuelItem(Properties properties, int burnTime) {
		super(properties);
		this.burnTime = burnTime;
	}

	@Override //TODO: how does fuel work now? ~ Dizzle
	public int getBurnTime(ItemStack itemStack) {
		return burnTime;
	}
}
