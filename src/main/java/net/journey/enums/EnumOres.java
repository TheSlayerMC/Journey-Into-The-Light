package net.journey.enums;

import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public enum EnumOres {

    HELLSTONE(JourneyBlocks.hellstoneOre, JourneyItems.hellstoneDust, JourneyItems.hellstoneIngot);

    public Block ore;
    public Item dust, ingot;

    EnumOres(Block ore, Item dust, Item ingot) {
        this.ore = ore;
        this.dust = dust;
        this.ingot = ingot;
    }
}