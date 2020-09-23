package net.journey.items;

import net.journey.common.JManagers;
import net.journey.dialogue.DialogueManager;
import net.journey.dialogue.DialogueNode;
import net.journey.entity.mob.overworld.EntityBigHongo;
import net.journey.init.JDialogues;
import net.journey.items.base.JItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import ru.timeconqueror.timecore.util.reflection.ReflectionHelper;
import ru.timeconqueror.timecore.util.reflection.UnlockedMethod;

public class ItemTestBug extends JItem {
	private final UnlockedMethod<Void> mStartDialogue = ReflectionHelper.findMethodUnsuppressed(DialogueManager.class, "startDialogue", EntityPlayerMP.class, Class.class, DialogueNode.class);

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		if (!world.isRemote) {
			mStartDialogue.invoke(JManagers.DIALOGUE_MANAGER, player, EntityBigHongo.class, JDialogues.TEST.getRootNode());
//			new WorldGenCorbaTotems().generate(world, itemRand, player.getPosition());
			//new WorldGenTowerDungeonCyl().generate(world, itemRand, player.getPosition());
		}

		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
	}
}
