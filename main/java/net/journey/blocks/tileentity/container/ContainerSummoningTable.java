package net.journey.blocks.tileentity.container;

import net.journey.blocks.tileentity.TileEntitySummoningTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerSummoningTable extends Container {

	private final TileEntitySummoningTable tileentity;
	public IInventory tableInventory;
	private World world;

	public ContainerSummoningTable(InventoryPlayer inventory, TileEntitySummoningTable entity, World w) {
		this.tileentity = entity;
		this.world = w;
		IItemHandler h = entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		this.addSlotToContainer(new SlotItemHandler(h, 0, 44, 17));
		this.addSlotToContainer(new SlotItemHandler(h, 1, 44, 35));
		this.addSlotToContainer(new SlotItemHandler(h, 2, 44, 53));
		this.addSlotToContainer(new SlotItemHandler(h, 3, 80, 35));
		this.addSlotToContainer(new SlotItemHandler(h, 4, 117, 17));
		this.addSlotToContainer(new SlotItemHandler(h, 5, 117, 35));
		this.addSlotToContainer(new SlotItemHandler(h, 6, 117, 53));

		for(int i = 0; i < 3; ++i) for(int j = 0; j < 9; ++j) this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
		for(int i = 0; i < 9; ++i) this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
}