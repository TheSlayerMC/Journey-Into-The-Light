package net.journey.blocks;

import net.slayer.api.block.BlockModSlab;

public class BlockModDoubleSlab extends BlockModSlab {

    public BlockModDoubleSlab(boolean full, String name, String f) {
        super(full, name, f);
        setCreativeTab(null);
    }

	/*@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, BlockPos pos){
		Block b = this == EssenceBlocks.eucaFullSlab ? EssenceBlocks.eucaSlab : this == EssenceBlocks.depthsFullSlab ? EssenceBlocks.depthsSlab : null;
		return Item.getItemFromBlock(b);
	}*/
}
