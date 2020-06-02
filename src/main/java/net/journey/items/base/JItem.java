package net.journey.items.base;

import net.journey.client.ItemDescription;
import net.journey.init.JourneyTabs;
import net.journey.util.StuffConstructor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class JItem extends Item {
	public JItem(String name, String enName) {
		this(name, enName, JourneyTabs.ITEMS);
	}

	public JItem(String name, String enName, CreativeTabs tab) {
		StuffConstructor.regAndSetupItem(this, name, enName, tab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, World world, List<String> list, ITooltipFlag flagIn) {
		ItemDescription.addInformation(item, list);
		addInformation(item, list);
	}

	//TODO get rid of it
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack i, List l) {
	}
}