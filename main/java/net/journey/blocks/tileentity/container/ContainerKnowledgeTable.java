package net.journey.blocks.tileentity.container;

import net.journey.blocks.tileentity.TileEntityKnowledgeTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerKnowledgeTable extends Container {

	public IInventory tableInventory;
	private World world;

	public ContainerKnowledgeTable(InventoryPlayer inventory, TileEntityKnowledgeTable entity, World w) {
		world = w;
		tableInventory = entity;
		this.addSlotToContainer(new Slot(entity, 0, 80, 35));
		for(int i = 0; i < 3; ++i) for(int j = 0; j < 9; ++j) this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
		for(int i = 0; i < 9; ++i) this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		if(!world.isRemote) {
			ItemStack itemstack = this.tableInventory.removeStackFromSlot(0);
			if (itemstack != null) {
				playerIn.dropItem(itemstack, false);
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
}