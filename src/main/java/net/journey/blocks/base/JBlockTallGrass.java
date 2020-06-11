package net.journey.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.slayer.api.EnumMaterialTypes;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Base class for tall plant blocks.
 * <p>
 * By default:
 * <ul>
 *     <li>Checks for ground to be placed and stay</li>
 *     <li>Drops itself ONLY when harvested with shears</li>
 *     <li>Is replaceable</li>
 *     <li>Has small model offset as tall grass has</li>
 *     <li>Has {@link Material#VINE} (right mapping: TALL_PLANTS) material unless it is provided in a constructor</li>
 * </ul>
 * <p>
 * The item model for it should be placed to "models/item/block/plant/" by default.
 */
public class JBlockTallGrass extends JBlockPlant implements IShearable {
	protected static final AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

	public JBlockTallGrass(String name, String enName) {
		super(name, enName);
	}

	public JBlockTallGrass(String name, String enName, CreativeTabs tab) {
		this(EnumMaterialTypes.TALL_PLANTS, name, enName, tab);
	}

	public JBlockTallGrass(EnumMaterialTypes type, String name, String enName, CreativeTabs tab) {
		super(type, name, enName, tab);
	}

	@Override
	public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
		return TALL_GRASS_AABB.offset(state.getOffset(source, pos));
	}

	@Override
	public boolean isReplaceable(@NotNull IBlockAccess worldIn, @NotNull BlockPos pos) {
		return true;
	}

	@Override
	public @NotNull Item getItemDropped(@NotNull IBlockState state, @NotNull Random rand, int fortune) {
		return Items.AIR;
	}

	@Override
	public void harvestBlock(World worldIn, @NotNull EntityPlayer player, @NotNull BlockPos pos, @NotNull IBlockState state, @Nullable TileEntity te, @NotNull ItemStack stack) {
		if (!worldIn.isRemote && stack.getItem() == Items.SHEARS) {
			spawnAsEntity(worldIn, pos, new ItemStack(this, 1));
		} else {
			super.harvestBlock(worldIn, player, pos, state, te, stack);
		}
	}

	/**
	 * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
	 */
	public Block.@NotNull EnumOffsetType getOffsetType() {
		return EnumOffsetType.XYZ;
	}

	@Override
	public boolean isShearable(@NotNull ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public @NotNull NonNullList<ItemStack> onSheared(@NotNull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(this, 1));
	}
}
