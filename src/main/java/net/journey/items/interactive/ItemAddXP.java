package net.journey.items.interactive;

import net.journey.items.base.JItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

public class ItemAddXP extends JItem {

	private final int amount;

	public ItemAddXP(int amount) {
		this.amount = amount;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		worldIn.playEvent(2002, player.getPosition(), PotionUtils.getPotionColor(PotionTypes.LEAPING));
		if (!worldIn.isRemote) {
			player.addExperienceLevel(amount);
			stack.shrink(1);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void addInformation(ItemStack i, List l) {
		if (amount < 1) l.add("Adds " + amount + " experience level");
		else if (amount > 1) l.add("Adds " + amount + " experience levels");
	}
}