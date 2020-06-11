package net.journey.blocks.base;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.slayer.api.EnumMaterialTypes;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for small flower blocks.<br>
 * <i>Big flowers can't support offset, so they extend {@link JBlockPlant} directly.</i><br>
 * Actually it's the same as {@link JBlockPlant} but it supports model and bounding box offsetting
 * <p>
 * By default:
 * <ul>
 *     <li>Checks for ground to be placed and stay</li>
 *     <li>Drops itself</li>
 *     <li>Is NOT replaceable</li>
 *     <li>Has small model offset as vanilla flower have</li>
 *     <li>Has {@link Material#PLANTS} material unless it is  provided in a constructor</li>
 *     <li>Has no hardness (will be instantly broken)</li>
 * </ul>
 * <p>
 * The item model for it should be placed to "models/item/block/plant/" by default.
 */
public class JBlockSmallFlower extends JBlockPlant {
	public JBlockSmallFlower(String name, String enName) {
		super(name, enName);
	}

	public JBlockSmallFlower(String name, String enName, CreativeTabs tab) {
		super(name, enName, tab);
	}

	public JBlockSmallFlower(EnumMaterialTypes type, String name, String enName, CreativeTabs tab) {
		super(type, name, enName, tab);
	}

	@Override
	public @NotNull EnumOffsetType getOffsetType() {
		return EnumOffsetType.XZ;
	}

	@Override
	public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
		return super.getBoundingBox(state, source, pos).offset(state.getOffset(source, pos));
	}
}
