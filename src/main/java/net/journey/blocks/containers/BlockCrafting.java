package net.journey.blocks.containers;

import net.journey.init.JourneyTabs;
import net.journey.util.StuffConstructor;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.SoundType;

public class BlockCrafting extends BlockWorkbench {

    public BlockCrafting(String name, String enName) {
        setSoundType(SoundType.STONE);
        setHardness(2.0F);
        StuffConstructor.regAndSetupBlock(this, name, enName, JourneyTabs.INTERACTIVE_BLOCKS);
    }
}