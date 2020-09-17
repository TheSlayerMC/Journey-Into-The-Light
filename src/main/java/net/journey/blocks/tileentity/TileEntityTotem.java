package net.journey.blocks.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;

public class TileEntityTotem extends TileEntityLockable implements ITickable {

	public TileEntityTotem() {
	}

	@Override
	public void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
	}

	public void setUnlocked() {
		setLockCode(null);
		markDirty();
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int i1) {
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int i) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) {
	}

	@Override
	public int getInventoryStackLimit() {
		return 0;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
		return false;
	}

	@Override
	public void openInventory(EntityPlayer entityPlayer) {
	}

	@Override
	public void closeInventory(EntityPlayer entityPlayer) {
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemStack) {
		return false;
	}

	@Override
	public int getField(int i) {
		return 0;
	}

	@Override
	public void setField(int i, int i1) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
	}

	@Override
	public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer entityPlayer) {
		return null;
	}

	@Override
	public String getGuiID() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public void update() {
	}
}
