package net.slayer.api.block;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockModFence extends BlockFence {

	public BlockModFence(Block block, String name, String finalName, boolean light) {
		super(Material.ROCK, null);
		LangRegistry.addBlock(name, finalName);
		setUnlocalizedName(name);
		setCreativeTab(JourneyTabs.blocks);
		if(light) setLightLevel(0.5F);
		setHardness(block.getBlockHardness(null, null, null));
		JourneyBlocks.blockName.add(name);
		JourneyBlocks.blocks.add(this);
		setRegistryName(name);
	}
	
	public BlockModFence(Block b, String n, String finalName) {
		this(b, n, finalName, false);
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