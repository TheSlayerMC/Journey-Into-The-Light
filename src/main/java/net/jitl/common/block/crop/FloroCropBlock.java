package net.jitl.common.block.crop;

import net.jitl.common.block.base.JCropBlock;
import net.jitl.init.JItems;
import net.minecraft.item.Item;

public class FloroCropBlock extends JCropBlock {

    @Override
    public Item getSeedItem() {
        return JItems.FLORO_SEEDS;
    }

    @Override
    public int getMaxAge() {
        return 5;
    }
}
