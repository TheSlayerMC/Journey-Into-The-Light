package net.journey.api.block.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.slayer.api.EnumMaterialTypes;

/**
 * Base class for all plants that behave like tall grass (replaceable)
 * <p>
 * The item model for it should be placed to "models/item/block/plant/" by default.
 */
public class JBlockGrassPlant extends JBlockPlant {
	public JBlockGrassPlant(String name, String enName) {
		super(name, enName);
	}

	public JBlockGrassPlant(String name, String enName, CreativeTabs tab) {
		super(name, enName, tab);
	}

	public JBlockGrassPlant(EnumMaterialTypes type, String name, String enName, CreativeTabs tab) {
		super(type, name, enName, tab);
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}
}
