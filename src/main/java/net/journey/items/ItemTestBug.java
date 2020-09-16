package net.journey.items;

import net.journey.dialogue.DialogueManager;
import net.journey.dialogue.DialogueNode;
import net.journey.items.base.JItem;
import net.journey.util.WorldGenHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import ru.timeconqueror.timecore.util.reflection.ReflectionHelper;
import ru.timeconqueror.timecore.util.reflection.UnlockedMethod;

public class ItemTestBug extends JItem {
	private final UnlockedMethod<Void> mStartDialogue = ReflectionHelper.findMethodUnsuppressed(DialogueManager.class, "startDialogue", EntityPlayerMP.class, Class.class, DialogueNode.class);

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		if (!world.isRemote) {
			//mStartDialogue.invoke(JManagers.DIALOGUE_MANAGER, player, EntityTordo.class, JDialogues.TEST.getRootNode());
//			new WorldGenTowerDungeonCyl().generate(world, itemRand, player.getPosition());

			WorldGenHelper.genHollowCylinder(player.getPosition(), 5, 3, EnumFacing.NORTH, mPos -> WorldGenHelper.setStateFast(world, mPos, Blocks.BEDROCK.getDefaultState()));
		}

		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));
	}
}
