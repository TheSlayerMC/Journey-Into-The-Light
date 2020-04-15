package net.journey.client.render;

import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RenderItemTileEntity extends TileEntityItemStackRenderer {

    private static final TileEntityJourneyChest JOURNEY_CHEST = new TileEntityJourneyChest();

    public static RenderItemTileEntity instance = new RenderItemTileEntity();

    @Override
    public void renderByItem(ItemStack itemStackIn) {
        Item item = itemStackIn.getItem();
        if (Block.getBlockFromItem(item) instanceof BlockChest) {
            {
                TileEntityRendererDispatcher.instance.render(JOURNEY_CHEST, 0, 0);
            }
        } else {
            super.renderByItem(itemStackIn);
        }
    }
}
