package net.slayer.api.block;

import net.journey.JITL;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockModFence extends BlockFence {

    public String name;

    public BlockModFence(Block block, String name, String finalName, boolean light) {
        super(Material.ROCK, null);
        this.name = name;
        setTranslationKey(name);
        setCreativeTab(JourneyTabs.BLOCKS);
        if (light) setLightLevel(0.5F);
        setHardness(block.getBlockHardness(null, null, null));
        JourneyBlocks.blocks.add(this);
        setRegistryName(JITL.MOD_ID, name);
        LangGeneratorFacade.addBlockEntry(this, finalName);
        JourneyItems.items.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public BlockModFence(Block b, String n, String finalName) {
        this(b, n, finalName, false);
        this.name = n;
    }

	
	/*@Override
	public boolean canConnectTo(IBlockAccess blockAccess, BlockPos pos) {
		Block block = blockAccess.getBlockState(pos).getBlock();
		 return 
				 block == Blocks.barrier ? false : ((!(
				 block instanceof BlockFence) || 
				 block.getMaterial() != this.blockMaterial) && !(
				 block instanceof BlockFenceGate) ? (
				 block.getMaterial().isOpaque() && 
				 block.isFullCube() ? 
				 block.getMaterial() != Material.gourd : false) : true);
	}*/
}