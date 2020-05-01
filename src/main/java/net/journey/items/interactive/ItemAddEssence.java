package net.journey.items.interactive;

import java.util.List;

import net.journey.client.server.EssenceProvider;
import net.journey.client.server.IEssence;
import net.journey.init.JourneyTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.slayer.api.item.ItemMod;

public class ItemAddEssence extends ItemMod {

	private int amount;

	public ItemAddEssence(String name, String finalN, int uses, int amount) {
		super(name, finalN, JourneyTabs.UTIL);
		setMaxDamage(uses);
		this.amount = amount;
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		IEssence mana = player.getCapability(EssenceProvider.ESSENCE_CAP, null);
		if(!worldIn.isRemote) {
			mana.addEssence(amount);
			stack.damageItem(1, player);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void addInformation(ItemStack i, List l) {
		l.add("Adds " + amount + " Essence");
	}
}