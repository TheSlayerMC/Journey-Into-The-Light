package net.journey.items.base;

import net.journey.client.ItemDescription;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class JItem extends Item {
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