package net.journey.blocks.containers;

import net.journey.blocks.tileentity.TileEntityAdvancedLoot;
import net.journey.init.JourneyTabs;
import net.journey.init.items.JourneyItems;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.entity.tileentity.container.BlockModContainer;

import java.util.ArrayList;

/**
 * basically a chest class that doesn't have a GUI. Sole purpose is to drop loot table contents
 * unlike JBlockRandomLoot, the block can be picked back up and can't be used to exploit loot tables
 * the loot table also isn't static or based on the block itself, so any loot table can be applied to any BlockAdvancedLoot block
 */

public class BlockAdvancedLoot extends BlockModContainer {

	public BlockAdvancedLoot(EnumMaterialTypes enumMaterialTypes, String name, String f, float hardness) {
		super(enumMaterialTypes, name, f, hardness, JourneyTabs.INTERACTIVE_BLOCKS);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityAdvancedLoot chest = (TileEntityAdvancedLoot) worldIn.getTileEntity(pos);
		if (chest != null) {
			if (!chest.isLocked()) {
				InventoryHelper.dropInventoryItems(worldIn, pos, chest);
			}
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<>();
		drops.add(new ItemStack(JourneyItems.pottery_shard, rand.nextInt(4) + 4));
		return drops;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			/**
			 * why does this act so buggy?
			 */
			TileEntityAdvancedLoot chest = (TileEntityAdvancedLoot) worldIn.getTileEntity(pos);
			InvWrapper wrapper = new InvWrapper(chest);
			if (chest != null) {
				for (int i = 0; i < wrapper.getSlots(); i++) {
					ItemStack itemStack = wrapper.extractItem(i, 1, false);

					EntityItem entityItem = new EntityItem(worldIn);
					entityItem.setItem(itemStack);
					entityItem.setPosition(pos.getX(), pos.getY() + 1.0F, pos.getZ());

					if (!itemStack.isEmpty()) {
						worldIn.spawnEntity(entityItem);
					}
				}
			}
		}
		return true;
	}

	public ILockableContainer getLockableContainer(World worldIn, BlockPos pos) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (!(tileentity instanceof TileEntityAdvancedLoot)) {
			return null;
		} else {
			return (ILockableContainer) tileentity;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityAdvancedLoot();
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return Container.calcRedstoneFromInventory(this.getLockableContainer(worldIn, pos));
	}
}
