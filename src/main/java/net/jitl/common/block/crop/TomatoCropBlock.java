package net.jitl.common.block.crop;

import net.jitl.common.block.base.JCropBlock;
import net.jitl.init.JItems;
import net.jitl.util.JBlockProperties;
import net.minecraft.item.Item;

public class TomatoCropBlock extends JCropBlock {

    public TomatoCropBlock() {
        super(JBlockProperties.CROP_PROPS.create());
    }

    @Override
    public Item getSeedItem() {
        return JItems.TOMATO_SEEDS;
    }

    @Override
    public int getMaxAge() {
        return 7;
    }
}
