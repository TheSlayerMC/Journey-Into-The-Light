package net.journey.blocks;

import net.slayer.api.block.BlockMod;

public class BlockDungeonBlock extends BlockMod {

	public BlockDungeonBlock(String name, String f, boolean light) {
		super(name, f, -1.0F);
		if(light) setLightLevel(0.1F);
	}

	public BlockDungeonBlock(String name, String f){
		this(name, f, false);
	}
}