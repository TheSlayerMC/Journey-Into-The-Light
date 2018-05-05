package net.slayer.api.block;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockModStairs extends BlockStairs {

	public BlockModStairs(Block stair, String name, String finalName, boolean light) {
		super(stair.getDefaultState());
		LangRegistry.addBlock(name, finalName);
		setCreativeTab(JourneyTabs.blocks);
		setUnlocalizedName(name);
		if(light) setLightLevel(0.5F);
		this.setLightOpacity(1);
		setHardness(stair.getBlockHardness(null, null));
		JourneyBlocks.blockName.add(name);
		GameRegistry.registerBlock(this, name);
	}
	
	public BlockModStairs(Block b, String n, String n2) {
		this(b, n, n2, false);
	}
}