package net.journey.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

public class ItemPresent extends ItemMod {

	public ItemPresent(String name, String f) {
		super(name, f, JourneyTabs.items);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		Random r = new Random();
		items.add(new ItemStack(JourneyItems.frostFlake, 6));
		items.add(new ItemStack(JourneyItems.blueGem, 8));
		items.add(new ItemStack(JourneyItems.frostGem, 2));
		items.add(new ItemStack(JourneyItems.crystalFlake, 6));
		items.add(new ItemStack(Items.snowball, 12));
		items.add(new ItemStack(Blocks.ice, 4));
		items.add(new ItemStack(Items.diamond));
		if(!world.isRemote) {
			EnumSounds.playSound(EnumSounds.WRAPPER, world, player);
			int index = r.nextInt(items.size());
			String name = StatCollector.translateToLocal(items.get(index).getItem().getUnlocalizedName() + ".name");
			SlayerAPI.addChatMessage(player, "You recieved " + name);
			EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, items.get(index));
			world.spawnEntityInWorld(item);
		}
		player.inventory.consumeInventoryItem(this);
		return stack;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list) {
		list.add("Right click to open");
	}
}