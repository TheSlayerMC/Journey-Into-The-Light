package net.journey.items.interactive;

import java.util.List;

import net.journey.init.JourneySounds;
import net.journey.init.JourneyTabs;
import net.journey.util.PotionEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.item.ItemMod;

public class ItemRestoreHealth extends ItemMod {

	private int amount;
	private boolean restoreFullHealth;

	public ItemRestoreHealth(String name, String finalN, boolean restoreFullHealth) {
		super(name, finalN, JourneyTabs.UTIL);
		this.restoreFullHealth = restoreFullHealth;
	}
	
	public ItemRestoreHealth(String name, String finalN, int amount, boolean restoreFullHealth) {
		super(name, finalN, JourneyTabs.UTIL);
		this.amount = amount;
		this.restoreFullHealth = restoreFullHealth;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
        worldIn.playEvent(2002, player.getPosition(), PotionUtils.getPotionColor(PotionTypes.HEALING));
        worldIn.playSound(player.posX, player.posY, player.posZ, JourneySounds.SUMMON_TABLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
		if(!worldIn.isRemote) { 
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(PotionEffects.regeneration), 100, 1));
			stack.shrink(1);
			if(restoreFullHealth == true) {
				player.heal(player.getMaxHealth());
			}
			if(restoreFullHealth == true) {
				player.heal(amount);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void addInformation(ItemStack i, List l) {
		l.add("Restores " + amount + " health");
	}
}