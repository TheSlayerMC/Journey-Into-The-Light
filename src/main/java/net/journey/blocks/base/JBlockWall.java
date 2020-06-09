package net.journey.blocks.base;

import net.journey.JITL;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.item.ItemBlock;

public class JBlockWall extends BlockWall {

	public String name;
	
	public JBlockWall(Block modelBlock, String name, String enName) {
		super(modelBlock);
        this.name = name;
        setTranslationKey(name);
        setCreativeTab(JourneyTabs.BLOCKS);
        setHardness(2.0F);
        JourneyBlocks.blocks.add(this);
        setRegistryName(JITL.MOD_ID, name);
        LangGeneratorFacade.addBlockEntry(this, enName);
        JourneyBlocks.itemBlocks.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
}
