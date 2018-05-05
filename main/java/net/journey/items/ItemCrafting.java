package net.journey.items;

import java.util.List;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.blocks.machines.BlockStoneCraftingTable;
import net.journey.blocks.tileentity.container.ContainerJourneyCrafting;
import net.journey.client.server.DarkEnergyBar;
import net.journey.enums.EnumSounds;
import net.journey.items.container.ContainerJourneyItemCrafting;
import net.journey.util.LangRegistry;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

public class ItemCrafting extends Item {

private int type;

	public ItemCrafting(String name, String finalName, int type){
		this(name, finalName, JourneyTabs.items);
		this.maxStackSize = 1;
		this.type = type;
	}

	public ItemCrafting(String name, String finalName, CreativeTabs tab){
		LangRegistry.addItem(name, finalName);
		setUnlocalizedName(name);
		setCreativeTab(tab);
		JourneyItems.itemNames.add(name);
		GameRegistry.registerItem(this, name);
	}

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		ItemCrafting item = (ItemCrafting)player.getHeldItem().getItem();

		if (!world.isRemote) {
			EnumSounds.playSound(EnumSounds.CHEST_OPEN_0, world, player);
			if (!player.isSneaking()) {
				if(item.type == 0) {
					player.displayGui(new BlockStoneCraftingTable.InterfaceStoneCraftingTable(world, player.getPosition()));
				}
			}
		}
		return stack;
	}
}