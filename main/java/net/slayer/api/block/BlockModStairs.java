package net.slayer.api.block;

import net.journey.JITL;
import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.journey.util.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slayer.api.SlayerAPI;

public class BlockModStairs extends BlockStairs {

	public String name;
	public BlockModStairs(Block stair, String name, String finalName, boolean light) {
		super(stair.getDefaultState());
		this.name = name;
		LangRegistry.addBlock(name, finalName);
		setCreativeTab(JourneyTabs.blocks);
		setUnlocalizedName(name);
		if(light) setLightLevel(0.5F);
		this.setLightOpacity(1);
		JourneyBlocks.blockName.add(SlayerAPI.PREFIX + name);
		JourneyBlocks.blocks.add(this);
		setRegistryName(SlayerAPI.MOD_ID, name);
	}
	
	public BlockModStairs(Block b, String n, String n2) {
		this(b, n, n2, false);
		this.name = n;
	}
	
	public void registerItemModel(Item itemBlock) {
		JITL.proxy.registerItemRenderer(itemBlock, 0, name);
	}
	
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}
}