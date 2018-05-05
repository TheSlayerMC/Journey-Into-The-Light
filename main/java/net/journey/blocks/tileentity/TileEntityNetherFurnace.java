package net.journey.blocks.tileentity;

import net.journey.blocks.tileentity.base.TileEntityAdvancedFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.slayer.api.block.BlockNetherFurnace;

public class TileEntityNetherFurnace extends TileEntityAdvancedFurnace {

	public TileEntityNetherFurnace() {
		super("Nether Furnace", 275);
	}

	@Override
	public void addUpdate() {
		BlockNetherFurnace.setState(this.furnaceBurnTime > 0, this.worldObj, pos);
	}

	@Override
	public IChatComponent getDisplayName() {
		return null;
	}
}