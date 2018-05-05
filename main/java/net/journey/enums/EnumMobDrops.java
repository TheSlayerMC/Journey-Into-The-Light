package net.journey.enums;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * <b>WIP</b>
 */
public enum EnumMobDrops {

	ROBOT(Items.stick, Items.iron_ingot);

	private Item[] drops;

	private EnumMobDrops(Item...drops) {
		this.drops = drops;
	}

	public Item[] getDrops() {
		return drops;
	}
}