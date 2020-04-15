package net.journey.enums;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * <b>WIP</b>
 */
public enum EnumMobDrops {

    ROBOT(Items.STICK, Items.IRON_INGOT);

    private Item[] drops;

    EnumMobDrops(Item... drops) {
        this.drops = drops;
    }

    public Item[] getDrops() {
        return drops;
    }
}