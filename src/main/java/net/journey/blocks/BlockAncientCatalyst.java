package net.journey.blocks;

import com.google.common.base.Predicates;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.slayer.api.block.BlockMod;

public class BlockAncientCatalyst extends BlockMod {

	private static BlockPattern pattern;
	
    public BlockAncientCatalyst(String name, String f) {
        super(name, f, false);
    }
    
    public static BlockPattern getOrCreatepattern() {
        if (pattern == null) {
            pattern = FactoryBlockPattern.start().aisle(
            "v?v", 
            "???", 
            "v?v").where(
            '?', BlockWorldState.hasState(BlockStateMatcher.ANY)).where(
            'v', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.ANCIENT_SOCKET).where(BlockAncientSocket.INSERT, Predicates.equalTo(Boolean.valueOf(true))))).build();
        }
        return pattern;
    }
}