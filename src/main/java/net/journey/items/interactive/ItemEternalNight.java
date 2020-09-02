package net.journey.items.interactive;

import net.journey.api.capability.EssenceStorage;
import net.journey.api.item.IUsesEssence;
import net.journey.common.capability.JCapabilityManager;
import net.journey.init.JourneySounds;
import net.journey.init.JourneyTabs;
import net.journey.items.base.JItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;

import java.util.List;

public class ItemEternalNight extends JItem implements IUsesEssence {

	public ItemEternalNight(String name, String finalName) {
		super(name, finalName, JourneyTabs.UTIL);
		setMaxDamage(3);
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		EssenceStorage mana = JCapabilityManager.asJourneyPlayer(player).getEssenceStorage();
		if (mana.useEssence(10)) {
			worldIn.setWorldTime(18000L);
			stack.damageItem(1, player);
			worldIn.playSound(player, player.getPosition(), JourneySounds.ETERNAL_NIGHT, SoundCategory.PLAYERS, 1.0F, 1.0F);
			SlayerAPI.addChatMessage(player, player.getDisplayNameString() + " isn't scared of the dark...");
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