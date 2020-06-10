package net.journey.items;

import net.journey.init.JourneyTabs;
import net.journey.init.ScrollRegistry;
import net.journey.init.items.JourneyItems;
import net.journey.items.base.JItem;
import net.journey.items.interactive.ItemLoreScroll;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemTestBug extends JItem {
	public ItemTestBug(String name, String enName) {
		super(name, enName, JourneyTabs.UTIL);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (!worldIn.isRemote) {
			ItemStack scrollStack = new ItemStack(JourneyItems.loreScroll);
			ItemLoreScroll.bindScrollEntry(scrollStack, ScrollRegistry.senterianGospel);

			worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY + 0.5F, playerIn.posZ, scrollStack));
		}

		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
}
