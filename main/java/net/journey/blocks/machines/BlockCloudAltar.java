package net.journey.blocks.machines;

import net.journey.blocks.tileentity.TileEntityGrindstone;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.slayer.api.block.BlockMod;
import net.slayer.api.entity.tileentity.container.BlockModContainer;

public class BlockCloudAltar extends BlockModContainer {

	public BlockCloudAltar(String name, String finalName) {
		super(name, finalName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityGrindstone();
	}
}
