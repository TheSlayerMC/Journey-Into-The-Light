package net.journey.items.interactive;

import net.journey.client.render.gui.scroll.GuiLoreScroll;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.slayer.api.item.ItemMod;

public class ItemLoreBook extends ItemMod {

	public ItemLoreBook(String name, String finalName) {
		super(name, finalName);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (worldIn.isRemote) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiLoreScroll());
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
}
