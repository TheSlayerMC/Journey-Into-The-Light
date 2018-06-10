package net.journey.blocks.crop.base;

import net.journey.JourneyItems;
import net.minecraft.item.Item;
import net.slayer.api.block.BlockModCrop;

public class BlockFruitCrop extends BlockModCrop {

	public BlockFruitCrop(String name) {
		super(name);
	}

	@Override
	public int getStages() {
		return 2;
	}

	@Override
	public Item getSeed() {
		return JourneyItems.bleedheart;
	}

	@Override
	public Item getCrop() {
		return JourneyItems.bleedheart;
	}
}   