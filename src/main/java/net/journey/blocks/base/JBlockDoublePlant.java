package net.journey.blocks.base;

import net.journey.api.block.IHasCustomItemPath;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for all double plant blocks.<br>
 * By default:
 * <ul>
 *     <li>Checks for ground to be placed and stay</li>
 *     <li>Drops itself</li>
 *     <li>Is NOT replaceable</li>
 *     <li>Has {@link Material#PLANTS} material unless it is  provided in a constructor</li>
 *     <li>Has no hardness (will be instantly broken)</li>
 * </ul>
 * <p>
 * You can also set:
 * <ul>
 *     <li>{@link #groundPredicate} - predicate that checks if plant can be placed and sustain on provided block</li>
 *     <li>{@link #plantDirection} - the side of ground block where plant can be placed and stay.</li>
 *     <li>{@link #topBox}, {@link #bottomBox} - bounding boxes of the plant for top and bottom state.</li>
 * </ul>
 * The item model for it should be placed to "models/item/block/plant" by default.
 */
@SuppressWarnings("JavadocReference")
public class JBlockDoublePlant extends JBlockPlant implements IHasCustomItemPath {
	public static final PropertyEnum<EnumHalf> HALF = PropertyEnum.create("half", EnumHalf.class);
	private AxisAlignedBB bottomBox = TALL_PLANT_BB;
	private AxisAlignedBB topBox = BUSH_AABB;

	public JBlockDoublePlant(String name, String enName) {
		super(name, enName);
	}

	public JBlockDoublePlant(String name, String enName, CreativeTabs tab) {
		super(name, enName, tab);
	}

	public JBlockDoublePlant(EnumMaterialTypes type, String name, String enName, CreativeTabs tab) {
		super(type, name, enName, tab);
	}

	/**
	 * Places both bottom and top blocks.
	 *
	 * @param world     world where to set
	 * @param bottomPos position where bottom block will be placed.
	 * @param flags     the same as in {@link World#setBlockState(BlockPos, IBlockState, int)}
	 */
	public void placeAt(World world, BlockPos bottomPos, int flags) {
		world.setBlockState(bottomPos, this.getDefaultState().withProperty(HALF, EnumHalf.BOTTOM), flags);
		world.setBlockState(bottomPos.up(), this.getDefaultState().withProperty(HALF, EnumHalf.TOP), flags);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return state.getValue(HALF) == EnumHalf.TOP ? topBox : bottomBox;
	}

	public JBlockDoublePlant setFullBlockBoundingBox() {
		return setBoundingBox(FULL_BLOCK_AABB);
	}

	@Override
	public JBlockDoublePlant setBoundingBox(AxisAlignedBB bb) {
		return setBoundingBox(bb, bb);
	}

	public JBlockDoublePlant setBoundingBox(AxisAlignedBB bottom, AxisAlignedBB top) {
		this.bottomBox = bottom;
		this.topBox = top;

		return this;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, @NotNull BlockPos pos) {
		return worldIn.isAirBlock(pos.up()) && super.canPlaceBlockAt(worldIn, pos);
	}

	@Override
	public boolean canBlockStay(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state) {
		boolean canStay = true;

		boolean checkAsBottomState = state.getBlock() != this || state.getValue(HALF) != EnumHalf.TOP;

		if (state.getBlock() == this) {
			if (state.getValue(HALF) == EnumHalf.TOP) {
				IBlockState bottomState = worldIn.getBlockState(pos.down());
				canStay = bottomState.getBlock() == this && bottomState.getValue(HALF) == EnumHalf.BOTTOM;
			} else {
				IBlockState topState = worldIn.getBlockState(pos.up());
				canStay = topState.getBlock() == this && topState.getValue(HALF) == EnumHalf.TOP;
			}
		}

		return canStay && (checkAsBottomState ? super.canBlockStay(worldIn, pos, state) : super.canBlockStay(worldIn, pos.down(), state));
	}

	@Override
	protected void checkAndDropBlock(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState state) {
		if (!canBlockStay(world, pos, state)) {
			boolean isTop = state.getValue(HALF) == EnumHalf.TOP;

			BlockPos top = isTop ? pos : pos.up();
			BlockPos bottom = isTop ? pos.down() : pos;

			Block topBlock = isTop ? this : world.getBlockState(bottom).getBlock();
			Block bottomBlock = isTop ? world.getBlockState(bottom).getBlock() : this;

			if (!isTop) dropBlockAsItem(world, pos, state, 0);

			if (topBlock == this) world.setBlockState(top, Blocks.AIR.getDefaultState(), 2);

			if (bottomBlock == this) world.setBlockState(bottom, Blocks.AIR.getDefaultState(), 3);
		}
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, @NotNull EntityLivingBase placer, @NotNull ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

		worldIn.setBlockState(pos.up(), state.withProperty(HALF, EnumHalf.TOP));// we set block also on client to prevent top block placing being slow for eyes
	}

	@Override
	public void onBlockHarvested(World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull EntityPlayer player) {
		if (!worldIn.isRemote) {
			if (state.getValue(HALF) == EnumHalf.TOP) {
				if (worldIn.getBlockState(pos.down()).getBlock() == this) {
					if (player.capabilities.isCreativeMode) {
						worldIn.setBlockToAir(pos.down());
					} else {
						worldIn.destroyBlock(pos.down(), true);
					}
				}
			} else if (worldIn.getBlockState(pos.up()).getBlock() == this) {
				worldIn.setBlockState(pos.up(), Blocks.AIR.getDefaultState(), 2);
			}
		}

		super.onBlockHarvested(worldIn, pos, state, player);
	}

	@Override
	public @NotNull IBlockState getStateFromMeta(int meta) {
		return meta == 0 ? getDefaultState().withProperty(HALF, EnumHalf.BOTTOM) : getDefaultState().withProperty(HALF, EnumHalf.TOP);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(HALF) == EnumHalf.BOTTOM ? 0 : 1;
	}

	@Override
	protected @NotNull BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, HALF);
	}
}
