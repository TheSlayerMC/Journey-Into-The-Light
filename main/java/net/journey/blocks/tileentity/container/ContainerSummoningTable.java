package net.journey.blocks.tileentity.container;

import net.journey.blocks.tileentity.TileEntitySummoningTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerSummoningTable extends Container {

	public IInventory tableInventory;
	private World world;

	public ContainerSummoningTable(InventoryPlayer inventory, TileEntitySummoningTable entity, World w) {
		world = w;
		tableInventory = entity;
		this.addSlotToContainer(new Slot(entity, 0, 44, 17));
		this.addSlotToContainer(new Slot(entity, 1, 44, 35));
		this.addSlotToContainer(new Slot(entity, 2, 44, 53));
		this.addSlotToContainer(new Slot(entity, 3, 80, 35));
		this.addSlotToContainer(new Slot(entity, 4, 117, 17));
		this.addSlotToContainer(new Slot(entity, 5, 117, 35));
		this.addSlotToContainer(new Slot(entity, 6, 117, 53));

		for(int i = 0; i < 3; ++i) for(int j = 0; j < 9; ++j) this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
		for(int i = 0; i < 9; ++i) this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
		return itemstack;
	}
}