package net.journey.blocks.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityAdvancedLoot extends TileEntityLockableLoot implements ITickable {

	private NonNullList<ItemStack> advancedLootContents = NonNullList.withSize(27, ItemStack.EMPTY);
	private int ticksSinceSync;

	public TileEntityAdvancedLoot() {
	}

	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.advancedLootContents) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.advanced_loot";
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.advancedLootContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		if (!this.checkLootAndRead(compound)) {
			ItemStackHelper.loadAllItems(compound, this.advancedLootContents);
		}

		if (compound.hasKey("CustomName", 8)) {
			this.customName = compound.getString("CustomName");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		if (!this.checkLootAndWrite(compound)) {
			ItemStackHelper.saveAllItems(compound, this.advancedLootContents);
		}

		if (this.hasCustomName()) {
			compound.setString("CustomName", this.customName);
		}

		return compound;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
	}

	@Override
	public void update() {
		++this.ticksSinceSync;
	}

	@Override
	public boolean receiveClientEvent(int id, int type) {
		if (id == 1) {
			return true;
		} else {
			return super.receiveClientEvent(id, type);
		}
	}

	@Override
	public void invalidate() {
		super.invalidate();
		this.updateContainingBlockInfo();
	}

	//never used, but required
	@Override
	public String getGuiID() {
		return "minecraft:chest";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		this.fillWithLoot(playerIn);
		return new ContainerChest(playerInventory, this, playerIn);
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.advancedLootContents;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(pos.add(-1, 0, -1), pos.add(2, 2, 2));
	}
}
