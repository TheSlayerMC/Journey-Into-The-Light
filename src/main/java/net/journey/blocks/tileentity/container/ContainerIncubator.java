package net.journey.blocks.tileentity.container;

import net.journey.blocks.tileentity.TileEntityIncubator;
import net.journey.blocks.tileentity.container.slot.SlotIncubatorFuel;
import net.journey.blocks.tileentity.container.slot.SlotIncubatorOutput;
import net.journey.util.IncubatorRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerIncubator extends Container {
	private final IInventory tileIncubator;
	private int cookTime;
	private int totalCookTime;
	private int IncubatorBurnTime;
	private int currentItemBurnTime;

	public ContainerIncubator(InventoryPlayer playerInventory, IInventory IncubatorInventory) {
		this.tileIncubator = IncubatorInventory;
		this.addSlotToContainer(new Slot(IncubatorInventory, 0, 56, 17));
		this.addSlotToContainer(new SlotIncubatorFuel(IncubatorInventory, 1, 56, 53));
		this.addSlotToContainer(new SlotIncubatorOutput(playerInventory.player, IncubatorInventory, 2, 116, 35));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
		}
	}

	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileIncubator);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icontainerlistener = this.listeners.get(i);

			if (this.cookTime != this.tileIncubator.getField(2)) {
				icontainerlistener.sendWindowProperty(this, 2, this.tileIncubator.getField(2));
			}

			if (this.IncubatorBurnTime != this.tileIncubator.getField(0)) {
				icontainerlistener.sendWindowProperty(this, 0, this.tileIncubator.getField(0));
			}

			if (this.currentItemBurnTime != this.tileIncubator.getField(1)) {
				icontainerlistener.sendWindowProperty(this, 1, this.tileIncubator.getField(1));
			}

			if (this.totalCookTime != this.tileIncubator.getField(3)) {
				icontainerlistener.sendWindowProperty(this, 3, this.tileIncubator.getField(3));
			}
		}
		this.cookTime = this.tileIncubator.getField(2);
		this.IncubatorBurnTime = this.tileIncubator.getField(0);
		this.currentItemBurnTime = this.tileIncubator.getField(1);
		this.totalCookTime = this.tileIncubator.getField(3);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.tileIncubator.setField(id, data);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileIncubator.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index == 2) {
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (index != 1 && index != 0) {
				if (!IncubatorRecipes.instance().getSmeltingResult(itemstack1).isEmpty()) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				}
				else if (TileEntityIncubator.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return ItemStack.EMPTY;
					}
				}
				else if (index >= 3 && index < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return ItemStack.EMPTY;
					}
				}
				else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
			slot.onTake(playerIn, itemstack1);
		}
		return itemstack;
	}
}