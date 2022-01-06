package net.jitl.common.block.crop;

import net.jitl.common.block.base.JCropBlock;
import net.jitl.init.JItems;
import net.minecraft.world.item.Item;

import Item;

public class TomatoCropBlock extends JCropBlock {

    @Override
    public Item getSeedItem() {
        return JItems.TOMATO_SEEDS;
    }

    @Override
    public int getMaxAge() {
        return 7;
    }
}
