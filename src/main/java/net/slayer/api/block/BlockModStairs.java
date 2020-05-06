package net.slayer.api.block;

import net.journey.JITL;
import net.journey.init.JourneyTabs;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyItems;
import net.journey.util.gen.lang.LangGeneratorFacade;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.item.ItemBlock;
import net.slayer.api.SlayerAPI;

public class BlockModStairs extends BlockStairs {

    public String name;

    public BlockModStairs(Block stair, String name, String finalName, boolean light) {
        super(stair.getDefaultState());
        this.name = name;
        setCreativeTab(JourneyTabs.BLOCKS);
        setTranslationKey(name);
        if (light) setLightLevel(0.5F);
        this.setLightOpacity(1);
        JourneyBlocks.blockName.add(SlayerAPI.PREFIX + name);
        JourneyBlocks.blocks.add(this);
        setRegistryName(JITL.MOD_ID, name);

	    LangGeneratorFacade.addBlockEntry(this, finalName);
	    JourneyItems.items.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public BlockModStairs(Block b, String n, String n2) {
        this(b, n, n2, false);
        this.name = n;
    }
}