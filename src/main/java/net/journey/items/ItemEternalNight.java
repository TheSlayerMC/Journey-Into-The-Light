package net.journey.items;

import java.util.List;

import net.journey.client.server.EssenceProvider;
import net.journey.client.server.IEssence;
import net.journey.init.JourneyTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.item.ItemMod;

public class ItemEternalNight extends ItemMod {

	public ItemEternalNight(String name, String finalName) {
		super(name, finalName, JourneyTabs.UTIL);
		setMaxDamage(3);
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		IEssence mana = player.getCapability(EssenceProvider.ESSENCE_CAP, null);

		if(mana.useEssence(10)) {
			worldIn.setWorldTime(18000L);
			stack.damageItem(1, player);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
	}
	
	@Override
	public void addInformation(ItemStack i, List l) {
		l.add("On use: consumes 10 Essence");
		l.add("Turns time to night");
	}
}