package net.journey.items;

import net.journey.client.render.gui.base.dialogue.GuiDialogue;
import net.journey.dialogue.Dialogue;
import net.journey.entity.mob.corba.EntityCorbanianMollusk;
import net.journey.init.DialogueRegistry;
import net.journey.items.base.JItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemTestBug extends JItem {
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		if (world.isRemote) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiDialogue(new Dialogue(new EntityCorbanianMollusk(world), DialogueRegistry.TEST)));
		}

		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
	}
}
