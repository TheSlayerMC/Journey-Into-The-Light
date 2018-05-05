package net.journey.blocks.tileentity;

import net.journey.JourneyItems;
import net.journey.items.ItemKnowledge;
import net.journey.util.Helper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityKnowledgeTable extends TileEntity implements ITickable, IInventory {

	private ItemStack inventory;

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}

	@Override
	public void update() {
		if(getStackInSlot(0) != null) {
			Item i = inventory.getItem();
			if(i == JourneyItems.overworldKnowledge) {
				Helper.print("TADA");
				clear();
			}
		}
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return i == 0 ? inventory : null;
	}

	@Override
	public String getName() {
		return "Knowledge Table";
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}

	@Override
	public IChatComponent getDisplayName() {
		return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if(i == 0 && inventory != null) {
			if(inventory.stackSize <= j) {
				ItemStack itemstack = inventory;
				inventory = null;
				return itemstack;
			} else {
				inventory.stackSize -= j;
				return new ItemStack(inventory.getItem(), j, inventory.getMetadata());
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if(index == 0 && this.inventory != null)  {
			ItemStack itemstack = this.inventory;
			this.inventory = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		if(i == 0) this.inventory = stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) { }

	@Override
	public void closeInventory(EntityPlayer player) { }

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return stack.getItem() != null && stack.getItem() instanceof ItemKnowledge;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) { }

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		inventory = null;
	}
}