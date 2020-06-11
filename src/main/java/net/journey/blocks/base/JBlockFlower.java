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
 * Base class for flower blocks.<br>
 * Actually it's the same as {@link JBlockPlant} but it supports model and bounding box offsetting
 * <i>Big flowers can't support offset, so they extend {@link JBlockPlant} directly.</i><br>
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
 * You can also set:
 * <ul>
 *     <li>{@link JBlockPlant#groundPredicate} - predicate that checks if plant can be placed and sustain on provided block</li>
 *     <li>{@link JBlockPlant#plantDirection} - the side of ground block where plant can be placed and stay.</li>
 *     <li>{@link JBlockPlant#boundingBox} - bounding box of the plant. Equals standard bush box by default.</li>
 * </ul>
 * <p>
 * The item model for it should be placed to "models/item/block/plant/" by default.
 */
public class JBlockFlower extends JBlockPlant {
	public JBlockFlower(String name, String enName) {
		super(name, enName);
	}

	public JBlockFlower(String name, String enName, CreativeTabs tab) {
		super(name, enName, tab);
	}

	public JBlockFlower(EnumMaterialTypes type, String name, String enName, CreativeTabs tab) {
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
