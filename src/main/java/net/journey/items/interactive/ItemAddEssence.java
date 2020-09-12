package net.journey.items.interactive;

import net.journey.api.capability.EssenceStorage;
import net.journey.api.capability.JourneyPlayer;
import net.journey.api.item.IUsesEssence;
import net.journey.common.capability.JCapabilityManager;
import net.journey.items.base.JItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemAddEssence extends JItem implements IUsesEssence {

	private final int amount;

	public ItemAddEssence(int amount) {
		this.amount = amount;
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		worldIn.playEvent(2002, player.getPosition(), PotionUtils.getPotionColor(PotionTypes.STRONG_LEAPING));

		JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(player);
		EssenceStorage mana = journeyPlayer.getEssenceStorage();

		if (!worldIn.isRemote) {
			mana.addEssence(amount);
			journeyPlayer.sendUpdates();
			stack.shrink(1);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void addInformation(ItemStack i, List l) {
		if (amount == 10) l.add(TextFormatting.GREEN + "Refills Essence Bar");
		else if (amount < 10) l.add("Adds " + amount + TextFormatting.GREEN + " Essence");
	}
}