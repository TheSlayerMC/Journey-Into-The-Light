package net.journey.blocks.tileentity;

import java.util.Random;

import net.journey.JourneyItems;
import net.journey.client.render.particles.EntityModFireFX;
import net.journey.client.render.particles.EntityModSnowFX;
import net.journey.client.render.particles.EntitySummoningFX;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityTrophyTable extends TileEntity implements IInventory {

	private ItemStack[] inventory;

	public TileEntityTrophyTable() {
		this.inventory = new ItemStack[1];
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList nbttaglist = new NBTTagList();
		for(int i = 0; i < inventory.length; i++) {
			if(this.inventory[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		nbt.setTag("Items", nbttaglist);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		for(int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");
			if(b0 >= 0 && b0 < this.inventory.length)
				this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		} 
	}

	public boolean areItemStacksInSlots(ItemStack s, ItemStack s1, ItemStack s2, ItemStack s3, ItemStack s4, ItemStack s5, ItemStack s6) {
		return inventory[0] == s && inventory[1] == s1 && inventory[2] == s2 && inventory[3] == s3 && inventory[4] == s4 && inventory[5] == s5 && inventory[6] == s6;
	}

	public boolean areItemsInSlots(Item s, Item s1, Item s2, Item s3, Item s4, Item s5, Item s6) {
		return inventory[0].getItem() == s && inventory[1].getItem() == s1 && inventory[2].getItem() == s2 && inventory[3].getItem() == s3 && inventory[4].getItem() == s4 && inventory[5].getItem() == s5 && inventory[6].getItem() == s6;
	}

	public void setAllSlotsToNull() {
		clear();
	}

	public void setInventorySlots(ItemStack s, ItemStack s1, ItemStack s2, ItemStack s3, ItemStack s4, ItemStack s5, ItemStack s6) {
		inventory[0] = s;
		inventory[1] = s1;
		inventory[2] = s2;
		inventory[3] = s3;
		inventory[4] = s4;
		inventory[5] = s5;
		inventory[6] = s6;
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inventory[i];
	}

	@Override
	public String getName() {
		return "Trophy Table";
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
		if(inventory[i] != null) {
			if(inventory[i].stackSize <= j) {
				ItemStack itemstack = inventory[i];
				inventory[i] = null;
				return itemstack;
			} else {
				inventory[i].stackSize -= j;
				return new ItemStack(inventory[i].getItem(), j, inventory[i].getMetadata());
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if(this.inventory[index] != null)  {
			ItemStack itemstack = inventory[index];
			this.inventory[index] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		boolean flag = stack != null && stack.isItemEqual(this.inventory[index]) && ItemStack.areItemStackTagsEqual(stack, this.inventory[index]);
		this.inventory[index] = stack;
		if(stack != null && stack.stackSize > this.getInventoryStackLimit())
			stack.stackSize = this.getInventoryStackLimit();
		if(index == 0 && !flag) this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
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
		return stack.getItem() != null;
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
		for(int i = 0; i < getSizeInventory(); i++) inventory[i] = null;
	}
}