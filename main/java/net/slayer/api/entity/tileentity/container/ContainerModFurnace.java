package net.slayer.api.entity.tileentity.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.entity.tileentity.TileEntityModFurnace;

public class ContainerModFurnace extends Container {

	private TileEntityModFurnace tileFurnace;
	private int lastCookTime, lastBurnTime, lastItemBurnTime;
	private boolean hasFuel;

	public ContainerModFurnace(InventoryPlayer player, TileEntityModFurnace furnace, boolean hasFuel) {
		this.tileFurnace = furnace;
		this.addSlotToContainer(new Slot(furnace, 0, 56, 17));
        this.addSlotToContainer(new SlotFurnaceFuel(furnace, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnaceOutput(player.player, furnace, 2, 116, 35));
		this.hasFuel = hasFuel;
		int i;
		for(i = 0; i < 3; ++i) {
			for(int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for(i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
		}
	}

	@Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileFurnace);
    }

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.listeners.size(); ++i) {
            IContainerListener icontainerlistener = this.listeners.get(i);
			if(this.lastCookTime != this.tileFurnace.cookTime) 
				icontainerlistener.sendWindowProperty(this, 0, this.tileFurnace.cookTime);
			if(this.lastBurnTime != this.tileFurnace.furnaceBurnTime)
				icontainerlistener.sendWindowProperty(this, 1, this.tileFurnace.furnaceBurnTime);
			if(this.lastItemBurnTime != this.tileFurnace.currentItemBurnTime)
				icontainerlistener.sendWindowProperty(this, 2, this.tileFurnace.currentItemBurnTime);
		}
		this.lastCookTime = this.tileFurnace.cookTime;
		this.lastBurnTime = this.tileFurnace.furnaceBurnTime;
		this.lastItemBurnTime = this.tileFurnace.currentItemBurnTime;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if(par1 == 0) this.tileFurnace.cookTime = par2;
		if(par1 == 1) this.tileFurnace.furnaceBurnTime = par2;
		if(par1 == 2) this.tileFurnace.currentItemBurnTime = par2;
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 0) {
                if (!this.mergeItemStack(itemstack1, 1, 37, true)) {
                    return null;
                }
            } else {
                if (this.inventorySlots.get(0).getHasStack() || !this.inventorySlots.get(0).isItemValid(itemstack1)) {
                    return null;
                }

                if (itemstack1.hasTagCompound() && itemstack1.getCount() == 1) {
                    this.inventorySlots.get(0).putStack(itemstack1.copy());
                    itemstack1.setCount(0);
                }
                else if (itemstack1.getCount() >= 1) {
                    this.inventorySlots.get(0).putStack(new ItemStack(itemstack1.getItem(), 1, itemstack1.getItemDamage()));
                    itemstack1.shrink(1);;
                }
            }

            if (itemstack1.getCount() == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return null;
            }

            slot.onTake(par1EntityPlayer, itemstack1);
        }
        return itemstack;
    }
}