package net.journey.items;

import net.journey.common.JManagers;
import net.journey.entity.mob.corba.npc.EntityTordo;
import net.journey.init.JDialogues;
import net.journey.items.base.JItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemTestBug extends JItem {
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		if (!world.isRemote) {
			JManagers.DIALOGUE_MANAGER.startDialogue((EntityPlayerMP) player, EntityTordo.class, JDialogues.TEST.getRootNode());
		}

		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
	}
}
