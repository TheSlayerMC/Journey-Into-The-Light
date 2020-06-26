package net.journey.items.interactive;

import net.journey.api.item.IUsesEssence;
import net.journey.client.server.EssenceProvider;
import net.journey.client.server.IEssence;
import net.journey.init.JourneyTabs;
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

public class ItemAddEssence extends JItem implements IUsesEssence {

	private final int amount;

	public ItemAddEssence(String name, String finalN, int amount) {
		super(name, finalN, JourneyTabs.UTIL);
		this.amount = amount;
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
        worldIn.playEvent(2002, player.getPosition(), PotionUtils.getPotionColor(PotionTypes.STRONG_LEAPING));
		IEssence mana = player.getCapability(EssenceProvider.ESSENCE_CAP, null);
		if(!worldIn.isRemote) {
			mana.addEssence(amount);
			stack.shrink(1);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void addInformation(ItemStack i, List l) {
		l.add("Adds " + amount + " Essence");
	}
}