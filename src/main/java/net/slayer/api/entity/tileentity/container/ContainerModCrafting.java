package net.slayer.api.entity.tileentity.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerModCrafting extends Container {
    private final EntityPlayer player;
    public InventoryCrafting invCraft;
    public IInventory outcome;
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public InventoryCraftResult craftResult = new InventoryCraftResult();
    private World w;

    public ContainerModCrafting(InventoryPlayer inventoryplayer, World world) {
        player = inventoryplayer.player;
        invCraft = new InventoryCrafting(this, 3, 3);
        outcome = new InventoryCraftResult();
        world = this.w;
        this.addSlotToContainer(new SlotCrafting(inventoryplayer.player, invCraft, outcome, 0, 124, 35));
        for (int i = 0; i < 3; i++) {
            for (int l = 0; l < 3; l++) {
                this.addSlotToContainer(new Slot(invCraft, l + i * 3, 30 + l * 18, 17 + i * 18));
            }
        }
        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 9; row++) {
                this.addSlotToContainer(new Slot(inventoryplayer, row + column * 9 + 9, 8 + row * 18, 84 + column * 18));
            }
        }

        for (int column = 0; column < 9; column++) {
            this.addSlotToContainer(new Slot(inventoryplayer, column, 8 + column * 18, 142));
        }
        onCraftMatrixChanged(invCraft);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public void onCraftMatrixChanged(IInventory inv) {
        this.slotChangedCraftingGrid(this.w, this.player, this.craftMatrix, this.craftResult);
    }

    @Override
    public void onContainerClosed(EntityPlayer entityplayer) {
        super.onContainerClosed(entityplayer);
        if (w.isRemote) {
            return;
        }
        for (int i = 0; i < 9; i++) {
            ItemStack item = invCraft.getStackInSlot(i);
            if (item != null) {
                entityplayer.entityDropItem(item, 0);
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i) {
        ItemStack item = null;
        Slot slot = inventorySlots.get(i);
        if (slot != null && slot.getHasStack()) {
            ItemStack item1 = slot.getStack();
            item = item1.copy();
            if (i == 0) {
                if (!mergeItemStack(item1, 10, 46, true)) {
                    return null;
                }
            } else if (i >= 10 && i < 37) {
                if (!mergeItemStack(item1, 37, 46, false)) {
                    return null;
                }
            } else if (i >= 37 && i < 46) {
                if (!mergeItemStack(item1, 10, 37, false)) {
                    return null;
                }
            } else if (!mergeItemStack(item1, 10, 46, false)) {
                return null;
            }
            if (item1.getCount() == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
            if (item1.getCount() != item.getCount()) {
                slot.onTake(entityplayer, item1);
            } else {
                return null;
            }
        }
        return null;
    }
}